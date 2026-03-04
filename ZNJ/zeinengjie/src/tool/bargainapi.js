import axios from 'axios';



/**
 * API 请求封装
 * 主要是bargain页面的接口封装
 */

// 创建一个 axios 实例
export const apiClient = axios.create({
  baseURL: 'http://localhost:8081/', // 统一的基础路径
  timeout: 5000,   // 超时时间
});


export const getBarginsData = (data) => {
  return apiClient.get('/bargain/getBargainData', data);
};  

export const BarginsPage = (data) => {
    return apiClient.get('/bargain/getpage',{ params: { gameName: data }});
};  

