package com.changhong.safemodeserver.entity.vo;

import lombok.Data;

/**
 * @author konb
 */
@Data
public class UserVo {

    private String id;

    private String username;

    private String password;

    private String oldPassword;

    private String checkPassword;

}
