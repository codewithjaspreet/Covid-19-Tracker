package com.jaspreetsingh.CovidTracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.jaspreetsingh.travelmania.R;
import com.jaspreetsingh.CovidTracker.api.apiutilities;
import com.jaspreetsingh.CovidTracker.api.countrydata;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<countrydata> list;
    private ProgressDialog dialog;
    private EditText searchbar;
    String country = "India";
    private CountryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        searchbar = findViewById(R.id.searchbar);
        recyclerView = findViewById(R.id.countries);
        list = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
         adapter = new CountryAdapter(this,list);
        recyclerView.setAdapter(adapter);



        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        apiutilities.getapiInterface().getCountryData()
                .enqueue(new Callback<List<countrydata>>() {
                    @Override
                    public void onResponse(Call<List<countrydata>> call, Response<List<countrydata>> response) {
                        list.addAll(response.body());
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();

                    }

                    @Override
                    public void onFailure(Call<List<countrydata>> call, Throwable t) {
                        Toast.makeText(CountryActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });
    }

    private void filter(String text) {
        List<countrydata> filterlist = new ArrayList<>();
        for (countrydata items: list){
            if (items.getCountry().toLowerCase().contains(text.toLowerCase())){
                filterlist.add(items);
            }
        }
        adapter.filterlist(filterlist);
    }
}