package com.yangkg.utilsTest;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.common.utils.ReadFromFile.readFileByLines;

/**
 * @author：Kyle.yangkg
 * @create：2018-01-26 下午 4:50
 * ©copyright：www.aisino.com
 */
public class FileUtilsTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtilsTest.class.getName());

    public static void main(String[] args) {
        String fileName = "D:\\warn.2018-01-25-1.log";
        List<String> lines = readFileByLines(fileName);
        List<QueryTime> queryTimes = new ArrayList<>();

        for (String line : lines) {
            //请求流水号：150010201711201801252024134827,总用时：966,平均用时：23,请求次数：42
            String[] tempSplit = line.split(",");
            QueryTime queryTime = new QueryTime();
            for (String keyV : tempSplit) {
                String[] strings = keyV.split("：");
                System.out.println(keyV);
                if ("请求流水号".equals(strings[0])) {
                    queryTime.setFfqqlsh(strings[1]);
                }
                if ("总用时".equals(strings[0])) {
                    queryTime.setTotalTime(strings[1]);
                }
                if ("平均用时".equals(strings[0])) {
                    queryTime.setAvrgTime(strings[1]);
                }
                if ("请求次数".equals(strings[0])) {
                    queryTime.setNum(strings[1]);
                }

            }
            queryTimes.add(queryTime);
        }


        writeExcel("D:\\2018-01-25-1.xls", queryTimes);


    }

    public static void writeExcel(String fileName, List<QueryTime> queryTimes) {
        LOGGER.info("文件{}开始写入", fileName);
        //第一步，创建一个workbook对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //第二部，在workbook中创建一个sheet对应excel中的sheet
        HSSFSheet sheet = workbook.createSheet("订单列表查询耗时统计");
        //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        HSSFRow row = sheet.createRow(0);
        //第四步，创建单元格，设置表头
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("发票请求流水号");
        cell = row.createCell(1);
        cell.setCellValue("总用时");
        cell = row.createCell(2);
        cell.setCellValue("平均用时");
        cell = row.createCell(3);
        cell.setCellValue("请求次数");

        //第五步，写入实体数据，实际应用中这些数据从数据库得到,对象封装数据，集合包对象。对象的属性值对应表的每行的值
        for (int i = 0; i < queryTimes.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            QueryTime queryTime = queryTimes.get(i);
            //创建单元格设值
            row1.createCell(0).setCellValue(queryTime.getFfqqlsh());
            row1.createCell(1).setCellValue(queryTime.getTotalTime());
            row1.createCell(2).setCellValue(queryTime.getAvrgTime());
            row1.createCell(3).setCellValue(queryTime.getNum());
        }

        //将文件保存到指定的位置
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            workbook.write(fos);
            System.out.println("写入成功");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


class QueryTime {
    private String ffqqlsh;
    private String totalTime;
    private String avrgTime;
    private String num;

    QueryTime() {
    }

    public String getFfqqlsh() {
        return ffqqlsh;
    }

    public QueryTime setFfqqlsh(String ffqqlsh) {
        this.ffqqlsh = ffqqlsh;
        return this;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public QueryTime setTotalTime(String totalTime) {
        this.totalTime = totalTime;
        return this;
    }

    public String getAvrgTime() {
        return avrgTime;
    }

    public QueryTime setAvrgTime(String avrgTime) {
        this.avrgTime = avrgTime;
        return this;
    }

    public String getNum() {
        return num;
    }

    public QueryTime setNum(String num) {
        this.num = num;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ffqqlsh", ffqqlsh)
                .append("totalTime", totalTime)
                .append("avrgTime", avrgTime)
                .append("num", num)
                .toString();
    }
}

