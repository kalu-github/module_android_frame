package lib.kalu.frame.mvp.util;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.HashMap;

public final class SoundPoolUtil {

    private static int mSoundId = -1;
    private static SoundPool mSoundPlayer = null;

    private static Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1999) {
                unload();
            }
        }
    };

    private static void checkNull() {
        try {
            if (null != mSoundPlayer)
                throw new Exception("mSoundPlayer error: not null");
//            pause();
//            release();
            //sdk版本21是SoundPool 的一个分水岭
            if (Build.VERSION.SDK_INT >= 21) {
                SoundPool.Builder builder = new SoundPool.Builder();
                //传入最多播放音频数量,
                builder.setMaxStreams(1);
                //AudioAttributes是一个封装音频各种属性的方法
                AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
                //设置音频流的合适的属性
                attrBuilder.setLegacyStreamType(AudioManager.STREAM_MUSIC);
                //加载一个AudioAttributes
                builder.setAudioAttributes(attrBuilder.build());
                mSoundPlayer = builder.build();
            } else {
                // 第一个参数：int maxStreams：SoundPool对象的最大并发流数
                // 第二个参数：int streamType：AudioManager中描述的音频流类型
                // 第三个参数：int srcQuality：采样率转换器的质量。 目前没有效果。 使用0作为默认值。
                mSoundPlayer = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            }
        } catch (Exception e) {
            MvpUtil.logE("SoundPoolUtil => build => " + e.getMessage());
        }
    }

    public static boolean isPlaying() {
        return mSoundId != -1;
    }

    public static void unload() {
        try {
            if (null == mSoundPlayer)
                throw new Exception("mSoundPlayer error: null");
//            mSoundPlayer.setOnLoadCompleteListener(null);
//            mSoundPlayer.release();
            if (mSoundId != -1) {
                mSoundPlayer.unload(mSoundId);
            }
            mSoundId = -1;
        } catch (Exception e) {
            MvpUtil.logE("SoundPoolUtil => unload => " + e.getMessage());
        }
    }

    public static void pause() {
        try {
            if (null == mSoundPlayer)
                throw new Exception("mSoundPlayer error: null");
            if (mSoundId == -1)
                throw new Exception("mSoundId error: " + mSoundId);
            mSoundPlayer.pause(mSoundId);
        } catch (Exception e) {
            MvpUtil.logE("SoundPoolUtil => pause => " + e.getMessage());
        }
    }

    public static void stop() {
        try {
            if (null == mSoundPlayer)
                throw new Exception("mSoundPlayer error: null");
            if (mSoundId == -1)
                throw new Exception("mSoundId error: " + mSoundId);
            mSoundPlayer.stop(mSoundId);
        } catch (Exception e) {
            MvpUtil.logE("SoundPoolUtil => stop => " + e.getMessage());
        }
    }

    public static void resume() {
        try {
            if (null == mSoundPlayer)
                throw new Exception("mSoundPlayer error: null");
            if (mSoundId == -1)
                throw new Exception("mSoundId error: " + mSoundId);
            mSoundPlayer.resume(mSoundId);
        } catch (Exception e) {
            MvpUtil.logE("SoundPoolUtil => resume => " + e.getMessage());
        }
    }

    public static void start(@NonNull String filePath) {
        checkNull();
        try {
            if (null == filePath || filePath.length() == 0)
                throw new Exception("filePath error: " + filePath);
            File file = new File(filePath);
            if (!file.exists() || file.isDirectory())
                throw new Exception("file error: null");
            if (mSoundId != -1) {
                mSoundPlayer.unload(mSoundId);
            }
            long duraing = getDuraing(filePath);
            if (duraing <= 0)
                throw new Exception("duraing error: " + duraing);
            mSoundId = mSoundPlayer.load(filePath, 1);
            mSoundPlayer.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    if (status == 0) {
                        //第一个参数soundID
                        //第二个参数leftVolume为左侧音量值（范围= 0.0到1.0）
                        //第三个参数rightVolume为右的音量值（范围= 0.0到1.0）
                        //第四个参数priority 为流的优先级，值越大优先级高，影响当同时播放数量超出了最大支持数时SoundPool对该流的处理
                        //第五个参数loop 为音频重复播放次数，0为值播放一次，-1为无限循环，其他值为播放loop+1次
                        //第六个参数 rate为播放的速率，范围0.5-2.0(0.5为一半速率，1.0为正常速率，2.0为两倍速率)
                        soundPool.play(mSoundId, 1, 1, 1, 0, 1);
                        mHandler.sendEmptyMessageDelayed(1999, duraing);
                    }
                }
            });
        } catch (Exception e) {
            MvpUtil.logE("SoundPoolUtil => start => " + e.getMessage());
        }
    }

    public static long getDuraing(String filePath) {
        try {
            if (null == filePath || filePath.length() == 0)
                throw new Exception("filePath error: " + filePath);
            File file = new File(filePath);
            if (!file.exists())
                throw new Exception("file error: not exists");
            android.media.MediaMetadataRetriever mmr = new android.media.MediaMetadataRetriever();
//            HashMap<String, String> headers = null;
//            if (headers == null) {
//                headers = new HashMap<String, String>();
//                headers.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.4.2; zh-CN; MW-KW-001 Build/JRO03C) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 UCBrowser/1.0.0.001 U4/0.8.0 Mobile Safari/533.1");
//            }
            mmr.setDataSource(filePath);
            String data = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            mmr.release();
            if (null == data || data.length() == 0)
                throw new Exception("data error: " + data);
            long duration = Long.parseLong(data);
            if (duration <= 0L)
                throw new Exception("duration error: " + duration);
            return duration;
        } catch (Exception e) {
            MvpUtil.logE("SoundPoolUtil => getDuraing => " + e.getMessage());
            return 0L;
        }
    }
}
