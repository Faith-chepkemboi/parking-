package com.example.newparq;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

import static com.example.newparq.R.layout.activity_google_maps;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.Placeholder;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newparq.databinding.ActivityGoogleMapsBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
//import com.example.newparq.databinding.ActivityGoogleMapsBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class GoogleMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private Bundle savedInstanceState;

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

                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            init();


        }
    }
    private static final float DEFAULT_ZOOM = 10f;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    GoogleMap mMap;
    GoogleMap googleMap;
    SupportMapFragment mapFragment;

    private static final String TAG = "GoogleMapsActivity";

    private static final String FINE_LOCATION = ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;

    private Boolean LocationPermissionsGranted = false;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(activity_google_maps);

        searchText = (EditText) findViewById(R.id.search_location);
        getLocationPermission();



    }
    private  void init(){
        Log.d(TAG,"init: initializing");
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
        hideSoftKeyboard();
    }

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


    private void getDeviceLocation(){
            Log.d(TAG, "getDeviceLocation: getting the current location");
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

            try {
                if (LocationPermissionsGranted){
                    Task location  =fusedLocationProviderClient.getLastLocation();
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
                                Toast.makeText(GoogleMapsActivity.this, " Unable to get current loaction", Toast.LENGTH_SHORT).show();
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

        if (title.equals("my location")){

            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(title);
            mMap.addMarker(options);

        }
        hideSoftKeyboard();

        }


    private void initMap() {
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment =(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(GoogleMapsActivity.this);
    }

    private void getLocationPermission() {
        Log.d(TAG,"Getting location permission");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
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


}











//    }
//







//
//                                              ocoder geocoder = new Geocoder(GoogleMapsActivity.this);
////                    addressList.add(Address);
//                                                  List<Address> addressList = new ArrayList<>();
//
//                                                  try {
//
//                                                      addressList = geocoder.getFromLocationName(location, 1);
////
////                            if (addressList.size() > 0) {
////
////                                latitude = addressList.get(0).getLatitude();
////                                longtitude = addressList.get(0).getLongitude();
////                            }
//
//                                                  } catch (IOException e) {
//                                                      Log.e(TAG, "Geolocate exeption" + e.getMessage());
//                                                  }
//                                                  if (addressList.size() > 0) {
//                                                      Address address = addressList.get(0);
//                                                      Log.d(TAG, "geolocate found location" + address.toString());
//
//                                                      moveCamera(new LatLng(address.getLatitude(), address.getLongitude()),
//                                                              DEFAULT_ZOOM, "my location");
//
//
//                                                  } else {
//                                                      Log.e(TAG, "onComplete: current location is null");
//                                                      Toast.makeText(GoogleMapsActivity.this, "unable to get current location",
//                                                              Toast.LENGTH_SHORT).show();
//                                                  }
//
//
////                    Toast.makeText(GoogleMapsActivity.this, address.toString(), Toast.LENGTH_SHORT).show();
//                                              }


//                    //getting location from the list
//                Address address = addressList.get(0);

                //creating variable for the location by adding longitudes and latitudes


//                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
//
//                //adding marker to the position
//
//                mMap.addMarker(new MarkerOptions().position(latLng).title(location));
//
//                //animating camera to that position
//                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
//
//                return false;
//                }




//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                return false;
//            }
//        });
//        mapFragment.getMapAsync(this::onMapReady);
//
//
//    }
//
//    private void moveCamera(LatLng latLng, float zoom, String title) {
//        Log.d(TAG, "moveCamera: moving camera to:lat: "+ latLng.latitude+ ", lng:" +latLng.longitude);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
//
//        MarkerOptions options =new MarkerOptions()
//                .position(latLng)
//                .title(title);
//        mMap.addMarker(options);
//
//    }
//
//
//    /**
//         * Manipulates the map once available.
//         * This callback is triggered when the map is ready to be used.
//         * This is where we can add markers or lines, add listeners or move the camera. In this case,
//         * we just add a marker near Sydney, Australia.
//         * If Google Play services is not installed on the device, the user will be prompted to install
//         * it inside the SupportMapFragment. This method will only be triggered once the user has
//         * installed Google Play services and returned to the app.
//         */
//        @Override
//        public void onMapReady (@NonNull GoogleMap googleMap){
//            mMap = googleMap;
//
//
//            // Add a marker in Sydney and move the camera
//            LatLng Nairobi = new LatLng( -0.0632045267341352, 37.89191639003633 );
//            mMap.addMarker(new MarkerOptions().position(Nairobi).title("Marker in Nairobi"));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(Nairobi));
//
//        }
//    }





