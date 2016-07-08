package com.feedbotretailapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Preetam on 10-Jun-16.
 */
public class ThankyouActivity extends Activity{

    private static final String PREFER_NAME = "PratikriyaFeedbackPre";
    public static final String KEY_NAME = "name";
    SharedPreferences sp;

    String mobNo;


    private String resp;
    private String errorMsg;

    JSONParser jParser = new JSONParser();
    JSONObject json;
    private static String url_setResult = "http://ikvoxserver.78kuyxr39b.us-west-2.elasticbeanstalk.com/SetResults.do";
    String Q1,Q2,Q3,Q4,Q5,Q6,Q7,Q8,Q9,Q10,Qo1,Qo2,Qo3,Qo4,Qo5,Qo6,Qo7,Qo8,Qo9,Qo10;

   // View progressOverlay;
    int c1,c0;

    private static final String APP_QUERY_RESULTS = "PratikriyaQueryResultsSP";
    public static final String queary1result = "queary1result";
    public static final String queary2result = "queary2result";
    public static final String queary3result = "queary3result";
    public static final String queary4result = "queary4result";
    public static final String queary5result = "queary5result";
    public static final String queary6result = "queary6result";
    public static final String queary7result = "queary7result";
    public static final String queary8result = "queary8result";
    public static final String queary9result = "queary9result";
    public static final String queary10result = "queary10result";
    SharedPreferences ResultsSharedpreferences;
    static String[] queryResults;
    static String[] storeResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thnxs);
       // setContentView(R.layout.home_page);
        //progressOverlay = findViewById(R.id.progress_overlay);
        c1 = 0;c0=0;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        queryResults=new String[10];
        storeResult=new String[10];
        //sharedpreferences = getSharedPreferences(APP_QUERY, Context.MODE_PRIVATE);
        sp=getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE);
       // mobNo=sp.getString(KEY_NAME,null);
        ResultsSharedpreferences   = getSharedPreferences(APP_QUERY_RESULTS, Context.MODE_PRIVATE);

        for(int i=0;i < storeResult.length; i++){
            storeResult[i]="0";
        }


       queryResults[0]= ResultsSharedpreferences.getString(queary1result, "0");
        queryResults[1]= ResultsSharedpreferences.getString(queary2result, "0");
        queryResults[2]= ResultsSharedpreferences.getString(queary3result, "0");
        queryResults[3]= ResultsSharedpreferences.getString(queary4result, "0");
        queryResults[4]= ResultsSharedpreferences.getString(queary5result, "0");
        queryResults[5]= ResultsSharedpreferences.getString(queary6result, "0");
        queryResults[6]= ResultsSharedpreferences.getString(queary7result, "0");
        queryResults[7]= ResultsSharedpreferences.getString(queary8result, "0");
        queryResults[8]= ResultsSharedpreferences.getString(queary9result, "0");
        queryResults[9]= ResultsSharedpreferences.getString(queary10result, "0");

        for (int j = 0; j < queryResults.length; j++) {

            switch (queryResults[j]) {
                case "GO":
                    storeResult[j]="1";
                    break;
                case "YES":
                    storeResult[j]="1";
                    break;
                case "NO":
                    storeResult[j]="0";
                    break;
                case "STRONGLY_AGREE":
                    storeResult[j]="1";
                    break;
                case "AGREE":
                    storeResult[j]="1";
                    break;
                case "NEUTRAL":
                    storeResult[j]="0";
                    break;
                case "DISAGREE":
                    storeResult[j]="0";
                    break;

                default: //Optional
                    //Statements
            }

        }



        new MyTask().execute();


    }
    private class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
           // AndroidUtils.animateView(progressOverlay, View.VISIBLE, 0.8f, 200);
            super.onPreExecute();
        }


        @Override
        protected Void doInBackground(Void... params) {
            new Thread(new Runnable() {
                @Override
                public void run() {


                    mobNo=sp.getString(KEY_NAME, null);

                    ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
                    postParameters.add(new BasicNameValuePair("mobile", mobNo));
                    postParameters.add(new BasicNameValuePair("q1", storeResult[0]));
                    postParameters.add(new BasicNameValuePair("q2", storeResult[1]));
                    postParameters.add(new BasicNameValuePair("q3", storeResult[2]));
                    postParameters.add(new BasicNameValuePair("q4", storeResult[3]));
                    postParameters.add(new BasicNameValuePair("q5", storeResult[4]));
                    postParameters.add(new BasicNameValuePair("q6", storeResult[5]));
                    postParameters.add(new BasicNameValuePair("q7", storeResult[6]));
                    postParameters.add(new BasicNameValuePair("q8", storeResult[7]));
                    postParameters.add(new BasicNameValuePair("q9", storeResult[8]));
                    postParameters.add(new BasicNameValuePair("q10", storeResult[9]));

                    json = jParser.makeHttpRequest(url_setResult, "GET", postParameters);
                    String s = null;

                    try {

                        s= json.getString("status");


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

                    SharedPreferences.Editor editor1 = ResultsSharedpreferences.edit();
                    editor1.clear();

                    Intent intent = new Intent(ThankyouActivity.this, FeedbackActivity.class);
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
           // AndroidUtils.animateView(progressOverlay, View.GONE, 0, 200);
            if (c1 == 1) {

                Toast.makeText(ThankyouActivity.this, "---Failed--- Retry Please or Check your Network Connection", Toast.LENGTH_SHORT).show();
            } else if (c0 == 1)
            {
                //  Toast.makeText(FeedbackActivity.this, "Welcome : "+UName, Toast.LENGTH_SHORT).show();
                Toast.makeText(ThankyouActivity.this,"No query found", Toast.LENGTH_SHORT).show();
            }

            super.onPostExecute(aVoid);
        }
    }
}
