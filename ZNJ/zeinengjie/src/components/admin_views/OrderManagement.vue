<template>
    <div class="order_management_root">
        <div class="rent-order-page">
                <!-- 搜索栏 -->
        <el-form :inline="true" class="search-form">
        <el-form-item label="订单号">
            <el-input v-model="searchForm.OrderId" placeholder="请输入订单号" />
        </el-form-item>
        <el-form-item label="游戏名">
            <el-input v-model="searchForm.GameName" placeholder="请输入游戏名" />
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="fetchOrders()">搜索</el-button>
        </el-form-item>
        </el-form>
    <!-- 订单列表 -->
    <el-table :data="orders" border style="width: 1600px">
      <el-table-column prop="id" label="订单编号" width="180"/>
      <el-table-column prop="gameName" label="游戏名字" width="150"/>
      <el-table-column prop="gameCharacterName" label="游戏角色名" width="150"/>
      <el-table-column prop="gameTag" label="游戏类型" width="150"/>
      <el-table-column prop="money" label="每小时金额" width="150"/>
      <el-table-column prop="orderTime" label="订单开始时间" width="200" />
      <el-table-column prop="username" label="所属用户" width="150" />
      <el-table-column prop="gameStatus" label="订单状态" width="200">
        <template #default="{ row }">
          <el-tag :type="getStatusTag(row.gameStatus)">{{ row.gameStatus===0?'待审核':row.gameStatus===1?'已上架':row.gameStatus===2?'进行中':"3"}}</el-tag>
        </template>
        </el-table-column>
      <el-table-column label="操作" width="200">
        <template #default="{ row }">
          <el-button 
            v-if="true" 
            type="danger" 
            size="small" 
            @click="cancelOrder(row.id,row.gameStatus,row.gameName)">
            下架/删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      background
      layout="prev, pager, next"
      :total="total"
      :page-size="pageSize"
      :current-page="currentPage"
      @current-change="fetchOrders"
    />

    <!-- 订单详情弹窗 -->
    <el-dialog v-model="dialogVisible" title="订单审核" destroy-on-close>
    <el-row>
      <el-col :span="12">
        <el-card style="max-width: 380px">
              <template #header>游戏名称</template>
              <swiper
                          :modules="[ Pagination]"
                          :slides-per-view="1"
                          :space-between="10"
                          :loop="true"
                          :autoplay="{ delay: 3000 }"
                          :pagination="{ clickable: true }"
                          class="mySwiper"
                  >
                  <swiper-slide v-for="(img, index) in OrderPutaway.imgList" :key="index">
                        <img :src="img" alt="轮播图" class="slide-img" />
                  </swiper-slide>
              </swiper>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card style="max-width: 480px">
          <template #header>订单详细</template>
        <el-text class="mx-1">{{ OrderPutaway.titledesc }}</el-text>
        <el-col class="mx-2">
          <el-table :data="account" border style="width: 400px">
            <el-table-column prop="user" label="账号" width="200"/>
            <el-table-column prop="pwd" label="密码" width="200"/>
          </el-table>
        </el-col>
        <el-col class="mx-3">
          <el-tree
              style="max-width: 600px"
              :data="data"
              :props="defaultProps"
              accordion
              @node-click="handleNodeClick"
            />
        </el-col>
        </el-card>
      </el-col>
    </el-row>
      <template #footer>
        <el-button type="primary" @click="putawayOder()">上架</el-button>
        <el-button type="primary" @click="dialogVisible = false">关闭</el-button>
      </template>
    </el-dialog>
  </div>
    </div>
</template>

