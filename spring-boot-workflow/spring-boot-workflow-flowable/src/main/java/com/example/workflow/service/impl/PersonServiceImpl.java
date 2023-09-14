package com.example.workflow.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.workflow.domain.Person;
import com.example.workflow.mapper.PersonMapper;
import com.example.workflow.service.PersonService;
import org.springframework.stereotype.Service;

/**
 * @author gonsin
 * @description 针对表【person】的数据库操作Service实现
 * @createDate 2023-09-14 15:19:50
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

}




