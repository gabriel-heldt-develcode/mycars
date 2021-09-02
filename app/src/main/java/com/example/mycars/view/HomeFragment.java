package com.example.mycars.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycars.R;
import com.example.mycars.constants.DatabaseConstants;
import com.example.mycars.databinding.FragmentHomeBinding;
import com.example.mycars.model.Feedback;
import com.example.mycars.model.VehiclesModel;
import com.example.mycars.view.adapter.VehiclesAdapter;
import com.example.mycars.view.listener.OnListClick;
import com.example.mycars.viewmodel.HomeViewModel;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private final ViewHolder mViewHolder = new ViewHolder();
    private final VehiclesAdapter mAdapter = new VehiclesAdapter();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        com.example.mycars.databinding.FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.mViewHolder.recyclerVehicles = root.findViewById(R.id.recycler_list);
        this.mViewHolder.recyclerVehicles.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mViewHolder.recyclerVehicles.setAdapter(this.mAdapter);

        OnListClick listener = new OnListClick() {
            @Override
            public void onClick(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt(DatabaseConstants.VEHICLEID, id);
                Intent intent = new Intent(getContext(), NewCarsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onDelete(int id) {
                mViewModel.delete(id);
                mViewModel.getList();
            }
        };
        this.mAdapter.attachListener(listener);

        this.observers();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mViewModel.getList();
    }

    private void observers() {
        this.mViewModel.vehicleList.observe(getViewLifecycleOwner(), new Observer<List<VehiclesModel>>() {
            @Override
            public void onChanged(List<VehiclesModel> list) {
                mAdapter.attachList(list);
            }
        });
        this.mViewModel.feedback.observe(getViewLifecycleOwner(), new Observer<Feedback>() {
            @Override
            public void onChanged(Feedback feedback) {
                Toast.makeText(getContext(), feedback.getMassage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static class ViewHolder {
        RecyclerView recyclerVehicles;
    }
}