package org.jarvis.operations;

import java.io.File;

/**
 * 类：FileScan
 * 作用：扫描文件；返回指定目录下的文件名(不包含子目录)
 */

public class FileScan {

    private File[] listFiles = null;

    public String getFileName(File file1) {
        listFiles = file1.listFiles();
        String fileName = "";
        if (listFiles != null && listFiles.length > 0) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    getFileName(new File(file2.getAbsolutePath()));

                }
                if (file2.isFile()) {
                    String strFileName = file2.getName();
                    fileName = fileName + strFileName + "\r\n";

                }
            }
        }
        return fileName;

    }
}
