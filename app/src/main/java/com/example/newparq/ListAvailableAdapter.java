package com.example.newparq;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAvailableAdapter extends ArrayAdapter{
    private Activity mContext;
    List<AddDetails> addDetailsList;

    public ListAvailableAdapter(Activity mContext, List<AddDetails> addDetailsList){
        super(mContext, R.layout.list_item, addDetailsList);
        this.mContext=mContext;
        this.addDetailsList=addDetailsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_item,null,true);

        TextView textviewPhoneNumber = listItemView.findViewById(R.id.textView_show_phone);
        TextView textViewCharges = listItemView.findViewById(R.id.textView_show_money);
        TextView textViewNumber = listItemView.findViewById(R.id.textView_show_Number);
        TextView textViewLocation = listItemView.findViewById(R.id.textView_show_location);

        AddDetails addDetails= addDetailsList.get(position);

        textviewPhoneNumber.setText(addDetails.getPhone());
        textViewCharges.setText(addDetails.getCharges());
        textViewNumber.setText(addDetails.getSlots());
        textViewLocation.setText(addDetails.getLocation());

        return listItemView;


    }
}

