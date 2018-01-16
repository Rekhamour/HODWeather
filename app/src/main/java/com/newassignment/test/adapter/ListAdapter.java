package com.newassignment.test.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.newassignment.retrofitexample.R;
import com.newassignment.test.view.Main2Activity;
import com.newassignment.test.model.WeatherDaily;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    List<WeatherDaily> list;
    Context ctx;


    public ListAdapter(Context ctx, List<WeatherDaily> list) {
        this.list = list;
        this.ctx = ctx;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.setIsRecyclable(false);

        holder.temprature.setText(String.valueOf(list.get(position).getTemp().getMax()));
        holder.Humidity.setText(String.valueOf(list.get(position).getTemp().getHumidity()));
        holder.country.setText(String.valueOf(list.get(position).getName()));
        holder.weather.setText(String.valueOf(list.get(position).getWeatherlist().get(0).getMain()));
        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, Main2Activity.class);
                intent.putExtra("object", (Serializable) list);
                ctx.startActivity(intent);
            }
        });
        if (list.get(position).getWeatherlist().get(0).getMain().equalsIgnoreCase("mist")) {
            holder.image.setImageResource(R.drawable.sunny);
        } else if (list.get(position).getWeatherlist().get(0).getMain().equalsIgnoreCase("cloudy")) {
            holder.image.setImageResource(R.drawable.clear);

        } else if (list.get(position).getWeatherlist().get(0).getMain().equalsIgnoreCase("rainy")) {
            holder.image.setImageResource(R.drawable.rainy);

        } else {
            holder.image.setImageResource(R.drawable.clear);

        }


        ;

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.temprature)
        TextView temprature;
        @BindView(R.id.row)
        LinearLayout row;
        @BindView(R.id.weather)
        TextView weather;

        @BindView(R.id.country)
        TextView country;

        @BindView(R.id.image)
        ImageView image;

        @BindView(R.id.day)
        TextView Humidity;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);



        }
    }
}


