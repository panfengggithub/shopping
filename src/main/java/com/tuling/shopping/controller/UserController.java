package com.tuling.shopping.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.tuling.shopping.model.User;
import com.tuling.shopping.service.IUserService;
import com.tuling.shopping.utils.AjaxResult;
import com.tuling.shopping.utils.SearchUtil;
import com.tuling.shopping.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2022-05-13
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
//    @GetMapping("/getUser")
//    public AjaxResult getUser(@RequestParam(name ="pageNum", required =false, defaultValue ="1")int pageNum,
//                              @RequestParam(name ="pageSize", required =false, defaultValue ="15")int pageSize,
//                              @RequestParam(name ="condition",required =false) String conditionJson){

//        return AjaxResult.success(userService.getById("1"));
//        QueryWrapper queryWrapper = SearchUtil.parseWhereSql(conditionJson);
//        IPage<User> pageTestList = userService.getPageTestList(queryWrapper,pageNum, pageSize);
//        if (pageTestList.isSearchCount()) {
//            return AjaxResult.success("成功", pageTestList);
//        }else {
//            return AjaxResult.error("用户名或密码错误");
//        }
//    }
    @GetMapping("/getUserInfo")
    public AjaxResult getUserInfo(@RequestParam(name ="userName")String userName,@RequestParam(name ="passWord")String passWord){
        try {
            if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)) return AjaxResult.error("参数不正确");
            return userService.getByName(userName,passWord);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("请求失败");
        }
    }
    @PostMapping("/getUserInfo")
    public AjaxResult registUser(@RequestParam(name ="user")User user){
        try {
            if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getUserPwd())) return AjaxResult.error("参数不正确");
            return userService.registUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("请求失败");
        }
    }
    @GetMapping("/getUserList")
    public AjaxResult getUserList(@RequestParam(name ="pageNum", required =false, defaultValue ="1")int pageNum,
                              @RequestParam(name ="pageSize", required =false, defaultValue ="15")int pageSize,
                              @RequestParam(name ="condition",required =false) String conditionJson){
        try {
            List<User> userList = userService.getUserList(pageNum, pageSize);
            return AjaxResult.success("请求成功",userList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("请求失败");
        }
    }
    @GetMapping("/getUserById")
    public AjaxResult getUser(@RequestParam(name ="user_id",required =true) String user_id){
        try {
            if (StringUtils.isEmpty(user_id))return AjaxResult.error("缺少参数");
            return AjaxResult.success("请求成功",userService.getById(user_id));
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("请求失败");
        }
    }
}
