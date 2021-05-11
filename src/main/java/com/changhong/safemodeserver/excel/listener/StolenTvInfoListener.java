package com.changhong.safemodeserver.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.changhong.safemodeserver.excel.data.StolenTvInfoData;
import com.changhong.safemodeserver.excel.data.message.StolenTvInfoMessage;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author konb
 */
public class StolenTvInfoListener extends AnalysisEventListener<StolenTvInfoData> {

    private List<StolenTvInfoData> stolenTvInfoDataList;

    private List<StolenTvInfoMessage> messageList;

    private List<String> macList = new ArrayList<>();

    public StolenTvInfoListener(List<StolenTvInfoData> stolenTvInfoDataList) {
        this.stolenTvInfoDataList = stolenTvInfoDataList;
    }

    public StolenTvInfoListener(List<StolenTvInfoData> stolenTvInfoDataList, List<StolenTvInfoMessage> messageList) {
        this.stolenTvInfoDataList = stolenTvInfoDataList;
        this.messageList = messageList;
    }

    @Override
    public void invoke(StolenTvInfoData data, AnalysisContext context) {
        if (this.checkData(data)) {
            this.stolenTvInfoDataList.add(data);
        }
        this.macList.add(data.getMac());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("解析完成");
    }

    private boolean checkData(StolenTvInfoData data) {
        boolean check = true;
        int index = macList.indexOf(data.getMac());
        if (macList.size() > 0 && index >= 0) {
            StolenTvInfoMessage message = new StolenTvInfoMessage();
            BeanUtils.copyProperties(data, message);
            message.setMessage("当前MAC地址已存在，同" + (index + 1));
            this.messageList.add(message);
            check = false;
        }
        return check;
    }

}
