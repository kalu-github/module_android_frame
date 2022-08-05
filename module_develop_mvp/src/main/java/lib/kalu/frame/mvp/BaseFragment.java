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
public abstract class BaseFragment<V extends BaseView, P extends BasePresenter> extends Fragment implements BaseView {

    private P mP = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        mP = initPresenter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public P getPresenter() {
        if (null != mP) {
            return mP;
        } else {
            throw new IllegalArgumentException("view-model is null");
        }
    }

    @Override
    public P initPresenter() {
        try {
            Class<V> clazzV = (Class<V>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            Class<P> clazzP = (Class<P>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            Constructor constructorP = clazzP.getDeclaredConstructor(new Class[]{clazzV});
            constructorP.setAccessible(true);
            return (P) constructorP.newInstance(this);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
