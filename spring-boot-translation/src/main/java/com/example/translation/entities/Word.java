package com.example.translation.entities;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.*;

/**
 * @author fzy
 * @description:
 * @date 2024/4/16 11:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Word {

    @ExcelIgnore
    private String index;

    @ColumnWidth(50)
    @ExcelProperty(value = "原词语", index = 0)
    private String original;

    @ColumnWidth(50)
    @ExcelProperty(value = "翻译后词语", index = 1)
    private String translated;

    @ColumnWidth(50)
    @ExcelProperty(value = "开发备注", index = 2)
    private String remarks;
}
