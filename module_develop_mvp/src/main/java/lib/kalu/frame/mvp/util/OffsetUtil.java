
package lib.kalu.frame.mvp.util;

import java.util.List;

public final class OffsetUtil {

    public static int getOffset(List data, int pageNum) {
        try {
            int size = data.size();
            if (size <= 0)
                throw new Exception();
            if (size % pageNum == 0) {
                return size / pageNum + 1;
            } else {
                return size / pageNum + 2;
            }
        } catch (Exception e) {
            return 1;
        }
    }
}
