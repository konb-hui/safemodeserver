package com.changhong.safemodeserver.excel.data.message;

import com.changhong.safemodeserver.excel.data.StolenTvInfoData;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author konb
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StolenTvInfoMessage extends StolenTvInfoData {

    private String message;

}
