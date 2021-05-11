package com.changhong.safemodeserver.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author konb
 */
public class ExcelUtils {

    public static List<Object> readExcel(File file, Class clazz,  ReadListener readListener) {
        List<Object> dataList = new ArrayList<>();
        //读取上传的文件
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(file, clazz, readListener).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally{
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
        return dataList;
    }

}
