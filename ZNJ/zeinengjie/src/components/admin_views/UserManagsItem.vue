<template>
    <div class="leaseOrderRoot">
        <div class="rent-order-page">
                <!-- 搜索栏 -->
        <el-form :inline="true" class="search-form">
        <el-form-item label="用户">
            <el-input v-model="searchForm.gameName" placeholder="请输入用户" />
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="fetchOrders">搜索</el-button>
        </el-form-item>
        </el-form>
    <!-- 订单列表 -->
    <el-table :data="orders" border style="width: 1600px">
      <el-table-column prop="id" label="用户编号" width="180" />
      <el-table-column prop="username" label="用户账号" width="150" />
      <el-table-column prop="createTime" label="用户创建时间" width="200" />
      <el-table-column prop="mail" label="邮箱" width="200"/>
      <el-table-column prop="phone" label="电话号码" width="200"/>
      <el-table-column prop="userdescription" label="备注" width="200"/>
      <el-table-column prop="status" label="用户权限" width="200">
        <template #default="{ row }">
          <el-tag :type="getStatusTag(row.status)">{{ row.status===0?'管理员':row.status===1?'普通用户':'违规封禁'}}</el-tag>
        </template>
        </el-table-column>
      <el-table-column label="操作" width="200">
        <template #default="{ row }">
          <el-button type="primary" size="small" @click="viewDetails(row.id,row.status)">修改权限</el-button>
          <el-button 
            v-if="true" 
            type="danger" 
            size="small" 
            @click="cancelOrder(row.id)">
            删除用户
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
    <el-dialog v-model="dialogVisible" title="用户管理" destroy-on-close>
        <el-row :gutter="20">
    <el-col :span="12">
      <el-table :data="roleList" @current-change="handleSelect" highlight-current-row>
        <el-table-column type="index" width="50" />
        <el-table-column prop="roleName" label="角色名" />
        <el-table-column prop="roleDesc" label="描述" />
      </el-table>
    </el-col>
    <el-col :span="12">
      <el-form :model="form">
        <el-form-item label="选择角色">
            <el-select v-model="form.selectedRoleId" placeholder="请选择角色" @change="handleRoleChange">
                <el-option
                v-for="role in roleList"
                :key="role.id"
                :label="role.roleName"
                :value="role.id"
                />
            </el-select>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="form.remark" />
        </el-form-item>
        <el-form-item label="是否启用">
          <el-switch v-model="form.status" />
        </el-form-item>
        <el-button type="primary" @click="submit">提交修改</el-button>
      </el-form>
    </el-col>
  </el-row>
      <template #footer>
        <el-button type="primary" @click="dialogVisible = false">关闭</el-button>
      </template>
    </el-dialog>
  </div>
    </div>
</template>

<script setup>
import { ref,onMounted} from "vue";
import { ElMessage,ElMessageBox } from "element-plus";
// import { getAllLease,getLeaseCountPage,getDetailedness } from "@/tool/backgroundapi";
import { getUserList,getUserAllpageNumber,deleteUser,UpUserStatus} from "@/tool/managementapi";

const orders = ref([{id:"00",gameName:"test01",status:1,Time:new Date()}]);
const total = ref(50);
const pageSize = ref(10);
const currentPage = ref(1);
const dialogVisible = ref(false);
const searchForm = ref({
  UserName: "",
});
const form = ref({
  selectedRoleId: null,
  selectedRoleName: '',
  orderId:'',
  remark: '',
  status: true,
})
const roleList = ref([
  { id: 0, roleName: '管理员', roleDesc: '具有最高权限' },
  { id: 1, roleName: '普通用户', roleDesc: '具有普通权限' },
  { id: 2, roleName: '违规账号', roleDesc: '封禁' },
]);

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
      deleteUser(indexId).then(() => {
        ElMessage.success('成功删除用户编号为 ' + indexId+" 的用户")
        for(let i in orders.value){
          if(orders.value[i].id==indexId){
            orders.value.splice(i,1)
            break;
          }
        }
      }).catch(() => {
        ElMessage.error('删除失败')
      })
    })
    .catch((error) => {
      ElMessage.info('已取消删除')
      console.log(error);
      
    })
}
function handleSelect(row) {
  form.value.selectedRoleId = row.id
  form.value.selectedRoleName = row.roleName
}

function submit() {
   UpUserStatus(form.value).then(()=>{
    ElMessage.success('修改成功')
    fetchOrders();
    dialogVisible.value = false
  }).catch(()=>{
    ElMessage.error('修改失败')
  })

}

function handleRoleChange(roleId) {
  const selected = roleList.value.find(r => r.id === roleId)
  form.value.selectedRoleName = selected ? selected.roleName : ''
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
    await getUserList({
        Page:currentPage.value,
        pageSize: pageSize.value,
    }).then(res=>{
        orders.value=res.data;
        for(let i in orders.value){
            orders.value[i].createTime = parseTime(orders.value[i].createTime);
        }
        console.log("用户列表",res.data);
        
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
   await getUserAllpageNumber().then(res=>{
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

// 查看订单详情
const viewDetails = async (orderId,status) => {
    console.log("viewDetails",orderId);
    form.value.orderId = orderId;
    dialogVisible.value=true;
    form.value.selectedRoleId=status;
};
onMounted(() => {
    fetchOrders();
    getPageCount();
});
</script>

<style scoped>
.leaseOrderRoot{
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