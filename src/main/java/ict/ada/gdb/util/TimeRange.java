package ict.ada.gdb.util;

/**
 * Created by lon on 16-3-16.
 */
public class TimeRange {

    public static final TimeRange ANY_TIME = new TimeRange(Integer.MIN_VALUE, Integer.MAX_VALUE);

    private final int startSecInclusive;// in seconds
    private final int endSecExclusive;// in seconds

    /**
     * @param startSecInclusive in seconds
     * @param endSecExclusive   in seconds
     */
    public TimeRange(int startSecInclusive, int endSecExclusive) {
        if (startSecInclusive > endSecExclusive)
            throw new IllegalArgumentException("startInclusive=" + startSecInclusive + " endExclusive="
                    + endSecExclusive);
        this.startSecInclusive = startSecInclusive;
        this.endSecExclusive = endSecExclusive;
    }

    /**
     * @return start time in seconds
     */
    public int getStartInclusiveInSec() {
        return startSecInclusive;
    }

    /**
     * @return end time in seconds
     */
    public int getEndExclusiveInSec() {
        return endSecExclusive;
    }

    /**
     * @return start time in milliseconds
     */
    public long getStartInclusiveInMs() {
        return startSecInclusive * 1000;
    }

    /**
     * @return end time in milliseconds
     */
    public long getEndExclusiveInMs() {
        return endSecExclusive * 1000;
    }

    /**
     * Decide whether a given date is included in this TimeInteval
     *
     * @param timeInSec date in seconds
     * @return
     */
    public boolean include(int timeInSec) {
        return timeInSec >= startSecInclusive && timeInSec < endSecExclusive;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (endSecExclusive ^ (endSecExclusive >>> 32));
        result = 31 * result + (startSecInclusive ^ (startSecInclusive >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        TimeRange other = (TimeRange) obj;
        return (endSecExclusive == other.endSecExclusive) && (startSecInclusive == other.startSecInclusive);
    }

    @Override
    public String toString() {
        return "[" + getStartInclusiveInSec() + "s, " + getEndExclusiveInSec() + "s )";
    }
}
