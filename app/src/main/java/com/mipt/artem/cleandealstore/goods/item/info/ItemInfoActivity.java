package com.mipt.artem.cleandealstore.goods.item.info;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.base.BaseActivity;
import com.mipt.artem.cleandealstore.rest.responcedata.Item;

public class ItemInfoActivity extends BaseActivity {
    private Item mItem;


    public static void goTo(Item item, Activity context) {
        Intent intent = new Intent(context, ItemInfoActivity.class);
        intent.putExtra(Item.EXTRA_ITEM, item);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_without_toolbar);
        mItem = Item.getItemFromBundle(getIntent().getExtras());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, ItemInfoFragment.newInstance(mItem)).commit();
        }
    }


}
