package ict.ada.gdb.imodel;

import java.util.List;

/**
 * Created by chenbo on 2016/3/31.
 */
public interface WithAttribute {
    List<Attribute> attributes();

    boolean addAttribute(String key, Object value);



    Object getAttributeValue(String key);

    boolean deleteAllAttributes();

    boolean deleteAttributes(String key);

}
