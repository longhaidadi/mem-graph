package ict.ada.gdb.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 双向Map
 * <p/>
 * Created by chenbo on 2016/3/28.
 */
public class DoubleMap<K1, K2> {
    private Map<K1, K2> map1;
    private Map<K2, K1> map2;

    public DoubleMap(int capacity) {
        map1 = new HashMap<K1, K2>(capacity);
        map2 = new HashMap<K2, K1>(capacity);
    }

    public void put(K1 key, K2 value) {
        map1.put(key, value);
        map2.put(value, key);
    }

    public int size() {
        return map1.size();
    }

    public K2 mapKey(K1 key) {
        return map1.get(key);
    }

    public K1 mapValue(K2 value) {
        return map2.get(value);
    }

    public void removeKey(K1 key) {
        if (map1.containsKey(key)) {
            K2 value = map1.get(key);
            map1.remove(key);
            map2.remove(value);
        }
    }

    public boolean hasKey(K1 key) {
        return map1.containsKey(key);
    }

    public boolean hasValue(K2 value) {
        return map2.containsKey(value);
    }

    public Map<K1, K2> getFirstMap() {
        return map1;
    }

    @Override
    public String toString() {
        return "DoubleMap{" +
                "map1=" + map1 +
                ", map2=" + map2 +
                '}';
    }
}
