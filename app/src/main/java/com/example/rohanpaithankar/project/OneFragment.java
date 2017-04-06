package com.example.rohanpaithankar.project;

/**
 * Created by Aaroh on 05-Apr-17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

//import info.androidhive.materialtabs.R;


public class OneFragment extends Fragment{

    ProgressBar progressBar;
    TextView responseView;
    Button queryButton;
    EditText product;
    String response,query,id;

 
            public OneFragment() {
        // Required empty public constructor
    }

 
            @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
 
            @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_one, container, false);


                return view;



    }
 
}