package com.feedbotretailapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Preetam on 01-Jun-16.
 */
public class StartFeedbackCollection extends Activity {

    private static final String APP_GRAPHVALUE = "APP_GRAPHVALUE";
    public static final String GV = "GV";

    private static final String APP_QUERY = "PratikriyaQuerySP1";
    public static final String QS = "QS";
    public static final String QT = "QT";
    public static final String QFK = "QFK";

    private static final String APP_QUERY_RESULTS = "PratikriyaQueryResultsSP";
    public static final String queary1result = "queary1result";

    SharedPreferences QuerySharedpreferences;
    SharedPreferences ResultsSharedpreferences;
    SharedPreferences GraphValueSharedpreferences;
    TextView tv;
    Button a1;
    ImageView next;

    int CurrentIndex;
    static String Q, O, FK;
    ArrayList<String> QLIST, QOPTION, QFOCUSKEYWORD, QRESULT ,GRAPHVALUE;
    LinearLayout CardOptions;
    static int resultSeeker, resultHeart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout_btm);
        CurrentIndex = 0;
        tv = (TextView) findViewById(R.id.tv);

        CardOptions = (LinearLayout) findViewById(R.id.CardOptions);

        a1 = (Button) findViewById(R.id.a1);
        next = (ImageView) findViewById(R.id.next);
        a1.setText("GO");
        next.setVisibility(View.INVISIBLE);
        QLIST = new ArrayList<>();
        QOPTION = new ArrayList<>();
        QFOCUSKEYWORD = new ArrayList<>();
        QRESULT = new ArrayList<>();
        GRAPHVALUE = new ArrayList<>();

        ResultsSharedpreferences = getSharedPreferences(APP_QUERY_RESULTS, Context.MODE_PRIVATE);
        QuerySharedpreferences = getSharedPreferences(APP_QUERY, Context.MODE_PRIVATE);
        GraphValueSharedpreferences = getSharedPreferences(APP_GRAPHVALUE, Context.MODE_PRIVATE);
        Q = QuerySharedpreferences.getString(QS, null);
        O = QuerySharedpreferences.getString(QT, null);
        FK = QuerySharedpreferences.getString(QFK, null);


        Gson gson = new Gson();
        QLIST = gson.fromJson(Q, ArrayList.class);
        QOPTION = gson.fromJson(O, ArrayList.class);
        QFOCUSKEYWORD = gson.fromJson(FK, ArrayList.class);


        if (QLIST.isEmpty()) {
            Toast.makeText(StartFeedbackCollection.this, "No Query Found ----- Add Some Query to Display", Toast.LENGTH_SHORT).show();
        }
        try {
            // Toast.makeText(StartFeedbackCollection.this,QLIST.get(0), Toast.LENGTH_SHORT).show();
            // Toast.makeText(StartFeedbackCollection.this,QOPTION.get(0), Toast.LENGTH_SHORT).show();
            //  Toast.makeText(StartFeedbackCollection.this,QFOCUSKEYWORD .get(0), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

        }

    }

    public void nextQuery1(View view) {

        if (QOPTION.size() == CurrentIndex) {

            resultstore();

            Intent intent = new Intent(StartFeedbackCollection.this, Contact.class);
            startActivity(intent);
            finish();

        } else if (QOPTION.get(CurrentIndex).equals("1")) {
            tv.setText(QLIST.get(CurrentIndex));
            CardOptions.removeAllViews();
            LinearLayout l1 = new LinearLayout(getApplicationContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
            l1.setOrientation(LinearLayout.HORIZONTAL);
            l1.setGravity(Gravity.CENTER);
            l1.setWeightSum(1);
            l1.setLayoutParams(layoutParams);

            final Button yes = new Button(getApplicationContext());
            yes.setBackgroundColor(Color.GRAY);
            yes.setTextColor(Color.BLACK);
            yes.setText("YES");
            yes.setBackground(getDrawable(R.drawable.yourbuttonbackground));
            yes.setGravity(Gravity.CENTER);

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    QRESULT.add(yes.getText().toString().trim());
                    GRAPHVALUE.add("1");
                    nextQuery1(v);

                }
            });
            LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
            buttonLayoutParams.setMargins(10, 10, 10, 10);
            buttonLayoutParams.weight= (float) 0.5;
            yes.setLayoutParams(buttonLayoutParams);
            yes.setWidth(50);
            final Button no = new Button(getApplicationContext());

            no.setBackgroundColor(Color.GRAY);
            no.setTextColor(Color.BLACK);
            no.setText("NO");
            no.setGravity(Gravity.CENTER);
            no.setBackground(getDrawable(R.drawable.yourbuttonbackground));
            no.setLayoutParams(buttonLayoutParams);
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    QRESULT.add(no.getText().toString().trim());
                    GRAPHVALUE.add("0");
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
            final Button bad = new Button(getApplicationContext());
            bad.setBackgroundColor(Color.GRAY);
            bad.setTextColor(Color.BLACK);
            bad.setText("BAD");
            bad.setBackground(getDrawable(R.drawable.yourbuttonbackground));
            bad.setGravity(Gravity.CENTER);
            bad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    QRESULT.add(bad.getText().toString().trim());
                    GRAPHVALUE.add("0");
                    nextQuery1(v);
                }
            });
            LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
            buttonLayoutParams.setMargins(10, 10, 10, 10);
            buttonLayoutParams.weight= (float) 0.5;
            bad.setLayoutParams(buttonLayoutParams);
            final Button good = new Button(getApplicationContext());
            good.setBackgroundColor(Color.GRAY);
            good.setTextColor(Color.BLACK);
            good.setText("GOOD");
            good.setBackground(getDrawable(R.drawable.yourbuttonbackground));
            good.setGravity(Gravity.CENTER);
            good.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    QRESULT.add(good.getText().toString().trim());
                    GRAPHVALUE.add("1");
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
            final Button vgood = new Button(getApplicationContext());
            vgood.setBackgroundColor(Color.GRAY);
            vgood.setTextColor(Color.BLACK);
            vgood.setText("VERY GOOD");
            vgood.setBackground(getDrawable(R.drawable.yourbuttonbackground));
            vgood.setGravity(Gravity.CENTER);
            vgood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    QRESULT.add(vgood.getText().toString().trim());
                    GRAPHVALUE.add("1");
                    nextQuery1(v);
                }
            });
            vgood.setLayoutParams(buttonLayoutParams);
            final Button awesome = new Button(getApplicationContext());
            awesome.setBackgroundColor(Color.GRAY);
            awesome.setTextColor(Color.BLACK);
            awesome.setText("AWESOME");
            awesome.setBackground(getDrawable(R.drawable.yourbuttonbackground));
            awesome.setGravity(Gravity.CENTER);
            awesome.setLayoutParams(buttonLayoutParams);
            awesome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    QRESULT.add(awesome.getText().toString().trim());
                    GRAPHVALUE.add("1");
                    nextQuery1(v);
                }
            });
            l2.addView(vgood);
            l2.addView(awesome);
            l.addView(l1);
            l.addView(l2);
            CardOptions.addView(l);
            CurrentIndex++;
        } else if (QOPTION.get(CurrentIndex).equals("3")) {
            CardOptions.removeAllViews();
            next.setVisibility(View.VISIBLE);
            tv.setText(QLIST.get(CurrentIndex));
            LinearLayout l = new LinearLayout(getApplicationContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
            l.setOrientation(LinearLayout.VERTICAL);
            l.setGravity(Gravity.CENTER);
            l.setLayoutParams(layoutParams);

            SeekBar sb = new SeekBar(getApplicationContext());
            sb.setMax(10);
            sb.setBackgroundColor(Color.rgb(255, 255, 255));
            LinearLayout.LayoutParams sbparam = new LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);

            final TextView textView = new TextView(getApplicationContext());
            textView.setText("0");
            textView.setTextSize(23);
          //  textView.setTextColor(Color.rgb(33, 33, 32));
            textView.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams textParam = new LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
            textParam.setMargins(10, 10, 10, 10);

            sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                    // Toast.makeText(StartFeedbackCollection.this, String.valueOf(progress), Toast.LENGTH_SHORT).show();
                    int n1 = Math.round(progress);
                    resultSeeker = n1;
                    textView.setText(String.valueOf(n1));

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    //   View v = new View(getApplication());

                    //nextQuery1(v);

                }
            });
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    QRESULT.add(String.valueOf(resultSeeker));
                    if (resultSeeker > 5) {
                        GRAPHVALUE.add("1");
                    } else {
                        GRAPHVALUE.add("0");
                    }
                    nextQuery1(v);
                }
            });

            textView.setLayoutParams(textParam);
            sb.setLayoutParams(sbparam);

            l.addView(textView);
            l.addView(sb);
            CardOptions.addView(l);
            CurrentIndex++;

        } else if (QOPTION.get(CurrentIndex).equals("4")) {

            CardOptions.removeAllViews();
            tv.setText(QLIST.get(CurrentIndex));
            next.setVisibility(View.VISIBLE);

            View v = getLayoutInflater().inflate(R.layout.rating_bar, null);
            RatingBar progress = (RatingBar) v.findViewById(R.id.ratingbar);
            progress.setNumStars(10);

            final TextView textView = new TextView(getApplicationContext());
            textView.setText("0");
            textView.setTextSize(23);
            //textView.setTextColor(Color.rgb(33, 33, 32));
            textView.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams textParam = new LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
            textParam.setMargins(10, 40, 10, 20);

            progress.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                    Float n = rating * 1;
                    int n1 = Math.round(n);
                    textView.setText(Integer.toString(n1));
                    resultHeart = n1;
                }

            });


            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    QRESULT.add(String.valueOf(resultHeart));
                    if (resultHeart > 5) {
                        GRAPHVALUE.add("1");
                    } else {
                        GRAPHVALUE.add("0");
                    }

                    nextQuery1(v);
                }
            });

            CardOptions.addView(v);
            CardOptions.addView(textView);
            CurrentIndex++;
        }

    }


    public void resultstore() {

        SharedPreferences.Editor editor = ResultsSharedpreferences.edit();
        editor.putString(queary1result, QRESULT.toString());
        editor.commit();

        SharedPreferences.Editor editor1 = GraphValueSharedpreferences.edit();
        editor1.putString(GV, GRAPHVALUE.toString());
        editor1.commit();

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
*/

/*
   public void contactsubmit(View view) {





       *//**//* EditText name=(EditText)findViewById(R.id.name);
        EditText email=(EditText)findViewById(R.id.email);
        EditText phone=(EditText)findViewById(R.id.phone);
        EditText suggestionBox=(EditText)findViewById(R.id.suggestionBox);*//**//*
       // recordResult();


       *//**//* editor.putInt("result1",resultCollector[0] );editor.putInt("result2", resultCollector[1]);
        editor.putInt("result3",resultCollector[2] );editor.putInt("result4",resultCollector[3] );
        editor.putInt("result5",resultCollector[4] );editor.putInt("result6",resultCollector[5] );
        editor.putInt("result7",resultCollector[6] );editor.putInt("result8",resultCollector[7] );
        editor.putInt("result9",resultCollector[8] );editor.putInt("result10",resultCollector[9] );

        editor.commit();*//**//*




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

            *//**//*cname = name.getText().toString().trim();

            cemail = email.getText().toString().trim();
            cphone = phone.getText().toString().trim();
            csuggestion = suggestionBox.getText().toString().trim();*//**//*

             *//**//*if(cname.isEmpty()== true){
                cname="empty";
            }else if (cemail.equals("")||cemail.equals(null)){
                cemail="empty";
            }else if (cphone.equals("")||cphone.equals(null)){
                cphone="empty";
            }else if (csuggestion.equals("")||csuggestion.equals(null)){
                csuggestion="empty";
            }*//**//*


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
                *//**//*SharedPreferences.Editor editor1 = ResultsSharedpreferences.edit();
                editor1.clear();*//**//*
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



