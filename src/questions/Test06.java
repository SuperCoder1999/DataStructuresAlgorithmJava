package questions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
    set中 的TreeSet能用 普通for 循环遍历吗？
    --答：暂时不清楚，集合需要复习。
 */

public class Test06 {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("邓超", "孙俪");
        map.put("王宝强", "马蓉");
        map.put("宋喆", "马蓉");
        map.put("刘令博", null);
        map.put(null, "刘亦菲");
        map.put("鹿晗", "关晓彤");
        Collection keyset = map.keySet();
    }
}
