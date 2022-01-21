package lib.kalu.frame.mvp;

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
public abstract class BaseFragment<K extends BaseViewModel, M extends BasePresenter> extends Fragment implements BaseView {

    private M mP = null;
    private K mVM = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        mVM = initViewModel();
        mP = initPresenter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public K getViewModel() {
        if (null != mVM) {
            return mVM;
        } else {
            throw new IllegalArgumentException("mBaseViewModel is null");
        }
    }

    @Override
    public <K extends BaseViewModel> K initViewModel() {
        try {
            Class<K> clazz = (Class<K>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            Constructor constructor = clazz.getDeclaredConstructor(new Class[]{Application.class});
            constructor.setAccessible(true);
            return (K) constructor.newInstance(getActivity().getApplication());
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public M getPresenter() {
        if (null != mP) {
            return mP;
        } else {
            throw new IllegalArgumentException("mBasePresenter is null");
        }
    }

    @Override
    public <M extends BasePresenter> M initPresenter() {
        try {
            Class<M> clazz = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            Constructor constructor = clazz.getDeclaredConstructor(new Class[]{BaseView.class, BaseViewModel.class});
            constructor.setAccessible(true);
            return (M) constructor.newInstance(this, getViewModel());
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
