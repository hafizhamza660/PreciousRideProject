package com.example.task.RideAcceptWithPrice;



import com.example.task.RideRequestedHistory.Data;

import java.util.List;

public class AcceptRideWithPriceRequest {
   public String price;
   public String driver_id;
   public String ride_id;
   public String client_id;

   public String getClient_id() {
      return client_id;
   }

   public void setClient_id(String client_id) {
      this.client_id = client_id;
   }

   public String getPrice() {
      return price;
   }

   public void setPrice(String price) {
      this.price = price;
   }

   public String getDriver_id() {
      return driver_id;
   }

   public void setDriver_id(String driver_id) {
      this.driver_id = driver_id;
   }

   public String getRide_id() {
      return ride_id;
   }

   public void setRide_id(String ride_id) {
      this.ride_id = ride_id;
   }
}
