package com.changhong.safemodeserver.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author konb
 */
@Data
public class StolenTvInfo {

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    private String model;

    private String mac;

    private String sn;

    private String deviceId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date editTime;

}

