package lib.kalu.frame.mvp.glide;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

@Keep
public interface OkhttpGlideProgressListener {

    void onStart();

    void onComplete();

    void onProgress(@NonNull int progress);
}
