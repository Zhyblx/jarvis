package org.jarvis.search.folder;

/**
 * 类：ScanDirectory
 * 作用：扫描目录
 */

import java.io.File;

import java.util.List;
import java.util.ArrayList;

public class ScanDirectory {
    //获取层级的方法
    public static String getLevel(int level) {
        //A mutable sequence of characters.
        StringBuilder sb = new StringBuilder();
//        for (int l = 0; l < level; l++) {
//            sb.append("|--");
//        }
        return sb.toString();
    }

    private static List<String> list = new ArrayList<String>();

    @Deprecated
    public static List<String> getScanDirectory(File dir, int level) {
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                //这里面用了递归的算法
                getScanDirectory(files[i], level);

            }
        }

//        System.out.println(getLevel(level) + dir.getName());
//        System.out.println(getLevel(level)+dir.getPath());
        list.add(getLevel(level) + dir.getPath());
        level++;

        return list;
    }

}