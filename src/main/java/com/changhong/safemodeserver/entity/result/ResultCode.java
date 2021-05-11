package com.changhong.safemodeserver.entity.result;

/**
 * @author konb
 */

public interface ResultCode {
    /**
     * 成功
     */
    public final static int SUCCESS = 200;
    /**
     * 失败
     */
    public final static int ERROR = 300;
    /**
     * 部分成功
     */
    public final static int HALF_SUCCESS = 250;
    /**
     * 查询到MAC
     */
    public final static int MAC_SUCCESS = 666;
    /**
     * 为查询到MAC
     */
    public final static int MAC_ERROR = 999;
}
