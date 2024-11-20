package com.zjp.shardingjdbcdemo.service.impl;

import com.zjp.shardingjdbcdemo.entity.UserItem;
import com.zjp.shardingjdbcdemo.mapper.UserItemMapper;
import com.zjp.shardingjdbcdemo.service.IUserItemService;
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
public class UserItemServiceImpl extends ServiceImpl<UserItemMapper, UserItem> implements IUserItemService {

}
