package com.tuling.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuling.shopping.model.User;
import com.tuling.shopping.mapper.UserMapper;
import com.tuling.shopping.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuling.shopping.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2022-05-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
@Autowired
private UserMapper mapper;
        @Override
    public IPage<User> getPageEntityList(QueryWrapper queryWrapper, int pageNum, int pageSize){
        Page<User> page =new Page<>(pageNum,pageSize);
        IPage<User> list =page(page,queryWrapper);
        return list;
    }
    @Override
    public List<User> getUserList(int pageNum,int pageSize){
        Page<User> page =new Page<>(pageNum,pageSize);
//        IPage<User> list = mapper.getPageTestList(page);
        List<User> users = mapper.selectList(new QueryWrapper<>());
        return users;
    }

    private User getUser(String userName, String passWord){
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getUserName, userName);
        queryWrapper.eq(User::getUserPwd, passWord);
        User user = mapper.selectOne(queryWrapper);
        return user;
    }
    private User getUser(String userName){
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getUserName, userName);
        User user = mapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public AjaxResult getByName(String userName, String passWord) {
        try {
            User user = getUser(userName,passWord);
            if (null != user){
                return AjaxResult.success("获取成功",user);
            }else {
                return AjaxResult.error("用户名或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("请求失败");
        }
    }

    @Override
    public AjaxResult registUser(User use) {
        try {
            User user = getUser(use.getUserName());
            if (null != user){
                return AjaxResult.error("该用户名已被占用，请修改用户名");
            }else {
                int insert = mapper.insert(use);
                if (insert>0) return AjaxResult.success("注册成功");
            }
            return AjaxResult.error("注册失败");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("注册失败");
        }
    }
}
