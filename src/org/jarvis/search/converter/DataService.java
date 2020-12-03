package org.jarvis.search.converter;

import java.util.List;

/**
 * 类：DataService
 * 作用：数组转化服务
 */
public class DataService {
    public static String[] getArray(List<String> list) {
        String[] arrayData = null;
        try {

            arrayData = new String[list.size()];
            for (int i = 0; i < arrayData.length; i++) {
                arrayData[i] = list.get(i);

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
//
        return arrayData;
    }

}
