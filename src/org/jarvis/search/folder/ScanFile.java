package org.jarvis.search.folder;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

/**
 * 类：ScanFile
 * 作用：扫描文件
 */

public class ScanFile {

    /**
     * 递归扫描文件路径
     */
    public static List<String> getScanFilePath(File file) {
        List<String> list1 = new ArrayList<String>();
        File[] listFiles = file.listFiles();
        try {
            if (listFiles != null && listFiles.length > 0) {

                for (File file2 : listFiles) {
                    if (file2.isDirectory()) {
//                        getScanFilePath(new File(file2.getAbsolutePath()));
                        list1.addAll(getScanFilePath(new File(file2.getAbsolutePath())));

                    }
                    if (file2.isFile()) {
//                        System.out.print(file2.getAbsolutePath());
                        String strFilePath = file2.getAbsolutePath();
//                        System.out.println(strFilePath);
                        list1.add(strFilePath);

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return list1;
    }

    /**
     * 递归扫描文件名称
     */
    public static List<String> getScanFileName(File file) {
        List<String> list2 = new ArrayList<String>();
        File[] listFiles = file.listFiles();
        try {
            if (listFiles != null && listFiles.length > 0) {

                for (File file2 : listFiles) {
                    if (file2.isDirectory()) {
//                        getScanFileName(new File(file2.getAbsolutePath()));
                        list2.addAll(getScanFileName(new File(file2.getAbsolutePath())));

                    }
                    if (file2.isFile()) {
                        String strFilePath = file2.getAbsolutePath();
                        int lastIndex = strFilePath.lastIndexOf("/");
//                        System.out.println(strFilePath.substring(lastIndex + 1, strFilePath.length()));
                        list2.add(strFilePath.substring(lastIndex + 1, strFilePath.length()));

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return list2;
    }

}


