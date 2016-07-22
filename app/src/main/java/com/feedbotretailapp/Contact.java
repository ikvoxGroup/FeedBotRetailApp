package com.feedbotretailapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Preetam on 24-Jun-16.
 */
public class Contact extends Activity {
    private static final String PREFER_NAME = "PratikriyaFeedbackPre";
    public static final String KEY_NAME = "name";

    private static final String APP_QUERY = "PratikriyaQuerySP1";
    public static final String QS = "QS";
    public static final String QT = "QT";
    public static final String QFK = "QFK";

    private static final String APP_GRAPHVALUE = "APP_GRAPHVALUE";
    public static final String GV = "GV";

    private static final String APP_QUERY_RESULTS = "PratikriyaQueryResultsSP";
    public static final String queary1result = "queary1result";

    public static final String FeedbackApp = "FeedbackAppUserDetails" ;
    public static final String branch = "branchKey";
    public static final String companykey = "companykey";

    SharedPreferences userDetailsSharedPreference;
    SharedPreferences QuerySharedpreferences;
    SharedPreferences ResultsSharedpreferences;
    SharedPreferences GraphValueSharedpreferences;

    SharedPreferences app;

    private String date,dateID,resp,errorMsg;

    static EditText[] contactEditText;
    static String[] contactDetails;
    static int id[] = {R.id.email, R.id.phone, R.id.suggestionBox};
    LinearLayout bot;

    Gson gson;
    ArrayList<String> Query,result,GraphValue;

    View progressOverlay;
    View CouponOverlay;
    int c1,c0;

    JSONParser jParser = new JSONParser();
    JSONObject json;
    private static String url_getQuery = "http://feedbotappserver.cgihum6dcd.us-west-2.elasticbeanstalk.com/FeedbackDataBucket.do";
    static String good="",bad="",negative="";
    public static MyDatabase db;
    public static SQLiteDatabase sdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.contact_page);
        progressOverlay = findViewById(R.id.progress_overlay);
        CouponOverlay= findViewById(R.id.coupon_overlay);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        bot=(LinearLayout)findViewById(R.id.bot);
        db = new MyDatabase(getApplicationContext());
        sdb= db.getWritableDatabase();
        contactEditText = new EditText[3];
        contactDetails = new String[3];
        ResultsSharedpreferences   = getSharedPreferences(APP_GRAPHVALUE, Context.MODE_PRIVATE);
        QuerySharedpreferences = getSharedPreferences(APP_QUERY, Context.MODE_PRIVATE);
        String QFKey=QuerySharedpreferences.getString(QFK,null);
        String r=ResultsSharedpreferences.getString(GV,null);
        Gson gson = new Gson();
        ArrayList<String> resultSeeker=gson.fromJson(r,ArrayList.class);
        ArrayList<String> focuskey=gson.fromJson(QFKey,ArrayList.class);

        for (int i = 0; i < contactEditText.length; i++) {
            contactEditText[i] = (EditText) findViewById(id[i]);
        }
        for (int j = 0; j < contactDetails.length; j++) {
            contactDetails[j] = "n/a";
        }

        userDetailsSharedPreference   = getSharedPreferences(FeedbackApp, Context.MODE_PRIVATE);

        ResultsSharedpreferences   = getSharedPreferences(APP_QUERY_RESULTS, Context.MODE_PRIVATE);

        QuerySharedpreferences = getSharedPreferences(APP_QUERY, Context.MODE_PRIVATE);

        GraphValueSharedpreferences=getSharedPreferences(APP_GRAPHVALUE,Context.MODE_PRIVATE);

        app=getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE);



        Query=new ArrayList<String>();
        result=new ArrayList<String>();


        for (int j=0;j<resultSeeker.size();j++){
            if ((String.valueOf(resultSeeker.get(j))).equals("1.0")) {
                //storeResult[j]=String.valueOf(Integer.parseInt(resultSeeker.get(j)));

                String s=String.valueOf(focuskey.get(j));
                good=s;
            }
            else{

                String s1=String.valueOf(focuskey.get(j));
                negative=s1;
                bad=", while we understand you were not happy with "+negative+" ";
            }

        }
