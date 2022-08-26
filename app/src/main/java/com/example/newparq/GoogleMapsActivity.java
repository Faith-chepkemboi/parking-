package com.example.newparq;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GoogleMapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener, NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        }

    private Bundle savedInstanceState;



    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        Toast.makeText(this, "Map is ready",
                Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;



        if (LocationPermissionsGranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)

                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            init();

            for (int i = 0; i < locationArrayList.size(); i++) {
                mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i))
                        .snippet("Book a slot")
                        .icon(BitmapFromVector(getApplicationContext(),  R.drawable.ic_baseline_local_parking_24))
                        .title("Parking slot"));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));


            }



//            }


            //chamnging to bookActivity onclickk
            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    Intent intent = new Intent(GoogleMapsActivity.this, BookActivity.class);
                    startActivity(intent);
                }
            });




        }


    }

    private BitmapDescriptor BitmapFromVector(Context applicationContext, int ic_baseline_local_parking_24) {

        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(applicationContext, ic_baseline_local_parking_24);

        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);




    }

    private static final float DEFAULT_ZOOM = 10f;
    private GeoDataClient mGeoDataClient;
    GoogleApiClient  googleApiClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(

            new LatLng(-40, -168), new LatLng(71, 136));



    GoogleMap mMap;
    GoogleMap googleMap;
    SupportMapFragment mapFragment;

    //addinng many markers
    LatLng Jamaa = new LatLng( 0.0079 ,36.3671);
    LatLng Mugo = new LatLng(  0.0341496, 36.3626534 );
    LatLng KCB = new LatLng(  0.035165, 36.364293 );
    LatLng Equity = new LatLng( 0.0386, 36.3643 );


    private ArrayList<LatLng> locationArrayList;


    private static final String TAG = "GoogleMapsActivity";

    private static final String FINE_LOCATION = ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;

    private Boolean LocationPermissionsGranted = false;

    private FusedLocationProviderClient fusedLocationProviderClient;

    //widgets
    private GoogleApiClient mGoogleApiClent;
    private AutoCompleteTextView searchText;
    private ImageView gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);

        searchText = (AutoCompleteTextView) findViewById(R.id.search_location);
        gps =(ImageView) findViewById(R.id.ic_gps);

        //navigation drawers
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        //hide or show menu(login /logout)
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_login).setVisible(false);
//        menu.findItem(R.id.nav_profile).setVisible(false);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //make menu clickable
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);




        getLocationPermission();
        locationArrayList = new ArrayList<>();
        locationArrayList.add(Jamaa);
        locationArrayList.add(KCB);
        locationArrayList.add(Mugo);
        locationArrayList.add(Equity);






    }

    //to close drawerlayout on back press

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {


            super.onBackPressed();
        }
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }


    private  void init(){
        Log.d(TAG,"init: initializing");

        mGoogleApiClent = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this,this)
                .build();
        PlaceAutocompleteAdapter mplaceAutocompleteAdapter = new PlaceAutocompleteAdapter(this,mGeoDataClient,LAT_LNG_BOUNDS,null,mGoogleApiClent);


        searchText.setAdapter(mplaceAutocompleteAdapter);


        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || keyEvent.getAction() == keyEvent.ACTION_DOWN
                    || keyEvent.getAction() == keyEvent.KEYCODE_ENTER){

                    geolocate();


                    }

                return false;
            }
        });
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked gps icon");
                getDeviceLocation();

            }
        });
        hideSoftKeyboard();
    }


       //creating  search in google maps
    private void geolocate() {
        Log.d(TAG, "geolocate: geolocating");

        String searchString = searchText.getText().toString();

        Geocoder geocoder = new Geocoder(GoogleMapsActivity.this);
        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(searchString, 1);

        }catch (IOException e){
            Log.e(TAG, "geolocate: IOException:" + e.getMessage());
        }
        if (list.size() > 0){
            Address address = list.get(0);

            Log.d(TAG,"geolocate: found a location:" + address.toString());

            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM,
                    address.getAddressLine(0));



            
        }

    }

      //getting current location on google maps
    private void getDeviceLocation(){
            Log.d(TAG, "getDeviceLocation: getting the current location");
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

            try {
                if (LocationPermissionsGranted){
                    @SuppressLint("MissingPermission") Task location  =fusedLocationProviderClient.getLastLocation();
                    location.addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful() && task.getResult() !=null){
                                Log.d(TAG,"found location");
                                Location currentLocation = (Location) task.getResult();
                                moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                DEFAULT_ZOOM,
                                "my location");

                            }else{
                                Log.d(TAG,"current location is null");
                                Toast.makeText(GoogleMapsActivity.this, " Unable to get current location", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });



                }

            }catch (SecurityException e){
                Log.e(TAG, "getDeviceLocation: security Exeption"  +e.getMessage() );
        }


        }
        private void moveCamera(LatLng latLng,float zoom,String title){
        Log.d(TAG, "moveCamera: moving camera to: lat:" +latLng.latitude + ",lng:" + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

//        if (title.equals("my location")){

            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(title);
            mMap.addMarker(options);





        }
//        hideSoftKeyboard();

//         }


    private void initMap() {
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment =(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(GoogleMapsActivity.this);
    }
     //accessing google maps permisiion
    private void getLocationPermission() {
        Log.d(TAG,"Getting location permission");
        String[] permissions = {ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PERMISSION_GRANTED) {
                LocationPermissionsGranted = true;
                initMap();

            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
                }


            }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
       Log.d(TAG, "onRequestPermission: called");
        LocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PERMISSION_GRANTED) {

                            LocationPermissionsGranted = false;
                            Log.d(TAG,"Permission failed");
                            return;
                        }


                    }
                    Log.d(TAG, "Permission granted");
                    LocationPermissionsGranted = true;
                    initMap();
                }

            }
        }
    }

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    //menu clickable

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_profile:
                Intent intent = new Intent(GoogleMapsActivity.this,UserProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.rateus:
                Toast.makeText(this, "Rate us", Toast.LENGTH_SHORT).show();
                Intent intent5 =new Intent(GoogleMapsActivity.this,RateUsActivity.class);
                startActivity(intent5);
                break;
            case  R.id.nav_addslot:
                Intent intent1 = new Intent(GoogleMapsActivity.this, AddSlotActivity.class);
                startActivity(intent1);
            case R.id.nav_available_slot:
                Intent intent2=new Intent(GoogleMapsActivity.this,AvailableSlotsActivity.class);
                startActivity(intent2);
            case  R.id.help:
                Intent intent3=new Intent(GoogleMapsActivity.this,MpesaActivity.class);
                startActivity(intent3);
            case  R.id.nav_logout:
//
                Intent intent4=new Intent(GoogleMapsActivity.this,LoginActivity.class);
                startActivity(intent4);

//                intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent4);
//                this.finish();

        }
        drawerLayout.closeDrawer(GravityCompat.START);


        return true;
    }
}








