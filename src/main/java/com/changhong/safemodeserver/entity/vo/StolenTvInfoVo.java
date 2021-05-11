package com.changhong.safemodeserver.entity.vo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.changhong.safemodeserver.entity.StolenTvInfo;
import lombok.Data;

/**
 * @author konb
 */
@Data
public class StolenTvInfoVo {

    private String model;

    private String mac;

    private String sn;

    private String deviceId;

    private Integer current;

    private Integer size;

    public QueryWrapper<StolenTvInfo> getWrapper() {
        QueryWrapper<StolenTvInfo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(this.model)) {
            queryWrapper.like("model", this.model);
        }
        if (StringUtils.isNotEmpty(this.mac)) {
            queryWrapper.like("mac", this.mac);
        }
        if (StringUtils.isNotEmpty(this.sn)) {
            queryWrapper.like("sn", this.sn);
        }
        if (StringUtils.isNotEmpty(this.deviceId)) {
            queryWrapper.like("device_id", this.deviceId);
        }
        return queryWrapper;
    }

}
