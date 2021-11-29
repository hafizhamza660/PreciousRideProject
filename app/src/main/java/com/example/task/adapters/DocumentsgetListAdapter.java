package com.example.task.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.DocumentDataFiles.Data;
import com.example.task.R;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DocumentsgetListAdapter extends RecyclerView.Adapter<DocumentsgetListAdapter.MyViewHolder> {
    private CallbackInterface mCallback;
    private List<Data> dataList;
    Context context;
    Activity activity;
    byte[] byteArray;
    String encodedImage;
    Calendar myCalendar;
    String dateset;

    public interface CallbackInterface{


        void onHandleSelection(int c_position, String name,String type,String document_type,String unique_code,String expiry_date);
    }

    public DocumentsgetListAdapter(Activity activity, Context context, List<Data> dataList) {
        this.activity = activity;
        this.context = context;
        this.dataList = dataList;

        // .. Attach the interface
        try{
            mCallback = (CallbackInterface) context;
        }catch(ClassCastException ex){
            //.. should log the error or throw and exception
            Log.e("MyAdapter","Must implement the CallbackInterface in the Activity", ex);
        }
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public DocumentsgetListAdapter.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.documents_all_item, parent, false);
        return new DocumentsgetListAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(DocumentsgetListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final Data data = dataList.get(position);

        myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                // TODO Auto-generated method stub

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                updateLabel(position);
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

               holder.expiry_date.setText(sdf.format(myCalendar.getTime()));
            }

        };
        if (data.type.equals("Driver")) {
            String document_type = "Driver";
            holder.name_licenses.setText(document_type+" "+data.name);
            if (data.document_type.equals("Mandatory"))
            {
                holder.required_txt.setVisibility(View.VISIBLE);
            }
            else{
                holder.required_txt.setVisibility(View.GONE);
            }
            if (data.expiry_date!=null){
                holder.expiry_layout.setVisibility(View.VISIBLE);
            }
            else{
                holder.expiry_layout.setVisibility(View.GONE);
            }
            if (data.unique_code!=null)
            {
                holder.unique_layout.setVisibility(View.VISIBLE);
            }
            else{
                holder.unique_layout.setVisibility(View.GONE);
            }

            holder.expiry_date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DatePickerDialog(activity, date ,myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                    holder.expiry_date.setText(dateset);
                }
            });

            holder.uploadBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ChooseImage();
                    if(mCallback != null){
                        String expiry_txt,unique_code_txt;
                        if (holder.expiry_layout.getVisibility()==View.GONE)
                        {
                            expiry_txt=null;
                        }
                        else {
                            expiry_txt=holder.expiry_date.getText().toString();
                        }
                        if (holder.unique_layout.getVisibility()==View.GONE)
                        {
                            unique_code_txt=null;
                        }  else {
                            unique_code_txt=holder.unique_code.getText().toString();
                        }
//                        if (unique_code_txt.isEmpty())
//                        {
//                            holder.unique_code.setError("Required");
//                            holder.unique_code.setFocusable(true);
//                        }
//                        else if (expiry_txt.isEmpty())
//                        {
//                            holder.expiry_date.setError("Required");
//
//                        }
//                        else {
                            mCallback.onHandleSelection(position, data.name, data.type, data.document_type, unique_code_txt, expiry_txt);
//                        }
//                       Log.d("DocumentListImage","ImageName :"+name_image);
//                       holder.image_name.setText(name_image);
                    }
                }
            });
        }
        else if (data.type.equals("Vehicle")) {
            String document_type = "Vehicle";
            holder.name_licenses.setText(document_type+" "+data.name);
            if (data.document_type.equals("Mandatory"))
            {
                holder.required_txt.setVisibility(View.VISIBLE);
            }
            else{
                holder.required_txt.setVisibility(View.GONE);
            }
            if (data.expiry_date!=null){
                holder.expiry_layout.setVisibility(View.VISIBLE);
            }
            else{
                holder.expiry_layout.setVisibility(View.GONE);
            }
            if (data.unique_code!=null)
            {
                holder.unique_layout.setVisibility(View.VISIBLE);
            }
            else{
                holder.unique_layout.setVisibility(View.GONE);
            }
            holder.expiry_date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DatePickerDialog(activity, date ,myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                    holder.expiry_date.setText(dateset);
                }
            });

            holder.uploadBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ChooseImage();
                    if(mCallback != null){
                        String expiry_txt,unique_code_txt;
                        if (holder.expiry_layout.getVisibility()==View.GONE)
                        {
                            expiry_txt=null;
                        }
                        else {
                            expiry_txt=holder.expiry_date.getText().toString();
                        }
                        if (holder.unique_layout.getVisibility()==View.GONE)
                        {
                            unique_code_txt=null;
                        }  else {
                            unique_code_txt=holder.unique_code.getText().toString();
                        }
//                        if (unique_code_txt.isEmpty())
//                        {
//                            holder.unique_code.setError("Required");
//                            holder.unique_code.setFocusable(true);
//                        }
//                        else if (expiry_txt.isEmpty())
//                        {
//                            holder.expiry_date.setError("Required");
//
//                        }
//                        else {
                            mCallback.onHandleSelection(position, data.name, data.type, data.document_type, unique_code_txt, expiry_txt);
//                       Log.d("DocumentListImage","ImageName :"+name_image);
//                       holder.image_name.setText(name_image);
//                        }
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_licenses, required_txt, expiry_date,image_name;
        EditText unique_code;
        LinearLayout unique_layout, expiry_layout;
        Button uploadBtn;
        public MyViewHolder(View itemView) {
            super(itemView);
            name_licenses = itemView.findViewById(R.id.name_licenses);
            unique_layout = itemView.findViewById(R.id.unique_layout);
            expiry_layout = itemView.findViewById(R.id.expiry_layout);
            required_txt = itemView.findViewById(R.id.required_txt);
            expiry_date = itemView.findViewById(R.id.expiry_date);
            unique_code = itemView.findViewById(R.id.unique_code);
            uploadBtn = itemView.findViewById(R.id.uploadBtn);
            image_name = itemView.findViewById(R.id.image_name);

        }
    }

    private void updateLabel(int position) {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateset= sdf.format(myCalendar.getTime());
//        vehicle_year.setText();
    }

}
