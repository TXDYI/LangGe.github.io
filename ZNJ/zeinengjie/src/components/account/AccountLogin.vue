<template>
    <div class="container">
      <div class="login_container">
        <!-- 登录面板 -->
        <transition name="slide">
          <div 
            v-show="!showRegister"
            class="login_header login_panel"
            :class="{ 'active-panel': !showRegister }"
          >
            <!-- 登录内容保持不变 -->
            <div class="login_header">
            <i class="wen_zi">
                登录
            </i>
            <div>
                <el-input v-model="input_user" style="width: 240px"  class="login_input" placeholder="账号" />
                <br>
                <el-input v-model="input_pwd" style="width: 240px"  type="password" class="login_input"  placeholder="密码" />
                <div>
                <el-checkbox-group v-model="checkboxGroup1" size="small" >
                <el-checkbox-button v-for="city in cities" :key="city" :value="city" class="checkbox_button">
                    {{ city }}
                </el-checkbox-button>
                </el-checkbox-group>
                <div class="mb-4">
                    <el-button type="primary" round size="large" @click="backstage" >登入</el-button>
                </div>
            </div>
            <div class="z_c">
                <el-button-group>
                    <el-button type="primary" size="small" class="small-button">
                        去注册
                        <el-icon class="el-icon--right"><ArrowRight/></el-icon>
                    </el-button>
                </el-button-group>
            </div>
            </div>
        </div>
          </div>
        </transition>
  
        <!-- 注册面板 -->
        <transition name="slide-reverse">
          <div 
            v-show="showRegister"
            class="login_header_right login_panel"
            :class="{ 'active-panel': showRegister }"
          >
            <!-- 注册内容保持不变 -->
            <div class="login_header_right login_header">
            <i class="wen_zi">
                注册
            </i>
            <div>
                <el-input v-model="input_user" style="width: 240px"  class="login_input" placeholder="账号" />
                <br>
                <el-input v-model="input_pwd" style="width: 240px"  type="password" class="login_input"  placeholder="密码" />
                <br>
                <el-input v-model="input_pwd_OK" style="width: 240px"  type="password"  class="login_input"  placeholder="确定密码" />
                <br>
                <el-input v-model="input_mali" style="width: 240px"  type="email" class="login_input"  placeholder="邮箱" />
                <button @click="getVerificationCode" :disabled="isCounting" class="disabled_">
                  {{ isCounting ? `${count}s 后重新获取` : "获取验证码" }}
                </button>
                <br>
                <el-input v-model="isCode" style="width: 240px"  class="login_input" placeholder="MailCode" />
                <br>
                <el-input v-model="input_phone" style="width: 240px" class="login_input"  placeholder="电话号码" />
                <div>
                <div class="mb-4">
                    <el-button type="primary" round size="large" @click="SignIn" v-bind="{disabled: this.input_pwd !== this.input_pwd_OK}">注册</el-button>
                </div>
            </div>
            </div>
        </div>
          </div>
        </transition>
  
        <!-- 切换按钮 -->
        <div class="z_c">
          <el-button-group>
            <el-button 
              type="primary" 
              size="small" 
              class="small-button"
              @click="showRegister = !showRegister"
            >
              {{ showRegister ? '去登录' : '去注册' }}
              <el-icon class="el-icon--right"><ArrowRight/></el-icon>
            </el-button>
          </el-button-group>
        </div>
      </div>
    </div>
  </template>

