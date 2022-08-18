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

    default void notifyItemChanged(@IdRes int id, @NonNull int position) {
        try {
            RecyclerView recyclerView = ((BaseView) this).findViewById(id);
            recyclerView.getAdapter().notifyItemChanged(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
