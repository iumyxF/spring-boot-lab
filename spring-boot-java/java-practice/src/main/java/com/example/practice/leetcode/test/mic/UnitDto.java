package com.example.practice.leetcode.test.mic;

/**
 * @author fzy
 * @description:
 * @date 2024/7/25 13:41
 */
public class UnitDto {

    private Integer id;

    private Integer status;

    public UnitDto(Integer id, Integer status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public UnitDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public UnitDto setStatus(Integer status) {
        this.status = status;
        return this;
    }
}
