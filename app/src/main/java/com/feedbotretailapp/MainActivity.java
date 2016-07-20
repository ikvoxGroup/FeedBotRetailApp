package com.feedbotretailapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import LoginSessionManagement.UserSessionManager;


/**
 * Created by Preetam on 05-Apr-16.
 */
public class MainActivity extends Activity {

    Button loginB;
    EditText mNumber, password,branchid;
    String mNumberS, passwordS,OwnerBranchS;

    private String resp;
    private String errorMsg;

    // User Session Manager Class
    UserSessionManager session;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    private static String url_login = "http://feedbotappserver.cgihum6dcd.us-west-2.elasticbeanstalk.com/feedbacklogin.do";

    String email=null;
    String companyName=null;
    String ownerFName=null;
    String ownerLName=null;
    String id=null;
    String OwnerBranch=null;

    public static final String FeedbackApp = "FeedbackAppUserDetails" ;
    public static final String branch = "branchKey";
    public static final String fname = "fnameKey";
    public static final String lname = "lnameKey";
    public static final String Emailkey = "emailKey";
    public static final String companykey = "companykey";
    public static final String idkey = "idkey";

    SharedPreferences sharedpreferences;


   // Button login;
    /*public int currentimageindex=0;
    Timer timer;
    TimerTask task;
    ImageView slidingimage;
    private int[] IMAGE_IDS = {
            R.drawable.splash0, R.drawable.splash1, R.drawable.splash2,
            R.drawable.splash3,R.drawable.splash4
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // login=(Button)findViewById(R.id.login);

       // session.logoutUser();
        // User Session Manager
        session = new UserSessionManager(getApplicationContext());
        sharedpreferences = getSharedPreferences(FeedbackApp, Context.MODE_PRIVATE);

        loginB = (Button) findViewById(R.id.loginB);

        mNumber = (EditText) findViewById(R.id.mNumber);
        password = (EditText) findViewById(R.id.password);
        branchid = (EditText) findViewById(R.id.branchid);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        /*final Handler mHandler = new Handler();

        // Create runnable for posting
        final Runnable mUpdateResults = new Runnable() {
            public void run() {

                AnimateandSlideShow();

            }
        };

        int delay = 1000; // delay for 1 sec.

        int period = 5000; // repeat every 4 sec.

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

                mHandler.post(mUpdateResults);

            }


        }, delay, period);*/






        /*login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });*/


        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mNumberS = mNumber.getText().toString().trim();
                passwordS = password.getText().toString().trim();
                OwnerBranchS =branchid.getText().toString().trim();

                if (mNumberS.equals("")) {
                    mNumber.setError("Please enter a valid mobile number");
                    // Toast.makeText(MainActivity.this, "Please enter a valid Mobile Number", Toast.LENGTH_SHORT).show();
                } else if (passwordS.equals("")) {
                    password.setError("Please enter your password");
                    //Toast.makeText(MainActivity.this, "Password Please", Toast.LENGTH_SHORT).show();
                }else if (OwnerBranchS.equals("")) {
                    branchid.setError("Please enter Branch Name/location");
                    //Toast.makeText(MainActivity.this, "Password Please", Toast.LENGTH_SHORT).show();
                } else
                /**
                 * According with the new StrictGuard policy, running long tasks
                 * on the Main UI thread is not possible So creating new thread
                 `  * to create and execute http operations
                 */
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                           /* Calendar c = Calendar.getInstance();
                            SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
                            String date = df2.format(c.getTime());*/

                            ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();

                            postParameters.add(new BasicNameValuePair("mobile", mNumberS));
                            postParameters.add(new BasicNameValuePair("password", passwordS));
                            //postParameters.add(new BasicNameValuePair("date", date));

                            json = jParser.makeHttpRequest(url_login, "GET", postParameters);
                            String s = null;
                            try {
                                /*response = SimpleHttpClient
                                        .executeHttpPost(
                                                "http://ikvoxmainserver.2ruipbvvnv.us-west-2.elasticbeanstalk.com/feedbacklogin.do",
                                                postParameters);*/

                                s = json.getString("status");
                                email = json.getString("email");
                                companyName = json.getString("companyName");
                                ownerFName = json.getString("ownerFName");
                                ownerLName = json.getString("ownerLName");
                                id = json.getString("id");

                                /*String res = response.toString();
                                System.out.println(res);
                                resp = res.replaceAll("\\s+", "");*/
                                resp = s;
                            } catch (JSONException e) {
                                e.printStackTrace();
                                errorMsg = e.getMessage();
                            } catch (Exception e) {
                                e.printStackTrace();
                                errorMsg = e.getMessage();
                            }

                        }
                    }).start();
                try {
                    Thread.sleep(3000);
                    /**
                     * Inside the new thread we cannot update the main thread So
                     * updating the main thread outside the new thread
                     */
                    // Toast.makeText(LoginActivity.this, resp, Toast.LENGTH_LONG).show();
                    if (resp.equals("success")) {
                        session.createUserLoginSession(mNumberS, passwordS);

                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString(fname, ownerFName);
                        editor.putString(lname, ownerLName);
                        editor.putString(Emailkey, email);
                        editor.putString(companykey, companyName.toLowerCase());
                        editor.putString(idkey, id);
                        editor.putString(branch,OwnerBranchS.toLowerCase());
                        editor.commit();

                        Toast.makeText(MainActivity.this, companyName, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, FeedbackActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        // Add new Flag to start new Activity
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();

                    } else {

                        Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                    }

                    if (null != errorMsg && !errorMsg.isEmpty()) {

                        //Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                   // Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }

        });


    }
   /* private void AnimateandSlideShow() {


        slidingimage = (ImageView)findViewById(R.id.ImageView3_Left);
        slidingimage.setImageResource(IMAGE_IDS[currentimageindex%IMAGE_IDS.length]);

        currentimageindex++;

        Animation rotateimage = AnimationUtils.loadAnimation(this, R.anim.custom_anim);


        slidingimage.startAnimation(rotateimage);



    }*/
    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }
}
