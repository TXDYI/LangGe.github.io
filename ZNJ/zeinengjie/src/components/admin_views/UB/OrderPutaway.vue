<template>
    <div class="order-putaway">
        <el-card class="upload-card">
    <h2>上架游戏账号</h2>
    <el-form :model="form" label-width="120px">
      
      <!-- 游戏信息 -->
      <el-form-item label="选择游戏">
        <el-select v-model="form.game" placeholder="请选择游戏">
          <div v-for="(game,index) in gameFormData" :key="index">
            <el-option :label="game.gameallName" :value="game.gameallName"></el-option>
          </div>
        </el-select>
      </el-form-item>

      <el-form-item label="账号等级">
        <el-input v-model="form.level" placeholder="请输入账号等级"></el-input>
      </el-form-item>

      <el-form-item label="游戏角色">
        <el-input v-model="form.rank" placeholder="游戏角色名称"></el-input>
      </el-form-item>
      <el-form-item label="账号">
        <el-input v-model="form.uname" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.pwd"  type="password" placeholder="密码"></el-input>

      </el-form-item>
      <!-- 添加标题 -->
      <el-form-item label="添加标题">
        <div class="article-item">
            <el-input v-model="form.titledesc" placeholder="添加标题" type="textarea" />
        </div>
    </el-form-item>
      <!-- 标题输入框 -->
      <el-form-item label="添加说明">
        <div v-for="(article, index) in form.articles" :key="index" class="article-item">
            <el-input v-model="article.title" placeholder="道具分类" />
            <el-input v-model="article.content" placeholder="道具" type="textarea" />
            <el-button type="danger" @click="removeArticle(index)">删除</el-button>
        </div>
        <el-button type="primary" @click="addArticle">+ 添加</el-button>
        </el-form-item>
      <!-- 租赁规则 -->
      <el-form-item label="租赁价格（元/小时）">
        <el-input-number v-model="form.price" :min="1"></el-input-number>
      </el-form-item>

      <!-- 截图上传 -->
      <el-form-item label="上传截图">
        <el-upload
          action="http://localhost:8081/test"
          list-type="picture-card"
          :file-list="form.imgList"
          :on-success="handleUploadSuccess"
          :on-remove="handleRemove"
          :limit="5"
          :data="uploadData"
          :on-preview="handlePictureCardPreview"
          multiple
        >
          <el-icon><Plus /></el-icon>
        </el-upload>
      </el-form-item>

      <!-- 联系方式 -->
      <el-form-item label="联系方式">
        <el-input v-model="form.contact" placeholder="请输入QQ/微信/手机号"></el-input>
      </el-form-item>

      <!-- 提交按钮 -->
      <el-form-item>
        <el-button type="primary" @click="submitForm">立即上架</el-button>
      </el-form-item>

    </el-form>
  </el-card>
    </div>
</template>

<script>
import { ref } from "vue";
import { ElMessage,ElLoading } from "element-plus";
import { Plus } from "@element-plus/icons-vue";
import { testBackground,getGameform } from "@/tool/backgroundapi";
export default {
    name: 'OrderPutaway',
    components: { Plus },
    mounted() {
      this.getGameformFun();
    },
    setup() {
    const form = ref({
      game: "",
      level: "",
      rank: "",
      uname:"",
      pwd:"",
      price: 3,
      contact: "",
      imgUrl: "",
      imgList: [],
      articles: [], // 存储文章
      titledesc: "",
      userName: ""
    });
    const response=ref({});
    const gameFormData = ref([]);
    const getGameformFun = ()=>{
      getGameform().then((res)=>{
        console.log(res.data);
        gameFormData.value=res.data.data;
      }).catch((error)=>{
        console.log("游戏列表获取失败！");
        console.log(error);
        
      })
    }
    const handleAvatarSuccess = (response, uploadFile) => {
      form.value.imgUrl = URL.createObjectURL(uploadFile.raw);
    };

    const beforeAvatarUpload = (rawFile) => {
      if (rawFile.size / 1024 / 1024 > 2) {
        ElMessage.error("上传图片不能超过2MB！");
        return false;
      }
      return true;
    };
    const submitForm = () => {
    form.value.userName = localStorage.getItem('user');
    const submitData = { ...form.value, imgList: form.value.imgList.map(item => item.name) };
        testBackground(submitData)
          .then((res) => {
            console.log(res.data);
            const loading = ElLoading.service({
                lock: true,
                text: 'Loading',
                background: 'rgba(0, 0, 0, 0.7)',
            })
              setTimeout(() => {
                loading.close()
            }, 2000)
            if(res.data.statusCode == 404){
              ElMessage.error("上架失败！");
            }if(res.data.statusCode == 200){
              ElMessage.success("上架成功！");
              form.value={};
            }else{
              ElMessage.error("服务器错误！");
            }
          })
          .catch((error) => {
            console.error("提交失败", error);
          });
  };

    // 图片上传成功
    const handleUploadSuccess = (response, file, fileList) => {
  form.value.imgList = fileList.map(item => ({
    uid: item.uid || file.uid, // 确保有 uid
    name: item.name || file.name, // 确保有文件名
    url: item.url || item.response?.url || file.url // 确保有图片 URL
  }));
};




    // 删除图片
    const handleRemove = (file, fileList) => {
    form.value.imgList = fileList;
    };
    const userName = ref(localStorage.getItem('user'));
    const uploadData = ref({
      userName: userName.value // 附带用户名
    });

    const dialogImageUrl = ref(""); // 存储预览的图片 URL
    const dialogVisible = ref(false); // 控制弹窗显示

// 预览图片
const handlePictureCardPreview = (file) => {
    console.log(file);
    
  dialogImageUrl.value = file.url || file.response.url; // 兼容不同数据格式
  dialogVisible.value = true; // 打开预览弹窗
};
const addArticle = () => {
  form.value.articles.push({ title: "", content: "" });
};

// 删除文章
const removeArticle = (index) => {
  form.value.articles.splice(index, 1);
};
    return { 
        form, 
        handleAvatarSuccess, 
        beforeAvatarUpload, 
        submitForm,
        handleUploadSuccess,
        handleRemove ,
        uploadData,
        handlePictureCardPreview,
        dialogImageUrl,
        dialogVisible,
        removeArticle,
        addArticle,
        response,
        getGameformFun,
        gameFormData,
    };
  },
};
</script>

<style scoped>
.upload-card {
  width: 600px;
  margin: 20px auto;
  padding: 20px;
}
</style>