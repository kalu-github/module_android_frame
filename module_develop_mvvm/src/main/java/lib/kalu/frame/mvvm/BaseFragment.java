package lib.kalu.frame.mvvm;

import android.app.Application;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

import lib.kalu.frame.mvvm.util.MvvmUtil;

/**
 * @author zhanghang
 * @description:
 * @date :2022-01-17
 */
public abstract class BaseFragment<M extends BaseModel, V extends BaseView, VM extends BaseViewModel> extends Fragment implements BaseView {

    private VM mVM = null;

    @Override
    public void onDestroyView() {
        try {
            mVM.onCleared();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroyView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        mVM = initViewModel();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public final VM getViewModel() {
        if (null != mVM) {
            return mVM;
        } else {
            throw new IllegalArgumentException("view-model is null");
        }
    }

    private final <VM extends BaseViewModel> VM initViewModel() {
        try {
            Class<M> clazzM = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            M m = clazzM.newInstance();
            Class<V> clazzV = (Class<V>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            Class<VM> clazzVM = (Class<VM>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
            Constructor constructorVM = clazzVM.getDeclaredConstructor(new Class[]{Application.class, clazzV, clazzM});
            constructorVM.setAccessible(true);
            return (VM) constructorVM.newInstance(getActivity().getApplication(), this, m);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        try {
            hideLoading();
        } catch (Exception e) {
        }
    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        try {
            return super.getDefaultViewModelCreationExtras();
        } catch (Exception e) {
            MvvmUtil.logE("BaseFragment => getDefaultViewModelCreationExtras => " + e.getMessage());
            return new MutableCreationExtras();
        }
    }
}
