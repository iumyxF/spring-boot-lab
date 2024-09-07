package com.example.excel.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2024/9/7 15:52
 */
@AllArgsConstructor
@Data
public class WriteBo {

    private List<User> dataList;

    private Integer index;
}
