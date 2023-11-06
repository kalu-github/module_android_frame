package lib.kalu.frame.mvvm.util;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class ProguardUtil {

    public static String genProguard(@NonNull Context context, @NonNull String fileName, @NonNull List<String> data) {
        try {
            // step1
            if (null == fileName || fileName.length() == 0)
                throw new Exception("fileName error: " + fileName);
            File dir = context.getFilesDir();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, fileName);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            // step2
            String[] unicode = data.toArray(new String[0]);
            List<String> result = new ArrayList<>();
            for (int i = 1; i <= 4; i++) {
                List<String[]> list = new ArrayList<>();
                arrangementSelect(unicode, i, list);
                for (String[] s1 : list) {
                    StringBuilder builder = new StringBuilder();
                    for (String s2 : s1) {
                        builder.append(s2);
                    }
                    result.add(builder.toString());
                }
            }
            // step3
            Collections.shuffle(result);
            FileOutputStream fos = new FileOutputStream(file);
            int size = result.size();
            for (int i = 0; i < size; i++) {
                String s = result.get(i);
                if (null == s || s.length() == 0)
                    continue;
                byte[] bytes = s.getBytes("UTF-8");
                fos.write(bytes);
                if (i + 1 < size) {
                    fos.write('\n');
                }
            }
            fos.flush();
            fos.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            MvvmUtil.logE("ProguardUtil => genProguard => " + e.getMessage());
            return null;
        }
    }

    /***/

    /// <summary>
    /// 从n中 取出 m的组合个数
    /// n!/(m!*(n-m)!)
    /// </summary>
    /// <param name="n"></param>
    /// <param name="m"></param>
    /// <returns></returns>
    private static int combination(int n, int m) {
        return factorial(n, n - m) / factorial(m, 0);
    }

    /// <summary>
    /// 阶乘
    /// </summary>
    /// <param name="n"></param>
    /// <returns></returns>
    private static int factorial(int n, int limit) {
        int res = 1;
        for (int i = n; i > limit; i--) {
            res = res * i;
        }
        return res;
    }

    /**
     * 从n个数字中选择m个数字
     */
    private static List<int[]> combine(int[] a, int m) {
        int n = a.length;
        if (m > n) {
            return null;
        }
        if (m == n) {
            ArrayList<int[]> ints = new ArrayList<>();
            ints.add(a);
            return ints;
        }

        List<int[]> result = new ArrayList<int[]>();

        int[] bs = new int[n];
        for (int i = 0; i < n; i++) {
            bs[i] = 0;
        }
        //初始化
        for (int i = 0; i < m; i++) {
            bs[i] = 1;
        }
        boolean flag = true;
        boolean tempFlag = false;
        int pos, sum;
        //首先找到第一个10组合，然后变成01，同时将左边所有的1移动到数组的最左边
        do {
            sum = 0;
            pos = 0;
            tempFlag = true;
            result.add(print(bs, a, m));

            for (int i = 0; i < n - 1; i++) {
                if (bs[i] == 1 && bs[i + 1] == 0) {
                    bs[i] = 0;
                    bs[i + 1] = 1;
                    pos = i;
                    break;
                }
            }
            //将左边的1全部移动到数组的最左边

            for (int i = 0; i < pos; i++) {
                if (bs[i] == 1) {
                    sum++;
                }
            }
            for (int i = 0; i < pos; i++) {
                if (i < sum) {
                    bs[i] = 1;
                } else {
                    bs[i] = 0;
                }
            }

            //检查是否所有的1都移动到了最右边
            for (int i = n - m; i < n; i++) {
                if (bs[i] == 0) {
                    tempFlag = false;
                    break;
                }
            }
            flag = tempFlag == false;

        } while (flag);
        result.add(print(bs, a, m));

        return result;
    }

    private static List<String[]> combine(String[] a, int m) {
        int n = a.length;
        if (m > n) {
            return null;
        }
        if (m == n) {
            ArrayList<String[]> ints = new ArrayList<>();
            ints.add(a);
            return ints;
        }

        List<String[]> result = new ArrayList<>();

        String[] bs = new String[n];
        for (int i = 0; i < n; i++) {
            bs[i] = "0";
        }
        //初始化
        for (int i = 0; i < m; i++) {
            bs[i] = "1";
        }
        boolean flag = true;
        boolean tempFlag = false;
        int pos, sum;
        //首先找到第一个10组合，然后变成01，同时将左边所有的1移动到数组的最左边
        do {
            sum = 0;
            pos = 0;
            tempFlag = true;
            result.add(print(bs, a, m));

            for (int i = 0; i < n - 1; i++) {
                if (Objects.equals(bs[i], "1") && Objects.equals(bs[i + 1], "0")) {
                    bs[i] = "0";
                    bs[i + 1] = "1";
                    pos = i;
                    break;
                }
            }
            //将左边的1全部移动到数组的最左边
            for (int i = 0; i < pos; i++) {
                if (Objects.equals(bs[i], "1")) {
                    sum++;
                }
            }
            for (int i = 0; i < pos; i++) {
                if (i < sum) {
                    bs[i] = "1";
                } else {
                    bs[i] = "0";
                }
            }

            //检查是否所有的1都移动到了最右边
            for (int i = n - m; i < n; i++) {
                if (Objects.equals(bs[i], "0")) {
                    tempFlag = false;
                    break;
                }
            }
            flag = !tempFlag;

        } while (flag);
        result.add(print(bs, a, m));

        return result;
    }

    private static int[] print(int[] bs, int[] a, int m) {
        int[] result = new int[m];
        int pos = 0;
        for (int i = 0; i < bs.length; i++) {
            if (bs[i] == 1) {
                result[pos] = a[i];
                pos++;
            }
        }
        return result;
    }

    private static String[] print(String[] bs, String[] a, int m) {
        String[] result = new String[m];
        int pos = 0;
        for (int i = 0; i < bs.length; i++) {
            if (Objects.equals(bs[i], "1")) {
                result[pos] = a[i];
                pos++;
            }
        }
        return result;
    }

    /**
     * 排列选择（从列表中选择n个排列）
     *
     * @param dataList 待选列表
     * @param n        选择个数
     */
    private static void arrangementSelect(String[] dataList, int n, List<String[]> result) {
        arrangementSelect(dataList, new String[n], 0, result);
    }

    /**
     * 排列选择
     *
     * @param dataList    待选列表
     * @param results     前面（resultIndex-1）个的排列结果
     * @param resultIndex 选择索引，从0开始
     */
    private static void arrangementSelect(String[] dataList, String[] results, int resultIndex, List<String[]> result) {
        int resultLen = results.length;
        if (resultIndex >= resultLen) { // 全部选择完时，输出排列结果
            String[] temps = new String[resultLen];
            System.arraycopy(results, 0, temps, 0, results.length);
            result.add(temps);
//            System.out.println(Arrays.asList(temps));
            return;
        }
        // 递归选择下一个
        for (String aDataList : dataList) {
            // 判断待选项是否存在于排列结果中
            boolean exists = false;
            for (int j = 0; j < resultIndex; j++) {
                if (aDataList.equals(results[j])) {
                    exists = true;
                    break;
                }
            }
            if (!exists) { // 排列结果不存在该项，才可选择
                results[resultIndex] = aDataList;
                arrangementSelect(dataList, results, resultIndex + 1, result);
            }
        }
    }

    /**
     * 组合选择（从列表中选择n个组合）
     *
     * @param dataList 待选列表
     * @param n        选择个数
     */
    private static void combinationSelect(String[] dataList, int n, List<String[]> result) {
        combinationSelect(dataList, 0, new String[n], 0, result);
    }

    /**
     * 组合选择
     *
     * @param dataList    待选列表
     * @param dataIndex   待选开始索引
     * @param resultList  前面（resultIndex-1）个的组合结果
     * @param resultIndex 选择索引，从0开始
     */
    private static void combinationSelect(String[] dataList, int dataIndex, String[] resultList, int resultIndex, List<String[]> result) {
        int resultLen = resultList.length;
        int resultCount = resultIndex + 1;
        if (resultCount > resultLen) { // 全部选择完时，输出组合结果
            String[] temps = new String[resultLen];
            System.arraycopy(resultList, 0, temps, 0, resultList.length);
            result.add(temps);
//            System.out.println(Arrays.asList(temps));
            return;
        }

        // 递归选择下一个
        for (int i = dataIndex; i < dataList.length + resultCount - resultLen; i++) {
            resultList[resultIndex] = dataList[i];
            combinationSelect(dataList, i + 1, resultList, resultIndex + 1, result);
        }
    }

    /**
     * 计算阶乘数，即n! = n * (n-1) * ... * 2 * 1
     */
    private static long factorial(int n) {
        return (n > 1) ? n * factorial(n - 1) : 1;
    }

    /**
     * 计算排列数，即A(n, m) = n!/(n-m)!
     */
    private static long arrangement(int n, int m) {
        return (n >= m) ? factorial(n) / factorial(n - m) : 0;
    }

    /***/
}
