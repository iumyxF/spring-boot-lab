package com.example.office.mode;

import lombok.Data;

/**
 * @author iumyxF
 * @description:
 * @date 2023/12/15 9:52
 */
@Data
public class Permissions {

    private boolean comment;
    private boolean copy;
    private boolean download;
    private boolean edit;
    private boolean print;
    private boolean fillForms;
    private boolean modifyFilter;
    private boolean modifyContentControl;
    private boolean review;
    private CommentGroups commentGroups;
}
