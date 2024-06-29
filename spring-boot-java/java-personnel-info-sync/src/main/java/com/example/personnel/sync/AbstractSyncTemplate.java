package com.example.personnel.sync;

import com.example.personnel.model.dto.Credential;
import com.example.personnel.model.dto.ExternalConfig;

/**
 * @author iumyxF
 * @description:
 * @date 2024/6/28 10:13
 */
public abstract class AbstractSyncTemplate implements SyncService {


    @Override
    public void doDepartmentalSyncTask() {
        ExternalConfig config = getExternalConfig();
        Credential credential = getCredential(config);
        //syncDepartment();
    }

    @Override
    public void doUserSyncTask() {
        ExternalConfig config = getExternalConfig();
        Credential credential = getCredential(config);
        syncUser(credential);
    }

    protected abstract ExternalConfig getExternalConfig();

    protected abstract Credential getCredential(ExternalConfig config);

    //protected abstract void syncDepartment();

    protected abstract void syncUser(Credential credential);
}
