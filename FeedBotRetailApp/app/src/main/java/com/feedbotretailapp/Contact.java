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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ServerLinkHttpClient.SimpleHttpClient;


/**
 * Created by Preetam on 24-Jun-16.
 */
public class Contact extends Activity {
    private static final String PREFER_NAME = "PratikriyaFeedbackPre";
    public static final String KEY_NAME = "name";


    private static final String APP_QUERY = "PratikriyaQuerySP1";
    public static final String QS = "QS";
    public static final String QTypeS = "QTypeS";
    public static final String QFocusS = "QFocusS";
    public static final String QSrlNoS = "QSrlNoS";

    private static final String APP_QUERY_RESULTS = "PratikriyaQueryResultsSP";
    public static final String queary1result = "queary1result";

   /* public static final String QOS1 = "QOS1";
    public static final String QOS2 = "QOS2";
    public static final String QOS3 = "QOS3";
    public static final String QOS4 = "QOS4";
    public static final String QOS5 = "QOS5";
    public static final String QOS6 = "QOS6";
    public static final String QOS7 = "QOS7";
    public static final String QOS8 = "QOS8";
    public static final String QOS9 = "QOS9";
    public static final String QOS10 = "QOS10";*/


    /*private static final String APP_QUERY_RESULTS = "PratikriyaQueryResultsSP";
    public static final String queary1result = "queary1result";
    public static final String queary2result = "queary2result";
    public static final String queary3result = "queary3result";
    public static final String queary4result = "queary4result";
    public static final String queary5result = "queary5result";
    public static final String queary6result = "queary6result";
    public static final String queary7result = "queary7result";
    public static final String queary8result = "queary8result";
    public static final String queary9result = "queary9result";
    public static final String queary10result = "queary10result";*/



    public static final String FeedbackApp = "FeedbackAppUserDetails" ;
    public static final String branch = "branchKey";
    public static final String fname = "fnameKey";
    public static final String lname = "lnameKey";
    public static final String Emailkey = "emailKey";
    public static final String companykey = "companykey";
    public static final String idkey = "idkey";

    SharedPreferences userDetailsSharedPreference;
    SharedPreferences QuerySharedpreferences;
    SharedPreferences ResultsSharedpreferences;
    SharedPreferences ResultsCollecterSharedpreferences;
    SharedPreferences app;
    int i,j,c;
    int currentIndex=0;
    int d=0;
    static String[] query;
    static String[] queryOption;
    static String[] queryResults;
    static int[] resultCollector;
    static int[] resultStoredSP;


    TextView tv;
    String cage="na";
    String csex="na";
    String cname="na",cemail="na",cphone="na",csuggestion="na";
    private String date,dateID,resp,errorMsg;
    Button a1,a2,a3,a4;
    //String Q1,Q2,Q3,Q4,Q5,Q6,Q7,Q8,Q9,Q10;


    static EditText[] contactEditText;
    static String[] contactDetails;
    static int id[] = {R.id.email, R.id.phone, R.id.suggestionBox};

    LinearLayout bot;

