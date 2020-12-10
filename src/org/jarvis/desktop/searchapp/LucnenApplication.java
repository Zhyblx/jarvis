package org.jarvis.desktop.searchapp;

import org.jarvis.search.lucnen.SearchBuilder;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


public class LucnenApplication implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("Boss你要搜什么？");
            Scanner scanner = new Scanner(System.in);
            String query = scanner.nextLine();//输入的关键词

            List<String> list = new ArrayList<String>();
            list.addAll(SearchBuilder.doSearch(Createindex.INDEX_DIR_PATH, query));//搜索结果存储list

            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                String strQuery = iterator.next();
                System.out.println(strQuery);

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) throws Exception {
        new Thread(new LucnenApplication()).start();

    }
}