package com.example.zeinengjie.service.imp;

import com.example.zeinengjie.Util.Mail;
import com.example.zeinengjie.mapper.ToolsMapper;
import com.example.zeinengjie.pojo.Game;
import com.example.zeinengjie.pojo.LeaseOrder;
import com.example.zeinengjie.pojo.User;
import com.example.zeinengjie.service.ToolsService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ToolsImp implements ToolsService {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(ToolsImp.class);
    @Autowired
    private ToolsMapper toolsMapper;
    @Autowired
    private LeaseImp leaseImp;
    @Autowired
    private BackgroundManageImp backgroundManageImp;
    @Autowired
    private ManagementImp managementImp;
    @Override
    public int inspect(Integer id) {
        return toolsMapper.inspect(id);
    }
    @Scheduled(fixedRate = 60000)
    @Transactional
    public void timer(){
        List<Integer> integers = returnIdList();
        if(!integers.isEmpty()){
            for (Integer id: integers) {
                Game zuhaoItem = leaseImp.getZuhaoItem(id);
                List<LeaseOrder> lo = toolsMapper.getLeaseOrder(id);
                for (LeaseOrder l:lo) {
                    UpDateLeaseorderStatus(l.getLeaseOrderId());
                    logger.info("订单租赁"+l.getLeaseOrderId()+"已完成");
                    logger.info("用户：id"+zuhaoItem.getUserId());
                    addMoney(l.getLeaseOrderId(),zuhaoItem.getUserId());
                    managementImp.UpGameAllLeisureknown(zuhaoItem.getGameName());
                    managementImp.UpGameAllLeisureendOrderNub(zuhaoItem.getGameName());
                    User user = toolsMapper.getUserStatus(zuhaoItem.getUserId());
                    try {
                        new Mail().UpPassword(user.getMail(), zuhaoItem.getGameName(), l.getGamesId(), zuhaoItem.getUname());
                    } catch (GeneralSecurityException e) {
                        e.printStackTrace();
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                    logger.info("订单"+id+"已完成");
                }
                inspect(id);
            }
        }
    }

    @Override
    public List<Integer> returnIdList() {
        return toolsMapper.returnIdList();
    }

    @Override
    public Integer addMoney(Integer LeaseId, Integer Userid) {
        return toolsMapper.addMoney(LeaseId, Userid);
    }


    @Override
    public List<LeaseOrder> getLeaseOrder(Integer OrderId) {
        return toolsMapper.getLeaseOrder(OrderId);
    }


    @Override
    public void UpDateLeaseorderStatus(Integer LeaseId) {
        toolsMapper.UpDateLeaseorderStatus(LeaseId);
    }

    @Override
    public User getUserStatus(Integer id) {
        return toolsMapper.getUserStatus(id);
    }
}
