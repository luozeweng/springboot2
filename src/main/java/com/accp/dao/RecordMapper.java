package com.accp.dao;

import com.accp.pojo.Record;
import com.accp.pojo.RecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Record record);

    Record selectByPrimaryKey(Integer id);

    List<Record> selectAll();

    int updateByPrimaryKey(Record record);

    List<RecordVo> selectByStuCode(String stuCode);

    List<RecordVo> selectRecord(@Param("name") String name, @Param("sname") String sname);

    Record selectById(Integer id);

    int updateRecord(@Param("name") String name, @Param("des") String des, @Param("id") Integer id);
}