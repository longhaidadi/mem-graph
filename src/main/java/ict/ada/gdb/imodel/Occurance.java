package ict.ada.gdb.imodel;

/**
 * Created by chenbo on 2016/3/31.
 */
public interface Occurance extends WithTimestamp,Comparable<Occurance> {
    SourceRef ref();
}
