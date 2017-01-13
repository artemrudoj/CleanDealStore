package com.mipt.artem.cleandealstore.shoppingcart;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mipt.artem.cleandealstore.R;
import com.mipt.artem.cleandealstore.base.Presenter;
import com.mipt.artem.cleandealstore.base.RecyclerViewWithoutToolbarFragment;
import com.mipt.artem.cleandealstore.base.recycledviews.RecyclerViewBaseFragment;
import com.mipt.artem.cleandealstore.model.ItemInCart;
import com.mipt.artem.cleandealstore.utils.ItemsInCartDiffCallback;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class ShoppingCartBaseFragment extends RecyclerViewWithoutToolbarFragment<ItemInCart> implements ShoppingCartView,
        ItemsUpdateListener{

    @Bind(R.id.goods_rv)
    protected RecyclerView mShoppingCartRecyclerView;

    protected ShoppingCartAdapter mAdapter;

    public ShoppingCartBaseFragment() {
        // Required empty public constructor
    }


    protected void handleItemOnSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        long position = viewHolder.getAdapterPosition();
        ItemInCart item = mAdapter.getItemById((int) position);
        ((ShoppingCartBasePresenter)getPresenter()).moveItemToAnotherBasket(item);
        mAdapter.removeItem((int) position);
    }

    private Paint p = new Paint();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_cart_base, container, false);

        ButterKnife.bind(this, view);

        initProgressBarBuilder(mShoppingCartRecyclerView, view, getActivity());

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mShoppingCartRecyclerView.setLayoutManager(llm);

        initSwipe();
        return view;
    }

    abstract protected int getSwipeDirs();

    private void initSwipe() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, getSwipeDirs()) {


            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                handleItemOnSwiped(viewHolder, direction);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX > 0){
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mShoppingCartRecyclerView);
    }



    @Override
    public void showEmpty() {

    }

    protected abstract ShoppingCartAdapter createAdapter(List<ItemInCart> data);

    @Override
    public void showData(List<ItemInCart> data) {
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && parentFragment instanceof ItemsUpdateListener) {
            ((ItemsUpdateListener)parentFragment).onUpdateItems(data);
        } else {
            throw new IllegalArgumentException("parent fragment should implement ItemsUpdateListener");
        }
    }



    @Override
    public void onUpdateItems(List<ItemInCart> items) {
//        if (mAdapter == null) {
//            mAdapter = createAdapter(items);
//            mShoppingCartRecyclerView.setAdapter(mAdapter);
//        } else {
//            List<ItemInCart> oldItems = mAdapter.getItems();
//            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new ItemsInCartDiffCallback(oldItems, items));
//            mAdapter.setItems(items);
//            result.dispatchUpdatesTo(mAdapter);
//        }


        mAdapter = createAdapter(items);
        mShoppingCartRecyclerView.setAdapter(mAdapter);
        updatePrice(items);
    }

    @Override
    public void updatePrice(List<ItemInCart> items) {
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && parentFragment instanceof ItemsUpdateListener) {
            ((ItemsUpdateListener)parentFragment).updatePrice(items);
        } else {
            throw new IllegalArgumentException("parent fragment should implement ItemsUpdateListener");
        }
    }
}

