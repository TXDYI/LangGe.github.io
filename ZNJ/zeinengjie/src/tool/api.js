import axios from 'axios';



/**
 * API 请求封装
 * 主要是关于用户登录、注册、获取用户信息等API
 */

// 创建一个 axios 实例
const apiClient = axios.create({
  baseURL: 'http://localhost:8081/', // 统一的基础路径
  timeout: 5000,   // 超时时间
});

// 获取数据的封装方法
export const fetchData = () => {
  return apiClient.get('/data'); // 只传相对路径
};

// 其他 API 也可以在这里封装，比如 post、put、delete 等
export const postData = (data) => {
    return apiClient.post('/login', data);
};

export const registerData = (data) => {
    return apiClient.post('/register', data);
}
export const getmailData = (data) => {
    return apiClient.post('/getmail', data);
}
export const getuserAvatar = (data) => {
    return apiClient.post('/getuseravatar', data);
}