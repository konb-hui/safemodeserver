package com.changhong.safemodeserver.excel.data;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author konb
 */
@Data
public class StolenTvInfoData {

    @ExcelProperty("Model")
    private String model;

    @ExcelProperty("MAC")
    private String mac;

    @ExcelProperty("SN")
    private String sn;

    @ExcelProperty("Device ID")
    private String deviceId;

}
