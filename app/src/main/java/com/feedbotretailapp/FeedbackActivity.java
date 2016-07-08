package com.feedbotretailapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FeedbackActivity extends Activity {
    //TextView queary1,queary2,queary3,queary4,quearyno,heading,q1,q2,q3,q4,tag;
    public static final String FeedbackApp = "FeedbackAppUserDetails" ;
    public static final String branch = "branchKey";
    public static final String fname = "fnameKey";
    public static final String lname = "lnameKey";
    public static final String Emailkey = "emailKey";
    public static final String companykey = "companykey";
    public static final String idkey = "idkey";
   // Typeface type;
    private static final String PREFER_NAME = "PratikriyaFeedbackPre";
    public static final String KEY_NAME = "name";
    SharedPreferences sp;
    SharedPreferences spAppDetails;
    String mobNo;

    private static final String APP_QUERY = "PratikriyaQuerySP1";
    public static final String QS = "QS";
    public static final String QT = "QT";
    public static final String QFK = "QFK";

    public static final String QS1 = "QS1";
    public static final String QS2 = "QS2";
    public static final String QS3 = "QS3";
    public static final String QS4 = "QS4";
    public static final String QS5 = "QS5";
    public static final String QS6 = "QS6";
    public static final String QS7 = "QS7";
    public static final String QS8 = "QS8";
    public static final String QS9 = "QS9";
    public static final String QS10 = "QS10";

    public static final String QOS1 = "QOS1";
    public static final String QOS2 = "QOS2";
    public static final String QOS3 = "QOS3";
    public static final String QOS4 = "QOS4";
    public static final String QOS5 = "QOS5";
    public static final String QOS6 = "QOS6";
    public static final String QOS7 = "QOS7";
    public static final String QOS8 = "QOS8";
    public static final String QOS9 = "QOS9";
    public static final String QOS10 = "QOS10";

    SharedPreferences sharedpreferences;
    private String resp;
    private String errorMsg;

    JSONParser jParser = new JSONParser();
    JSONObject json;
    private static String url_getQuery = "http://ikvoxserver.78kuyxr39b.us-west-2.elasticbeanstalk.com/RetriveQueryForTablet.do";
    //String Q1,Q2,Q3,Q4,Q5,Q6,Q7,Q8,Q9,Q10,Qo1,Qo2,Qo3,Qo4,Qo5,Qo6,Qo7,Qo8,Qo9,Qo10;

    View progressOverlay;
    int c1,c0;

    String QLISTJSON,QOPTIONJSON,QFOCUSKEYWORDJSON;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

       // type = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        progressOverlay = findViewById(R.id.progress_overlay);
        c1 = 0;c0=0;
        sharedpreferences = getSharedPreferences(APP_QUERY, Context.MODE_PRIVATE);
        sp=getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE);
        spAppDetails=getSharedPreferences(FeedbackApp, Context.MODE_PRIVATE);



        new MyTask().execute();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id)
        {
            case R.id.action_UserProfile:
                //startActivity(new Intent(getApplicationContext(), Edit_Profile.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void refresh(View view) {
        Intent intent=new Intent(FeedbackActivity.this,FeedbackActivity.class);
        startActivity(intent);
        finish();
    }

    private class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            AndroidUtils.animateView(progressOverlay, View.VISIBLE, 0.8f, 200);
            super.onPreExecute();
        }


        @Override
        protected Void doInBackground(Void... params) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String companyName=spAppDetails.getString(companykey,null);
                    String branchS=spAppDetails.getString(branch,null);


                    mobNo=sp.getString(KEY_NAME, null);

                    ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
                  //  postParameters.add(new BasicNameValuePair("mobile", mobNo));
                    postParameters.add(new BasicNameValuePair("CompanyName",companyName ));
                    postParameters.add(new BasicNameValuePair("BranchLocation",branchS));

                    json = jParser.makeHttpRequest(url_getQuery, "GET", postParameters);
                    String s = null;

                    try {

                        s= json.getString("status");
                        QLISTJSON=json.getString("QueryList");
                        QOPTIONJSON=json.getString("QueryType");
                        QFOCUSKEYWORDJSON=json.getString("FocusKeyword");


                       /* Q1=json.getString("Q1");
                        Q2=json.getString("Q2");
                        Q3=json.getString("Q3");
                        Q4=json.getString("Q4");
                        Q5=json.getString("Q5");
                        Q6=json.getString("Q6");
                        Q7=json.getString("Q7");
                        Q8=json.getString("Q8");
                        Q9=json.getString("Q9");
                        Q10=json.getString("Q10");

                        Qo1=json.getString("Qo1");
                        Qo2=json.getString("Qo2");
                        Qo3=json.getString("Qo3");
                        Qo4=json.getString("Qo4");
                        Qo5=json.getString("Qo5");
                        Qo6=json.getString("Qo6");
                        Qo7=json.getString("Qo7");
                        Qo8=json.getString("Qo8");
                        Qo9=json.getString("Qo9");
                        Qo10=json.getString("Qo10");*/



                        resp=s;
                    } catch (JSONException e ) {
                        e.printStackTrace();
                        errorMsg = e.getMessage();
                    }catch (Exception e ) {
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

                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(QS, QLISTJSON);
                    editor.putString(QT, QOPTIONJSON);
                    editor.putString(QFK, QFOCUSKEYWORDJSON);


                   /* editor.putString(QS1, Q1);
                    editor.putString(QS2, Q2);
                    editor.putString(QS3, Q3);
                    editor.putString(QS4, Q4);
                    editor.putString(QS5, Q5);
                    editor.putString(QS6, Q6);
                    editor.putString(QS7, Q7);
                    editor.putString(QS8, Q8);
                    editor.putString(QS9, Q9);
                    editor.putString(QS10, Q10);

                    editor.putString(QOS1, Qo1);
                    editor.putString(QOS2, Qo2);
                    editor.putString(QOS3, Qo3);
                    editor.putString(QOS4, Qo4);
                    editor.putString(QOS5, Qo5);
                    editor.putString(QOS6, Qo6);
                    editor.putString(QOS7, Qo7);
                    editor.putString(QOS8, Qo8);
                    editor.putString(QOS9, Qo9);
                    editor.putString(QOS10, Qo10);*/

                    editor.commit();

                    Intent intent = new Intent(FeedbackActivity.this, StartFeedbackCollection.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    // Add new Flag to start new Activity
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                } else {
                    c1 = 1;
                   // Toast.makeText(FeedbackActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // Toast.makeText(Login_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            AndroidUtils.animateView(progressOverlay, View.GONE, 0, 200);
            if (c1 == 1) {

                Toast.makeText(FeedbackActivity.this, "---Failed--- Retry Please or Check your Network Connection", Toast.LENGTH_SHORT).show();
            } else if (c0 == 1)
            {
              //  Toast.makeText(FeedbackActivity.this, "Welcome : "+UName, Toast.LENGTH_SHORT).show();
                Toast.makeText(FeedbackActivity.this,"No query found", Toast.LENGTH_SHORT).show();
            }

            super.onPostExecute(aVoid);
        }
    }


}


