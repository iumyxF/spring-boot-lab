package com.example.office.mode;

import lombok.Data;

/**
 * @author iumyxF
 * @description:
 * @date 2023/12/15 9:50
 */
@Data
public class Config {

    private String type;
    private String mode;
    private String documentType;

    private Document document;
    private EditorConfig editorConfig;

    private String height;
    private String width;
    private String token;
    private String frameEditorId;

}
