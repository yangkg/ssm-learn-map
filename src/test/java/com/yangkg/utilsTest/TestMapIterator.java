package com.yangkg.utilsTest;

;
;import sun.applet.Main;
import sun.awt.SunHints;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * map遍历的四种方式
 *
 * @author：Kyle.yangkg
 * @create：2017-10-17 上午 9:27
 * ©copyright：www.aisino.com
 */
public class TestMapIterator {

    public static void main(String[] args) {
        final String ywxx = "CZLX~1;ZDBB~;ZGBB~3.0;BBSM~设置高版本;";
        String[] ywxxArr = ywxx.split(";");
        HashMap<String, String> ywxxMap = new HashMap<>();
        for (String temp : ywxxArr) {
            String[] ywxxMsg = temp.split("~");
            ywxxMap.put(ywxxMsg[0], ywxxMsg.length == 2 ? ywxxMsg[1] : "");
        }

        System.out.println("第一种：通过Map.keySet遍历key和value：");
        for (String key : ywxxMap.keySet()) {
            String value = ywxxMap.get(key);
            System.out.println(key + ":" + value);
        }

        System.out.println("第二种：通过Map.entrySet使用iterator遍历key和value：");
        Iterator<Map.Entry<String, String>> iterator = ywxxMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey()+":"+next.getValue());
        }

        System.out.println("第三种：通过Map.entrySet遍历key和value");
        for (Map.Entry<String,String > entry :ywxxMap.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }

        System.out.println("第四种：通过Map.values()遍历所有的value，但不能遍历key");
        for (String value : ywxxMap.values()){
            System.out.println(value);
        }


        HashMap<Object , String > map = new HashMap<>();
        map.put("sqm",null);
        map.put(null,"233");
        map.put("","344");

        System.out.println(map.get("sqm"));
        System.out.println(map.get(null));
        System.out.println(map.get(""));
    }
}
