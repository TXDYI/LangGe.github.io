package com.example.zeinengjie.mapper;

import com.example.zeinengjie.pojo.LeaseOrder;
import com.example.zeinengjie.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.javassist.runtime.Inner;

import java.util.List;

@Mapper
public interface ToolsMapper {

    int inspect(Integer id);
    List<Integer> returnIdList();
    List<LeaseOrder> getLeaseOrder(Integer OrderId);
    Integer addMoney(Integer LeaseId,Integer Userid);
    void UpDateLeaseorderStatus(Integer LeaseId);
    @Select("select * from users where id=#{id}")
    User getUserStatus(Integer id);
}
