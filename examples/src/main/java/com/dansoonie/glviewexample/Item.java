package com.dansoonie.glviewexample;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
  private final String name;
  private final Class klass;

  public Item(String name,
              Class<?> klass) {

    this.name = name;
    this.klass = klass;
  }

  protected Item(Parcel in) {
    name = in.readString();
    klass = (Class) in.readSerializable();
  }

  public Class getKlass() {
    return klass;
  }

  public String getName() {
    return name;
  }

  public static final Creator<Item> CREATOR = new Creator<Item>() {
    @Override
    public Item createFromParcel(Parcel in) {
      return new Item(in);
    }

    @Override
    public Item[] newArray(int size) {
      return new Item[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(name);
    parcel.writeSerializable(klass);
  }
}
