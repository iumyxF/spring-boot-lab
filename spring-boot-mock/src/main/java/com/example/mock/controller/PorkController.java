package com.example.mock.controller;

import com.example.mock.entities.PorkInst;
import com.example.mock.exception.BaseBusinessException;
import com.example.mock.service.PorkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description:
 * @Date 2023/2/11 10:49
 * @author iumyxF
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/pork")
public class PorkController {

    private final PorkService porkService;

    @PostMapping("/buy")
    public ResponseEntity<PorkInst> buyPork(@RequestParam("weight") Long weight,
                                            @RequestBody Map<String, Object> params) {
        if (weight == null) {
            throw new BaseBusinessException("无效的请求参数");
        }
        return ResponseEntity.ok(porkService.getPork(weight, params));
    }

}
