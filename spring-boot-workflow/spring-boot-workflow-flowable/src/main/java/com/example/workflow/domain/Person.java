package com.example.workflow.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName person
 * @author iumyxF
 */
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "person")
@Data
public class Person implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String firstname;

    /**
     *
     */
    private String lastname;

    /**
     *
     */
    private Date birthdate;

    public Person(String username, String firstname, String lastname, Date birthdate) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
    }

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
        Person other = (Person) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
                && (this.getFirstname() == null ? other.getFirstname() == null : this.getFirstname().equals(other.getFirstname()))
                && (this.getLastname() == null ? other.getLastname() == null : this.getLastname().equals(other.getLastname()))
                && (this.getBirthdate() == null ? other.getBirthdate() == null : this.getBirthdate().equals(other.getBirthdate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getFirstname() == null) ? 0 : getFirstname().hashCode());
        result = prime * result + ((getLastname() == null) ? 0 : getLastname().hashCode());
        result = prime * result + ((getBirthdate() == null) ? 0 : getBirthdate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", username=" + username +
                ", firstname=" + firstname +
                ", lastname=" + lastname +
                ", birthdate=" + birthdate +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}