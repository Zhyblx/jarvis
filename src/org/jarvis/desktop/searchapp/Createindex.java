package org.jarvis.desktop.searchapp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.jarvis.search.converter.DataService;
import org.jarvis.search.folder.DeleteDirectory;
import org.jarvis.search.folder.ScanFile;


/**
 * lucene创建索引
 *
 * @author he
 * @date 2018/9/20
 */
public class Createindex {

    private volatile static Createindex instance;

    public final static String INDEX_DIR_PATH = "./out/SearchIndex/"; //索引目录地址

    private static class SingletonHolder {
        private final static Createindex instance = new Createindex();

    }

    public static Createindex getInstance() {
        return SingletonHolder.instance;

    }

    public boolean createIndex(String indexDir) throws IOException {
        //加点测试的静态数据

        File file = new File("/Users/zhangyibin/Documents/");//加入搜索的目录地址
        String[] strTitle = DataService.getArray(ScanFile.getScanFileName(file));
        String[] strTcontent = DataService.getArray(ScanFile.getScanFilePath(file));

        String titles[] = new String[strTitle.length];
        System.arraycopy(strTitle, 0, titles, 0, strTitle.length);

        String tcontents[] = new String[strTcontent.length];
        System.arraycopy(strTcontent, 0, tcontents, 0, strTcontent.length);

        long startTime = System.currentTimeMillis();//记录索引开始时间

        Analyzer analyzer = new SmartChineseAnalyzer();
        Directory directory = FSDirectory.open(Paths.get(indexDir));
        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        IndexWriter indexWriter = new IndexWriter(directory, config);

        for (int i = 0; i < titles.length; i++) {
            Document doc = new Document();
            //添加字段
            doc.add(new TextField("title", titles[i], Field.Store.YES)); //添加文件名称
            doc.add(new TextField("tcontent", tcontents[i], Field.Store.YES)); //添加文件地址
            indexWriter.addDocument(doc);
        }

        indexWriter.commit();
        System.out.println("共索引了" + indexWriter.numDocs() + "个文件");
        indexWriter.close();
        System.out.println("创建索引所用时间：" + (System.currentTimeMillis() - startTime) + "毫秒");

        return true;
    }

    public static void main(String[] args) {
        try {
            boolean ifDel = DeleteDirectory
                    .deleteDir(new File("./out/SearchIndex/")) ? true : false;

            if (ifDel == true) {
                System.out.println("原索引已删除!");
                boolean r = Createindex.getInstance().createIndex(INDEX_DIR_PATH);
                if (r) {
                    System.out.println("索引创建成功!");
                } else {
                    System.out.println("索引创建失败!");
                }

            } else {
                System.out.println("原索引未删除，程序退出!");

            }


        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}