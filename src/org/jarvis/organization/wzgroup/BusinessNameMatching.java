package org.jarvis.organization.wzgroup;

import org.jarvis.operations.ReadExcel;
import org.jarvis.operations.TemporaryFiles;
import org.jarvis.operations.ZhangTextSimilarity;
import org.json.JSONObject;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

/**
 * 文本相似度计算应用；
 *
 */
public class BusinessNameMatching {

    /*
    public static void main(String[] args) throws Exception {
        File file = new File("/Users/zhangyibin/Downloads/财务数据/财务表公司全简称对应.xlsx");
        ReadExcel readExcel = new ReadExcel();
        TemporaryFiles temporaryFiles=new TemporaryFiles();
        List<String> list1 = new ArrayList<String>();
        list1.addAll(readExcel.setExcelCell(file, "Sheet1", 0));

        List<String> list2 = new ArrayList<String>();
        list2.addAll(readExcel.setExcelCell(file, "Sheet2", 1));
//        System.out.println(list1);
//        System.out.println(list2);

        for (int i = 1; i < list1.size(); i++) {
            for (int j = 1; j < list2.size(); j++) {
                String text1 = list1.get(i).toString();
                String text2 = list2.get(j).toString();
                String textSimilarity = new ZhangTextSimilarity().getBaiDuTextSimilarity(text1, text2); //百度文本相似度
                JSONObject jsonObject = new JSONObject(textSimilarity);
                double score = Double.valueOf(jsonObject.get("score").toString());
                System.out.println(text1 + "," + text2 + "," + score);
                if(score>=0.9){
                    temporaryFiles.stLog(text1 + "," + text2 + "," + score);

                }
                Thread.sleep(1000);// 延迟1秒

            }
        }
    }
     */


    public static void main(String[] args) throws Exception {
        File file = new File("/Users/zhangyibin/Downloads/财务数据/财务表公司全简称对应.xlsx");
        ReadExcel readExcel = new ReadExcel();
        TemporaryFiles temporaryFiles=new TemporaryFiles();
        List<String> list1 = new ArrayList<String>();
        list1.addAll(readExcel.setExcelCell(file, "Sheet1", 0));

        List<String> list2 = new ArrayList<String>();
        list2.addAll(readExcel.setExcelCell(file, "Sheet2", 1));
//        System.out.println(list1);
//        System.out.println(list2);

        for (int i = 1; i < list1.size(); i++) {
            for (int j = 1; j < list2.size(); j++) {
                String text1 = list1.get(i).toString();
                String text2 = list2.get(j).toString();
                double score = new ZhangTextSimilarity().getWordTextSimilarity(text1, text2);
                System.out.println(text1 + "," + text2 + "," + score);
                if(score>=0.9){
                    temporaryFiles.stLog(text1 + "," + text2 + "," + score);

                }
                Thread.sleep(1000);// 延迟1秒

            }
        }
    }


}