    Gson gson;
    ArrayList<String> Query,result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_page);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        bot=(LinearLayout)findViewById(R.id.bot);
        contactEditText = new EditText[3];
        contactDetails = new String[3];

        Typewriter writer = new Typewriter(this);
        //ln.addView(writer);
        // setContentView(writer);
        bot.addView(writer,-1,-1);


        //Add a character every 150ms
        writer.setCharacterDelay(75);
        writer.animateText("Thank you for your valuable feedback ! \n" +
                "we are happy that you found our service as good .\n" +
                "To help  improve our services please leave comment below. I personally assure to make it better.");
       /* I can see you found our p1 as bad and*/

        for (int i = 0; i < contactEditText.length; i++) {
            contactEditText[i] = (EditText) findViewById(id[i]);
        }

       /* query = new String[11];
        queryResults=new String[11];
        queryOption=new String[11];
        resultCollector=new int[11];
        resultStoredSP=new int[11];*/

        userDetailsSharedPreference   = getSharedPreferences(FeedbackApp, Context.MODE_PRIVATE);

        ResultsSharedpreferences   = getSharedPreferences(APP_QUERY_RESULTS, Context.MODE_PRIVATE);

        QuerySharedpreferences = getSharedPreferences(APP_QUERY, Context.MODE_PRIVATE);
        // ResultsCollecterSharedpreferences = getSharedPreferences(RESULTCollecter, Context.MODE_PRIVATE);
        app=getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE);

        /*query[0]=  QuerySharedpreferences.getString(QS1, "na");
        query[1]=  QuerySharedpreferences.getString(QS2,"na");
        query[2]=  QuerySharedpreferences.getString(QS3,"na");
        query[3]=  QuerySharedpreferences.getString(QS4,"na");
        query[4]=  QuerySharedpreferences.getString(QS5,"na");
        query[5]=  QuerySharedpreferences.getString(QS6,"na");
        query[6]=  QuerySharedpreferences.getString(QS7,"na");
        query[7]=  QuerySharedpreferences.getString(QS8, "na");
        query[8]=  QuerySharedpreferences.getString(QS9, "na");
        query[9]=  QuerySharedpreferences.getString(QS10, "na");

        queryOption[0]=  QuerySharedpreferences.getString(QOS1,null);
        queryOption[1]=  QuerySharedpreferences.getString(QOS2,null);
        queryOption[2]=  QuerySharedpreferences.getString(QOS3,null);
        queryOption[3]=  QuerySharedpreferences.getString(QOS4,null);
        queryOption[4]=  QuerySharedpreferences.getString(QOS5,null);
        queryOption[5]=  QuerySharedpreferences.getString(QOS6,null);
        queryOption[6]=  QuerySharedpreferences.getString(QOS7,null);
        queryOption[7]=  QuerySharedpreferences.getString(QOS8,null);
        queryOption[8]=  QuerySharedpreferences.getString(QOS9,null);
        queryOption[9]=  QuerySharedpreferences.getString(QOS10,null);
*/
        Query=new ArrayList<String>();
        result=new ArrayList<String>();



    }

    public void contactsubmit(View view) {

       /* EditText name=(EditText)findViewById(R.id.name);
        EditText email=(EditText)findViewById(R.id.email);
        EditText phone=(EditText)findViewById(R.id.phone);
        EditText suggestionBox=(EditText)findViewById(R.id.suggestionBox);*/
        // recordResult();


       /* editor.putInt("result1",resultCollector[0] );editor.putInt("result2", resultCollector[1]);
        editor.putInt("result3",resultCollector[2] );editor.putInt("result4",resultCollector[3] );
        editor.putInt("result5",resultCollector[4] );editor.putInt("result6",resultCollector[5] );
        editor.putInt("result7",resultCollector[6] );editor.putInt("result8",resultCollector[7] );
        editor.putInt("result9",resultCollector[8] );editor.putInt("result10",resultCollector[9] );

        editor.commit();*/




        for (int i = 0; i < contactEditText.length; i++) {
            contactEditText[i] = (EditText) findViewById(id[i]);
        }
        for (int j = 0; j < contactDetails.length; j++) {
            contactDetails[j] = contactEditText[j].getText().toString().trim();

            if (contactDetails[j].equals("")){
                contactDetails[j]="empty";
            }
        }

        try {
            Calendar c = Calendar.getInstance();
            System.out.println("Current time => " + c.getTime());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateID = dateFormat.format(new Date()); // Find todays date

            SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
            date = df2.format(c.getTime());

            /*cname = name.getText().toString().trim();

            cemail = email.getText().toString().trim();
            cphone = phone.getText().toString().trim();
            csuggestion = suggestionBox.getText().toString().trim();*/

             /*if(cname.isEmpty()== true){
                cname="empty";
            }else if (cemail.equals("")||cemail.equals(null)){
                cemail="empty";
            }else if (cphone.equals("")||cphone.equals(null)){
                cphone="empty";
            }else if (csuggestion.equals("")||csuggestion.equals(null)){
                csuggestion="empty";
            }*/


        } catch (Exception e) {

        }finally {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    gson = new Gson();
                    Query = gson.fromJson(QuerySharedpreferences.getString(QS,null), ArrayList.class);
                    result=gson.fromJson(ResultsSharedpreferences.getString(QS,null), ArrayList.class);


                    String mob=app.getString(KEY_NAME,null);
                    ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
                    postParameters.add(new BasicNameValuePair("mob", mob));
                    postParameters.add(new BasicNameValuePair("companyname", userDetailsSharedPreference.getString(companykey, "na")));
                    postParameters.add(new BasicNameValuePair("branchname", userDetailsSharedPreference.getString(branch, "na")));
                    postParameters.add(new BasicNameValuePair("dateID", dateID));
                    postParameters.add(new BasicNameValuePair("date", date));

                    postParameters.add(new BasicNameValuePair("email", contactDetails[0]));
                    postParameters.add(new BasicNameValuePair("phone", contactDetails[1]));
                    postParameters.add(new BasicNameValuePair("suggestion", contactDetails[2]));

                    postParameters.add(new BasicNameValuePair("queary",QuerySharedpreferences.getString(QS,null)));
                    postParameters.add(new BasicNameValuePair("result1", ResultsSharedpreferences.getString(queary1result, "na")));

                   /* postParameters.add(new BasicNameValuePair("queary1", query[0]));
                    postParameters.add(new BasicNameValuePair("queary2", query[1]));
                    postParameters.add(new BasicNameValuePair("queary3", query[2]));
                    postParameters.add(new BasicNameValuePair("queary4", query[3]));
                    postParameters.add(new BasicNameValuePair("queary5", query[4]));
                    postParameters.add(new BasicNameValuePair("queary6", query[5]));
                    postParameters.add(new BasicNameValuePair("queary7", query[6]));
                    postParameters.add(new BasicNameValuePair("queary8", query[7]));
                    postParameters.add(new BasicNameValuePair("queary9", query[8]));
                    postParameters.add(new BasicNameValuePair("queary10", query[9]));
                    postParameters.add(new BasicNameValuePair("result1", ResultsSharedpreferences.getString(queary1result, "na")));
                    postParameters.add(new BasicNameValuePair("result2", ResultsSharedpreferences.getString(queary2result, "na")));
                    postParameters.add(new BasicNameValuePair("result3", ResultsSharedpreferences.getString(queary3result, "na")));
                    postParameters.add(new BasicNameValuePair("result4", ResultsSharedpreferences.getString(queary4result, "na")));
                    postParameters.add(new BasicNameValuePair("result5", ResultsSharedpreferences.getString(queary5result, "na")));
                    postParameters.add(new BasicNameValuePair("result6", ResultsSharedpreferences.getString(queary6result, "na")));
                    postParameters.add(new BasicNameValuePair("result7", ResultsSharedpreferences.getString(queary7result, "na")));
                    postParameters.add(new BasicNameValuePair("result8", ResultsSharedpreferences.getString(queary8result, "na")));
                    postParameters.add(new BasicNameValuePair("result9", ResultsSharedpreferences.getString(queary9result, "na")));
                    postParameters.add(new BasicNameValuePair("result10", ResultsSharedpreferences.getString(queary10result, "na")));

*/
                    String response = null;
                    try {
                        response = SimpleHttpClient
                                .executeHttpPost(
                                        "http://ikvoxserver.78kuyxr39b.us-west-2.elasticbeanstalk.com/feedbackdata.do",
                                        postParameters);

                        String res = response.toString();
                        System.out.println(res);
                        resp = res.replaceAll("\\s+", "");

                    } catch (Exception e) {
                        e.printStackTrace();
                        errorMsg = e.getMessage();
                    }
                }
            }).start();
            try {
                //setContentView(R.layout.thnxs);
                Thread.sleep(5000);
                /*SharedPreferences.Editor editor1 = ResultsSharedpreferences.edit();
                editor1.clear();*/
                //  Intent intent = new Intent(StartFeedbackCollection.this, FeedbackActivity.class);
                Intent intent = new Intent(Contact.this, ThankyouActivity.class);
                startActivity(intent);
                finish();

            } catch (Exception e) {
                Toast.makeText(Contact.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }

        }

    }
}
