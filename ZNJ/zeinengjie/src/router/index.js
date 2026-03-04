import { createRouter, createWebHistory } from 'vue-router'
import BargainView from '@/views/BargainView.vue'      // 导入页面组件
import HomeView from '@/views/HomeView.vue'
import AdminView from '@/views/AdminView.vue'
import PieChart from '@/components/admin_views/PieChart.vue';
import LineChart from '@/components/admin_views/LineChart.vue';
import CollapsibleSidebar from '@/components/admin_views/CollapsibleSidebar.vue';
import AccountViews from '@/views/AccountViews.vue'; 
import ZuHaoYeMianViews from '@/views/ZuHaoYeMianViews.vue';
import Error403 from '@/components/error/ErrorFourHundredThirty.vue';
import Error404 from '@/components/error/ErrorFourHundredFour.vue';
import Error500 from '@/components/error/ErrorFiveHundred.vue';
import NormalUserExplainView from '@/views/NormalUserExplainView.vue';
const routes = [
  {
    path: '/BargainView',
    name: 'BargainView',
    component: BargainView
  },
  {
    path: '/',
    name:'HomeView',
    component:HomeView
  },
  {
    path: '/AdminView',
    name:'AdminView',
    component:AdminView,
    meta: { requiresAuth: true, status: '0' } // 需要登录 & 需要管理员角色
  },
  {
    path: '/admin',
    component: CollapsibleSidebar,
    children: [
      {
        path: 'pie-chart',
        component: PieChart
      },
      {
        path: 'line-chart',
        component: LineChart
      }
    ]
  },
  {
    path: '/login',
    name:'AccountViews',
    component:AccountViews
  },
  {
    path: '/zuhao',
    name:'ZuHaoYeMianViews',
    component:ZuHaoYeMianViews
  },
  {
    path: '/403',
    name:'Error403',
    component:Error403
  },
  {
    path: '/:pathMatch(.*)*',
    name:'Error404',
    component:Error404
  },
  {
    path: '/500',
    name:'Error500',
    component:Error500
  },
  {
    path: '/normaluserexplain',
    name:'NormalUserExplainView',
    component:NormalUserExplainView
  },


]

const router = createRouter({
  history: createWebHistory(),
  routes
})
// 🌟 全局前置守卫：在跳转前检查权限
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token'); // 获取 Token
  const userRole = localStorage.getItem('status'); // 获取用户角色（后端返回）
  console.log(to);
  if (to.meta.requiresAuth) {
    // 1️⃣ 没有 Token，跳转到登录页
    if (!token) {
      return next('/login'); 
    }
    // 2️⃣ 需要特定角色，但当前角色不匹配，跳转到 403 页面
    if (to.meta.status && to.meta.status !== userRole) {
      return next('/403'); 
    }
  }
  if(to.fullPath=='/login'){
    if(localStorage.getItem('user')){
      return next('/');
    }
  }
  next(); // 允许跳转
})
export default router