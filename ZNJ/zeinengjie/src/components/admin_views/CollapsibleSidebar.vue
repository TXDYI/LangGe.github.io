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
              <span>用户管理</span>
            </template>
          </el-menu-item>
          <el-sub-menu index="2">
          <template #title>
            <el-icon><location /></el-icon>
            <span>订单详细</span>
          </template>
            <el-menu-item index="2-1">订单审核</el-menu-item>
            <el-menu-item index="2-2">订单管理</el-menu-item>
        </el-sub-menu>
        <el-menu-item index="3">
            <i class="el-icon-menu"></i>
            <template #title>
              <span>页面管理</span>
            </template>
          </el-menu-item>
          <el-menu-item index="4">
            <i class="el-icon-document"></i>
            <template #title>
              <span>订单统计</span>
            </template>
          </el-menu-item>
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
        <component :is="currentComponent"/>
        <!-- <component :is="currentComponent" v-bind="currentComponentProps" /> -->
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import PieChart from '@/components/admin_views/PieChart';
import LineChart from '@/components/admin_views/LineChart';
import PersonageItem from './PersonageItem';
import UserManagsItem from './UserManagsItem';
import OrderCheck from "./OrderCheck"
import OrderManagement from './OrderManagement';
import GameManage from './GameManage';
export default {
  name: "CollapsibleSidebar",
  data() {
    return {
      activeMenu: "1",
      isCollapsed: false, // 导航栏是否收起
      expandedWidth: "200px", // 展开时的宽度
      collapsedWidth: "64px", // 收起时的宽度
      // 菜单索引到组件名称的映射
      componentMap: {
        '1': 'UserManagsItem',
        '2-1': 'OrderCheck',
        '2-2': 'OrderManagement',
        '3': 'GameManage',
        '4':'PieChart',
      },
      // 示例数据
      todayRentCount: 25,
      totalRevenue: "$3,200",
      lineChartData: {
        months: ["1月", "2月", "3月", "4月", "5月", "6月"],
        counts: [5, 20, 36, 10, 10, 20]
      },
      pieChartData: {
        labels: ["在租", "空闲", "待审核","已完成"],
        values: [60, 30, 10,0]
      },
      // 默认当前组件，初始为菜单1对应的组件
      currentComponent: "UserManagsItem"
    };
  },
  components: {
    PieChart,
    LineChart,
    PersonageItem,
    UserManagsItem,
    OrderCheck,
    OrderManagement,
    GameManage,
  },
  computed: {
    sidebarWidth() {
      return this.isCollapsed ? this.collapsedWidth : this.expandedWidth;
    },
    // // 根据当前组件动态返回相应的 props 对象
    // currentComponentProps() {
    //   if (this.currentComponent === 'LineChart') {
    //     return { lineChartData: this.lineChartData };
    //   } else if (this.currentComponent === 'PieChart') {
    //     return { chartData: this.pieChartData };
    //   }else if (this.currentComponent === 'UserManagsItem') {
    //     return { chartData: null};
    //   }else {
    //     return {};
    //   }
    // }
  },
  methods: {
    toggleSidebar() {
      this.isCollapsed = !this.isCollapsed;
    },
    handleSelect(index, indexPath) {
      console.log("菜单选中：", index, indexPath);
      this.activeMenu = index;
      this.currentComponent = this.componentMap[index];
    },
    handleOpen(key,keyPath){
      console.log("菜单打开：", key, keyPath);
    },
    handleClose(key,keyPath){
      console.log("菜单关闭：", key, keyPath);
    }

  }
};
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