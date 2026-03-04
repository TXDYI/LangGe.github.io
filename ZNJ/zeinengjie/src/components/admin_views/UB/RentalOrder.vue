<template>
    <div class="RentalOrderRoot">
        <div class="rent-order-page">
    <!-- 搜索栏 -->
    <el-form :inline="true" class="search-form">
      <el-form-item label="订单号">
        <el-input v-model="searchForm.orderId" placeholder="请输入订单号" />
      </el-form-item>
      <el-form-item label="游戏名称">
        <el-input v-model="searchForm.gameName" placeholder="请输入游戏名称" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="fetchOrders">搜索</el-button>
      </el-form-item>
    </el-form>

    <!-- 订单列表 -->
    <el-table :data="orders" border style="width: 1600px">
      <el-table-column prop="id" label="订单号" width="180" />
      <el-table-column prop="gameName" label="游戏名称" width="150" />
      <el-table-column prop="orderTime" label="上架时间" width="200" />
      <el-table-column prop="money" label="价格(￥)" width="100" />
      <el-table-column prop="gamesStatus" label="订单状态" width="120">
        <template #default="{ row }">
          <el-tag :type="getStatusTag(row.gamesStatus)">{{ row.gamesStatus===1?'已上架':row.gamesStatus===2?'进行中':row.gamesStatus===0?'正在审核':'可重新上架'}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template #default="{ row }">
          <el-button type="primary" size="small" @click="viewDetails(row)">查看</el-button>
          <el-button 
            v-if="row.gamesStatus === 1||row.gamesStatus===3" 
            type="danger" 
            size="small" 
            @click="cancelOrder(row.id,row.gamesStatus,row.gameName)">
            {{ row.gamesStatus===1?'取消订单':'重新上架' }}
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
    <el-dialog v-model="dialogVisible" title="订单详情">
      <el-descriptions title="订单信息" border>
        <el-descriptions-item label="订单号">{{ orderDetails.id }}</el-descriptions-item>
        <el-descriptions-item label="游戏名称">{{ orderDetails.gameName }}</el-descriptions-item>
        <el-descriptions-item label="出租时间">{{ orderDetails.orderTime }}</el-descriptions-item>
        <el-descriptions-item label="价格(￥)">{{ orderDetails.money }}</el-descriptions-item>
        <el-descriptions-item label="押金(￥)">无需押金</el-descriptions-item>
        <el-descriptions-item label="订单状态">
          <el-tag :type="getStatusTag(orderDetails.gamesStatus)">{{ orderDetails.gamesStatus }}</el-tag>
        </el-descriptions-item>
      </el-descriptions>
      <template #footer>
        <el-button type="primary" @click="dialogVisible = false">关闭</el-button>
      </template>
    </el-dialog>
  </div>
        
    </div>
</template>

<!-- <script>
import {onMounted,ref} from 'vue'
export default {
    name : 'RentalOrder',
    setup () {
        const useerName = ref('')
        onMounted(()=>{
            useerName.value = localStorage.getItem('user')
            console.log("RentalOrder钩子",useerName.value);
        })
        return {
            useerName,
        }
    }
}
</script> -->
<script setup>
import { ref, onMounted} from "vue";
import { ElMessage } from "element-plus";
import { getAllRental,getRentalCountPage,deleteOrder } from "@/tool/backgroundapi";
const searchForm = ref({
  orderId: "",
  gameName: "",
  status: "",
});

const orders = ref([]);
const total = ref(50);
const pageSize = ref(10);
const currentPage = ref(1);
const dialogVisible = ref(false);
const orderDetails = ref({});
// 订单状态颜色映射
const getStatusTag = (status) => {
  switch (status) {
    case 2: return "warning";
    case 1: return "success";
    case 3: return "success";
    case 0: return "info";
    default: return "danger";
  }
};
// watch(() => searchForm.value.status,(newValue)=>{
//     console.log("searchForm",newValue);
    
//     switch(newValue){
//         case '全部':
//         fetchOrders();
//             break;
//         case '进行中':
//         orders.value = orders.value.filter(item=>item.status===1)
//             break;
//         case '已完成':
//             orders.value = orders.value.filter(item=>item.status===2)
//             break;
//         case '已取消':
//             orders.value = orders.value.filter(item=>item.status===0)
//             break;
//     }

// },{immediate:true});


//解析时间
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
  try {
    await getAllRental({
        userName: localStorage.getItem('user'),
        Page:currentPage.value,
        pageSize: pageSize.value,
    }).then(res=>{
        console.log(res.data);
        orders.value = res.data;
          for(let i in orders.value){
            orders.value[i].orderTime = parseTime(orders.value[i].orderTime);
        }
    }).catch(error=>{
        ElMessage.error("订单列表获取失败")
        console.log(error);
        
    });

  } catch (error) {
    console.error("获取订单失败", error);
  }
};

// 查看订单详情
const viewDetails = (order) => {
  try {
    orderDetails.value = order
    dialogVisible.value=true;
  } catch (error) {
    console.error("获取订单详情失败", error);
  }
};

// 取消订单orderId订单号，gamesStatus订单状态
const cancelOrder = async (orderId,gamesStatus,gameName) => {
  try {
         await deleteOrder({
          userName: localStorage.getItem('user'),
          orderId: orderId,
          gamesStatus: gamesStatus,
          gameName: gameName
         }).then(res=>{
          ElMessage.success("操作成功")
          console.log(res);
        }).catch(error=>{
          ElMessage.error("操作失败")
          console.log(error);
        })
    // await apiClient.post(`/rentingorder/cancel/${orderId}`);
    fetchOrders(currentPage.value);
  } catch (error) {
    console.error("取消订单失败", error);
  }
};
//获取总页数
const getPageCount= async ()=>{
   await getRentalCountPage({
    userName: localStorage.getItem('user'),
  }).then(res=>{
    total.value = res.data;
    console.log(res.data);
  }).catch(error=>{
    ElMessage.error("订单列表获取失败")
    console.log(error);
  });
}

// 页面加载时获取订单
onMounted(() => {
  getPageCount();
  fetchOrders();
});
</script>

<style scoped>
.RentalOrderRoot{
    width: 1700px;
    height: 1000px;
    background-color: aliceblue;
    border-radius: 5px;
}
.search-form {
  display: flex;
  gap: 10px;
  margin-bottom: 15px;
}

.rent-order-page {
  width: 100%;
  padding: 20px;
}
</style>