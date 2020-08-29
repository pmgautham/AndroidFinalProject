package com.example.griloenoyul;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MatchDetailActivity extends AppCompatActivity {

    TextView mTeam1Tv, mTeam2Tv, mMatchStatusTv, mScoreTv, mDescriptionTv, mDateTv;
    Button back,players,summary;
    boolean isActive;
    int count = 0;

    private String url = "https://cricapi.com/api/cricketScore/?apikey=8bjesvvq7KTkJoIs6aZznT0MDLm1&unique_id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle("Match Details");

        //actionBar.setDisplayShowHomeEnabled(true);
        //actionBar.setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        String id = intent.getStringExtra("match_id");
        String date = intent.getStringExtra("date");
        url = url + id;

        mTeam1Tv = findViewById(R.id.team1Tv);
        mTeam2Tv = findViewById(R.id.team2Tv);
        mMatchStatusTv = findViewById(R.id.matchStatusTv);
        mScoreTv = findViewById(R.id.scoreTv);
       // mDescriptionTv = findViewById(R.id.descriptionTv);
        mDateTv = findViewById(R.id.dateTv);
        back = findViewById(R.id.backmatchdetails);
        players = findViewById(R.id.viewplayersmatch);
        summary = findViewById(R.id.matchsummarydetails);

        mDateTv.setText(date);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MatchDetailActivity.this, MainActivity.class);
                startActivity(intent1);
                isActive = false;
            }
        });
        isActive = true;
        loadData();
    }

    private void loadData() {

        count++;
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();

                try {
                    JSONObject jsonObject  = new JSONObject(response);

                    String team1 = jsonObject.getString("team-1");
                    String team2 = jsonObject.getString("team-2");
                    Log.i("Score",""+team1);
                    Log.i("Score",""+team2);
                    String matchStatus = jsonObject.getString("matchStarted");
                    if(matchStatus.equals("true"))
                    {
                        matchStatus  = "Match In Progress";
                    }
                    else
                    {
                        matchStatus = "Match not yet started";
                    }

                    mTeam1Tv.setText(team1);
                    mTeam2Tv.setText(team2);
                    mMatchStatusTv.setText(matchStatus);

                    try {
                        // these values will be received only if the match is started
                        // so we are enclosing in separate try catch
                        String score = jsonObject.getString("score");
                        Log.i("Score",""+score);
                        String description = jsonObject.getString("description");

                        mScoreTv.setText(score);
                        //mDescriptionTv.setText(description);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(MatchDetailActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(MatchDetailActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MatchDetailActivity.this, "Error: "+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        //Toast.makeText(this, ""+count, Toast.LENGTH_LONG).show();

        if (isActive)
        {
            refresh(20000);
        }

    }

    private void refresh(int i) {
        final Handler handler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                loadData();
            }
        };

        handler.postDelayed(runnable,i);
    }

    public void matchsummary(View view) {
        Intent i = new Intent(MatchDetailActivity.this,MatchSummaryActivity.class);
        startActivity(i);
    }

    public void viewplayers(View view) {
        Intent inte = new Intent(MatchDetailActivity.this,PlayersActivity.class);
        startActivity(inte);
    }


    /*@Override
    public boolean onSupportNavigateUp() {
        onBackPressed();// go to previous activity on back pressed from action bar too
        return super.onSupportNavigateUp();
    }*/
}