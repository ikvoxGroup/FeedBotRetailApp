package com.feedbotretailapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

/**
 * Created by Preetam on 28-Jun-16.
 */
public class ImageUpload extends Activity {
Button upload;
    private static final int CAMERA_REQUEST = 0;
    private static final int IMAGE_PICK = 1;
    private static final String profilePic = "feedbotImage";
    public static final String profilepicURI = "image";
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_uploader);
        upload=(Button)findViewById(R.id.upload);
        sp=getSharedPreferences(profilePic, Context.MODE_PRIVATE);


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryintent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryintent.setType("image/*");
                startActivityForResult(galleryintent, IMAGE_PICK);
                //InputStream inputStream=galleryintent.getData();
                try {
                    Bitmap bmp=BitmapFactory.decodeStream(getContentResolver().openInputStream(galleryintent.getData()));

                Bitmap realImage = bmp;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                realImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] b = baos.toByteArray();

                String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
               // textEncode.setText(encodedImage);


                SharedPreferences.Editor edit = sp.edit();
                edit.putString(profilepicURI, encodedImage);
                edit.commit();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        });
        }
    }

