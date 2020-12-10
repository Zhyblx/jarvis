package org.jarvis.operations;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.io.File;

/**
 * 类：ReadExcel
 * 作用：读取Excel列数据
 */
public class ReadExcel {

    public String setExcelCell(File file,String sheetName,int intCell) throws Exception{
        String strCell="";

        XSSFWorkbook xssfWorkbook=new XSSFWorkbook(file);
        XSSFSheet xssfSheet=xssfWorkbook.getSheet(sheetName);
        int rowsNum=xssfSheet.getPhysicalNumberOfRows();
        for(int i=0;i<rowsNum;i++){
            XSSFRow xssfRow=xssfSheet.getRow(i);
            XSSFCell xssfCell=xssfRow.getCell(intCell);
            strCell=strCell+xssfCell.toString()+"\r\n";

        }
        xssfWorkbook.close();

        return strCell;

    }

//    public static void main(String[] args) throws Exception{
//        ReadExcel readExcel=new ReadExcel();
//        File file=new File("/Users/zhangyibin/Downloads/运营_厂家下游.xlsx");
//        System.out.println(
//                readExcel.setExcelCell(file,"sheet1",1)
//        );
//
//    }

}
