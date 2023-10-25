package lib.kalu.frame.mvp.impl;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import lib.kalu.frame.mvp.BaseView;
import lib.kalu.frame.mvp.util.MvpUtil;

@Keep
public interface BaseViewRecyclerView {

    default boolean isAdapterEmpty(@IdRes int id) {
        try {
            return getAdapetItemCount(id) <= 0;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRecyclerView => isAdapterEmpty => " + e.getMessage());
            return true;
        }
    }

    default boolean isAdapterEmpty(@NonNull View viewGroup, @IdRes int id) {
        try {
            return getAdapetItemCount(viewGroup, id) <= 0;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRecyclerView => isAdapterEmpty => " + e.getMessage());
            return true;
        }
    }

    default int getAdapetItemCount(@IdRes int id) {
        try {
            RecyclerView recyclerView = ((BaseView) this).findViewById(id);
            return recyclerView.getAdapter().getItemCount();
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRecyclerView => getAdapetItemCount => " + e.getMessage());
            return 0;
        }
    }

    default int getAdapetItemCount(@NonNull View viewGroup, @IdRes int id) {
        try {
            RecyclerView recyclerView = viewGroup.findViewById(id);
            return recyclerView.getAdapter().getItemCount();
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRecyclerView => getAdapetItemCount => " + e.getMessage());
            return 0;
        }
    }

    default void notifyDataSetChanged(@IdRes int id) {
        try {
            RecyclerView recyclerView = ((BaseView) this).findViewById(id);
            recyclerView.getAdapter().notifyDataSetChanged();
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRecyclerView => notifyDataSetChanged => " + e.getMessage());
        }
    }

    default void notifyDataSetChanged(@NonNull View viewGroup, @IdRes int id) {
        try {
            RecyclerView recyclerView = viewGroup.findViewById(id);
            recyclerView.getAdapter().notifyDataSetChanged();
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRecyclerView => notifyDataSetChanged => " + e.getMessage());
        }
    }

    default void notifyDataRangeChanged(@IdRes int id) {
        try {
            RecyclerView recyclerView = ((BaseView) this).findViewById(id);
            int count = recyclerView.getAdapter().getItemCount();
            notifyItemRangeChanged(id, 0, count);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRecyclerView => notifyDataRangeChanged => " + e.getMessage());
        }
    }

    default void notifyDataRangeChanged(@NonNull View viewGroup, @IdRes int id) {
        try {
            RecyclerView recyclerView = viewGroup.findViewById(id);
            int count = recyclerView.getAdapter().getItemCount();
            notifyItemRangeChanged(id, 0, count);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRecyclerView => notifyDataRangeChanged => " + e.getMessage());
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
            MvpUtil.logE("BaseViewRecyclerView => notifyItemRangeChanged => " + e.getMessage());
        }
    }

    default void notifyItemRangeChanged(@NonNull View viewGroup, @IdRes int id, @NonNull int position) {
        notifyItemRangeChanged(viewGroup, id, position, 1);
    }

    default void notifyItemRangeChanged(@NonNull View viewGroup, @IdRes int id, int positionStart, int itemCount) {
        try {
            RecyclerView recyclerView = viewGroup.findViewById(id);
            recyclerView.getAdapter().notifyItemRangeChanged(positionStart, itemCount);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRecyclerView => notifyItemRangeChanged => " + e.getMessage());
        }
    }

    default void notifyItemRangeRemoved(@IdRes int id, @NonNull int position, @NonNull int count) {
        try {
            RecyclerView recyclerView = ((BaseView) this).findViewById(id);
            recyclerView.getAdapter().notifyItemRangeRemoved(position, count);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRecyclerView => notifyItemRangeRemoved => " + e.getMessage());
        }
    }

    default void notifyItemRangeRemoved(@NonNull View viewGroup, @IdRes int id, @NonNull int position, @NonNull int count) {
        try {
            RecyclerView recyclerView = viewGroup.findViewById(id);
            recyclerView.getAdapter().notifyItemRangeRemoved(position, count);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRecyclerView => notifyItemRangeRemoved => " + e.getMessage());
        }
    }

    default void notifyItemRangeRemovedAll(@IdRes int id) {
        try {
            RecyclerView recyclerView = ((BaseView) this).findViewById(id);
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (null == adapter)
                throw new Exception("adapter error: null");
            int itemCount = adapter.getItemCount();
            recyclerView.getAdapter().notifyItemRangeRemoved(0, itemCount);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRecyclerView => notifyItemRangeRemovedAll => " + e.getMessage());
        }
    }

    default void notifyItemRangeRemovedAll(@NonNull View viewGroup, @IdRes int id) {
        try {
            RecyclerView recyclerView = viewGroup.findViewById(id);
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (null == adapter)
                throw new Exception("adapter error: null");
            int itemCount = adapter.getItemCount();
            recyclerView.getAdapter().notifyItemRangeRemoved(0, itemCount);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRecyclerView => notifyItemRangeRemovedAll => " + e.getMessage());
        }
    }

    default void notifyItemRemoved(@IdRes int id, @NonNull int position) {
        try {
            RecyclerView recyclerView = ((BaseView) this).findViewById(id);
            recyclerView.getAdapter().notifyItemRemoved(position);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRecyclerView => notifyItemRemoved => " + e.getMessage());
        }
    }

    default void notifyItemRemoved(@NonNull View viewGroup, @IdRes int id, @NonNull int position) {
        try {
            RecyclerView recyclerView = viewGroup.findViewById(id);
            recyclerView.getAdapter().notifyItemRemoved(position);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRecyclerView => notifyItemRemoved => " + e.getMessage());
        }
    }

    default void notifyItemRangeInserted(@IdRes int id, @NonNull int position, int length) {
        try {
            RecyclerView recyclerView = ((BaseView) this).findViewById(id);
            recyclerView.getAdapter().notifyItemRangeInserted(position, length);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRecyclerView => notifyItemRangeInserted => " + e.getMessage());
        }
    }

    default void notifyItemRangeInserted(@NonNull View viewGroup, @IdRes int id, @NonNull int position, int length) {
        try {
            RecyclerView recyclerView = viewGroup.findViewById(id);
            recyclerView.getAdapter().notifyItemRangeInserted(position, length);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRecyclerView => notifyItemRangeInserted => " + e.getMessage());
        }
    }

    default void notifyItemRangeInserted(@IdRes int id, int length) {
        try {
            RecyclerView recyclerView = ((BaseView) this).findViewById(id);
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            int itemCount = adapter.getItemCount();
            int position = itemCount - 1;
            if (position < 0) {
                position = 0;
            }
            adapter.notifyItemRangeInserted(position, length);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRecyclerView => notifyItemRangeInserted => " + e.getMessage());
        }
    }

    default void notifyItemRangeInserted(@NonNull View viewGroup, @IdRes int id, int length) {
        try {
            RecyclerView recyclerView = viewGroup.findViewById(id);
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            int itemCount = adapter.getItemCount();
            int position = itemCount - 1;
            if (position < 0) {
                position = 0;
            }
            adapter.notifyItemRangeInserted(position, length);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRecyclerView => notifyItemRangeInserted => " + e.getMessage());
        }
    }
}
