package com.accp.biz;

import com.accp.dao.RecordMapper;
import com.accp.pojo.Record;
import com.accp.pojo.RecordVo;
import com.accp.pojo.Student;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class RecordBiz {

    @Resource
    private RecordMapper mapper;

    public List<RecordVo> selectByStuCode(String stuCode){
        return mapper.selectByStuCode(stuCode);
    }

    public PageInfo<RecordVo> selectRecord(String name, String sname, Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<RecordVo>(mapper.selectRecord(name, sname));
    }

    public Record selectById(Integer id){
        return mapper.selectById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
    public int updateRecord(String name, String des, Integer id){
        return mapper.updateRecord(name, des, id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
    public int deleteByPrimaryKey(Integer id){
        return mapper.deleteByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
    public  int insert(Record record){
        return mapper.insert(record);
    }


}
