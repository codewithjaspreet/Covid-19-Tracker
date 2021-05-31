package com.jaspreetsingh.CovidTracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.jaspreetsingh.travelmania.R;
import com.jaspreetsingh.CovidTracker.api.apiutilities;
import com.jaspreetsingh.CovidTracker.api.countrydata;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView totalconfirm,date,todayconfirmed,totalrecover,todayrecover,totaldeath,todaydeath,totaltest,totalactive;
    private List<countrydata> list;
    PieChart pieChart;
    String country = "India";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        if (getIntent().getStringExtra("country")!=null){
            country = getIntent().getStringExtra("country");

        }
        list = new ArrayList<>();

                                init();
                                TextView cname = findViewById(R.id.cname);
                                cname.setText(country);
                                cname.setOnClickListener(v ->
                                        startActivity(new Intent(MainActivity.this,CountryActivity.class)));
                                apiutilities.getapiInterface().getCountryData()
                                        .enqueue(new Callback<List<countrydata>>() {
                                            @Override
                                            public void onResponse(Call<List<countrydata>> call, Response<List<countrydata>> response) {
                                                list.addAll(response.body());

                                                for (int i = 0;i<list.size();i++){
                                                    if (list.get(i).getCountry().equals(country)){
                                                        int confirm =Integer.parseInt(list.get(i).getCases());
                                                        int active =Integer.parseInt(list.get(i).getActive());
                                                        int recovered =Integer.parseInt(list.get(i).getRecovered());
                                                        int death =Integer.parseInt(list.get(i).getDeaths());

                                totalactive.setText(NumberFormat.getInstance().format(active));
                                totalrecover.setText(NumberFormat.getInstance().format(recovered));
                                totaldeath.setText(NumberFormat.getInstance().format(death));
                                totalconfirm.setText(NumberFormat.getInstance().format(confirm));
                                todaydeath.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodaydeaths())));
                                todayconfirmed.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodaycases())));
                                todayrecover.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayrecovered())));
                                totaltest.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTests())));

                                settext(list.get(i).getUpdated());


                                pieChart.addPieSlice(new PieModel("Confirmed",confirm,getResources().getColor(R.color.yellow)));
                                pieChart.addPieSlice(new PieModel("Active",active,getResources().getColor(R.color.blue)));
                                pieChart.addPieSlice(new PieModel("recovered",recovered,getResources().getColor(R.color.green_pie)));
                                pieChart.addPieSlice(new PieModel("death",death,getResources().getColor(R.color.red_pie)));
                                pieChart.startAnimation();

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<countrydata>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @SuppressLint("SetTextI18n")
    private void settext(String updated) {
        @SuppressLint("SimpleDateFormat") DateFormat format = new SimpleDateFormat("MMM dd, yyy");

        long millisec  = Long.parseLong(updated);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisec);

        date.setText("Updated at " + format.format(calendar.getTime()));
    }

    private void init() {
        totalconfirm = findViewById(R.id.totalconfirm);
        todayconfirmed = findViewById(R.id.todayconfirmed);
        totalrecover = findViewById(R.id.totalrecovered);
        todayrecover = findViewById(R.id.todayrecovered);
        totaldeath = findViewById(R.id.totaldeath);
        todaydeath = findViewById(R.id.todaydeath);
        pieChart = findViewById(R.id.Pie_Chart);
        date = findViewById(R.id.date);
        totaltest = findViewById(R.id.totaltest);
        totalactive = findViewById(R.id.totalactive);
    }
}