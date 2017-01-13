package ict.ada.gdb.imodel;

import java.util.List;

/**
 * Created by lon on 16-2-19.
 */
public interface Attribute extends WithTimestamp {
    String key();

    String value();

    List valueList();

    List<Integer>timeList();
}
