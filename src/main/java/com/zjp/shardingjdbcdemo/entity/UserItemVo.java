package com.zjp.shardingjdbcdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ClassName: UserItemVo
 * Package: com.zjp.shadingjdbcdemo.entity
 * Description:
 *
 * @author zjp
 * @version 1.0
 * @data: 2024/11/19 10:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserItemVo {
    /**
     * 用户编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 用户年龄
     */
    private Integer age;

    /**
     * 用户薪资
     */
    private Double salary;

    /**
     * 用户生日
     */

    private LocalDateTime birthday;

    private Long itemId;
}
