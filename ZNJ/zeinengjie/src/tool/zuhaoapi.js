import axios from 'axios';


/**
 * API 请求封装工具
 * 主要是关于主页的数据请求封装
 */

// 创建一个 axios 实例
const apiClient = axios.create({
  baseURL: 'http://localhost:8081/', // 统一的基础路径
  timeout: 5000,   // 超时时间
});

export const getApiZuhaoData = (zuhaoId) => {
    return apiClient.get('/zuhaoitem', { params: { id: zuhaoId } }); 
  };

  

  // 请求拦截器
apiClient.interceptors.request.use(config => {
  console.log('请求发出:', config.url);
  return config;
});

// 响应拦截器
apiClient.interceptors.response.use(
  response => {
    console.log('响应成功:', response.data);
    return response;
  },
  error => {
    console.error('响应错误:', error.message);
    return Promise.reject(error);
  }
);