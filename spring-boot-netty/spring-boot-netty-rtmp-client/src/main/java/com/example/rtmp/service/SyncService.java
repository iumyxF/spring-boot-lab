package com.example.rtmp.service;

import com.example.rtmp.entities.dto.SyncSearchRequestDto;
import com.example.rtmp.entities.dto.SyncStartRequestDto;
import com.example.rtmp.entities.vo.SyncVo;

import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2024/11/19 17:07
 */
public interface SyncService {

    /**
     * 发起同屏
     *
     * @param request 同屏请求
     * @return 结果
     */
    SyncVo startSync(SyncStartRequestDto request);

    /**
     * 查询同屏
     *
     * @param request 查询请求
     * @return 同屏信息
     */
    List<SyncVo> searchSync(SyncSearchRequestDto request);

    /**
     * 结束同屏
     *
     * @param syncKey 同屏主键
     */
    void endSync(String syncKey);
}
