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

export const getexposure = () => {
  return apiClient.get('/getexposure')
      .then(response => {
          console.log("Exposure Response: ", response.data);
          return response;
      })
      .catch(error => {
          console.error("Exposure Error: ", error);
          throw error;
      });
};