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
@TableName("t_dict")
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典id
     */
    @TableId(value = "dict_id", type = IdType.AUTO)
    private Long dictId;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 字典编码
     */
    private String code;

    /**
     * 字典值
     */
    private String value;


}
