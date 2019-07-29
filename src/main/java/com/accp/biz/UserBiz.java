package com.accp.biz;

import com.accp.dao.StudentMapper;
import com.accp.pojo.Student;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class UserBiz {

    @Resource
    private StudentMapper mapper;

    public Student selectStudent(String name, String pwd){
        return mapper.selectStudent(name, pwd);
    }

    public PageInfo<Student> selectAll(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<Student>(mapper.selectAll());
    }

    public List<Student> findAll(){
        return mapper.selectAll();
    }

}
