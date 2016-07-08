package com.feedbotretailapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Preetam on 01-Jun-16.
 */
public class StartFeedbackCollection extends Activity {
    private static final String PREFER_NAME = "PratikriyaFeedbackPre";
    public static final String KEY_NAME = "name";


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

    /*private static final String RESULTCollecter = "RESULTCollecter";
    public static final int result1 = 1;
    public static final int result2 = 2;
    public static final String result3= "result3";
    public static final String result4 = "result4";
    public static final String result5 = "result5";
    public static final String result6 = "result6";
    public static final String result7= "result7";
    public static final String result8 = "result8";
    public static final String result9 = "result9";
    public static final String result10 = "result10";*/




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
    int CurrentIndex;


    static EditText[] contactEditText;
    static String[] contactDetails;
    static int id[] = {R.id.email, R.id.phone, R.id.suggestionBox};
    static String Q,O,FK;
    ArrayList<String> QLIST,QOPTION,QFOCUSKEYWORD;
    JSONArray QLISTJSON,QOPTIONJSON,QFOCUSKEYWORDJSON;
    LinearLayout CardOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout_btm);
        CurrentIndex=0;
        tv=(TextView)findViewById(R.id.tv);
        CardOptions=(LinearLayout)findViewById(R.id.CardOptions);

        contactEditText = new EditText[4];
        contactDetails = new String[4];

        a1=(Button)findViewById(R.id.a1);
       // a2=(Button)findViewById(R.id.a2);
        //a3=(Button)findViewById(R.id.a3);
        //a4=(Button)findViewById(R.id.a4);
        //a1.setVisibility(View.INVISIBLE);
//        a2.setVisibility(View.INVISIBLE);
  //      a3.setVisibility(View.INVISIBLE);
    //    a4.setVisibility(View.INVISIBLE);
        a1.setText("GO");
        query = new String[11];
        queryResults=new String[11];
        queryOption=new String[11];
        resultCollector=new int[11];
        resultStoredSP=new int[11];

        QLIST=new ArrayList<String>();
        QOPTION=new ArrayList<String>();
        QFOCUSKEYWORD=new ArrayList<String>();

        userDetailsSharedPreference   = getSharedPreferences(FeedbackApp, Context.MODE_PRIVATE);

        ResultsSharedpreferences   = getSharedPreferences(APP_QUERY_RESULTS, Context.MODE_PRIVATE);

        QuerySharedpreferences = getSharedPreferences(APP_QUERY, Context.MODE_PRIVATE);
       // ResultsCollecterSharedpreferences = getSharedPreferences(RESULTCollecter, Context.MODE_PRIVATE);
        app=getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE);

      //  SharedPreferences.Editor editor = ResultsCollecterSharedpreferences.edit();
        Q=QuerySharedpreferences.getString(QS,null);
        O=QuerySharedpreferences.getString(QT, null);
        FK=QuerySharedpreferences.getString(QFK, null);



        Gson gson = new Gson();
         QLIST= gson.fromJson(Q, ArrayList.class);
        QOPTION=gson.fromJson(O, ArrayList.class);
        QFOCUSKEYWORD=gson.fromJson(FK, ArrayList.class);


        if(QLIST.isEmpty()){
            Toast.makeText(StartFeedbackCollection.this, "No Query Found Add Some Query to Display", Toast.LENGTH_SHORT).show();
        }try{
            Toast.makeText(StartFeedbackCollection.this,QLIST.get(0), Toast.LENGTH_SHORT).show();
            Toast.makeText(StartFeedbackCollection.this,QOPTION.get(0), Toast.LENGTH_SHORT).show();
            Toast.makeText(StartFeedbackCollection.this,QFOCUSKEYWORD .get(0), Toast.LENGTH_SHORT).show();
        }catch (Exception e){

        }

