package ict.ada.gdb.util;

/**
 * Created by chenbo on 2016/3/31.
 */
public class TimeUtil {
    public static int unixTimestamp() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static int getSysOppositeTime() {
        return getOppositeTime(unixTimestamp());
    }

    public static int getOppositeTime(int time) {
        if (time < 0) return Integer.MAX_VALUE;
        return Integer.MAX_VALUE - time;
    }

    public static int getTimeAsHour(int ts) {
        return ts <= 0 ? 0 : ts - ts % 3600;
    }
}
