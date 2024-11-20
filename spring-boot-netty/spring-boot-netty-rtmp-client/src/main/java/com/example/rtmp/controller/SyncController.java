package com.example.rtmp.controller;

import com.example.rtmp.entities.dto.SyncStartRequestDto;
import com.example.rtmp.entities.vo.SyncVo;
import com.example.rtmp.service.SyncService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fzy
 * @description:
 * @date 2024/11/19 16:42
 */
@RestController
public class SyncController {

    @Resource
    private SyncService syncService;

    @PutMapping("/sync/start")
    public ResponseEntity<SyncVo> start(@RequestBody SyncStartRequestDto requestDto) {
        return ResponseEntity.ok(syncService.startSync(requestDto));
    }
}
