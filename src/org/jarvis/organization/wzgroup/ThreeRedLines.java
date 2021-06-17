package org.jarvis.organization.wzgroup;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

    /*
    三条红线：

    分类	    红线	计算逻辑
    A(A股)	1	剔除预收款后的资产负债率
	        2	(负债合计 * 有息负债率 / 100 - 货币资金) / 所有者权益合计
	        3	货币资金 / (短期借款 + 一年内到期非流动负债)
    H(港股)	1	资产负债率
	        2	(带息负债 - 流动负债合计 * (货币资金 / 流动负债合计)) / 权益合计
	        3	流动负债合计 * (货币资金/流动负债合计) / 短期借债
     *
     */


/*
 * 国内上市：http://stockpage.10jqka.com.cn/000656/
 * 香港上市：http://stockpage.10jqka.com.cn/HK01895/
 */

public class ThreeRedLines {

    private String path = "";
    private int yearColumn = 0;

    private void setPath(String path) {
        this.path = path;

    }

    private String getPath() {
        return this.path;

    }

    private void setYearColumn(int yearColumn) {
        this.yearColumn = yearColumn;

    }

    private Integer getYearColumn() {
        return this.yearColumn;

    }

    public ThreeRedLines(String path, int yearColumn) {
        this.setPath(path);
        this.setYearColumn(yearColumn);

    }


    /**
     * 读取Excel数据
     *
     * @param yearColumn 年份列
     * @return
     * @throws Exception
     * @atuhor 明霄
     */
    private Map<String, String> ReadExcel(String path, int yearColumn) throws Exception {
        Map<String, String> map = new LinkedHashMap<String, String>();
        File file = new File(path);
        InputStream inputStream = new FileInputStream(file);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet hssfSheet = hssfWorkbook.getSheet("Worksheet");
        int row = hssfSheet.getPhysicalNumberOfRows();
        for (int i = 1; i < row; i++) {
            HSSFRow hssfRow = hssfSheet.getRow(i);
            HSSFCell name = hssfRow.getCell(0);
            String data = "";
            try {
                HSSFCell hssfCell = hssfRow.getCell(yearColumn);
                data = hssfCell.toString();
            } catch (Exception e) {
                data = "";
            }
            map.put(name.toString(), data);

        }
        return map;
    }

    /**
     * 负债合计
     *
     * @return
     * @author 明霄
     */
    private Double getTotalLiabilities() throws Exception {
        Map<String, String> outMap = new LinkedHashMap<String, String>();
        outMap.putAll(this.ReadExcel(this.getPath(), this.getYearColumn()));
        String strTotalLiabilities = outMap.get("负债合计");
        System.out.println("负债合计," + strTotalLiabilities);
        Double totalLiabilities = Double.valueOf(strTotalLiabilities.substring(0, strTotalLiabilities.length() - 1));
        return totalLiabilities;

    }

    /**
     * 预付款项、按金及其他应收款项
     *
     * @return
     * @throws Exception
     * @author 明霄
     */
    private Double getPrepaymentDepositsAndOtherReceivables() throws Exception {
        Map<String, String> outMap = new LinkedHashMap<String, String>();
        outMap.putAll(this.ReadExcel(this.getPath(), this.getYearColumn()));
        String strPrepaymentDepositsAndOtherReceivables = outMap.get("预付款项、按金及其他应收款项");
        System.out.println("预付款项、按金及其他应收款项," + strPrepaymentDepositsAndOtherReceivables);
        Double prepaymentDepositsAndOtherReceivables = Double.valueOf(strPrepaymentDepositsAndOtherReceivables
                .substring(0, strPrepaymentDepositsAndOtherReceivables.length() - 1));
        return prepaymentDepositsAndOtherReceivables;

    }

    /**
     * 资产合计
     *
     * @return
     * @throws Exception
     * @author 明霄
     */

    private Double getTotalAssets() throws Exception {
        Map<String, String> outMap = new LinkedHashMap<String, String>();
        outMap.putAll(this.ReadExcel(this.getPath(), this.getYearColumn()));
        String strTotalAssets = outMap.get("资产合计");
        System.out.println("资产合计," + strTotalAssets);
        Double totalAssets = Double.valueOf(strTotalAssets.substring(0, strTotalAssets.length() - 1));
        return totalAssets;

    }

    /**
     * 负债和股东权益总计
     *
     * @return
     * @throws Exception
     * @author 明霄
     */

    private Double getTotalLiabilitiesAndShareholdersEquity() throws Exception {
        Map<String, String> outMap = new LinkedHashMap<String, String>();
        outMap.putAll(this.ReadExcel(this.getPath(), this.getYearColumn()));
        String strTotalLiabilitiesAndShareholdersEquity = outMap.get("负债和股东权益总计");
        System.out.println("负债和股东权益总计," + strTotalLiabilitiesAndShareholdersEquity);
        Double totalLiabilitiesAndShareholdersEquity = Double.valueOf(strTotalLiabilitiesAndShareholdersEquity
                .substring(0, strTotalLiabilitiesAndShareholdersEquity.length() - 1));
        return totalLiabilitiesAndShareholdersEquity;

    }

