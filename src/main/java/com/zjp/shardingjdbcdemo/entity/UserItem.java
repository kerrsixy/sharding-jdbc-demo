package com.zjp.shardingjdbcdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zjp
 * @since 2024-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_item")
public class UserItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "item_id", type = IdType.AUTO)
    private Long itemId;

    private Long userId;


}
