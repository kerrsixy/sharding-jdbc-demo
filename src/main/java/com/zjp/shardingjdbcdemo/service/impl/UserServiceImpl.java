package com.zjp.shardingjdbcdemo.service.impl;

import com.zjp.shardingjdbcdemo.entity.User;
import com.zjp.shardingjdbcdemo.mapper.UserMapper;
import com.zjp.shardingjdbcdemo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zjp
 * @since 2024-11-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
