import axios from 'axios';



/**
 * API 请求封装
 * 主要是关于用户登录、注册、获取用户信息等API
 */

// 创建一个 axios 实例
const apiClient = axios.create({
  baseURL: 'http://localhost:8081/management', // 统一的基础路径
  timeout: 5000,   // 超时时间
});
const headers=()=>{
  return {
    headers: {
      'Authorization': 'Bearer ' + localStorage.getItem('token') 
    }
  }
}
export const getAdminIndex = () => {
    return apiClient.post('/adminIndex',{},headers());
}

export const getUserList = (data) => {
    return apiClient.post('/userList',data,headers());
}

export const getUserAllpageNumber = () => {
    return apiClient.get('/userallpagenumber',headers());
}

export const deleteUser = (userId)=>{
  return apiClient.delete('/deleteuser',{
    ...headers(),
    params:{
    userId:userId
  },
  });
}

export const UpUserStatus = (obj)=>{
  return apiClient.post('/upuserstatus',obj,headers());
}
export const getAllunauthorizedOder = (page)=>{
  return apiClient.post('/getunauthorizedOrder',page,headers());
}

export const setOrderId = (orderid)=>{
  return apiClient.post('/setOrderId',orderid,headers());
}

export const verifiedOrder = (orderid)=>{
  return apiClient.post('/verifiedOrder',orderid,headers());
}

export const deleteOrder = (OrderId) => {
  console.log(OrderId);
  return apiClient.delete('/deleteOrder', {
    params: { OrderId:OrderId.OrderId },
    headers: {
      Authorization: 'Bearer ' + localStorage.getItem('token')
    }
  });
};

export const DeleteOrderAdmin=(data)=>{
  return apiClient.delete('/deleteOrderAdmin',{
    ...headers(),
    params:{
      OrderId:data.OrderId,
      statuNub:data.statuNub,
      gameName:data.gameName
    }
  });
}


export const getUOrderPageCount = () =>{
    return apiClient.get('/UOrderPageCount', headers());
};

export const getAllOrderAdmin = (data)=>{
  return apiClient.post('/getAllOrderAdmin',data,headers());
};

export const getAllOrderAdminCount = (data)=>{
  return apiClient.post('/getAllOrderAdminPageCount',data,headers());
};

export const getPieChart = (data) => {
  return apiClient.get('/getPieChart', {
    params: {
      gameName: data.gameName
    },
    headers: {
      'Authorization': 'Bearer ' + localStorage.getItem('token') 
    }
  });
};
//获取全部Exposure曝光数据
export const getAllExposureM=(data)=>{
  console.log("getAllExposureM的data",data);
  return apiClient.get('/getAllExposureM',{
     params: {
     page:data.page,
     pageSize:data.pageSize
    },
    headers: {
      'Authorization': 'Bearer ' + localStorage.getItem('token') 
    }
  });
}
export const getExposureNum = ()=>{
  return apiClient.get('/getExposureNum',headers());
}
//设置是否曝光
export const settingShow=(data)=>{
  return apiClient.post('/setting',data,headers());
}

//添加曝光
export const addExposure=(data)=>{
  return apiClient.post('/addExposure',data,{
    headers: {
      'Authorization': 'Bearer ' + localStorage.getItem('token'),
      'Content-Type': 'multipart/form-data'
    }
  });
}
//修改密码
export const updatePro = (data)=>{
  return apiClient.post('/updatePro',data,headers());
}