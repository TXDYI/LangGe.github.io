<template>
    <div class="game-manage_root">
      <div class="header">
        <el-button type="primary" @click="drawerVisible = true">添加游戏</el-button>
      </div>
  
      <el-table :data="orders" border style="width: 100%; margin-top: 20px">
        <el-table-column prop="idExposure" label="编号" width="180" />
        <el-table-column prop="exposureImg" label="头像" width="150">
          <template #default="{ row }">
            <el-image :src="row.exposureImg" style="width: 60px; height: 60px" fit="cover" />
          </template>
        </el-table-column>
        <el-table-column prop="titleText" label="游戏名" width="200" />
        <el-table-column prop="explainText" label="广告语" width="300" />
        <el-table-column label="是否显示" width="150">
          <template #default="{ row }">
            <el-switch
              v-model="row.exposureShow"
              active-value="T"
              inactive-value="F"
              active-text="显示"
              inactive-text="隐藏"
              @click="show_setting(row.idExposure,row.exposureShow)"
            />
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150">
          <template #default="{ row }">
            <el-button type="danger" size="small" @click="cancelOrder(row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
  
      <el-pagination
        background
        layout="prev, pager, next"
        :total="total"
        :page-size="pageSize"
        :current-page="currentPage"
        @current-change="fetchOrders"
        style="margin-top: 20px"
      />
  
      <el-drawer v-model="drawerVisible" title="添加游戏" direction="rtl" size="40%">
        <el-form :model="form" label-width="100px">
          <el-form-item label="游戏封面">
          <el-upload
            ref="uploadRef"
            action="#" 
            :auto-upload="false"
            :file-list="fileList"
            list-type="picture-card"
            :on-change="handleChange"
            :on-remove="handleRemove"
            :on-exceed="handleExceed"
            :limit="1"
          >
            <el-icon v-if="fileList.length < 1"><Plus /></el-icon>
          </el-upload>
          </el-form-item>
  
          <el-form-item label="游戏名称">
            <el-input v-model="form.name" placeholder="请输入游戏名"></el-input>
          </el-form-item>
  
          <el-form-item label="广告语">
            <el-input
              type="textarea"
              v-model="form.advert"
              :rows="3"
              placeholder="请输入一句游戏宣传语"
            ></el-input>
          </el-form-item>
  
          <el-form-item>
            <el-button type="primary" @click="submitForm">提交</el-button>
            <el-button @click="resetForm">重置</el-button>
          </el-form-item>
        </el-form>
      </el-drawer>
    </div>
  </template>
  
  <script setup>
  import { ref,onMounted } from 'vue';
  import { Plus } from '@element-plus/icons-vue';
  import { getAllExposureM,getExposureNum,settingShow,addExposure} from '@/tool/managementapi';
import { ElMessage } from 'element-plus';
  const drawerVisible = ref(false);
  const form = ref({
    name: '',
    advert: '',
    avatar: null,
  });
    const currentPage = ref(1);
    const pageSize = ref(10);
    const total = ref(0);
  const fetchExposureNum = async () => {
    await getExposureNum().then(res => {
      total.value = res.data;
    }).catch(error => {
      console.log("获取全部数量失败");
      console.log(error);
    });
  };
  const handleChange = (uploadFile, uploadFiles) => {
  const file = uploadFile.raw;
  const isImage = file.type.startsWith('image/');
  const isLt2M = file.size / 1024 / 1024 < 2;
  console.log(uploadFiles);
  
  if (!isImage) {
    ElMessage.error('只能上传图片类型文件');
    return;
  }

  if (!isLt2M) {
    ElMessage.error('图片大小不能超过 2MB');
    return;
  }
  console.log("123123");
  
  fileList.value = [uploadFile]; // 只保留一个
};
  const show_setting = (id,Status) =>{
    console.log("show_setting->"+id+":"+Status);
    settingShow({
      Id: id,
      Status: Status
    }).then(res =>{
      ElMessage.success(res.data);
      
    }).catch(error=>{
      console.log(error);
      
    })
  }
  const handleExceed = () => {
  ElMessage.warning('最多只能上传一张图片');
  };
  const fetchExposureM = async (currentPage) => {
    await getAllExposureM({
      page: currentPage,
      pageSize: pageSize.value,
    }).then(res => {
      console.log(res.data);
      orders.value = res.data;
    }).catch(error => {
      console.log("获取Exposure列表失败");
      console.log(error);
    });
  };
  onMounted(() => {
    fetchExposureNum();//获取Exposure全部数量
    fetchExposureM(currentPage.value);
  });

  const orders = ref([
    // 示例数据，可根据实际需要填充或替换
    { idExposure: 1, exposureImg: '', titleText: '游戏A', explainText: '好玩不氪金', exposureShow: 'T' },
    { idExposure: 2, exposureImg: '', titleText: '游戏B', explainText: '热血竞技', exposureShow: 'F' }
  ]);
  
const uploadRef = ref(); // 引用 upload
const fileList = ref([]); // 上传的文件列表
  const handleRemove = file => {
    console.log('移除文件：', file);
  };
    const resetForm = () => {
    form.value = {
      name: '',
      advert: '',
      avatar: null,
    };
  }; 
const submitForm = async () => {
  // 校验图片是否选择
  if (!fileList.value.length) {
    ElMessage.warning("请上传游戏封面");
    return;
  }
  console.log(fileList.value);
  
const formData = new FormData();
  formData.append("name", form.value.name);
  formData.append("advert", form.value.advert);
  formData.append("cover", fileList.value[0].raw); // 获取上传文件的原始文件
  await addExposure(formData).then(res => {
    ElMessage.success(res.data);
    drawerVisible.value = false;
    resetForm();
  }).catch(error => {
    ElMessage.error("添加失败");
    console.log(error);
    
  });
}
  const cancelOrder = id => {
    console.log('删除订单 ID:', id);
  };
  const fetchOrders = page => {
    currentPage.value = page;
    fetchExposureM(page);
    console.log('切换到第', page, '页');
  };


  </script>
  
  <style scoped>
  .game-manage_root {
    width: 1700px;
    height: 1000px;
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  }
  .header {
    display: flex;
    justify-content: flex-end;
  }
  .upload {
    width: 120px;
    height: 120px;
  }
  </style>
  