package com.mipt.artem.cleandealstore.goods.item.info;

import android.content.Context;

import com.mipt.artem.cleandealstore.R;


/**
 * Created by artem on 20.08.16.
 */
public class ExtraInfoConverted {

    private Context mContext;
    private String mParameterName;
    private String mParameterInfo;
    private int mIconId;


    // // TODO: 20.08.16 must be found correct icons
    private int getIconId(String s) {
        if (s.equals(mContext.getString(R.string.volume))) {
            return R.drawable.vector_parameter;
        } else if (s.equals(mContext.getString(R.string.item_type))) {
            return R.drawable.vector_parameter;
        }else if (s.equals(mContext.getString(R.string.manufacturer))) {
            return R.drawable.vector_parameter;
        }else if (s.equals(mContext.getString(R.string.country))) {
            return R.drawable.vector_parameter;
        }else if (s.equals(mContext.getString(R.string.hair_type))) {
            return R.drawable.vector_parameter;
        }else if (s.equals(mContext.getString(R.string.effect))) {
            return R.drawable.vector_parameter;
        }else if (s.equals(mContext.getString(R.string.taste))) {
            return R.drawable.vector_parameter;
        }else if (s.equals(mContext.getString(R.string.use))) {
            return R.drawable.vector_parameter;
        }else {
            throw new IllegalStateException("incorrect extra info type " + s);
        }
    }

    public ExtraInfoConverted(String parameterName, String parameterInfo, Context context) {
        this.mContext = context;
        this.mParameterName = parameterName + ":";
        this.mParameterInfo = parameterInfo;
        this.mIconId = getIconId(parameterName);
    }

    public String getmParameterName() {
        return mParameterName;
    }

    public String getmParameterInfo() {
        return mParameterInfo;
    }

    public int getmIconId() {
        return mIconId;
    }
}
