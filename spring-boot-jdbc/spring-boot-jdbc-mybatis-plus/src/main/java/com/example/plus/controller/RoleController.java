package com.example.plus.controller;

import com.example.plus.mapper.RoleMapper;
import com.example.plus.model.Result;
import com.example.plus.model.vo.RoleVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author iumyxF
 * @description:
 * @date 2023/5/9 15:45
 */
@RestController
@RequiredArgsConstructor
public class RoleController {

    private final RoleMapper roleMapper;

    @GetMapping("/role/{id}")
    public Result<RoleVo> getInfo(@PathVariable Long id) {
        RoleVo roleVo = roleMapper.selectRoleVoById(id);
        return Result.ok(roleVo);
    }
}
