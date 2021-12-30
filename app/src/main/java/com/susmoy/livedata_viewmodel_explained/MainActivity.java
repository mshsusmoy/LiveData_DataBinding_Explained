package com.susmoy.livedata_viewmodel_explained;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.susmoy.livedata_viewmodel_explained.ViewModel.MyActivityViewModel;
import com.susmoy.livedata_viewmodel_explained.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MyActivityViewModel viewModel = new ViewModelProvider(this).get(MyActivityViewModel.class);

        viewModel.GetSeconds().observe(this, item -> {
            binding.textSeconds.setText(item.toString());
        });
        viewModel.finished.observe(this, item -> {
            Toast.makeText(this, "Finished", Toast.LENGTH_SHORT).show();
        });

        binding.btnStart.setOnClickListener(v -> {
            String timer = binding.inputMilliseconds.getText().toString();
            if (!timer.isEmpty() || timer.length() > 4) {
                viewModel.timerValue.setValue(Long.parseLong(timer));
                viewModel.StartTimer();
            } else {
                Toast.makeText(this, "Invalid Timer Value", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnStop.setOnClickListener(v -> {
            binding.textSeconds.setText("0");
            binding.inputMilliseconds.setText("0");
            viewModel.StopTimer();
        });
    }


}