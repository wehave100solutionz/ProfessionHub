package solutionz.professionhub.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import solutionz.professionhub.R;
import solutionz.professionhub.activity.ActivityShowWorkerList;

public class MainActivityWorker extends AppCompatActivity implements View.OnClickListener {


    ImageView iv_Profile_pic;
    EditText etFirstName;
    EditText etLastName;
    EditText etPassword;
    EditText etCNIC;
    Button btnSignUP;

    private static int RESULT_GALLERY_IMAGE = 1;
    private static int RESULT_CAPTURE_IMAGE = 2;
    private final static int REQUEST_PERMISSION_CAMERA = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_worker);
        setViews();
        EnableRuntimePermission();
        iv_Profile_pic.setOnClickListener(this);
        btnSignUP.setOnClickListener(this);

    }

    public void setViews() {
        iv_Profile_pic = (ImageView) findViewById(R.id.profile_pic);
        etFirstName = (EditText) findViewById(R.id.et_first_name);
        etLastName = (EditText) findViewById(R.id.et_last_name);
        etPassword = (EditText) findViewById(R.id.et_password);
        etCNIC = (EditText) findViewById(R.id.et_cnic);
        btnSignUP = (Button) findViewById(R.id.btn_signup);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_pic:

                selectImage();
                break;


            case R.id.btn_signup:

                if (validatorUserName()) {
                    Toast.makeText(getApplicationContext(), "SIgnUp", Toast.LENGTH_SHORT).show();
                    Intent intentWorker = new Intent(MainActivityWorker.this,ActivityShowWorkerList.class);
                    startActivity(intentWorker);
                } else {
                    Toast.makeText(getApplicationContext(), "SIgnUp Failed", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }

    public boolean validatorUserName() {

        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String password = etPassword.getText().toString();
        String cnic = etCNIC.getText().toString();

        if (firstName.isEmpty()) {
            etFirstName.setHint("username required");
            etFirstName.setHintTextColor(Color.RED);
            return false;
        }

        if (lastName.isEmpty()) {
            etLastName.setHint("last name required");
            etLastName.setHintTextColor(Color.RED);
            return false;
        }

        if (password.isEmpty()) {
            etPassword.setHint("password required");
            etPassword.setHintTextColor(Color.RED);
            return false;
        }

        if (password.length() < 5) {
            etPassword.getText().clear();
            etPassword.setHint("at least 6 digit");
            etPassword.setHintTextColor(Color.RED);
            return false;
        }

        if (cnic.isEmpty()) {
            etCNIC.setHint("CNIC required");
            etCNIC.setHintTextColor(Color.RED);
            return false;
        }


        if (cnic.length() < 13 || cnic.length() >= 14) {
            etCNIC.getText().clear();
            etCNIC.setHint("correct CNIC required");
            etCNIC.setHintTextColor(Color.RED);
            return false;
        }

        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RESULT_GALLERY_IMAGE && resultCode == RESULT_OK && data != null) {

            gallerySelectedImage(data);
        }

        else if (requestCode == RESULT_CAPTURE_IMAGE && resultCode == RESULT_OK && data !=null)
        {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            iv_Profile_pic.setImageBitmap(mphoto);
        }

    }




          private void selectImage() {
          final CharSequence[] items = { "Take Photo", "Choose From Gallery",
                "Cancel" };
          AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityWorker.this);
          builder.setTitle("Add Photo!");
          builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Take Photo")) {

                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, RESULT_CAPTURE_IMAGE);

                } else if (items[item].equals("Choose From Gallery")) {

                    Intent iv_intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(iv_intent, RESULT_GALLERY_IMAGE);

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    public void gallerySelectedImage(Intent data){


        Uri selectedImage = data.getData();

        String[] filePathColuam = {MediaStore.Images.Media.DATA};

        Cursor cursor = getContentResolver().query(selectedImage, filePathColuam, null, null, null);
        cursor.moveToFirst();

        int columIndex = cursor.getColumnIndex(filePathColuam[0]);
        String picturePath = cursor.getString(columIndex);
        cursor.close();
        Log.d("picturePath", picturePath);

        iv_Profile_pic.setImageBitmap(BitmapFactory.decodeFile(picturePath));


    }



    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivityWorker.this,
                Manifest.permission.CAMERA))
        {
            Toast.makeText(MainActivityWorker.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        }


        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivityWorker.this,
                Manifest.permission.READ_EXTERNAL_STORAGE))
        {
            Toast.makeText(MainActivityWorker.this,"READ_EXTERNAL_STORAGE allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        }

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivityWorker.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE))
        {
            Toast.makeText(MainActivityWorker.this,"WRITE_EXTERNAL_STORAGE allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        }

        else {

            ActivityCompat.requestPermissions(MainActivityWorker.this,new String[]{
                    Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CAMERA);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case REQUEST_PERMISSION_CAMERA:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(MainActivityWorker.this,"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(MainActivityWorker.this,"Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }

    }
}