/*
        String fq=QuerySharedpreferences.getString(QFK,null);
        Gson gson=new Gson();
        ArrayList<String> fqs=new ArrayList<String>();
        fqs.get(1);*/


        Typewriter writer = new Typewriter(this);
        bot.addView(writer,-1,-1);


        //Add a character every 150ms
        writer.setCharacterDelay(100);
        writer.animateText("Thank you for your valuable feedback !  \n" +
                "A coupon code has been allocated to you. To avail the code please provide us your mail id. \n"+
                "As the brand owner, we are happy that you found our "+good+" as good "+bad+".\n" +
                "To help  improve our services please leave comment below. I personally assure you that my team will work on it.");
       /* I can see you found our p1 as bad and*/



    }

    public void contactsubmit(View view) {
        good="";
        bad="";
       for (int i = 0; i < contactEditText.length; i++) {
            contactEditText[i] = (EditText) findViewById(id[i]);
        }
        for (int j = 0; j < contactDetails.length; j++) {
            contactDetails[j] = contactEditText[j].getText().toString().trim();

            if (contactDetails[j].equals("")){
                contactDetails[j]="empty";
            }
        }
        if (contactDetails[0].contains("@") && contactDetails[0].contains("."))
        {
            new CouponTask().execute();
        }
        else {
            new MyTask().execute();
        }
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

                    /*gson = new Gson();
                    Query = gson.fromJson(QuerySharedpreferences.getString(QS,null), ArrayList.class);
                    result=gson.fromJson(ResultsSharedpreferences.getString(queary1result,null), ArrayList.class);
                    GraphValue=gson.fromJson(GraphValueSharedpreferences.getString(GV, null), ArrayList.class);*/


                    String CName=userDetailsSharedPreference.getString(companykey, "na");
                    String BName=userDetailsSharedPreference.getString(branch, "na");

                 //   String mob=app.getString(KEY_NAME,null);
                    ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
                    // postParameters.add(new BasicNameValuePair("mob", mob));
                    postParameters.add(new BasicNameValuePair("companyname",CName ));
                    postParameters.add(new BasicNameValuePair("branchname",BName ));
                    // postParameters.add(new BasicNameValuePair("dateID", dateID));
                    //postParameters.add(new BasicNameValuePair("date", date));

                    postParameters.add(new BasicNameValuePair("email", contactDetails[0]));
                    postParameters.add(new BasicNameValuePair("phone", contactDetails[1]));
                    postParameters.add(new BasicNameValuePair("suggestion", contactDetails[2]));

                    postParameters.add(new BasicNameValuePair("Query",QuerySharedpreferences.getString(QS,null)));
                    postParameters.add(new BasicNameValuePair("quearyOption",QuerySharedpreferences.getString(QT,null)));
                    postParameters.add(new BasicNameValuePair("QueryResult", ResultsSharedpreferences.getString(queary1result,null)));

                    postParameters.add(new BasicNameValuePair("QueryResultGraph", GraphValueSharedpreferences.getString(GV, null)));
                    json = jParser.makeHttpRequest(url_getQuery, "POST", postParameters);
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

                    Intent intent = new Intent(Contact.this, ThankyouActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Intent intent = new Intent(Contact.this, ThankyouActivity.class);
                    startActivity(intent);
                    finish();
                    c1 = 1;
                    // Toast.makeText(FeedbackActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // Toast.makeText(Login_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Contact.this, ThankyouActivity.class);
                startActivity(intent);
                finish();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            AndroidUtils.animateView(progressOverlay, View.GONE, 0, 200);
            if (c1 == 1) {

                Toast.makeText(Contact.this, "---Failed--- Retry Please or Check your Network Connection", Toast.LENGTH_SHORT).show();
            } else if (c0 == 1)
            {
                //  Toast.makeText(FeedbackActivity.this, "Welcome : "+UName, Toast.LENGTH_SHORT).show();
                Toast.makeText(Contact.this,"No query found", Toast.LENGTH_SHORT).show();
            }

            super.onPostExecute(aVoid);
        }
    }
    private class CouponTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            AndroidUtils.animateView(CouponOverlay, View.VISIBLE, 1.0f, 200);
            super.onPreExecute();
        }


        @Override
        protected Void doInBackground(Void... params) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    /*gson = new Gson();
                    Query = gson.fromJson(QuerySharedpreferences.getString(QS,null), ArrayList.class);
                    result=gson.fromJson(ResultsSharedpreferences.getString(queary1result,null), ArrayList.class);
                    GraphValue=gson.fromJson(GraphValueSharedpreferences.getString(GV, null), ArrayList.class);*/


                    String CName=userDetailsSharedPreference.getString(companykey, "na");
                    String BName=userDetailsSharedPreference.getString(branch, "na");

                    //   String mob=app.getString(KEY_NAME,null);
                    ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
                    // postParameters.add(new BasicNameValuePair("mob", mob));
                    postParameters.add(new BasicNameValuePair("companyname",CName ));
                    postParameters.add(new BasicNameValuePair("branchname",BName ));
                    // postParameters.add(new BasicNameValuePair("dateID", dateID));
                    //postParameters.add(new BasicNameValuePair("date", date));

                    postParameters.add(new BasicNameValuePair("email", contactDetails[0]));
                    postParameters.add(new BasicNameValuePair("phone", contactDetails[1]));
                    postParameters.add(new BasicNameValuePair("suggestion", contactDetails[2]));

                    postParameters.add(new BasicNameValuePair("Query",QuerySharedpreferences.getString(QS,null)));
                    postParameters.add(new BasicNameValuePair("quearyOption",QuerySharedpreferences.getString(QT,null)));
                    postParameters.add(new BasicNameValuePair("QueryResult", ResultsSharedpreferences.getString(queary1result,null)));

                    postParameters.add(new BasicNameValuePair("QueryResultGraph", GraphValueSharedpreferences.getString(GV, null)));
                    json = jParser.makeHttpRequest(url_getQuery, "POST", postParameters);
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

                    Intent intent = new Intent(Contact.this, ThankyouActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    sdb= getApplicationContext().openOrCreateDatabase(MyDatabase.DBNAME,MODE_PRIVATE,null);
                    sdb.execSQL("CREATE TABLE IF NOT EXISTS Result (email TEXT, phone TEXT, suggestion TEXT, Query TEXT, quearyOption TEXT, QueryResult TEXT,QueryResultGraph TEXT);");
                    ContentValues value = new ContentValues();
                    value.put("email", contactDetails[0]);
                    value.put("phone", contactDetails[1] );
                    value.put("suggestion", contactDetails[2]);
                    value.put("Query", QuerySharedpreferences.getString(QS,null));
                    value.put("quearyOption", QuerySharedpreferences.getString(QT,null));
                    value.put("QueryResult", ResultsSharedpreferences.getString(queary1result,null) );
                    value.put("QueryResultGraph", GraphValueSharedpreferences.getString(GV, null));
                    sdb.insert("Result",null, value);
                    Intent intent = new Intent(Contact.this, ThankyouActivity.class);
                    startActivity(intent);
                    finish();
                    c1 = 1;
                    // Toast.makeText(FeedbackActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // Toast.makeText(Login_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Contact.this, ThankyouActivity.class);
                startActivity(intent);
                finish();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            AndroidUtils.animateView(CouponOverlay, View.GONE, 0, 200);
            if (c1 == 1) {

                Toast.makeText(Contact.this, "---Failed--- Retry Please or Check your Network Connection", Toast.LENGTH_SHORT).show();
            } else if (c0 == 1)
            {
                //  Toast.makeText(FeedbackActivity.this, "Welcome : "+UName, Toast.LENGTH_SHORT).show();
                Toast.makeText(Contact.this,"No query found", Toast.LENGTH_SHORT).show();
            }

            super.onPostExecute(aVoid);
        }
    }

    }

