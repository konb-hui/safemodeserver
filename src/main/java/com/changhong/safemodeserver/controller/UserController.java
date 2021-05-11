package com.changhong.safemodeserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.changhong.safemodeserver.entity.User;
import com.changhong.safemodeserver.entity.result.R;
import com.changhong.safemodeserver.entity.vo.UserVo;
import com.changhong.safemodeserver.service.UserService;
import com.changhong.safemodeserver.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author konb
 */
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public R login(@RequestBody User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userWrapper.eq("username", user.getUsername());
        User u = this.userService.getOne(userWrapper);
        if (u == null || !u.getPassword().equals(StringUtils.encrypt(user.getPassword()))) {
            return R.error().message("用户名或密码错误");
        }
        session.setAttribute("user", user);
        return R.ok().message("登录成功");
    }

    @GetMapping("getUserName")
    private R getUserName(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        if (loginUser == null) {
            return R.error().message("请先登录");
        }
        return R.ok().data("user", loginUser);
    }

    @PostMapping("updateUser")
    public R updateUser(@RequestBody UserVo userVo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        if (loginUser == null) {
            return R.error().message("请先登录");
        }
        List<User> userList = this.userService.list(null);
        if (!userList.get(0).getPassword().equals(StringUtils.encrypt(userVo.getOldPassword()))) {
            return R.error().message("旧密码错误");
        }
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        user.setPassword(StringUtils.encrypt(user.getPassword()));
        boolean update = this.userService.updateById(user);
        if (update) {
            return R.ok();
        }
        return R.error().message("更新失败");
    }

    @GetMapping("logout")
    public R logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        return R.ok();
    }

}
