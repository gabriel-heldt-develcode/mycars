package com.example.mycars.view;

import static com.example.mycars.util.ImageUtil.getStringToBase64;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mycars.R;
import com.example.mycars.constants.DatabaseConstants;
import com.example.mycars.model.VehiclesModel;
import com.example.mycars.util.ImageUtil;
import com.example.mycars.viewmodel.NewCarsViewModel;

public class NewCarsActivity extends AppCompatActivity {

    private final ViewHolder mViewHolder = new ViewHolder();
    private NewCarsViewModel mViewModel;
    private static final int PICK_IMAGE = 0;
    private int mVehicleId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cars);

        this.mViewModel = new ViewModelProvider(this).get(NewCarsViewModel.class);

        this.mViewHolder.editModel = findViewById(R.id.edit_model);
        this.mViewHolder.editYear = findViewById(R.id.edit_year);
        this.mViewHolder.editPrice = findViewById(R.id.edit_price);
        this.mViewHolder.buttonSave = findViewById(R.id.button_save);

        this.mViewHolder.imagePicture = findViewById(R.id.image_view_picture);
        this.mViewHolder.imageUploadImage = findViewById(R.id.image_upload_image);

        this.setListeners();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.mVehicleId = bundle.getInt(DatabaseConstants.VEHICLEID);
            this.mViewModel.load(this.mVehicleId);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            Uri imageUri = data.getData();
            this.mViewHolder.imagePicture.setImageURI(imageUri);
            this.mViewHolder.imageUploadImage.setVisibility(View.INVISIBLE);
        }
    }

    private void setListeners() {
        this.mViewHolder.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSave();
            }
        });
        this.mViewHolder.imageUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        this.mViewModel.vehiclesModel.observe(this, new Observer<VehiclesModel>() {
            @Override
            public void onChanged(VehiclesModel vehiclesModel) {
                mViewHolder.editModel.setText(vehiclesModel.getModel());
                mViewHolder.editYear.setText(String.valueOf(vehiclesModel.getYear()));
                mViewHolder.editPrice.setText(String.valueOf(vehiclesModel.getPrice()));
                mViewHolder.imageUploadImage.setImageBitmap(ImageUtil.base64ToImage(vehiclesModel.getImage()));

            }
        });
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    private void handleSave() {
        VehiclesModel vehicle = new VehiclesModel();
        vehicle.setModel(this.mViewHolder.editModel.getText().toString());
        vehicle.setYear(Integer.parseInt(this.mViewHolder.editYear.getText().toString()));
        vehicle.setPrice(Double.parseDouble(this.mViewHolder.editPrice.getText().toString()));
        vehicle.setImage(getStringToBase64(this.mViewHolder.imagePicture.getDrawable()));
        vehicle.setId(this.mVehicleId);

        this.mViewModel.save(vehicle);
    }

    private static class ViewHolder {
        EditText editModel;
        EditText editYear;
        EditText editPrice;
        Button buttonSave;
        ImageView imagePicture;
        ImageButton imageUploadImage;
    }
}