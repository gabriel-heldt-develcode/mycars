package com.example.mycars.view.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycars.R;
import com.example.mycars.model.VehiclesModel;
import com.example.mycars.util.ImageUtil;
import com.example.mycars.view.listener.OnListClick;
import com.google.android.material.card.MaterialCardView;

public class VehiclesViewHolder extends RecyclerView.ViewHolder {

    private final MaterialCardView mCardView;
    private final ImageView mImageView;
    private final TextView mTextModel;
    private final TextView mTextYear;
    private final TextView mTextPrice;

    public VehiclesViewHolder(@NonNull View itemView) {
        super(itemView);

        this.mCardView = itemView.findViewById(R.id.cardView);
        this.mImageView = itemView.findViewById(R.id.item_image_view_picture);
        this.mTextModel = itemView.findViewById(R.id.text_model);
        this.mTextYear = itemView.findViewById(R.id.text_year);
        this.mTextPrice = itemView.findViewById(R.id.text_price);
    }

    public void bind(VehiclesModel vehicle, OnListClick listener) {
        this.mImageView.setImageBitmap(ImageUtil.base64ToImage(vehicle.getImage()));
        this.mTextModel.setText(vehicle.getModel());
        this.mTextYear.setText(String.valueOf(vehicle.getYear()));
        this.mTextPrice.setText(String.valueOf(vehicle.getPrice()));

        this.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(vehicle.getId());
            }
        });
    }
}