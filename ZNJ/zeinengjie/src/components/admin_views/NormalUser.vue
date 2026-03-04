<template>
        <el-container style="height: 100vh">
    <!-- 左侧导航栏 -->
    <el-aside :width="sidebarWidth" class="sidebar">
      <!-- 导航内容区域 -->
      <div class="sidebar-content">
        <el-menu
          :default-active="activeMenu"
          class="el-menu-vertical-demo"
          @select="handleSelect"
          :collapse="isCollapsed"
          @open="handleOpen"
          @close="handleClose"
        >
          <el-menu-item index="1">
            <i class="el-icon-menu"></i>
            <template #title>
              <span>个人信息</span>
            </template>
          </el-menu-item>
          <el-menu-item index="2">
            <i class="el-icon-document"></i>
            <template #title>
              <span>订单上架</span>
            </template>
          </el-menu-item>
          <el-sub-menu index="3">
          <template #title>
            <el-icon><location /></el-icon>
            <span>租号管理</span>
          </template>
            <el-menu-item index="3-1">出租订单</el-menu-item>
            <el-menu-item index="3-2">租赁订单</el-menu-item>
        </el-sub-menu>
          <!-- 可添加更多菜单项 -->
        </el-menu>
      </div>
      <!-- 导航栏折叠按钮 -->
      <div class="collapse-btn" @click="toggleSidebar">
        <el-button type="text" :icon="isCollapsed ? 'el-icon-s-unfold' : 'el-icon-s-fold'"></el-button>
      </div>
    </el-aside>
    <!-- 右侧主内容区域 -->
    <el-container>
      <el-main>
        <!-- 使用动态组件渲染，根据 currentComponent 和 currentComponentProps 传递对应的 props -->
        <component :is="currentComponent" :currentComponentProps="currentComponentProps"/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import UserBock from './UB/UserBock';
import OrderPutaway from './UB/OrderPutaway';
import RentalOrder from './UB/RentalOrder.vue';//出租订单
import LeaseOrder from './UB/LeaseOrder.vue';//租赁支出
import { ref } from 'vue';
export default {
    setup () {
        const currentComponent = ref("UserBock")
        return {
          currentComponent,
        }
    },
    components:{
        UserBock,
        OrderPutaway,
        RentalOrder,
        LeaseOrder,


    },
    methods:{
    handleSelect(index, indexPath) {
      console.log("菜单选中：", index, indexPath);
      switch(index){
        case "1":
          this.currentComponent = "UserBock"
          break;
        case "2":
          this.currentComponent = "OrderPutaway"
          break;
        case "3-1":
          this.currentComponent = "RentalOrder"
          break;
        case "3-2":
          this.currentComponent = "LeaseOrder"
          break;
      }
    },
    handleOpen(key,keyPath){
      console.log("菜单打开：", key, keyPath);
    },
    handleClose(key,keyPath){
      console.log("菜单关闭：", key, keyPath);
    }
    }
}
</script>

<style scoped>
.sidebar {
  background-color: #f5f7fa;
  display: flex;
  flex-direction: column;
  border-right: 1px solid #ebeef5;
}

.sidebar-content {
  flex: 1;
  overflow: auto;
}

.collapse-btn {
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  border-top: 1px solid #ebeef5;
}

/* 折叠时隐藏文本 */
.el-menu-vertical-demo:not(.el-menu--collapse) .el-submenu .el-menu-item {
  padding-left: 40px;
}

.el-menu--collapse .el-submenu__title span {
  display: none;
}

.el-menu--collapse .el-submenu .el-menu-item {
  min-width: 200px;
  padding-left: 40px !important;
}
</style>