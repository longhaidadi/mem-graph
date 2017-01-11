package ict.ada.gdb.query;

import com.google.common.base.Preconditions;

/**
 * Created by lon on 17-1-10.
 */
public class QueryMap {
    private String key;
    private Object value;

    public QueryMap(String key, Object value) {
        Preconditions.checkArgument(key != null, "Invalid key");
        Preconditions.checkArgument(value != null, "Invalid value");
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }


    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "QueryMap{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
