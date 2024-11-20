package com.example.rtmp.service;

import cn.hutool.core.util.RandomUtil;
import com.example.rtmp.config.MediaConfig;
import com.example.rtmp.entities.dto.SyncSearchRequestDto;
import com.example.rtmp.entities.dto.SyncStartRequestDto;
import com.example.rtmp.entities.po.SyncPo;
import com.example.rtmp.entities.vo.SyncVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2024/11/19 17:16
 */
@Service
public class SyncServiceImpl implements SyncService {

    @Resource
    private MediaConfig mediaConfig;

    @Override
    public SyncVo startSync(SyncStartRequestDto request) {
        request.checkParams();
        String randomString = RandomUtil.randomString(6);
        SyncPo syncPo = new SyncPo();
        syncPo.setSyncKey(RandomUtil.randomString(16));
        syncPo.setPublisher(request.getPublisher());
        syncPo.setSyncType(request.getSyncType());
        syncPo.setRtmpUrl(mediaConfig.getRtmpUrl() + randomString);

        // int count = baseMapper.selectCount(new LambdaQueryWrapper<>().eq(SyncPo::getRtmpUrl,syncPo.getRtmpUrl()))
        // if count > 0 重新生成
        // save(SyncPo);
        return SyncVo.builder()
                .syncKey(syncPo.getSyncKey())
                .rtmpUrl(syncPo.getRtmpUrl())
                .build();
    }

    @Override
    public List<SyncVo> searchSync(SyncSearchRequestDto request) {
        return null;
    }

    @Override
    public void endSync(String syncKey) {
        // 获取syncPo
        // 如果是编码器同屏则关闭ffmpeg
        // 删除syncPo
    }
}
