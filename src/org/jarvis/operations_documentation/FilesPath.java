package org.jarvis.operations_documentation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 类：FilesPath
 * 作用：返回目录下所有文件的路径地址
 */
public class FilesPath {

    private final List<String> list = new ArrayList<String>();

    public List<String> getAllFilesPath(File dir) {
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                //这里面用了递归的算法
                getAllFilesPath(files[i]);

            }
        }
        list.add(dir.getPath());
        return list;

    }
}
