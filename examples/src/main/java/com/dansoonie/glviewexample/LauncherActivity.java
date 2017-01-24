package com.dansoonie.glviewexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class LauncherActivity extends AppCompatActivity {
  private ItemAdapter adapter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_launcher);

    adapter = new ItemAdapter();
    adapter.setItems(createItems());

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(adapter);
  }

  private static List<Item> createItems() {
    List<Item> items = new LinkedList<>();
    items.add(new Item("GLSurfaceView Example", GLSurfaceViewExample.class));
    return items;
  }
}