package org.jarvis.operations;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 类：FileScan
 * 作用：扫描文件；返回指定目录下的文件名(包含子目录)
 */

public class AllFileScan {

    private final List<String> listCatalog = new ArrayList<>();//存储目录
    private final List<String> listFile = new ArrayList<>();//存储文件
    private Iterator<String> iterator = null;
    private File file1 = null;
    private final FilesPath filesPath = new FilesPath();
    private final FileScan fileScan = new FileScan();

    public List<String> getDocumentScanner(File file) {
        listCatalog.addAll(filesPath.getAllFilesPath(file));
        iterator = listCatalog.iterator();
        while (iterator.hasNext()) {
            String filePath = iterator.next();
            file1 = new File(filePath);
            String fileName = fileScan.getFileName(file1);
            listFile.add(fileName);

        }
        return listFile;

    }
}