    /**
     * 货币资金
     *
     * @return
     * @throws Exception
     * @author 明霄
     */
    private Double getMoneyFunds() throws Exception {
        Map<String, String> outMap = new LinkedHashMap<String, String>();
        outMap.putAll(this.ReadExcel(this.getPath(), this.getYearColumn()));
        String strMoneyFunds = outMap.get("货币资金");
        System.out.println("货币资金," + strMoneyFunds);
        Double moneyFunds = Double.valueOf(strMoneyFunds.substring(0, strMoneyFunds.length() - 1));
        return moneyFunds;

    }

    /**
     * 权益合计
     *
     * @return
     * @throws Exception
     * @author 明霄
     */

    private Double getTotalEquity() throws Exception {
        Map<String, String> outMap = new LinkedHashMap<String, String>();
        outMap.putAll(this.ReadExcel(this.getPath(), this.getYearColumn()));
        String strTotalEquity = outMap.get("权益合计");
        System.out.println("权益合计," + strTotalEquity);
        Double totalEquity = Double.valueOf(strTotalEquity.substring(0, strTotalEquity.length() - 1));
        return totalEquity;

    }

    /**
     * 短期借款
     *
     * @return
     * @throws Exception
     * @author 明霄
     */

    private Double getShort_termLoan() throws Exception {
        Map<String, String> outMap = new LinkedHashMap<String, String>();
        outMap.putAll(this.ReadExcel(this.getPath(), this.getYearColumn()));
        String strShort_termLoan = outMap.get("短期借款");
        System.out.println("短期借款," + strShort_termLoan);
        Double short_termLoan = Double.valueOf(strShort_termLoan.substring(0, strShort_termLoan.length() - 1));
        return short_termLoan;

    }

    /**
     * 流动负债
     *
     * @return
     * @throws Exception
     * @author 明霄
     */
    private Double getCurrentLiabilities() throws Exception {
        Map<String, String> outMap = new LinkedHashMap<String, String>();
        outMap.putAll(this.ReadExcel(this.getPath(), this.getYearColumn()));
        String strCurrentLiabilities = outMap.get("流动负债");
        System.out.println("流动负债," + strCurrentLiabilities);
        Double currentLiabilities = Double.valueOf(strCurrentLiabilities.substring(0, strCurrentLiabilities.length() - 1));
        return currentLiabilities;

    }

    /**
     * 长期借款
     *
     * @return
     * @throws Exception
     * @author 明霄
     */

    private Double getLongTermLoan() throws Exception {
        Map<String, String> outMap = new LinkedHashMap<String, String>();
        outMap.putAll(this.ReadExcel(this.getPath(), this.getYearColumn()));
        String strLongTermLoan = outMap.get("长期借款");
        System.out.println("长期借款," + strLongTermLoan);
        Double longTermLoan = Double.valueOf(strLongTermLoan.substring(0, strLongTermLoan.length() - 1));
        return longTermLoan;


    }


    /**
     * 计算三条红线
     *
     * @throws Exception
     * @author 明霄
     */

    public void getThreeRedLines() throws Exception {

        System.out.println("---明霄网上的计算公式：-----");
        //红线一指标：剔除预收款后的资产负债率=(总负债-预收)/(总资产-预收)
        System.out.println("剔除预收款后的资产负债率:"
                + ((this.getTotalLiabilities() - this.getPrepaymentDepositsAndOtherReceivables()) / (this.getTotalAssets()
                - this.getPrepaymentDepositsAndOtherReceivables())));

        System.out.println("---------");

        //红线二指标：净负债率=(有息负债-货币资金)/合并权益
//        System.out.println("净负债率(有息负债取\"负债和股东权益总计\"):"
//                + ((this.getTotalLiabilitiesAndShareholdersEquity() - this.getMoneyFunds()) / this.getTotalEquity()));
//
//        System.out.println("---------");
//
//        System.out.println("净负债率(有息负债取\"负债合计\"):"
//                + ((this.getTotalLiabilities() - this.getMoneyFunds()) / this.getTotalEquity()));
//
//        System.out.println("---------");
//
//        System.out.println("净负债率(有息负债取\"长期借款\"):"
//                + ((this.getLongTermLoan() - this.getMoneyFunds()) / this.getTotalEquity()));
//
//        System.out.println("---------");

        //红线三指标：现金短债比=货币资金/短期有息债务
        System.out.println("现金短债比:"
                + (this.getMoneyFunds() / this.getShort_termLoan()));

    }


    public static void main(String[] args) throws Exception {
        /*
         * 万科企业：HK2202
         * 富力地产：HK2777
         * 中国恒大：HK3333
         *
         */

        String[] address = new String[]{"HK1895_debt_report.xls"};

        for (String str : address) {
            System.out.println("-----" + str + "-----");
            ThreeRedLines threeRedLines =
                    new ThreeRedLines("/Users/zhangyibin/Downloads/" + str,
                            1);
            threeRedLines.getThreeRedLines();
            System.out.println("");


        }

//        BigDecimal db = new BigDecimal(threeRedLines.getCurrentLiabilities());
//        String ii = db.toPlainString();
//        System.out.println(ii);
//        Map<String, String> outMap = new LinkedHashMap<String, String>();
//        outMap.putAll(threeRedLines.ReadExcel(1));
//        Iterator iterator = outMap.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry entry = (Map.Entry) iterator.next();
//            Object key = entry.getKey();
//            Object value = entry.getValue();
//            System.out.println(key + "," + value);
//        }

    }


}
