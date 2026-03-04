import axios from 'axios';


/**
 * API 请求封装工具
 * 主要是关于后台的数据请求封装
 */

// 创建一个 axios 实例
const apiClient = axios.create({
  baseURL: 'http://localhost:8081/', // 统一的基础路径
  timeout: 5000,   // 超时时间
});

export const testBackground = (data) => {
    return apiClient.post('/backgroundtest', data);
}

export const getGameform = (data) => {
    return apiClient.post('/getGameform', data);
}

export const getGameShow = () => {
    return apiClient.post('/getgameshow')
        .then(response => {
            console.log("GameShow Response: ", response.data);
            return response;
        })
        .catch(error => {
            console.error("GameShow Error: ", error);
            throw error;
        });
};

export const getMoney = (data) =>{
    return apiClient.post('/getmoney', data);
};

export const getAllRental = (data) =>{
    return apiClient.post('/allrental', data);
};

export const getAllLease=(data)=>{
    return apiClient.post('/alllease', data);
}


export const getRentalCountPage=(userName)=>{
    return apiClient.post('/getrentalcount', userName);
}

export const getLeaseCountPage=(userName)=>{
    return apiClient.post('/getleasecount', userName);
}


export const orderSubmit=(data)=>{
    return apiClient.post('/shopping', data);
}

export const getDetailedness=(orederId)=>{
    return apiClient.post('/getdetailedness', orederId);
}
//获取用户订单详细
export const getOrderDetail = (data)=>{
  return apiClient.post('/getOrderDetail',data);
}

//删除用户订单
export const deleteOrder = (data)=>{
  return apiClient.post('/deleteOrder',data);
}