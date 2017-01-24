package com.dansoonie.glviewexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
  private List<Item> itemList = new ArrayList<>();

  public void setItems(List<Item> itemList) {
    this.itemList = itemList;
  }

  private Item getItem(int position) {
    return itemList.get(position);
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    holder.bindTo(getItem(position), position);
  }

  @Override
  public int getItemCount() {
    return itemList.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private Item item;

    public ViewHolder(View itemView) {
      super(itemView);
      title = (TextView) itemView.findViewById(R.id.title);
      title.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          onItemClicked(view.getContext(), item);
        }
      });
    }

    public void bindTo(Item item, int position) {
      this.item = item;

      title.setText(item.getName());
      if ((position % 2) == 0) {
        title.setBackgroundResource(R.color.gray);
      } else {
        title.setBackgroundResource(R.color.white);
      }
    }

    void onItemClicked(Context context,
                       Item item) {

      Class klass = item.getKlass();
      if (Activity.class.isAssignableFrom(klass)) {
        context.startActivity(new Intent(context, klass));
      }
    }
  }
}
