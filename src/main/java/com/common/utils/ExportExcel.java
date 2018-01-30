package com.common.utils;

;
;

/**
 * web开发中，一个系统的普通需求也包括导出excel，一般采用POI做统计报表导出excel。
 * @author：Kyle.yangkg
 * @create：2018-01-29 上午 9:54
 * ©copyright：www.aisino.com
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportExcel {

    private ExportExcel() {
        super();
    }

    public static void exportExcel(List<Object> list, Map<Integer, Long> map,
                                   String[] titles) throws IOException {
        // 创建Excel文档
        HSSFWorkbook hwb = new HSSFWorkbook();
        // sheet 对应一个工作页
        HSSFSheet sheet = hwb.createSheet("exportReport");
        int colNum = titles.length;
        // 创建第一行
        HSSFRow firstrow = sheet.createRow(0);
        HSSFCell[] firstcell = new HSSFCell[colNum];
        for (int col = 0; col < colNum; col++) {
            firstcell[col] = firstrow.createCell(col);
            firstcell[col].setCellValue(new HSSFRichTextString(titles[col]));
        }

        // 插入记录
        int rowNum = map.size();
        for (int i = 0; i < rowNum; i++) {
            // 从第二行开始
            HSSFRow row = sheet.createRow(i + 1);
            // 插入list中的字段
            for (int col = 0; col < colNum - 2; col++) {
                HSSFCell cell = row.createCell(col);
                cell.setCellValue(list.get(col).toString());
            }
            // 插入月份或日期
            row.createCell(colNum - 2).setCellValue(i + 1);
            // 插入总量
            row.createCell(colNum - 1).setCellValue(map.get(i + 1));
        }
        String fileName = titles[1].substring(0, 2);
        if (colNum == 4) {
            fileName += list.get(0) + "_" + list.get(1) + "年_年度报表";
        } else if (colNum == 5) {
            fileName += list.get(0) + "_" + list.get(1) + "年" + list.get(2)
                    + "月_月度报表";
        }
        // 创建文件输出流，准备输出电子表格
        OutputStream out = new FileOutputStream("../webapps/UsedMallMinaServer/files/"
                + fileName + ".xls");
        hwb.write(out);
        out.close();
    }
}
