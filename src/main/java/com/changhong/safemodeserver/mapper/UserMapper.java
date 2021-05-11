package com.changhong.safemodeserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.changhong.safemodeserver.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author konb
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
