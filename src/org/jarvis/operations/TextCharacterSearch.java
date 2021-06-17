package org.jarvis.operations;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 字符在文本中搜索
 */
public class TextCharacterSearch {

    /**
     * 字符与文本间的匹配关系
     * @param word
     * @param text
     * @return
     */
    public String getTextCharacterSearch(String word, String text) {
        String strReturnText = "";
        if (text.lastIndexOf(word) != -1) {
            strReturnText = word + "," + text;
        }
        return strReturnText;

    }

    public static void main(String[] args)  {
        try{
            TextCharacterSearch textCharacterSearch = new TextCharacterSearch();

            File file = new File("/Users/zhangyibin/Downloads/0425/销售表.xlsx");
            ReadExcel readExcel = new ReadExcel();
            TemporaryFiles temporaryFiles = new TemporaryFiles();
            List<String> list1 = new ArrayList<String>();
            list1.addAll(readExcel.setExcelCell(file, "Sheet1", 0));

            List<String> list2 = new ArrayList<String>();
            list2.addAll(readExcel.setExcelCell(file, "Sheet2", 1));

            for (int i = 0; i < list1.size(); i++) {
                for (int j = 0; j < list2.size(); j++) {
                    String text1 = list1.get(i).toString();
                    String word = text1.substring(0, 4);
                    //System.out.println(word);
                    String text2 = list2.get(j).toString();
                    String data = textCharacterSearch.getTextCharacterSearch(word, text2);
                    if (!data.equals("")) {
                        System.out.println(text1 + "," + data);

                    }

                }
            }

        }catch(Exception e){

        }

    }
}
