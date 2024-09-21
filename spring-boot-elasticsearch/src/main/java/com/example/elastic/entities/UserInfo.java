package com.example.elastic.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @author fzy
 * @description: 用户信息
 * @date 2024/9/21 16:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long userId;

    private String username;

    private Integer age;

    private List<String> hobbies;

    private List<Location> locations;
}
