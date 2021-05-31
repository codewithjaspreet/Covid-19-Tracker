package com.jaspreetsingh.CovidTracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jaspreetsingh.CovidTracker.api.countrydata;
import com.jaspreetsingh.travelmania.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.countryViewholder> {
Context context;
List<countrydata> list;

    public CountryAdapter(Context context, List<countrydata> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public countryViewholder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.country_item_layout,parent,false);

        return new countryViewholder(view);
    }

    public void filterlist(List<countrydata> filterlist){
        list = filterlist;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull CountryAdapter.countryViewholder holder, int position) {
        countrydata countrydata = list.get(position);
        holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(countrydata.getCases())));
        holder.countryname.setText(countrydata.getCountry());
        holder.sno.setText(String.valueOf(position+1));
        Map<String,String> img =countrydata.getCountryInfo();
        Glide.with(context).load(img.get("flag")).into(holder.imageView);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context,MainActivity.class);
            intent.putExtra("country",countrydata.getCountry());
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class countryViewholder extends RecyclerView.ViewHolder {

        private TextView sno,countryname,cases;
        private ImageView imageView;

        public countryViewholder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
           sno = itemView.findViewById(R.id.sno);
            countryname = itemView.findViewById(R.id.countrynaame);
            cases = itemView.findViewById(R.id.cases);
            imageView = itemView.findViewById(R.id.flag);
        }
    }
}
