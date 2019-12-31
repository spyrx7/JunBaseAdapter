package com.lemonjun.junbaseadapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lemonjun.junbaseadapter.adapter.DemoAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> data  = new ArrayList<>();

    private DemoAdapter adapter;

    private RecyclerView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = findViewById(R.id.list);

        initAdapter();
    }

    private void initAdapter(){

        data.add("1");
        data.add("2");
        data.add("3");
        data.add("4");
        data.add("5");
        data.add("6");

        mList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DemoAdapter(this,R.layout.layout_item,data);
        //adapter.bindToRecyclerView(mList);
        mList.setAdapter(adapter);

        adapter.setmEmptyLayout(R.layout.layout_empty);
    }


}