<script>
import { ref } from 'vue'
import { ArrowRight } from '@element-plus/icons-vue'
import { postData,registerData,getmailData } from '../../tool/api.js';
import { ElMessage } from 'element-plus'
export default {
  name: "AccountLogin",
  components: { ArrowRight },
  setup() {
    const showRegister = ref(false)
    const count = ref(60); // 初始倒计时时间
    const isCounting = ref(false); // 是否正在倒计时
    let timer = null; // 计时器
    const response= ref();
    const data = ref([]);
    const message = ref('');
    const code = ref();
    const isCode = ref();
    return {
      input_user: ref(''),
      input_pwd: ref(''),
      input_pwd_OK: ref(''),
      input_mali:ref(''),
      checkboxGroup1: ref([]),
      input_phone: ref(''),
      count,
      isCounting,
      timer,
      cities: ['记住密码', '自动登入'],
      showRegister,
      data,
      response,
      message,
      code,
      isCode,
    }
  },
  methods:{
    async SignIn(){
      try{
          this.response =  await registerData({
          username: this.input_user,
          password: this.input_pwd,
          mail: this.input_mali,
          phone: this.input_phone,
          code: this.isCode,
        }).then(res => {
          console.log(res.data.data);
          ElMessage.success("注册成功")
          window.location.reload();
        }).catch((err)=>{
          ElMessage.error("验证码错误")
          console.log(err);
          
        });
      }catch(error){
        console.log(error)
      }
    },
    async backstage(){
      try{
        this.response = await postData({
              username: this.input_user,
              password: this.input_pwd,
          })
          this.data = this.response.data
          console.log(this.data);
          if(this.data.token){
            localStorage.setItem('token', this.data.token);
            localStorage.setItem('status',this.data.HData.status);
            localStorage.setItem('user',this.data.HData.username);
            this.$router.push('/');
          }else if(this.data.message==='账号被禁用'){
            ElMessage.error("该账号已被封禁")
          }else{
            ElMessage.error("账号或密码错误")
          }
      }catch(error){
          ElMessage.error("服务器错误")
        }
    },
    getVerificationCode(){
      if (!this.input_mali) {
        alert("请输入邮箱");
        return;
      }
      if (this.isCounting) return; // 避免重复点击
      this.isCounting = true;
      this.count = 60; // 重置倒计时

      this.timer = setInterval(() => {
        if (this.count > 0) {
          this.count--;
        } else {
          clearInterval(this.timer);
          this.isCounting = false;
        }
      }, 1000);
      try{
        this.response = getmailData({
          mail: this.input_mali
        })
        this.response.then(result=>{
          if(result.data.data.code){
            console.log(result.data.data.code);
            this.code = result.data.data.code;
          }else{
            ElMessage.error("邮箱错误！")
          }
        });
        console.log(this.response);
      }catch(error){
        console.log(error)
      }
    }
  }
}
</script>

<style scoped>
.disabled_ {
  padding: 8px 16px;
  background-color: #ff7c00;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 5px;
}

.disabled_:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}
.container {
    display: flex;
    justify-content: center;  /* 水平居中 */
    align-items: center;      /* 垂直居中 */
    height: 100vh;            /* 让父容器撑满整个视口 */
}

.login_container {
  position: relative;
  width: 600px;  /* 调整为单面板宽度 */
  height: 600px;
  background-color: aliceblue;
  border-radius: 20px;
  overflow: hidden; /* 隐藏溢出内容 */
}
.login_header,
.login_header_right {
  width: 100%;
  height: 100%;
  margin: 0;
  background: white;
  box-sizing: border-box;
  padding: 20px;
}
.login_input {
    margin-top:10px;
    margin-left: 150px;
}
.login_input:first-child{
    margin-top: 50px;
}
.checkbox_button {
    margin-top: 50px;
    margin-left: 25%;
}
.mb-4{
    margin-top: 50px;
    margin-left: 5px;
    text-align: center;
}

.el-button {
    width: 80%; /* 让按钮变宽 */
    height: 50px; /* 增加高度，显得更大气 */
    font-size: 18px;
    font-weight: bold;
    background-color: #409eff;
    border: none;
    border-radius: 25px; /* 保持圆角 */
    transition: all 0.3s ease-in-out;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* 添加轻微阴影，提升质感 */
}

.el-button:hover {
    background-color: #66b1ff; /* 悬停时颜色更亮 */
    transform: translateY(-3px); /* 悬停时按钮稍微上浮 */
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3); /* 悬停时阴影更明显 */
}

.el-button:active {
    transform: translateY(0); /* 点击时回到原位 */
    box-shadow: 0 3px 6px rgba(0, 0, 0, 0.2);
}
.wen_zi{
    margin-top: 10px;
    display: inline-block;
    width: 100%;
    text-align: center;
    font-size: 30px;
    font-family: system-ui, sans-serif;
    font-weight: bolder;
}
.login_header_right{
    float: right;
}
.small-button {
    font-size: 12px; /* 调整字体大小 */
    padding: 5px 10px; /* 适当减少内边距 */
    height: 32px; /* 让按钮更小 */
    border-radius: 15px; /*让按钮保持圆润*/
}
.z_c {
  position: absolute;
  bottom: 30px;
  right: 30px;
  z-index: 3;
}
/* 过渡动画 */
.slide-enter-active,
.slide-leave-active,
.slide-reverse-enter-active,
.slide-reverse-leave-active {
  transition: all 0.6s cubic-bezier(0.4, 0, 0.2, 1);
}

/* 登录面板动画 */
.slide-enter-from {
  transform: translateX(-100%);
  opacity: 0;
}
.slide-leave-to {
  transform: translateX(100%);
  opacity: 0;
}

/* 注册面板动画 */
.slide-reverse-enter-from {
  transform: translateX(100%);
  opacity: 0;
}
.slide-reverse-leave-to {
  transform: translateX(-100%);
  opacity: 0;
}

.login_panel {
  position: absolute;
  transition: all 0.6s;
  backface-visibility: hidden;
  perspective: 1000px;
}

.active-panel {
  z-index: 2;
}
</style>