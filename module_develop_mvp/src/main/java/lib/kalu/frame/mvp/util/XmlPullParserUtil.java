package lib.kalu.frame.mvp.util;

import androidx.annotation.NonNull;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

public class XmlPullParserUtil {

    public static String parser(@NonNull String data, @NonNull String nodeName, @NonNull String keyName) {
        try {
            if (null == data || data.length() == 0)
                throw new Exception("data error: " + data);
            if (null == nodeName || nodeName.length() == 0)
                throw new Exception("nodeName error: " + nodeName);
            if (null == keyName || keyName.length() == 0)
                throw new Exception("keyName error: " + keyName);
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true); // 设置是否支持XML命名空间。
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(data));
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                try {
                    String name = parser.getName();
                    if (null == name || name.length() == 0)
                        throw new Exception("name warning: " + name);
                    MvpUtil.logE("XmlPullParserUtil => parser => noteName = " + name);
                    switch (eventType) {
                        case XmlPullParser.START_TAG://开始解析
                            if (!name.equals(nodeName))
                                throw new Exception("name warning: not equals " + nodeName);
                            String value = parser.getAttributeValue(null, keyName);
                            MvpUtil.logE("XmlPullParserUtil => parser => noteName = " + name + ", keyName = " + keyName + ", value = " + value);
                            if (null == value || value.length() == 0) {
                                return null;
                            } else {
                                return value;
                            }
                    }
                    throw new Exception("not find");
                } catch (Exception e) {
                    MvpUtil.logE("XmlPullParserUtil => parser => " + e.getMessage());
                    eventType = parser.next();
                }
            }
            throw new Exception("not find");
        } catch (Exception e) {
            MvpUtil.logE("XmlPullParserUtil => parser => " + e.getMessage());
            return null;
        }
    }
}
