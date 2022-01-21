//package lib.kalu.frame.mvvm;
//
//import android.app.Application;
//import android.app.Fragment;
//import android.os.Bundle;
//
//import androidx.annotation.Nullable;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.ParameterizedType;
//
///**
// * @author zhanghang
// * @description:
// * @date :2022-01-17
// */
//public abstract class BaseFragment<M extends BaseModel, K extends BaseViewModel> extends Fragment implements BaseView {
//
//    private M mM = null;
//    private K mVM = null;
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        initWindow();
//        mM = initModel();
//        mVM = initViewModel();
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        initData();
//    }
//
//    @Override
//    public K getViewModel() {
//        if (null != mVM) {
//            return mVM;
//        } else {
//            throw new IllegalArgumentException("view-model is null");
//        }
//    }
//
//    @Override
//    public <K extends BaseViewModel> K initViewModel() {
//        try {
//            Class<K> clazz = (Class<K>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
//            Constructor constructor = clazz.getDeclaredConstructor(new Class[]{Application.class});
//            constructor.setAccessible(true);
//            return (K) constructor.newInstance(getActivity().getApplication());
//        } catch (Exception e) {
//            throw new IllegalArgumentException(e.getMessage());
//        }
//    }
//
//    @Override
//    public M getModel() {
//        if (null != mM) {
//            return mM;
//        } else {
//            throw new IllegalArgumentException("model is null");
//        }
//    }
//
//    @Override
//    public <M extends BaseModel> M initModel() {
//        try {
//            Class<M> clazz = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//            Constructor constructor = clazz.getDeclaredConstructor(BaseView.class);
//            constructor.setAccessible(true);
//            return (M) constructor.newInstance(this);
//        } catch (Exception e) {
////            Log.d("BaseActivity", "initViewModel => " + getClass().getName());
////            Log.d("BaseActivity", "initPresenter => " + e.getMessage(), e);
//            throw new IllegalArgumentException(e.getMessage());
//        }
//    }
//}
