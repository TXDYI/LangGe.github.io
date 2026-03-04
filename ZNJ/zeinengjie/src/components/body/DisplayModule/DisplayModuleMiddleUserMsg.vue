<template>
    <div class="user_msg_root">
        <div class="user_msg_top">
            <img :src="avatarimg" alt="" class="avatar_img">
            <br>
            <i style="margin-left: 120px; font-style: normal; margin-top: 10px; font-size: 20px;">
                欢迎来到贼能借
            </i>
            <br>
            <i style="margin-left: 100px; color: red; font-style: normal; font-size: 14px;">
                你的游戏梦想，从贼能借开始！
            </i>
            <div class="user_msg_middle_but" @click="go_login" v-show="lock">
               登入/注册
            </div>
            <div class="user_msg_middle_but_b" @click="getNormalUserExplain" v-show="!lock">
               {{name}}
            </div>
        </div>
        <div class="user_msg_middle">
            <div class="gd">
                <img src="@/assets/lbt/格斗.png" alt="">
            </div>
            <i class="user_msg_middle_text" @click="go_xiangxi">
                超级多的优质账号等你来！
                <br>
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color: red;">冲冲冲冲冲！</span>
            </i>
        </div>
        <div class="user_msg_bottom">
            <i style="float: left; font-style: normal; font-weight: bolder;">
                最新公告
            </i>
            <i style="float: right; margin-right: 20px; ">
                <img src="@/assets/ico/gd_icon.png" alt="" class="jt_ico">
            </i>
            <br>
            <p v-for="(item,index) in message" :key="index"  class="message_text">
                {{ item }}
            </p>
        </div>
    </div>
</template>

<script>
import { ref } from 'vue'
import { getuserAvatar } from '@/tool/api';
import { ElMessage } from 'element-plus'
export default {
    setup () {
        const message = ['我是服主，欢迎来到贼能借！','欢迎大家来玩，一起成长！','借号就来贼能借！']
        const response= ref();
        const avatarimg = ref(require("@/assets/lbt/tx.png"));
        const name = ref(localStorage.getItem('user'));
        const lock = ref(true);
    return {
        message,
        response,
        avatarimg,
        name,
        lock,
    };
    },
    methods: {
        go_login () {
            this.$router.push('/login')
        },
        go_xiangxi(){
            this.$router.push('BargainView')
        }
        ,
        async getavatar(){
            const JWT = localStorage.getItem('token');
            try{
                if(this.name){
                    this.response = await getuserAvatar({
                        userName: this.name,
                        token: JWT
                    }).then(result=>{
                        if(result.data==401){
                            localStorage.removeItem('token');
                            localStorage.removeItem('user');
                            localStorage.removeItem('status');
                            ElMessage.error("登录已过期！请重新登录！");
                            return ;
                        }
                        if(result.data){
                            this.avatarimg = result.data;
                        }
                        console.log(result.data);
                        this.lock = false;
                    }).catch(error=>{
                        ElMessage.error("服务器错误！")
                        console.log(error);
                    });
                }
            }catch(e){
                console.log("1");
                
            }

        },
        getNormalUserExplain(){
            this.$router.push("/normaluserexplain")
         }
    },
    async  mounted () {
        await this. getavatar()
    }
}
</script>

<style  scoped>
.user_msg_top{
    width: 383px;
    height: 209px;
}
.avatar_img {
  width: 80px;  /* 设置宽度 */
  height: 80px; /* 设置高度 */
  border-radius: 50%; /* 让图片变成圆形 */
  object-fit: cover; /* 保证图片填充整个区域，不变形 */
  border: 2px solid #ddd; /* 可选，给头像加个边框 */
  margin-left: 150px;
}

.message_text{
    font-size: 15px; 
    margin-top: 15px;
    text-decoration:underline;
    opacity: 0.7;
}
.message_text:hover{
    color: orange;
    cursor: pointer;
}
.user_msg_root{
    width: 380px;
    height: 482px;
    background-color: #f0efef;
    border-radius: 15px;
    margin: 10px 10px 10px 10px;
}

.user_msg_middle{
    width: 250px;
    height: 80px;
    background-color: #fff;
    margin: 20px 0px 0px 75px;
    border-radius: 15px;
}
.user_msg_middle:hover{
    box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.5);
    cursor: pointer;
}
.user_msg_middle_but{
    width: 180px;
    height: 38px;
    border-radius: 40px;
    margin: 20px 0px 0px 100px;
    text-align: center;
    line-height: 38px;
    color: #fff;
    font-size: 14px;
    background: linear-gradient(90deg, #FF8E36 0%, #FF4436 100%);
}
.user_msg_middle_but_b{
    width: 180px;
    height: 38px;
    border-radius: 40px;
    margin: 20px 0px 0px 100px;
    text-align: center;
    line-height: 38px;
    color: #fff;
    font-size: 20px;
    background: linear-gradient(90deg, #FF8E36 0%, #FF4436 100%);
}
.user_msg_middle_but:hover{
    cursor: pointer;
}
.user_msg_middle_but_b:hover{
    cursor: pointer;
}
.user_msg_middle_text{
    margin: 20px 10px 0px 30px;
    float: right;
    font-size: 12px;
    font-weight: bolder;
    font-style: normal;
}
.gd img{
    height: 30px;
    float: left;
    margin: 20px 0px 0px 30px;
}
.user_msg_bottom{
    margin-top: 20px;
    width: 300px;
    height: 30px;
    margin-left: 40px;
}
.jt_ico:hover{
    opacity: 0.7;
    cursor: pointer;
    border-radius: 10px;
    box-shadow: 0px 0px 3px rgba(0, 0, 0, 0.5);
}
</style>