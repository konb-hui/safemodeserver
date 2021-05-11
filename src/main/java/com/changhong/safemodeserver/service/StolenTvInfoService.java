package com.changhong.safemodeserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.changhong.safemodeserver.entity.StolenTvInfo;
import com.changhong.safemodeserver.excel.data.message.StolenTvInfoMessage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author konb
 */
public interface StolenTvInfoService extends IService<StolenTvInfo> {
    List<StolenTvInfoMessage> importExcel(MultipartFile file);
}
