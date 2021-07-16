package org.jarvis.operations;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * ExcelUtil(Excel文件创建工具)
 *
 * @author zhangyibin
 */
public class ExcelUtil {

    public static void writeXlsx(File file, List<String> list, String sheetName) {
        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet(sheetName);
            for (int i = 0; i < list.size(); i++) {
                XSSFRow row = sheet.createRow(i);//新建行
                String[] newStr = list.get(i).toString().split( "|" );
                for (int j = 0; j < newStr.length; j++) {
                    XSSFCell cell = row.createCell(j);//创建单元格
                    cell.setCellValue(newStr[j]);//写入单元格数据
                }
            }
            //File file=new File("/Users/zhangyibin/Downloads/1.xlsx");
            FileOutputStream outputStream = new FileOutputStream(file);//新建输出流
            xssfWorkbook.write(outputStream);
            outputStream.close();//关闭输出流
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

//    public static void main(String[] args) throws Exception{
//        List<String> list=new ArrayList<String>();
//        list.addAll(new ReportDAO().getAnnualFee());
//        File file=new File("/Users/zhangyibin/Downloads/1.xlsx");
//        new ExcelUtil().writeXlsx(file, list);
//    }

}
