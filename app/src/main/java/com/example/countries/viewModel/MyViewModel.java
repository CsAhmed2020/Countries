package com.example.countries.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.countries.model.Countries;
import com.example.countries.repository.MyRepo;

import java.util.ArrayList;

public class MyViewModel extends AndroidViewModel {
    private final MyRepo repository ;

    public MyViewModel(@NonNull Application application) {
        super(application);
        repository =new MyRepo(application);
    }

    public MutableLiveData<ArrayList<Countries>> loadData(){
        return repository.callAPI();
    }
}
