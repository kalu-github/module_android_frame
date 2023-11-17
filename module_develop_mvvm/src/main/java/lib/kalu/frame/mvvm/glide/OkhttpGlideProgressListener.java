package lib.kalu.frame.mvvm.glide;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

@Keep
public interface OkhttpGlideProgressListener {

    void onStart();

    void onComplete();

    void onError(@NonNull Exception e);

    void onProgress(@NonNull int progress);
}
