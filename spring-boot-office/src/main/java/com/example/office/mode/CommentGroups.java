package com.example.office.mode;

import lombok.Data;

import java.util.List;

/**
 * @author iumyxF
 * @description:
 * @date 2023/12/15 9:52
 */
@Data
public class CommentGroups {

    private List<String> edit;
    private List<String> remove;
    private String view;
}
