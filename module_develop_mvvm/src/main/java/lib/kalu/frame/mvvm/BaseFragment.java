package lib.kalu.frame.mvvm;

import android.app.Application;
import android.app.Fragment;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

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
    public VM getViewModel() {
        if (null != mVM) {
            return mVM;
        } else {
            throw new IllegalArgumentException("view-model is null");
        }
    }

    @Override
    public <VM extends BaseViewModel> VM initViewModel() {
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
}
