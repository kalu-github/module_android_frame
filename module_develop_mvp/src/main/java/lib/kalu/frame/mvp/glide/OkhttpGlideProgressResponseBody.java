package lib.kalu.frame.mvp.glide;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

final class OkhttpGlideProgressResponseBody extends ResponseBody {

    private BufferedSource bufferedSource;

    private ResponseBody responseBody;

    private OkhttpGlideProgressListener mOkhttpGlideProgressListener;

    public OkhttpGlideProgressResponseBody(@NonNull String url, @NonNull ResponseBody responseBody) {
        this.responseBody = responseBody;
        this.mOkhttpGlideProgressListener = OkhttpGlideInterceptor.getListener(url);
        if (null != mOkhttpGlideProgressListener) {
            this.mOkhttpGlideProgressListener.onStart();
        }
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(new ProgressSource(responseBody.source()));
        }
        return bufferedSource;
    }

    private class ProgressSource extends ForwardingSource {

        long totalBytesRead = 0;

        int currentProgress;

        ProgressSource(Source source) {
            super(source);
        }

        @Override
        public long read(Buffer sink, long byteCount) throws IOException {
            long bytesRead = super.read(sink, byteCount);
            long fullLength = responseBody.contentLength();
            if (bytesRead == -1) {
                totalBytesRead = fullLength;
            } else {
                totalBytesRead += bytesRead;
            }
            int progress = (int) (100f * totalBytesRead / fullLength);
            if (mOkhttpGlideProgressListener != null && progress != currentProgress) {
                mOkhttpGlideProgressListener.onProgress(progress);
            }
            if (mOkhttpGlideProgressListener != null && totalBytesRead == fullLength) {
                mOkhttpGlideProgressListener.onComplete();
                OkhttpGlideInterceptor.removeListener(mOkhttpGlideProgressListener);
                mOkhttpGlideProgressListener = null;
            }
            currentProgress = progress;
            return bytesRead;
        }
    }
}