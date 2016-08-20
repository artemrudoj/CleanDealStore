package com.mipt.artem.cleandealstore.rest.responcedata;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.transition.CircularPropagation;
import android.util.Pair;

import com.google.gson.annotations.SerializedName;
import com.mipt.artem.cleandealstore.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by artem on 20.08.16.
 */
public class ExtraInfo implements Parcelable {

    @SerializedName("volume")
    String volume;
    @SerializedName("item_type")
    String itemType;
    @SerializedName("manufacturer")
    String manufacturer;
    @SerializedName("country")
    String country;
    @SerializedName("hair_type")
    String hairType;
    @SerializedName("effect")
    String effect;
    @SerializedName("taste")
    String taste;
    @SerializedName("use")
    String use;

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHairType() {
        return hairType;
    }

    public void setHairType(String hairType) {
        this.hairType = hairType;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public Map<Integer, Pair<String, String>> toMap(Context context) {
        HashMap<Integer, Pair<String, String>> values = new HashMap<>();
        Integer i = 0;
        if (volume != null) {
            values.put(i, new Pair<String, String>(context.getString(R.string.volume), volume));
            i++;
        }
        if (itemType != null) {
            values.put(i, new Pair<String, String>(context.getString(R.string.item_type), itemType));
            i++;
        }
        if (manufacturer != null) {
            values.put(i, new Pair<String, String>(context.getString(R.string.manufacturer), manufacturer));
            i++;
        }
        if (country != null) {
            values.put(i, new Pair<String, String>(context.getString(R.string.country), country));
            i++;
        }
        if (hairType != null) {
            values.put(i, new Pair<String, String>(context.getString(R.string.hair_type), hairType));
            i++;
        }
        if (effect != null) {
            values.put(i, new Pair<String, String>(context.getString(R.string.effect), effect));
            i++;
        }
        if (taste != null) {
            values.put(i, new Pair<String, String>(context.getString(R.string.taste), taste));
            i++;
        }
        if (use != null) {
            values.put(i, new Pair<String, String>(context.getString(R.string.use), use));
            i++;
        }
        return values;
    }

    protected ExtraInfo(Parcel in) {
        volume = in.readString();
        itemType = in.readString();
        manufacturer = in.readString();
        country = in.readString();
        hairType = in.readString();
        effect = in.readString();
        taste = in.readString();
        use = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(volume);
        dest.writeString(itemType);
        dest.writeString(manufacturer);
        dest.writeString(country);
        dest.writeString(hairType);
        dest.writeString(effect);
        dest.writeString(taste);
        dest.writeString(use);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ExtraInfo> CREATOR = new Parcelable.Creator<ExtraInfo>() {
        @Override
        public ExtraInfo createFromParcel(Parcel in) {
            return new ExtraInfo(in);
        }

        @Override
        public ExtraInfo[] newArray(int size) {
            return new ExtraInfo[size];
        }
    };
}