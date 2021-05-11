package com.changhong.safemodeserver;

import com.changhong.safemodeserver.entity.StolenTvInfo;
import com.changhong.safemodeserver.entity.User;
import com.changhong.safemodeserver.service.StolenTvInfoService;
import com.changhong.safemodeserver.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SafeModeServerApplicationTests {

    @Autowired
    private StolenTvInfoService stolenTvInfoService;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {

    }

}
