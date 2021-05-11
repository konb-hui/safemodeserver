package com.changhong.safemodeserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.changhong.safemodeserver.entity.StolenTvInfo;
import com.changhong.safemodeserver.excel.data.StolenTvInfoData;
import com.changhong.safemodeserver.excel.data.message.StolenTvInfoMessage;
import com.changhong.safemodeserver.excel.listener.StolenTvInfoListener;
import com.changhong.safemodeserver.mapper.StolenTvInfoMapper;
import com.changhong.safemodeserver.service.StolenTvInfoService;
import com.changhong.safemodeserver.util.ExcelUtils;
import com.changhong.safemodeserver.util.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author konb
 */
@Service
public class StolenTvInfoServiceImpl extends ServiceImpl<StolenTvInfoMapper, StolenTvInfo> implements StolenTvInfoService {

    private final static String XLS = "xls";
    private final static String XLSX = "xlsx";

    @Override
    public List<StolenTvInfoMessage> importExcel(MultipartFile file) {
        if (file == null) {
            return null;
        }
        if (!file.getOriginalFilename().endsWith(XLS) && !file.getOriginalFilename().endsWith(XLSX)) {
            System.out.println("filename");
            return null;
        }
        File newFile = FileUtils.multipartFileToFile(file);
        List<StolenTvInfoData> stolenTvInfoDataList = new ArrayList<>();
        List<StolenTvInfoMessage> messageList = new ArrayList<>();
        ExcelUtils.readExcel(newFile, StolenTvInfoData.class, new StolenTvInfoListener(stolenTvInfoDataList, messageList));
        if (stolenTvInfoDataList.size() < 1) {
            System.out.println("size");
            return null;
        }
        this.checkAndAdd(stolenTvInfoDataList, messageList);
        return messageList;
    }

    /**
     * 添加和检查excel表的数据
     */
    private void checkAndAdd(List<StolenTvInfoData> stolenTvInfoDataList, List<StolenTvInfoMessage> messageList) {
        List<StolenTvInfo> allDataList = this.list(null);
        for (StolenTvInfoData stolenTvInfoData : stolenTvInfoDataList) {
            if (this.checkExistence(allDataList, stolenTvInfoData)) {
                StolenTvInfoMessage message = new StolenTvInfoMessage();
                BeanUtils.copyProperties(stolenTvInfoData, message);
                message.setMessage("该条数据的MAC地址在数据库中已经存在");
                messageList.add(message);
            } else {
                StolenTvInfo stolenTvInfo = new StolenTvInfo();
                BeanUtils.copyProperties(stolenTvInfoData, stolenTvInfo);
                this.save(stolenTvInfo);
            }
        }
    }

    /**
     * 检查数据库中当前记录是否已存在
     */
    private boolean checkExistence(List<StolenTvInfo> stolenTvInfoList, StolenTvInfoData stolenTvInfoData) {
        for (StolenTvInfo info: stolenTvInfoList
             ) {
            if (info.getMac().equals(stolenTvInfoData.getMac())) {
                return true;
            }
        }
        return false;
    }
}
