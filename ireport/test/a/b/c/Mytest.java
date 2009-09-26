package a.b.c;

import java.util.HashMap;
import java.util.Map;

public class Mytest {
	private static Map<String, Integer> map = new HashMap<String, Integer>();   
public static void main(String[] args) {
 testMap();
}

public static void testMap() {   
    map.put("a", 3);   
    map.put("b", 2);   
    map.put("c", 1);   
    for (Map.Entry en : map.entrySet()) {   
        System.out.println(en.getKey());   
    }   
}
}
