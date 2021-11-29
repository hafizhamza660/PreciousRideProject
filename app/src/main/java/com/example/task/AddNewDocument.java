package com.example.task;

import static com.example.task.Session.SaveSharedPreference.getClientId;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.task.UserServiceInterface.ApiClass;
import com.example.task.DocumentUploadFiles.RequestDocument;
import com.example.task.DocumentUploadFiles.ResponseDocumentUpload;

import java.io.ByteArrayOutputStream;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddNewDocument extends AppCompatActivity {
    CardView id_card_front_btn,id_card_back_btn;
    ImageView id_card_front_img,id_card_back_img,id_card_front_demo,id_card_back_demo,imageshow;

    private int PICK_IMAGE_REQUEST = 1;

    private Bitmap bitmap;

    private Uri filePath;
    String docName_s,button_value;
    TextView docName;
    EditText card_number;
    TextView expiry_date;
    Calendar myCalendar;

    Bitmap front_bitmap,back_bitmap;
    Context context;
    String front_base_code,back_base_code;
    byte[] byteArray;
    String encodedImage;
    ProgressBar pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_document);
        context=this;

        Intent intent=getIntent();
        if (intent!=null) {
            docName_s = intent.getStringExtra("docName");

            docName = findViewById(R.id.docName);
            docName.setText(docName_s);
        }

        id_card_front_btn= findViewById(R.id.id_card_front_btn);
        id_card_back_btn= findViewById(R.id.id_card_back_btn);
        id_card_front_img= findViewById(R.id.id_card_front_img);
        id_card_back_img= findViewById(R.id.id_card_back_img);
        id_card_front_demo= findViewById(R.id.id_card_front_demo);
        id_card_back_demo= findViewById(R.id.id_card_back_demo);
        card_number= findViewById(R.id.card_number);
        expiry_date= findViewById(R.id.expiry_date);
        imageshow= findViewById(R.id.imageshow);

        id_card_front_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_value="0";
//                showFileChooser();
                ChooseImage();
            }
        });

        id_card_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_value="1";
//                showFileChooser();
                ChooseImage();
            }
        });

        myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        expiry_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddNewDocument.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void back_button(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void complete_add_document(View view) {
//        front_base_code = getStringImage(front_bitmap);
//        back_base_code = getStringImage(back_bitmap);
//        front_base_code = ImageUtil.convert(front_bitmap);
//        back_base_code = ImageUtil.convert(back_bitmap);

        Log.d("ConvertBitmap",""+front_base_code);
//        if ()
        String type=docName_s;
        String cardnumber= card_number.getText().toString();
        String expiry = expiry_date.getText().toString();
//
        documentupload(front_base_code,back_base_code,type,cardnumber,expiry);

    }


    public void ChooseImage() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)
                && !Environment.getExternalStorageState().equals(
                Environment.MEDIA_CHECKING)) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 1);

        } else {
            Toast.makeText(AddNewDocument.this,
                    "No activity found to perform this task",
                    Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bitmap originBitmap = null;
            Uri selectedImage = data.getData();
            Toast.makeText(AddNewDocument.this, selectedImage.toString(),
                    Toast.LENGTH_LONG).show();
//            txtmsg.setText(selectedImage.toString());
            InputStream imageStream;
            try {
                imageStream = getContentResolver().openInputStream(
                        selectedImage);
                originBitmap = BitmapFactory.decodeStream(imageStream);

            } catch (FileNotFoundException e) {

//                txtmsg.setText(e.getMessage().toString());
            }
            if (originBitmap != null) {
//                this.imageshow.setImageBitmap(originBitmap);

                if (button_value.equals("0")) {

//                    front_base_code=encodedImage;
                    id_card_front_demo.setVisibility(View.GONE);
                    id_card_front_img.setVisibility(View.VISIBLE);
                    this.id_card_front_img.setImageBitmap(originBitmap);

                }
                else{
//                    back_base_code=encodedImage;
                    id_card_back_demo.setVisibility(View.GONE);
                    id_card_back_img.setVisibility(View.VISIBLE);
                    this.id_card_back_img.setImageBitmap(originBitmap);
                }

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                originBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArray = stream.toByteArray();
                encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
                if (button_value.equals("0")) {

                    front_base_code=encodedImage;
                    this.id_card_front_img.setImageBitmap(originBitmap);

                }
                else{
                    back_base_code=encodedImage;
                    this.id_card_back_img.setImageBitmap(originBitmap);
                }
                Toast.makeText(AddNewDocument.this, "Conversion Done",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
//            txtmsg.setText("There's an error if this code doesn't work, thats all I know");

        }
    }





    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }





    public void add_pic(View view) {
        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        expiry_date.setText(sdf.format(myCalendar.getTime()));
    }

    public void documentupload(String front_image,String back_image,String type,String card_number,String expiry) {
        RequestDocument requestDocument = new RequestDocument();
        requestDocument.setDriver_id(getClientId(context));
        requestDocument.setFront_image(front_image);
        requestDocument.setBack_image(back_image);
        requestDocument.setType(type);
        requestDocument.setCard_number(card_number);
        requestDocument.setExpiry(expiry);
//        signUpRequest.setI_code(invite_code);


        Call<ResponseDocumentUpload> responseDocumentUploadCall = ApiClass.getUserServiceAPI().userDriverAddCards(requestDocument);
        responseDocumentUploadCall.enqueue(new Callback<ResponseDocumentUpload>() {
            @Override
            public void onResponse(Call<ResponseDocumentUpload> call, Response<ResponseDocumentUpload> response) {
                if (response.isSuccessful()) {
                    Log.d("DataDocument",""+response.body().message);
                    Toast.makeText(AddNewDocument.this, ""+response.body().message, Toast.LENGTH_SHORT).show();
//                    setImageUrl(context,response.body().data.card_file_front);
                    Intent intent=new Intent(AddNewDocument.this,DocumentManagement.class);
                    startActivity(intent);
                    finish();


                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponseDocumentUpload> call, Throwable t) {
//                Toast.makeText(SignUp.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }
}