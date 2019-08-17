package excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;

public class RedExcel {

    public static void main(String[] args) throws Exception {
        File file = new File("/Users/zhangyibin/Downloads/2019-08-15至2019-08-15的订单.xlsx");
        InputStream inputStream = new FileInputStream(file);

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        int rowsNum = xssfSheet.getPhysicalNumberOfRows();
        double costPrice = 0.0;// 成本价
        double paymentPrice = 0.0; // 支付价

        for (int i = 0; i < rowsNum; i++) {
            if (i != 0) {
                XSSFRow xssfRow = xssfSheet.getRow(i);
                XSSFCell orderStatusXSSFCell = xssfRow.getCell(5); // 订单状态
                XSSFCell classifyXSSFCell = xssfRow.getCell(27); // 订单状态

                XSSFCell numberXSSFCell = xssfRow.getCell(12); // 商品数量
                XSSFCell costPriceXSSFCell = xssfRow.getCell(31);//成本价

                XSSFCell paymentPriceXSSFCell = xssfRow.getCell(14);//支付金额

                /*
                 * 条件：
                 * 1.订单状态【等于】付款成功
                 * 2.一级分类【不等于】金融、轻古集市、特权
                 */
                if (orderStatusXSSFCell.toString().equals("付款成功") &&
                        !(classifyXSSFCell.toString().equals("金融") || classifyXSSFCell.toString().equals("轻古集市") || classifyXSSFCell.toString().equals("特权"))
                ) {
//                    System.out.println(orderStatusXSSFCell.toString() + "_" + classifyXSSFCell.toString());

                    costPrice = costPrice + (Double.valueOf(numberXSSFCell.toString()) * Double.valueOf(costPriceXSSFCell.toString())); //实际成本汇总
                    paymentPrice = paymentPrice + (Double.valueOf(paymentPriceXSSFCell.toString())); // 支付金额汇总

                }
            }
        }
        System.out.println("当前利润：" + (paymentPrice - costPrice));

    }
}
