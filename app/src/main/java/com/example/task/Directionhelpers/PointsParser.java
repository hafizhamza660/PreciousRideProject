package com.example.task.Directionhelpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.task.UserServiceInterface.ApiClass;
import com.example.task.DataSendFiles.RequestDataSend;
import com.example.task.DataSendFiles.ResponseDataSend;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PointsParser extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {
    TaskLoadedCallback taskCallback;
    String directionMode = "driving";
    int id_To_Update = 0;
    Context context;

    public PointsParser(Context mContext, String directionMode) {
        context=mContext;
        this.taskCallback = (TaskLoadedCallback) mContext;
        this.directionMode = directionMode;
//        mydb = new DatabaseHandler(mContext.getApplicationContext());
    }

    // Parsing the data in non-ui thread
    @Override
    protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

        JSONObject jObject;
        List<List<HashMap<String, String>>> routes = null;

        try {
            jObject = new JSONObject(jsonData[0]);
//            datasend(jObject);
            setPreferenceObject(context,jObject,"mapObject");
            Log.d("JsonArray", jsonData[0].toString());
            DataParser parser = new DataParser();
            Log.d("Parser", parser.toString());

            // Starts parsing data
            routes = parser.parse(jObject);
            Log.d("mylog", "Executing routes");
            Log.d("mylog", routes.toString());

        } catch (Exception e) {
            Log.d("mylog", e.toString());
            e.printStackTrace();
        }

        return routes;
    }

    // Executes in UI thread, after the parsing process
    @Override
    protected void onPostExecute(List<List<HashMap<String, String>>> result) {
        ArrayList<LatLng> points;
        PolylineOptions lineOptions = null;
        // Traversing through all the routes
        for (int i = 0; i < result.size(); i++) {
            points = new ArrayList<>();
            lineOptions = new PolylineOptions();
            // Fetching i-th route
            List<HashMap<String, String>> path = result.get(i);
            // Fetching all the points in i-th route
            for (int j = 0; j < path.size(); j++) {
                HashMap<String, String> point = path.get(j);
                double lat = Double.parseDouble(point.get("lat"));
                double lng = Double.parseDouble(point.get("lng"));
                LatLng position = new LatLng(lat, lng);
                points.add(position);
            }
            // Adding all the points in the route to LineOptions
            lineOptions.addAll(points);
            if (directionMode.equalsIgnoreCase("driving")) {
                lineOptions.width(10);
                lineOptions.color(Color.BLUE);
            } else {
                lineOptions.width(20);
                lineOptions.color(Color.BLUE);
            }
            Log.d("mylog", "onPostExecute lineoptions decoded");
        }

        // Drawing polyline in the Google Map for the i-th route
        if (lineOptions != null) {
            //mMap.addPolyline(lineOptions);
            taskCallback.onTaskDone(lineOptions);

        } else {
            Log.d("mylog", "without Polylines drawn");
        }
    }



    public void datasend(JSONObject object) {
        RequestDataSend requestDataSend = new RequestDataSend();
        requestDataSend.setObject(object);
//        signUpRequest.setI_code(invite_code);


        Call<ResponseDataSend> signUpResponseCall = ApiClass.getUserServiceAPI().userDriverApiObject(requestDataSend);
        signUpResponseCall.enqueue(new Callback<ResponseDataSend>() {
            @Override
            public void onResponse(Call<ResponseDataSend> call, Response<ResponseDataSend> response) {
                if (response.isSuccessful()) {

                    ResponseDataSend responseDataSend = new ResponseDataSend();
//                    responseDataSend.setEnd_address(response.body().end_address);

                    Log.d("TAGtotal_distance",""+response.body().total_distance.text);
                    Log.d("TAGduration",""+response.body().duration.text);
//                    Log.d("TAGduration",""+response.body().duration.get(0));
                    Log.d("TAGstart_address",""+response.body().start_address);
                    Log.d("TAGend_address",""+response.body().end_address);

                    String total_distance=response.body().total_distance.text;
                    String duration=response.body().duration.text;
                    String start_address=response.body().start_address;
                    String end_address=response.body().end_address;


                    saveTask(total_distance,duration,start_address,end_address);



                } else {
//                    Toast.makeText(SignUp.this, "API not Hit", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataSend> call, Throwable t) {
//                Toast.makeText(SignUp.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }



    private void saveTask(String total_distance,String duration,String start_address,String end_address) {


        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

//                //creating a task
//                RideDataTable dataTable = new RideDataTable();
//                dataTable.setTotal_distance(total_distance);
//                dataTable.setDuration(duration);
//                dataTable.setStart_address(start_address);
//                dataTable.setEnd_address(end_address);
//
//                //adding to database
//                DatabaseClient.getInstance(context).getAppDatabase()
//                        .rideDao()
//                        .insert(dataTable);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
//                finish();
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }


    static public void setPreferenceObject(Context c, Object modal,String key) {
        /**** storing object in preferences  ****/
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(
                c.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();

        Gson gson = new Gson();
        String jsonObject = gson.toJson(modal);
        prefsEditor.putString(key, jsonObject);
        prefsEditor.commit();

    }

//    public void saveData(String total_distance,String duration,String start_address,String end_address) {
//        /*, */
// /*        mydb.addContact(name.getText().toString(),email.getText().toString(), street.getText().toString(), place.getText().toString(), phone.getText().toString());
//         finish();*/
//
//        Cursor idData = mydb.getData(id_To_Update);
//
//
//        if (idData != null) {
//            int Value = idData.getColumnCount();
//            if (Value > 0) {
//                if (mydb.updateStudentContact(id_To_Update, total_distance, duration, start_address,end_address)) {
//                    Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
////                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
////                    startActivity(intent);
//                } else {
//                    Toast.makeText(context, "Record not updated", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                if (mydb.addRideData(total_distance, duration,start_address, end_address)) {
//                    Toast.makeText(context, "Successfully Added", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(context, "Record not added", Toast.LENGTH_SHORT).show();
//                }
////                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
////                startActivity(intent);
//            }
//        }
//    }
}