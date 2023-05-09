package com.example.plus.model.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author iumyxF
 * @TableName menu
 */
@TableName(value = "menu")
@Data
public class Menu implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long menuId;

    /**
     *
     */
    private String menuName;

    /**
     *
     */
    private String menuPath;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Menu other = (Menu) that;
        return (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals(other.getMenuId()))
                && (this.getMenuName() == null ? other.getMenuName() == null : this.getMenuName().equals(other.getMenuName()))
                && (this.getMenuPath() == null ? other.getMenuPath() == null : this.getMenuPath().equals(other.getMenuPath()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMenuId() == null) ? 0 : getMenuId().hashCode());
        result = prime * result + ((getMenuName() == null) ? 0 : getMenuName().hashCode());
        result = prime * result + ((getMenuPath() == null) ? 0 : getMenuPath().hashCode());
        return result;
    }

    @Override
    public String toString() {
        String sb = getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", menuId=" + menuId +
                ", menuName=" + menuName +
                ", path=" + menuPath +
                ", serialVersionUID=" + serialVersionUID +
                "]";
        return sb;
    }
}