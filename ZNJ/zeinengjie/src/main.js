import { createApp } from 'vue'
import App from './App.vue'
import router from './router'  // 自动加载 router/index.js
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css' // 引入 Element Plus 样式

const app = createApp(App)
app.use(router)  // 使用路由
app.use(ElementPlus)
app.mount('#app')
// 开发阶段忽略 ResizeObserver 报错
const originalError = console.error;
console.error = (...args) => {
  if (args[0]?.toString().includes('ResizeObserver loop')) return;
  originalError(...args);
};