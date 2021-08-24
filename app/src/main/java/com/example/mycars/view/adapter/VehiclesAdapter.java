package com.example.mycars.view.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycars.R;
import com.example.mycars.model.VehiclesModel;
import com.example.mycars.view.listener.OnListClick;
import com.example.mycars.view.viewholder.VehiclesViewHolder;

import java.util.ArrayList;
import java.util.List;

public class VehiclesAdapter extends RecyclerView.Adapter<VehiclesViewHolder>{

    private List<VehiclesModel> mList = new ArrayList<>();
    private OnListClick mListener;

    //bora testar o github

    @NonNull
    @Override
    public VehiclesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_vehicle_list, parent, false);
        return new VehiclesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehiclesViewHolder holder, int position) {
        holder.bind(this.mList.get(position), this.mListener);
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void attachList(List<VehiclesModel> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public void attachListener(OnListClick listener) {
        this.mListener = listener;
    }
}
