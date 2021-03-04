package com.aditya.facebookui.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.facebookui.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter {
    private List<String> localDataSet;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;

        public ViewHolder(View view) {
            super(view);
            linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        }

    }




    public RecyclerAdapter(List<String> dataSet) {
        localDataSet = dataSet;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_tab, viewGroup, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

interface NewsItemClicked {
    void onItemClicked(String item);
}
