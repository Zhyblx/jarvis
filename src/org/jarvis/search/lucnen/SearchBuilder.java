package org.jarvis.search.lucnen;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.jarvis.desktop.searchapp.Createindex;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

/**
 * lucene搜索实现
 *
 * @author he
 * @date 2018/9/20
 */
public class SearchBuilder {

    public static List<String> doSearch(String indexDir, String queryStr) throws IOException, ParseException, InvalidTokenOffsetsException {

        List<String> list = new ArrayList<String>();

        Directory directory = FSDirectory.open(Paths.get(indexDir));
        DirectoryReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);
        Analyzer analyzer = new SmartChineseAnalyzer();
        QueryParser parser = new QueryParser("title", analyzer);//用标题匹配
        Query query = parser.parse(queryStr);

        long startTime = System.currentTimeMillis();
        TopDocs docs = searcher.search(query, 1000);

        System.out.println("查找" + queryStr + "所用时间：" + (System.currentTimeMillis() - startTime));
        System.out.println("查询到" + docs.totalHits + "条记录");

        //遍历查询结果
        for (ScoreDoc scoreDoc : docs.scoreDocs) {
            Document doc = searcher.doc(scoreDoc.doc);
            String title = doc.get("title");//文件名称
            String tcontent = doc.get("tcontent");//文件地址
            if (tcontent != null) {
                list.add(title + "---->" + tcontent);

            }
        }
        reader.close();

        return list;
    }

    public static void main(String[] args) {

        String q = "java"; //查询这个字符串
        try {
            System.out.println(doSearch(Createindex.INDEX_DIR_PATH, q));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}