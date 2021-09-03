package com.example.task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeOnlineBookingDetailsGotopickup extends AppCompatActivity {
    public static final String TAG ="Details";
    BottomSheetBehavior bottomSheetBehavior;
    ConstraintLayout bottomSheetLayout;
    FloatingActionButton floatingMessage;
    String driver_id,client_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_online_booking_details_gotopickup);

        bottomSheetLayout = findViewById(R.id.bottom_sheet);
        floatingMessage = findViewById(R.id.floatingMessage);

        Intent intent=getIntent();
        driver_id=intent.getStringExtra("driver_id");
        client_id=intent.getStringExtra("client_id");


        // init the bottom sheet behavior
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);
        // set callback for changes
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
//                        textViewBottomSheetState.setText("STATE HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
//                        textViewBottomSheetState.setText("STATE EXPANDED");
                        // update toggle button text
//                        toggleBottomSheet.setText("Expand BottomSheet");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
//                        textViewBottomSheetState.setText("STATE COLLAPSED");
                        // update collapsed button text
//                        toggleBottomSheet.setText("Collapse BottomSheet");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
//                        textViewBottomSheetState.setText("STATE DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
//                        textViewBottomSheetState.setText("STATE SETTLING");
                        break;
                }
                Log.d(TAG, "onStateChanged: " + newState);
            }
            @Override public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });


        floatingMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(HomeOnlineBookingDetailsGotopickup.this,Message.class);
                intent.putExtra("client_id",client_id);
                intent.putExtra("driver_id",driver_id);
                startActivity(intent);
            }
        });
    }

    public void back_button(View view) {
//        onBackPressed();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}