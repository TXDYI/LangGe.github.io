<template>
    <div class="orderCheck_root">
        <div class="rent-order-page">
                <!-- 搜索栏 -->
        <el-form :inline="true" class="search-form">
        <el-form-item label="订单号">
            <el-input v-model="searchForm.gameName" placeholder="请输入订单号" />
        </el-form-item>
        <el-form-item label="用户">
            <el-input v-model="searchForm.gameName" placeholder="请输入用户" />
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="fetchOrders">搜索</el-button>
        </el-form-item>
        </el-form>
    <!-- 订单列表 -->
    <el-table :data="orders" border style="width: 1600px">
      <el-table-column prop="id" label="订单编号" width="180"/>
      <el-table-column prop="gameName" label="游戏名字" width="150"/>
      <el-table-column prop="orderTime" label="订单创建时间" width="200" />
      <el-table-column prop="username" label="用户账号" width="150" />
      <el-table-column prop="status" label="用户权限" width="200">
        <template #default="{ row }">
          <el-tag :type="getStatusTag(row.status)">{{ row.status===0?'管理员':row.status===1?'普通用户':'违规封禁'}}</el-tag>
        </template>
        </el-table-column>
      <el-table-column label="操作" width="200">
        <template #default="{ row }">
          <el-button type="primary" size="small" @click="viewDetails(row.id)">审核</el-button>
          <el-button 
            v-if="true" 
            type="danger" 
            size="small" 
            @click="cancelOrder(row.id)">
            下架
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
import {getAllunauthorizedOder,setOrderId,verifiedOrder,deleteOrder,getUOrderPageCount} from "@/tool/managementapi";

const orders = ref([{id:"00",gameName:"test01",status:1,Time:new Date()}]);
const total = ref(50);
const pageSize = ref(10);
const currentPage = ref(1);
const dialogVisible = ref(false);
const account = ref([{user: "0000000",pwd: "0000000"}]);
const searchForm = ref({
  UserName: "",
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

const getProperty = () => {
  console.log("getProperty",OrderPutaway.value.contact);
  
            data.value = []  // 每次获取时清空旧数据
            const regex = /\{([^}]+)\}/g
            let match
            const str = OrderPutaway.value.contact
            while ((match = regex.exec(str)) !== null) {
                const item = match[1].split(',')  // 按逗号分割
                const title = item[0]  // 获取标题
                const properties = item.slice(1);  // 获取属性
                let str_=[]
                if(properties.length==1&&properties[0].includes('，')){
                    str_ =  properties[0].split('，');
                }else{
                    str_ =  properties[0].split(',');  
                }
                // result.value.push({ title, str_ });
                data.value.push({
                    label: title,
                    children: str_.map(str => ({ label: str })),
                });
            }
        }

const cancelOrder = (indexId)=>{
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
      deleteOrder({
        OrderId:indexId
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
    await getAllunauthorizedOder({
        Page:currentPage.value,
        pageSize: pageSize.value,
    }).then(res=>{
      console.log(res.data);
      
        orders.value=res.data;
        for(let i in orders.value){
            orders.value[i].orderTime= parseTime(orders.value[i].orderTime);
        }
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
   await getUOrderPageCount().then(res=>{
    total.value = res.data;
  }).catch(error=>{
    ElMessage.error("订单获取总页数获取失败")
    console.log(error);
  });
}
//权限颜色
const getStatusTag = (status) => {
  switch (status) {
    case 1: return "warning";
    case 0: return "success";
    case 2: return "danger";
    default: return "danger";
  }
};
const Order_id=ref(0);
// 查看订单详情
const viewDetails = async (orderId) => {
    Order_id.value=orderId;
    console.log("viewDetails",orderId);
    setOrderId({
      Orderid:orderId
    }).then(res=>{
        OrderPutaway.value = res.data;
        account.value[0].user=OrderPutaway.value.uname
        account.value[0].pwd=OrderPutaway.value.pwd
        console.log(OrderPutaway.value);
        getProperty();
    }).catch(error=>{
        ElMessage.error("订单详情获取失败")
        console.log(error);
    });
    dialogVisible.value=true;
};
onMounted(() => {
    fetchOrders();
    getPageCount();
});
</script>

<style scoped>
.orderCheck_root{
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
.slide-img{
  height: 400px;
}
.mx-2{
  margin-top: 40px
}
.slide-img {
  width: 100%;
  height: auto;
  object-fit: contain; /* 保证图片完整显示不裁剪 */
  display: block;
  margin: 0 auto;
  max-height: 300px; /* 限制最大高度，避免撑破卡片 */
}
</style>