package org.jarvis.operations_documentation;

/*
  类：IsFileExists
  作用：判断文件是否存在；存在(返回true)、不存在(返回false)
 */

import java.io.File;

public class IsFileExists {

    private static boolean order = false;

    public static boolean setIsFileExists(File file) {
        File isFile = file;
        if (isFile.exists()) {
            order = true;

        }
        return order;

    }
}
