package com.tuling.shopping.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tuling.shopping.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuling.shopping.utils.AjaxResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2022-05-13
 */
public interface IUserService extends IService<User> {
    public IPage<User> getPageEntityList(QueryWrapper queryWrapper, int pageNum, int pageSize);
    public List<User> getUserList(int pageNum, int pageSize);

    AjaxResult getByName(String userName, String passWord);

    AjaxResult registUser(User user);
}
