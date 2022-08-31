package lib.kalu.frame.mvp.demo;

import android.annotation.SuppressLint;

import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import lib.kalu.frame.mvp.bean.RequestBean;

public class Demo {

    @SuppressLint("CheckResult")
    public void request() {
        Observable.create(new ObservableOnSubscribe<Boolean>() {
                    @Override
                    public void subscribe(ObservableEmitter<Boolean> emitter) {
                        emitter.onNext(true);
                    }
                })
                .flatMap(new Function<Boolean, Observable<RequestBean<Object>>>() {
                    @Override
                    public Observable<RequestBean<Object>> apply(Boolean aBoolean) {
                        String s = "";
                        try {
                            JSONObject object = new JSONObject();
                            object.put("s1", "s1");
                            s = object.toString();
                        } catch (Exception e) {
                        }
                        return DemoClient.getDemoClient().getDemoApi().testApi1(s);
                    }
                })
                .flatMap(new Function<RequestBean<Object>, Observable<RequestBean<Object>>>() {
                    @Override
                    public Observable<RequestBean<Object>> apply(RequestBean<Object> o) {
                        String s = "";
                        try {
                            String extra = o.getExtra();
                            JSONObject object = new JSONObject(extra);
                            object.put("s2", "s2");
                            s = object.toString();
                        } catch (Exception e) {
                        }
                        return DemoClient.getDemoClient().getDemoApi().testApi2(s);
                    }
                })
                .doOnNext(new Consumer<RequestBean<Object>>() {
                    @Override
                    public void accept(RequestBean<Object> o) {
                        try {
                            String s = o.getExtra();
                            JSONObject object = new JSONObject(s);
                            String s1 = object.optString("s1", "");
                            String s2 = object.optString("s2", "");
                        } catch (Exception e) {
                        }
                    }
                })
                .subscribe();
    }
}