<script setup>
import { ref,onMounted} from "vue";
import { ElMessage,ElMessageBox } from "element-plus";
import { Swiper, SwiperSlide } from "swiper/vue";
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import {Pagination} from "swiper/modules";
import { getAllOrderAdmin,getAllOrderAdminCount,DeleteOrderAdmin} from "@/tool/managementapi";
const orders = ref([{id:"00",gameName:"test01",status:1,Time:new Date()}]);
const total = ref(50);
const pageSize = ref(10);
const currentPage = ref(1);
const dialogVisible = ref(false);
const account = ref([{user: "0000000",pwd: "0000000"}]);
const searchForm = ref({
    OrderId: "",
    GameName: "",
});
const OrderPutaway = ref({});
// 树形数据
const data = ref([])

// 树形组件的配置
const defaultProps = {
  children: 'children',
  label: 'label',
}

//订单通过，确定上架
const putawayOder=()=>{
  console.log("putawayOder",Order_id.value);
  verifiedOrder({orderid:Order_id.value}).then(res=>{
    console.log(res);
    fetchOrders();
    ElMessage.success("订单审核成功,已上架！")
    dialogVisible.value = false
  }).catch(error=>{
    ElMessage.error("订单审核失败")
    console.log(error);
  });
}

// 点击节点事件
const handleNodeClick = (node) => {
  console.log('点击的节点数据:', node)
}
const cancelOrder = (orderId,statu,gameName)=>{
    ElMessageBox.confirm(
    '此操作将永久删除该用户，是否继续？',
    '警告',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      // 👇 这里写删除用户的逻辑
      DeleteOrderAdmin({
        OrderId:orderId,
        statuNub:statu,
        gameName:gameName
      }).then(res=>{
        if(res.status==200){
          ElMessage.success("删除成功")
          fetchOrders() 
        }else{
          ElMessage.info("删除失败")
        }
      }).catch((error) => {
        ElMessage.error('删除失败')
        console.log(error);
      })
    })
    .catch((error) => {
      ElMessage.info('已取消删除')
      console.log(error);
      
    })
}
// 解析时间
const parseTime = (time) => {
    const dayjs = require("dayjs");
    const utc = require("dayjs/plugin/utc");
    const timezone = require("dayjs/plugin/timezone");

    dayjs.extend(utc);
    dayjs.extend(timezone);
    const beijingTime = dayjs.utc(time).tz("Asia/Shanghai").format("YYYY-MM-DD HH:mm:ss");
    return beijingTime;
};
// 获取订单列表
const fetchOrders = async (page=1) => {
  currentPage.value = page;
  console.log("订单id搜索",searchForm.value.OrderId);
  console.log("游戏名称订单搜索",searchForm.value.GameName,);
  
  try {
    await getAllOrderAdmin({
        orderId:searchForm.value.OrderId,//订单id搜索
        GameName:searchForm.value.GameName,//游戏名称订单搜索
        Page:page,
        pageSize: pageSize.value,
    }).then(res=>{
      console.log("全部订单",res.data);
      orders.value=res.data;
      for(let i in orders.value){
          orders.value[i].orderTime= parseTime(orders.value[i].orderTime);
      }
      getPageCount();
    }).catch(error=>{
        ElMessage.error("订单列表获取失败")
        console.log(error);
        
    });

  } catch (error) {
    console.error("获取订单失败", error);
  }
};
//获取总页数
const getPageCount= async ()=>{
   await getAllOrderAdminCount({
    OrderId: searchForm.value.OrderId,
    GameName: searchForm.value.GameName,
   }).then(res=>{
    total.value = res.data;
    console.log("订单获取总页数",total.value);
    
  }).catch(error=>{
    ElMessage.error("订单获取总页数获取失败")
    console.log(error);
  });
}
//权限颜色
const getStatusTag = (status) => {
  switch (status) {
    case 1: return "success";
    case 0: return "info";
    case 2: return "warning";
    default: return "danger";
  }
};
onMounted(() => {
    fetchOrders();
    getPageCount();
});
</script>

<style  scoped>
.order_management_root{
    width: 1700px;
    height: 1000px;
    background-color: aliceblue;
    border-radius: 5px;
}
.rent-order-page {
  width: 100%;
  padding: 20px;
}
</style>