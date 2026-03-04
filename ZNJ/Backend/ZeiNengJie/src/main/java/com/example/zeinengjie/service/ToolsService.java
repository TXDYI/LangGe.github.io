package com.example.zeinengjie.service;

import com.example.zeinengjie.pojo.LeaseOrder;
import com.example.zeinengjie.pojo.User;

import java.util.List;

public interface ToolsService {
    int inspect(Integer id);
    List<Integer> returnIdList();
    List<LeaseOrder> getLeaseOrder(Integer OrderId);
    Integer addMoney(Integer LeaseId,Integer Userid);
    void UpDateLeaseorderStatus(Integer LeaseId);
    User getUserStatus(Integer id);
}
