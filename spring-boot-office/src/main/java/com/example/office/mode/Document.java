package com.example.office.mode;

import lombok.Data;

/**
 * @author iumyxF
 * @description:
 * @date 2023/12/15 9:51
 */
@Data
public class Document {

    private String fileType;
    private String key;
    private Permissions permissions;
    private String title;
    private String url;
}
