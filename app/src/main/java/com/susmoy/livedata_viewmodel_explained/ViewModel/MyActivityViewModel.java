package com.susmoy.livedata_viewmodel_explained.ViewModel;

import android.os.CountDownTimer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyActivityViewModel extends ViewModel {
    private CountDownTimer timer;
    private MutableLiveData<Integer> _seconds = new MutableLiveData<Integer>();
    public MutableLiveData<Boolean> finished = new MutableLiveData<Boolean>();
    public MutableLiveData<Long> timerValue = new MutableLiveData<Long>();


    public LiveData<Integer> GetSeconds() {
        return _seconds;
    }

    public void StartTimer() {
        timer = new CountDownTimer(timerValue.getValue(), 1000) {
            @Override
            public void onTick(long l) {
                long timeLeft = l / 1000;
                _seconds.setValue(Integer.valueOf(String.valueOf(timeLeft)));
            }

            @Override
            public void onFinish() {
                finished.setValue(true);
            }
        }.start();
    }

    public void StopTimer() {
        timer.cancel();
    }
}