/*

        query[0]=  QuerySharedpreferences.getString(QS1, "na");
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





    }

    public void nextQuery1(View view) {

        /*for (int j = 0; j < queryResults.length; j++) {
            queryResults[j] = "empty";*/
            if (QOPTION.get(CurrentIndex).equals("1")) {
                tv.setText(QLIST.get(CurrentIndex));
                CardOptions.removeAllViews();
                LinearLayout l1 = new LinearLayout(getApplicationContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
                l1.setOrientation(LinearLayout.HORIZONTAL);
                l1.setGravity(Gravity.CENTER);
                l1.setLayoutParams(layoutParams);

                Button yes = new Button(getApplicationContext());
                yes.setBackgroundColor(Color.GRAY);
                yes.setTextColor(Color.BLACK);
                yes.setText("Yes");
                yes.setGravity(Gravity.CENTER);
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nextQuery1(v);
                    }
                });
                LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
                buttonLayoutParams.setMargins(10, 10, 10, 10);
                yes.setLayoutParams(buttonLayoutParams);

                Button no = new Button(getApplicationContext());

                no.setBackgroundColor(Color.GRAY);
                no.setTextColor(Color.BLACK);
                no.setText("No");
                no.setGravity(Gravity.CENTER);
                no.setLayoutParams(buttonLayoutParams);
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nextQuery1(v);
                    }
                });
                l1.addView(yes);
                l1.addView(no);
                CardOptions.addView(l1);
                CurrentIndex++;
            } else if (QOPTION.get(CurrentIndex).equals("2")) {
                CardOptions.removeAllViews();
                tv.setText(QLIST.get(CurrentIndex));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
                LinearLayout l = new LinearLayout(getApplicationContext());
                l.setOrientation(LinearLayout.VERTICAL);
                l.setGravity(Gravity.CENTER_HORIZONTAL);
                l.setLayoutParams(layoutParams);
                LinearLayout l1 = new LinearLayout(getApplicationContext());
                l1.setLayoutParams(layoutParams);
                l1.setOrientation(LinearLayout.HORIZONTAL);
                l1.setGravity(Gravity.CENTER);
                Button bad = new Button(getApplicationContext());
                bad.setBackgroundColor(Color.GRAY);
                bad.setTextColor(Color.BLACK);
                bad.setText("Bad");
                bad.setGravity(Gravity.CENTER);
                bad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nextQuery1(v);
                    }
                });
                LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
                buttonLayoutParams.setMargins(10, 10, 10, 10);
                bad.setLayoutParams(buttonLayoutParams);
                Button good = new Button(getApplicationContext());
                good.setBackgroundColor(Color.GRAY);
                good.setTextColor(Color.BLACK);
                good.setText("Good");
                good.setGravity(Gravity.CENTER);
                good.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nextQuery1(v);
                    }
                });
                good.setLayoutParams(buttonLayoutParams);
                l1.addView(bad);
                l1.addView(good);
                LinearLayout l2 = new LinearLayout(getApplicationContext());
                l2.setOrientation(LinearLayout.HORIZONTAL);
                l2.setGravity(Gravity.CENTER);
                l2.setLayoutParams(layoutParams);
                Button vgood = new Button(getApplicationContext());
                vgood.setBackgroundColor(Color.GRAY);
                vgood.setTextColor(Color.BLACK);
                vgood.setText("Very Good");
                vgood.setGravity(Gravity.CENTER);
                vgood.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nextQuery1(v);
                    }
                });
                vgood.setLayoutParams(buttonLayoutParams);
                Button awesome = new Button(getApplicationContext());
                awesome.setBackgroundColor(Color.GRAY);
                awesome.setTextColor(Color.BLACK);
                awesome.setText("Awesome");
                awesome.setGravity(Gravity.CENTER);
                awesome.setLayoutParams(buttonLayoutParams);
                awesome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nextQuery1(v);
                    }
                });
                l2.addView(vgood);
                l2.addView(awesome);
                l.addView(l1);
                l.addView(l2);
                CardOptions.addView(l);
            } else if (QOPTION.get(CurrentIndex).equals("3")) {
                CardOptions.removeAllViews();
                tv.setText(QLIST.get(CurrentIndex));
                LinearLayout l = new LinearLayout(getApplicationContext());
                l.setOrientation(LinearLayout.VERTICAL);
                l.setGravity(Gravity.CENTER_VERTICAL);

                SeekBar sb = new SeekBar(getApplicationContext());
                sb.setMax(10);
                sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        seekBar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                nextQuery1(v);
                            }
                        });
                    }
                });

                l.addView(sb);
                CardOptions.addView(l);
            } else if (QOPTION.get(CurrentIndex).equals("4")) {
                CardOptions.removeAllViews();
                tv.setText(QLIST.get(CurrentIndex));
                LinearLayout l = new LinearLayout(getApplicationContext());
                l.setOrientation(LinearLayout.VERTICAL);
                l.setGravity(Gravity.CENTER_VERTICAL);
                /*RatingBar rb= new RatingBar(context);
                Rect bounds = rb.getProgressDrawable().getBounds();
                Drawable d =rb.getResources().getDrawable(R.drawable.custom_bar);
                rb.setProgressDrawable(d);
                rb.getProgressDrawable().setBounds(bounds);
                l.addView(rb);*/
                LayoutInflater layoutInflater = (LayoutInflater)
                        getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                l.addView(layoutInflater.inflate(R.layout.rating_bar, CardOptions, false));
                RatingBar rating = (RatingBar) findViewById(R.id.ratingbar);
                rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        ratingBar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                nextQuery1(v);
                            }
                        });
                    }
                });
                CardOptions.addView(l);
            }

    }























    /*public void nextQuery1(View view) {
      //STRONGLY_AGREE
        for (i=0;i<query.length;i++){

            if (currentIndex>=10){

                Toast.makeText(StartFeedbackCollection.this, "thank you", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.contact_page);

            }else if (query[(currentIndex)].equals("empty")){
                resultstore();
                Intent intent= new Intent(StartFeedbackCollection.this,Contact.class);
                startActivity(intent);
              //  Toast.makeText(StartFeedbackCollection.this, "Need refresh ----No query", Toast.LENGTH_SHORT).show();
                //setContentView(R.layout.contact_page);

                break;
            }else if (queryOption[currentIndex].equals("2")) {
                queryResults[currentIndex]=a1.getText().toString().trim();
                tv.setText(query[(currentIndex++)]);
                a3.setText("YES");
                a4.setText("NO");
                a3.setVisibility(View.VISIBLE);
                a4.setVisibility(View.VISIBLE);
                a1.setVisibility(View.INVISIBLE);
                a2.setVisibility(View.INVISIBLE);
                //a3.setVisibility(View.VISIBLE);
                //a4.setVisibility(View.VISIBLE);

            }else{
                queryResults[currentIndex]=a1.getText().toString().trim();
                tv.setText(query[(currentIndex++)]);
                a1.setText("STRONGLY_AGREE");
                a2.setText("AGREE");
                a3.setText("NEUTRAL");
                a4.setText("DISAGREE");
                a1.setVisibility(View.VISIBLE);
                a2.setVisibility(View.VISIBLE);
                a3.setVisibility(View.VISIBLE);
                a4.setVisibility(View.VISIBLE);
            }break;
        }

    }

    public void nextQuery2(View view) {
//AGREE 
        for (i=0;i<query.length;i++) {

            if (currentIndex >= 10) {

                Toast.makeText(StartFeedbackCollection.this, "thank you", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.contact_page);

            } else if (query[i].equals("") || query[(currentIndex)].equals("empty")) {
                resultstore();
                Intent intent= new Intent(StartFeedbackCollection.this,Contact.class);
                startActivity(intent);
                //  Toast.makeText(StartFeedbackCollection.this, "Need refresh ----No query", Toast.LENGTH_SHORT).show();
                //setContentView(R.layout.contact_page);

                break;
            }else if (queryOption[currentIndex].equals("2")) {
                queryResults[currentIndex]=a2.getText().toString().trim();
                tv.setText(query[(currentIndex++)]);
                a3.setText("YES");
                a4.setText("NO");
                a3.setVisibility(View.VISIBLE);
                a4.setVisibility(View.VISIBLE);
                a1.setVisibility(View.INVISIBLE);
                a2.setVisibility(View.INVISIBLE);
            } else {
                queryResults[currentIndex] = a2.getText().toString().trim();
                tv.setText(query[(currentIndex++)]);
                //a1.setText("AGREE");
                a1.setText("STRONGLY_AGREE");
                a2.setText("AGREE");
                a3.setText("NEUTRAL");
                a4.setText("DISAGREE");
                a1.setVisibility(View.VISIBLE);
                a2.setVisibility(View.VISIBLE);
                a3.setVisibility(View.VISIBLE);
                a4.setVisibility(View.VISIBLE);
            }
            break;
        }
    }

    public void nextQuery3(View view) {
//NEUTRAL
        for (i=0;i<query.length;i++) {

            if (currentIndex >= 10) {

                Toast.makeText(StartFeedbackCollection.this, "thank you", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.contact_page);

            } else if (query[i].equals("") || query[(currentIndex)].equals("empty")) {
                resultstore();
                //  Toast.makeText(StartFeedbackCollection.this, "Need refresh ----No query", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(StartFeedbackCollection.this,Contact.class);
                startActivity(intent);

                break;
            }else if (queryOption[currentIndex].equals("2")) {
                queryResults[currentIndex]=a3.getText().toString().trim();
                tv.setText(query[(currentIndex++)]);
                a3.setText("YES");
                a4.setText("NO");
                a3.setVisibility(View.VISIBLE);
                a4.setVisibility(View.VISIBLE);
                a1.setVisibility(View.INVISIBLE);
                a2.setVisibility(View.INVISIBLE);

            } else {
                queryResults[currentIndex] = a3.getText().toString().trim();
                tv.setText(query[(currentIndex++)]);

                *//*a1.setText("NEUTRAL");
                a2.setVisibility(View.VISIBLE);
                a3.setVisibility(View.VISIBLE);
                a4.setVisibility(View.VISIBLE);*//*

                a1.setText("STRONGLY_AGREE");
                a2.setText("AGREE");
                a3.setText("NEUTRAL");
                a4.setText("DISAGREE");
                a1.setVisibility(View.VISIBLE);
                a2.setVisibility(View.VISIBLE);
                a3.setVisibility(View.VISIBLE);
                a4.setVisibility(View.VISIBLE);
            }
            break;
        }

    }

    public void nextQuery4(View view) {
//DISAGREE
        for (i=0;i<query.length;i++) {

            if (currentIndex >= 10) {

                Toast.makeText(StartFeedbackCollection.this, "thank you", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.contact_page);

            } else if (query[i].equals("") || query[(currentIndex)].equals("empty")) {
                resultstore();
                Intent intent= new Intent(StartFeedbackCollection.this,Contact.class);
                startActivity(intent);
                //  Toast.makeText(StartFeedbackCollection.this, "Need refresh ----No query", Toast.LENGTH_SHORT).show();
               // setContentView(R.layout.contact_page);
                break;
            }else if (queryOption[currentIndex].equals("2")) {
                queryResults[currentIndex]=a4.getText().toString().trim();
                tv.setText(query[(currentIndex++)]);
                a3.setText("YES");
                a4.setText("NO");
                a3.setVisibility(View.VISIBLE);
                a4.setVisibility(View.VISIBLE);
                a1.setVisibility(View.INVISIBLE);
                a2.setVisibility(View.INVISIBLE);

            } else {
                queryResults[currentIndex] = a4.getText().toString().trim();
                tv.setText(query[(currentIndex++)]);
               *//* a1.setText("DISAGREE");
                a2.setVisibility(View.VISIBLE);
                a3.setVisibility(View.VISIBLE);
                a4.setVisibility(View.VISIBLE);*//*

                a1.setText("STRONGLY_AGREE");
                a2.setText("AGREE");
                a3.setText("NEUTRAL");
                a4.setText("DISAGREE");
                a1.setVisibility(View.VISIBLE);
                a2.setVisibility(View.VISIBLE);
                a3.setVisibility(View.VISIBLE);
                a4.setVisibility(View.VISIBLE);
            }
            break;
        }

    }

public void resultstore(){
    SharedPreferences.Editor editor = ResultsSharedpreferences.edit();

    editor.putString(queary1result,queryResults[1]);
    editor.putString(queary2result, queryResults[2]);
    editor.putString(queary3result, queryResults[3]);
    editor.putString(queary4result, queryResults[4]);
    editor.putString(queary5result, queryResults[5]);
    editor.putString(queary6result, queryResults[6]);
    editor.putString(queary7result, queryResults[7]);
    editor.putString(queary8result, queryResults[8]);
    editor.putString(queary9result, queryResults[9]);
    editor.putString(queary10result, queryResults[10]);
    // editor.putString(queary10result, "AGREE");
    editor.commit();
}


   public void contactsubmit(View view) {





       *//* EditText name=(EditText)findViewById(R.id.name);
        EditText email=(EditText)findViewById(R.id.email);
        EditText phone=(EditText)findViewById(R.id.phone);
        EditText suggestionBox=(EditText)findViewById(R.id.suggestionBox);*//*
       // recordResult();


       *//* editor.putInt("result1",resultCollector[0] );editor.putInt("result2", resultCollector[1]);
        editor.putInt("result3",resultCollector[2] );editor.putInt("result4",resultCollector[3] );
        editor.putInt("result5",resultCollector[4] );editor.putInt("result6",resultCollector[5] );
        editor.putInt("result7",resultCollector[6] );editor.putInt("result8",resultCollector[7] );
        editor.putInt("result9",resultCollector[8] );editor.putInt("result10",resultCollector[9] );

        editor.commit();*//*




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

            *//*cname = name.getText().toString().trim();

            cemail = email.getText().toString().trim();
            cphone = phone.getText().toString().trim();
            csuggestion = suggestionBox.getText().toString().trim();*//*

             *//*if(cname.isEmpty()== true){
                cname="empty";
            }else if (cemail.equals("")||cemail.equals(null)){
                cemail="empty";
            }else if (cphone.equals("")||cphone.equals(null)){
                cphone="empty";
            }else if (csuggestion.equals("")||csuggestion.equals(null)){
                csuggestion="empty";
            }*//*


        } catch (Exception e) {

        }finally {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    String mob=app.getString(KEY_NAME,null);
                    ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
                    postParameters.add(new BasicNameValuePair("mob", mob));
                    postParameters.add(new BasicNameValuePair("companyname", userDetailsSharedPreference.getString(companykey, "na")));
                    postParameters.add(new BasicNameValuePair("branchname", userDetailsSharedPreference.getString(branch, "na")));
                    postParameters.add(new BasicNameValuePair("dateID", dateID));
                    postParameters.add(new BasicNameValuePair("date", date));
                    postParameters.add(new BasicNameValuePair("name", contactDetails[0]));
                    postParameters.add(new BasicNameValuePair("email", contactDetails[1]));
                    postParameters.add(new BasicNameValuePair("phone", contactDetails[2]));
                    postParameters.add(new BasicNameValuePair("suggestion", contactDetails[3]));
                    postParameters.add(new BasicNameValuePair("age", cage));
                    postParameters.add(new BasicNameValuePair("sex", csex));

                    postParameters.add(new BasicNameValuePair("queary1", query[0]));
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
                *//*SharedPreferences.Editor editor1 = ResultsSharedpreferences.edit();
                editor1.clear();*//*
              //  Intent intent = new Intent(StartFeedbackCollection.this, FeedbackActivity.class);
                Intent intent = new Intent(StartFeedbackCollection.this, ThankyouActivity.class);
                startActivity(intent);
                finish();

            } catch (Exception e) {
                Toast.makeText(StartFeedbackCollection.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }

        }

    }
*/



}
