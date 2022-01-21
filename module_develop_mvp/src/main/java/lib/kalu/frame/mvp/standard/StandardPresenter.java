package lib.kalu.frame.mvp.standard;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import lib.kalu.frame.mvvm.BasePresenter;
import lib.kalu.frame.mvvm.BaseView;
import lib.kalu.frame.mvvm.BaseViewModel;

/**
 * @author zhanghang
 * @description:
 * @date :2022-01-17
 */
@Keep
public class StandardPresenter<K extends BaseView, M extends BaseViewModel> extends BasePresenter {

    public StandardPresenter(@NonNull BaseView baseView, @NonNull BaseViewModel baseViewModel) {
        super(baseView, baseViewModel);
    }

    @Override
    protected K getBaseView() {
        return (K) super.getBaseView();
    }

    @Override
    protected M getBaseViewModel() {
        return (M) super.getBaseViewModel();
    }
}
