package lib.kalu.frame.mvvm.glide;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;

final class OkhttpGlideUrl extends GlideUrl {

    public OkhttpGlideUrl(String url) {
        super(url);
    }

    public OkhttpGlideUrl(String url, Headers headers) {
        super(url, headers);
    }
}
