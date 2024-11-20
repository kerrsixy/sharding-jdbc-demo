package com.zjp.shardingjdbcdemo.mapper;

import com.zjp.shardingjdbcdemo.entity.UserItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjp.shardingjdbcdemo.entity.UserItemVo;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zjp
 * @since 2024-11-20
 */
public interface UserItemMapper extends BaseMapper<UserItem> {
    UserItemVo selectList();
}
