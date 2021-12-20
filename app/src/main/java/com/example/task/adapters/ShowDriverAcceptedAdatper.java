package com.example.task.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.task.AcceptedInterStateRideFiles.Data;
import com.example.task.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShowDriverAcceptedAdatper extends RecyclerView.Adapter<ShowDriverAcceptedAdatper.MyViewHolder>{
    List<Data> dataList;
    Context context;
    Activity activity;
    public ShowDriverAcceptedAdatper(Activity activity, Context context, List<Data> dataList)
    {
        this.activity=activity;
        this.context=context;
        this.dataList=dataList;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ShowDriverAcceptedAdatper.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_accept_card_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Data data = dataList.get(position);

        holder.route.setText(data.getRoute_name());
      holder.driver_price.setText("$"+data.getDone_price());

      holder.minimum_price.setText("$"+data.getMinimum_price());
      holder.maximum_price.setText("$"+data.getMaximum_price());
      holder.client_price.setText("$"+data.getClient_price());
      holder.name_driver.setText(data.getClient_name());
      holder.date.setText(data.getDate());
      holder.time.setText(data.getTime());
        if (data.getClient_image()==null)
        {

        }
        else
        {
            String url= "http://precious-ride.ragzon.com/"+data.getClient_image();
            Picasso.get().load(url).into(holder.driver_image);
        }


//        holder.accept_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                acceptride(data.getDriver_id(),data.getRide_id(),data.getPrice());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView minimum_price,maximum_price,driver_price,client_price,name_driver,route,date,time;
        ImageView driver_image;
//        Button accept_btn;

        public MyViewHolder(View itemView) {
            super(itemView);
            route = itemView.findViewById(R.id.route);
            minimum_price = itemView.findViewById(R.id.minimum_price);
            maximum_price = itemView.findViewById(R.id.maximum_price);
            client_price = itemView.findViewById(R.id.client_price);
            driver_price = itemView.findViewById(R.id.driver_price);
            driver_image = itemView.findViewById(R.id.driver_image);
            name_driver = itemView.findViewById(R.id.name_driver);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);

        }
    }

//    public void acceptride(String driver_id,String ride_id,String price) {
//        AcceptInterStateRideRequest acceptRideRequest = new AcceptInterStateRideRequest();
//        acceptRideRequest.setDriver_id(driver_id);
//        acceptRideRequest.setRide_id(ride_id);
//        acceptRideRequest.setPrice(price);
//
//
//        Call<AcceptInterStateRideResponse> cancelride = ApiClass.getUserServiceAPI().userAcceptInterStateride(acceptRideRequest);
//        cancelride.enqueue(new Callback<AcceptInterStateRideResponse>() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onResponse(Call<AcceptInterStateRideResponse> call, Response<AcceptInterStateRideResponse> response) {
//                if (response.isSuccessful()) {
//
//
//                } else {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AcceptInterStateRideResponse> call, Throwable t) {
//                Toast.makeText(context,"Please check your internet connection",Toast.LENGTH_SHORT);
//                Log.d("TAG", "Error " + t);
//            }
//        });
//    }
}
