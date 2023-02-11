package com.example.mock.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 猪肉库存的数据库实体类
 * @Date 2023/2/11 10:40
 * @Author fzy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "pork_storage", autoResultMap = true)
public class PorkStorage {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long cnt;
}
