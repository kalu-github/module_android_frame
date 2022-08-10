package lib.kalu.frame.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
    public void onDestroyView() {
        try {
            mP.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroyView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        mP = initPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        ViewGroup viewGroup = (ViewGroup) container.getParent();
//        if (null != viewGroup) {
//            viewGroup.removeAllViews();
//        }
        View inflate = inflater.inflate(initLayout(), container, false);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
