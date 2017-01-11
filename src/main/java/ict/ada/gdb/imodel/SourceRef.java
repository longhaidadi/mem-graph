package ict.ada.gdb.imodel;

/**
 * Created by lon on 16-2-19.
 */
public interface SourceRef {
    String sourceId();

    int offset();

    int length();

    String addl();
}
