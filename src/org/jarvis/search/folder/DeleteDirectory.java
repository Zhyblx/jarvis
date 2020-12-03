package org.jarvis.search.folder;

import java.io.File;

/**
 * 类：DeleteDirectory
 * 删除目录下的所有文件及子目录下所有文件
 */
public class DeleteDirectory {
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();

            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    public static void main(String[] args) throws Exception {
        File file = new File("/Users/zhangyibin/Downloads/abc/");
        System.out.println(DeleteDirectory.deleteDir(file));

    }
}
