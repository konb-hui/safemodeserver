package com.changhong.safemodeserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.changhong.safemodeserver.entity.StolenTvInfo;
import com.changhong.safemodeserver.entity.User;
import com.changhong.safemodeserver.entity.result.R;
import com.changhong.safemodeserver.entity.result.ResultCode;
import com.changhong.safemodeserver.entity.vo.StolenTvInfoVo;
import com.changhong.safemodeserver.excel.data.message.StolenTvInfoMessage;
import com.changhong.safemodeserver.service.StolenTvInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author konb
 */
@RestController
@RequestMapping("stolenTvInfo")
@CrossOrigin
public class StolenTvInfoController {

    @Autowired
    private StolenTvInfoService stolenTvInfoService;

    private List<String> data = new ArrayList<>();


    @PostMapping("add")
    public R add(@RequestBody StolenTvInfo stolenTvInfo, HttpServletRequest request) {
        if (!checkUser(request)) {
            return R.error().message("请先登录");
        }
        QueryWrapper<StolenTvInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mac", stolenTvInfo.getMac());
        StolenTvInfo info = this.stolenTvInfoService.getOne(queryWrapper);
        if (info != null) {
            return R.error();
        }
        boolean add = this.stolenTvInfoService.save(stolenTvInfo);
        if (add) {
            return R.ok().message("添加成功").data("info", stolenTvInfo);
        }
        return R.error().message("添加失败");
    }

    @PostMapping("update")
    public R update(@RequestBody StolenTvInfo stolenTvInfo, HttpServletRequest request) {
        if (!checkUser(request)) {
            return R.error().message("请先登录");
        }
        boolean update = this.stolenTvInfoService.updateById(stolenTvInfo);
        if (update) {
            return R.ok().message("修改成功").data("info", stolenTvInfo);
        }
        return R.error().message("修改失败");
    }

    @GetMapping("listById/{id}")
    public R listById(@PathVariable String id) {
        StolenTvInfo stolenTvInfo = this.stolenTvInfoService.getById(id);
        if (stolenTvInfo == null) {
            return R.error().message("查询失败");
        }
        return R.ok().message("查询成功").data("info", stolenTvInfo);
    }

    @PostMapping("/list")
    public R list(@RequestBody(required = false) StolenTvInfoVo stolenTvInfoVo) {
        System.out.println(stolenTvInfoVo);
        QueryWrapper<StolenTvInfo> queryWrapper = stolenTvInfoVo.getWrapper();
        IPage<StolenTvInfo> page = new Page<>();
        if (stolenTvInfoVo.getCurrent() != null && stolenTvInfoVo.getCurrent() > 0) {
            page.setCurrent(stolenTvInfoVo.getCurrent());
        }
        if (stolenTvInfoVo.getSize() != null && stolenTvInfoVo.getSize() > 0) {
            page.setSize(stolenTvInfoVo.getSize());
        }
        this.stolenTvInfoService.page(page, queryWrapper);
        return R.ok().message("查询成功").data("data", page);
    }

    @PostMapping("importInfo")
    public R importInfo(MultipartFile file, HttpServletRequest request) {
        if (!checkUser(request)) {
            return R.error().message("请先登录");
        }
        List<StolenTvInfoMessage> messageList = this.stolenTvInfoService.importExcel(file);
        if (messageList == null) {
            return R.error().message("导入失败");
        }
        if (messageList.size() > 0) {
            return R.ok().code(ResultCode.HALF_SUCCESS).data("errorMessage", messageList);
        }
        return R.ok().message("导入成功");
    }

    @PostMapping("checkMac")
    public R checkMac(@RequestBody StolenTvInfo stolenTvInfo) {
        if (data.size() <= 0) {
            List<StolenTvInfo> stolenTvInfos = this.stolenTvInfoService.list(null);
            for (int i = 0; i < stolenTvInfos.size(); i++) {
                this.data.add(stolenTvInfos.get(i).getMac());
            }
        }
        if (this.data.contains(stolenTvInfo.getMac())) {
            return R.ok().code(ResultCode.MAC_SUCCESS);
        }
        return R.ok().code(ResultCode.MAC_ERROR);
//        QueryWrapper<StolenTvInfo> stolenTvInfoQueryWrapper = new QueryWrapper<>();
//        stolenTvInfoQueryWrapper.eq("mac", stolenTvInfo.getMac());
//        StolenTvInfo info = this.stolenTvInfoService.getOne(stolenTvInfoQueryWrapper);
//        if (info == null) {
//            return R.ok().code(ResultCode.MAC_ERROR);
//        }
//        return R.ok().code(ResultCode.MAC_SUCCESS);
    }

    @GetMapping("delete/{id}")
    public R delete(@PathVariable String id, HttpServletRequest request) {
        if (!checkUser(request)) {
            return R.error().message("请先登录");
        }
        boolean delete = this.stolenTvInfoService.removeById(id);
        if (delete) {
            return R.ok().message("删除成功");
        }
        return R.error().message("删除失败");
    }

    @PostMapping("deleteMultiple")
    public R deleteMultiple(@RequestBody(required = false) String ids, HttpServletRequest request) {
        if (!checkUser(request)) {
            return R.error().message("请先登录");
        }
        if (StringUtils.isEmpty(ids)) {
            return R.error();
        }
        String[] id = ids.substring(1, ids.length() - 1).split(",");
        if (id.length < 1) {
            return R.error();
        }
        boolean delete = this.stolenTvInfoService.removeByIds(Arrays.asList(id));
        System.out.println(Arrays.asList(id));
        if (delete) {
            return R.ok();
        }
        return R.error();
    }

    private boolean checkUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        return user != null;
    }

}
