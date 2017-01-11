package ict.ada.gdb.imodel;

import java.util.List;

/**
 * Created by chenbo on 2016/3/31.
 */
public interface WithOccur {
    // public List<SourceRef> refs(int before);
    //  public List<SourceRef> refs(int start,int end);
    //  public int getRefCount();
    //  public int getRefCounts(int before);

    int countOccurs();

    int countOccurs(int before);

    int countOccurs(int start, int end);

    int countOccurs(int start, int end, int count);

    List<Occurance> occurances();

    List<Occurance> occurances(int before);

    List<Occurance> occurances(int start, int end);

    List<Occurance> occurances(int start, int end, int count);

    void addOccur(int ts);

    void addOccur(String sourceId);

    void addOccur(String sourceId, int offset, int len);

    void addOccur(String sourceId, int offset, int len, int ts);


}
