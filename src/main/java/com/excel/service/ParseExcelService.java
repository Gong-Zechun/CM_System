//package com.excel.service;
//
//import org.apache.poi.ss.usermodel.Workbook;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author AllenGong
// * @version V1.0
// * @date 2016-10-31 9:37
// */
//public class ParseExcelService {
//    public static void main(String[] args) {
//        //读取文件夹中所有文件
//        File file = new File("D:\\User\\Desktop\\excel files");
//        String test[];
//        //list()方法可以读取到当前文件的所有文件
//        test = file.list();
//        for (int i = 0; i < test.length; i++) {
//            System.out.println(test[i]);
//        }
//        System.out.println("扫描的Excel文件个数为：" + test.length);
//        System.out.println(test[1]);
//    }
//
//    public void parseExcel(String file) {
//        try {
//            File excelFile = new File(file);
//
//            FileInputStream fis = null;
//
//            Workbook workbook = null;
//
//            fis = new FileInputStream(excelFile);
//
//            //当前处理方式兼容Excel2003、Excel2007和Excel2010
//            workbook = WorkbookFactory.create(fis);
//
//            //获取workbook
//            Sheet sheet = workbook.getSheetAt(0);
//
//            int rowCount = sheet.getPhysicalNumberOfRows();
//
//            //遍历excel每行
//            for(int lineNum = 0; lineNum < rowCount; lineNum++) {
//                Row cellValueRow = sheet.getRow(lineNum);
//                Map<String, String> rowMap = new HashMap<>();
//
//                //如果监测到当前行首个单元格未空白，跳出循环
//                if(StringUtil.isEmpty(cellValueRow.getCell(0).toString())) {
//                    System.out.println("文件名为" + "遍历的有效行数为：");
//                    break;
//                }
//
//            }
//
//
//
//            fis.close();
//
//        }catch(IOException e) {
//
//        }finally{
//
//        }
//
//    }
//}
