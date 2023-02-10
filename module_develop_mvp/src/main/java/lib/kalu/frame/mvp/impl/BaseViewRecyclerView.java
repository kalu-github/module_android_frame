package lib.kalu.frame.mvp.impl;

import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import lib.kalu.frame.mvp.BaseView;

@Keep
public interface BaseViewRecyclerView {

    default void notifyDataSetChanged(@IdRes int id) {
        try {
            RecyclerView recyclerView = ((BaseView) this).findViewById(id);
            recyclerView.getAdapter().notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void notifyDataRangeChanged(@IdRes int id) {
        try {
            RecyclerView recyclerView = ((BaseView) this).findViewById(id);
            int count = recyclerView.getAdapter().getItemCount();
            notifyItemRangeChanged(id, 0, count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void notifyItemRangeChanged(@IdRes int id, @NonNull int position) {
        notifyItemRangeChanged(id, position, 1);
    }

    default void notifyItemRangeChanged(@IdRes int id, int positionStart, int itemCount) {
        try {
            RecyclerView recyclerView = ((BaseView) this).findViewById(id);
            recyclerView.getAdapter().notifyItemRangeChanged(positionStart, itemCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void notifyItemRangeRemoved(@IdRes int id, @NonNull int position, @NonNull int count) {
        try {
            RecyclerView recyclerView = ((BaseView) this).findViewById(id);
            recyclerView.getAdapter().notifyItemRangeRemoved(position, count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void notifyItemRemoved(@IdRes int id, @NonNull int position) {
        try {
            RecyclerView recyclerView = ((BaseView) this).findViewById(id);
            recyclerView.getAdapter().notifyItemRemoved(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
