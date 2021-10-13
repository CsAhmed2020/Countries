package com.example.countries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.countries.adapter.CountryAdapter;
import com.example.countries.model.Countries;
import com.example.countries.viewModel.MyViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CountryAdapter.OnNoteList {

    CountryAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Countries> countriesArrayListt;
    MyViewModel myViewModel;
    EditText et_search;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        et_search = findViewById(R.id.et_search);
        countriesArrayListt = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        initRecycler();

        myViewModel.loadData().observe(this, new Observer<ArrayList<Countries>>() {
            @Override
            public void onChanged(ArrayList<Countries> countries) {
                if (countries != null) {
                    countriesArrayListt = countries;
                    adapter.updateList(countriesArrayListt);
                }
            }
        });
        Log.d(TAG, "Adel "+countriesArrayListt.size());
        search();

    }

    private void search() {
        et_search.setVisibility(View.VISIBLE);

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }


            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });
    }

    private void filter(String str) {
        ArrayList<Countries> filteredlist = new ArrayList<>();
        for(Countries countries : countriesArrayListt){
            if(countries.getName().toLowerCase().contains(str.toLowerCase())){
                filteredlist.add(countries);
            }

        }

        adapter.filteredlist(filteredlist);
    }

    private void initRecycler() {
        adapter = new CountryAdapter(this,this,countriesArrayListt,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnnoteClick(Countries userClass) {
        Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
        intent.putExtra("name",userClass.getName());
        intent.putExtra("capital",userClass.getCapital());
        intent.putExtra("flag",userClass.getFlag());
        intent.putExtra("population",userClass.getPopulation());
        intent.putExtra("region",userClass.getRegion());
        intent.putExtra("subregion",userClass.getSubregion());
        intent.putExtra("languages",userClass.getLanguages().toString());
        intent.putExtra("borders",userClass.getBorders().toString());

        startActivity(intent);
    }
}
