package com.example.office.mode;

import lombok.Data;

import java.util.List;

/**
 * @author iumyxF
 * @description:
 * @date 2023/12/15 9:51
 */
@Data
public class EditorConfig {

    private String mode;
    private String callbackUrl;
    private String lang;
    private String createUrl;
    private List<String> templates;
    private User user;
    private Customization customization;

    private boolean canCoAuthoring;
    private boolean canUseHistory;
    private boolean canHistoryClose;
    private boolean canHistoryRestore;
    private boolean canSendEmailAddresses;
    private boolean canRequestEditRights;
    private boolean canRequestClose;
    private boolean canRename;
    private boolean canMakeActionLink;
    private boolean canRequestUsers;
    private boolean canRequestSendNotify;
    private boolean canRequestSaveAs;
    private boolean canRequestInsertImage;
    private boolean canRequestMailMergeRecipients;
}
