package org.jarvis.operations;

import java.io.File;

/**
 * 类：FileRename
 * 作用：完成指定文件的重命名
 *
 */

public class FileRename {

    /** 文件重命名
     * @param path 文件目录
     * @param oldname  原来的文件名
     * @param newname 新文件名
     */
    public static void setFileRename(String path,String oldname,String newname){
        if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名
            File oldfile=new File(path+"/"+oldname);
            File newfile=new File(path+"/"+newname);
            if(!oldfile.exists()){
                return;//重命名文件不存在
            }
            if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名
                System.out.println(newname+"已经存在！");

            else{
                oldfile.renameTo(newfile);

            }
        }else{
            System.out.println("新文件名和旧文件名相同...");

        }
    }

}
