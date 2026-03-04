<template>
    <div>
        <div class="user-bock">
            <div class="user_msg_top">
                <div class="user_msg_top_left_avatar">
                    <img :src="avatarimg" alt="" class="avatar_img">
                    <div class="user_msg_name">{{ name }}</div>
                </div>
                <div class="user_msg_top_right_avatar">
                    <div class="shang">
                        <div class="shang_left">
                            <span>库</span>
                        </div>
                        <div class="shang_right">
                            <span>{{ balance }}</span>
                            <img src="https://static.uhaozu.com/zuhao/images/per-center/red-packet2.png" alt="" style="margin-right: 10px; margin-left: 5px;">
                            <button class="shang_right_but" @click="addbalance">余额充值</button>
                        </div>
                    </div>
                    <div class="xia">
                        <div class="xia_a">
                            <p>出租收入(元)</p>
                            <span>{{ form.rentalIncome }}</span>
                        </div>
                        <div class="xia_a">
                            <p>出租订单(笔)</p>
                            <span>{{ form.rentalOrder }}</span>
                        </div>
                        <div class="xia_a">
                            <p>租赁支出(元)</p>
                            <span>{{ form.LeasingExpenditure }}</span>
                        </div>
                        <div class="xia_b">
                            <p>租赁订单(笔)</p>
                            <span>{{ form.LeasingOrder }}</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="user_msg_bottom">
                <div class="zhanghuanquan">账户安全</div>
                <hr class="line"/>
                <div class="user_msg_anp_box">
                    <ul>
                        <li class="user_msg_item">
                            <img src="@/assets/ico/suo.png" alt="" srcset="">
                            <div class="user_msg_anp_box_txt">
                                <p style="font-weight: 500; font-size: 20px;">登录密码</p>
                                <span>用于保护帐号信息和登录安全，建议您不定期更换</span>
                            </div>
                            <button type="button" class="but" @click="dialogVisible = true" >修改密码</button>
                            <ChangeProfileDialog v-model="dialogVisible" />
                        </li>
                        <li class="user_msg_item">
                            <img src="@/assets/ico/phone.png" alt="" srcset="">
                            <div class="user_msg_anp_box_txt">
                                <p style="font-weight: 500; font-size: 20px;">绑定手机 188****7263</p>
                                <span>绑定手机可以用于登录帐号，重置密码或其他安全验证</span>
                            </div>
                            <button type="button" class="but">修改手机</button>
                        </li>
                        <li class="user_msg_item">
                            <img src="@/assets/ico/sfz.png" alt="" srcset="">
                            <div class="user_msg_anp_box_txt">
                                <p style="font-weight: 500; font-size: 20px;">实名认证</p>
                                <span>根据国家要求，网络虚拟交易需要进行实名认证</span>
                            </div>
                            <button type="button" class="but">立即认证</button>
                        </li>
                        <li class="user_msg_item">
                            <img src="@/assets/ico/qdp.png" alt="" srcset="">
                            <div class="user_msg_anp_box_txt">
                                <p style="font-weight: 500; font-size: 20px;">支付密码</p>
                                <span>您还 未开启 支付密码，用于余额支付时使用，保障账户资金安全</span>
                            </div>
                            <button type="button" class="but">开启</button>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="user_msg_middle_but" @click="logoutSystem">
                退出登录
            </div>
        </div>
    </div>
</template>

<script>
import { getuserAvatar } from '@/tool/api';
import { getMoney } from '@/tool/backgroundapi';
import { ref } from 'vue';
import ChangeProfileDialog from '@/components/admin_views/ChangeProfileDialog';
import { ElMessage } from 'element-plus';
import {getOrderDetail} from '@/tool/backgroundapi';
export default {
    name: 'UserBock',
    components:{
        ChangeProfileDialog,
    },
    setup () {
        const avatarimg = ref(require("@/assets/lbt/tx.png"));
        const name = ref(localStorage.getItem('user'));
        const balance = ref(999);  
        const dialogVisible = ref(false);
        const form = ref({
            rentalIncome:0,
            rentalOrder:0,
            LeasingExpenditure:0,
            LeasingOrder:0
        });
         const userOrderDetail=()=>{
            const userAccount =  localStorage.getItem('user');
            getOrderDetail({
                userAccount: userAccount
            }).then(result=>{
                console.log("getOrderDetail-API",result.data);
                form.value = result.data; 
                console.log("getOrderDetail-API",form.value);
            }).catch(error=>{
                console.log(error);
            });
        }
        return {
            avatarimg,
            name,
            balance,
            dialogVisible,
            form,
            userOrderDetail,
        }
    },
    mounted(){
        this.getavatar();
        this.islogin();
        this.getMoney();
        this.userOrderDetail();
        console.log("UserBock已挂载");
        
    },
    methods:{
        addbalance(){
            this.balance = this.balance + 1;
        },
        getavatar(){
              const JWT = localStorage.getItem('token');
            try{
                if(this.name){
                    this.response = getuserAvatar({
                        userName: this.name,
                        token: JWT
                    }).then(result=>{
                        if(result.data){
                            this.avatarimg = result.data;
                        }
                    }).catch(error=>{
                        ElMessage.error("服务器错误！")
                        console.log(error);
                    });
                }else{
                    console.log("UserBock,暂时不知放什么");
                }
            }catch(e){
                console.log("1");
                
            }
        },
        logoutSystem(){
         let user_id = localStorage.getItem('user');
         let token = localStorage.getItem('token');
         let status = localStorage.getItem('status');
         if(user_id && token && status){
            try{
                localStorage.removeItem('user');
                localStorage.removeItem('token');
                localStorage.removeItem('status');
                window.location.reload();
            }catch(e){
                console.log(e);
                console.log("注销出毛病了！");
            }
         }
        },
        islogin(){
            if(localStorage.getItem('user') && localStorage.getItem('token') && localStorage.getItem('status')){
                this.name=localStorage.getItem('user');
            }else{
                this.$router.push('/');
            }
        },
        getMoney(){
            getMoney({
                userName: localStorage.getItem('user')
            }).then(result=>{
                console.log(result.data);
                
                this.balance = result.data
            }).catch(error=>{
                ElMessage.error("服务器错误！")
                console.log(error);
            });
        }
    }
}
</script>

<style scoped>
.xia p{
    opacity: 0.5;
}
.xia span{
    font-size: 20px;
    font-weight: 700;
}
.user_msg_anp_box {
    height: 680px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}
.user_msg_item{
    height: 150px;
    border-bottom: 1px solid #eaeaea;
    margin-top: 10px;
    margin-bottom: 10px;
    display: flex;  
    align-items: center;
    justify-content: space-between;  /* 左右对齐，按钮会靠右 */
}
.user_msg_item img{
    width: 60px;
    height: 60px;
    margin-left: 20px;
}
.xia_a {
    width: 190px;
    border-right: 1px solid #eaeaea;
}

.xia_a p {
    margin-left: 20px;
    margin-top: 10px;
}

.xia_a span {
    display: block;
    margin-left: 85px;
    margin-top: 20px;
}

.shang_right_but {
    color: #ff6700;
    background: #fff;
    border-color: #ff6700;
    width: 100px;
    height: 28px;
}

.shang_right_but:hover {
    cursor: pointer;
}

.shang_right {
    float: right;
}

.shang_left {
    float: left;
}

.shang {
    width: 90%;
    height: 80px;
    border-bottom: 1px solid #eaeaea;
    margin: 0 auto;
    font-size: 30px;
    font-weight: 500;
    margin-top: 30px;
}

.xia {
    width: 98%;
    height: 140px;
    margin: 0 auto;
    display: flex;
    justify-content: space-between;
}

.user_msg_name {
    color: black;
    font-size: 30px;
    font-weight: 900;
}

.user-bock {
    width: 1700px;
    height: 1020px;
    background-color: #fff;
    padding-top: 10px;
}

.avatar_img {
    width: 80px;
    height: 80px;
    border-radius: 50%;
    object-fit: cover;
    border: 2px solid #ddd;
}

.user_msg_middle_but {
    width: 180px;
    height: 38px;
    border-radius: 40px;
    margin: 20px auto 0;
    text-align: center;
    line-height: 38px;
    color: #fff;
    font-size: 14px;
    background: linear-gradient(90deg, #FF8E36 0%, #FF4436 100%);
}

.user_msg_middle_but:hover {
    cursor: pointer;
}

.user_msg_top {
    width: 1000px;
    height: 250px;
    border: 1px solid #ddd;
    padding: 5px;
    margin: 0 auto;
    box-shadow: 0 1px 10px 3px #eaeaea;
}

.user_msg_top_left_avatar {
    width: 200px;
    height: 200px;
    border-radius: 15%;
    float: left;
    margin-top: 20px;
    box-shadow: 0 1px 10px 3px #eaeaea;
    text-align: center;
}

.user_msg_top_right_avatar {
    width: 780px;
    height: 100%;
    border-left: 1px solid #eaeaea;
    float: right;
}

.xia_b {
    width: 190px;
}

.xia_b p {
    margin-left: 20px;
    margin-top: 10px;
}

.xia_b span {
    display: block;
    margin-left: 85px;
    margin-top: 20px;
}

.user_msg_bottom {
    width: 1000px;
    height: 680px;
    box-shadow: 0 1px 10px 3px #eaeaea;
    margin: 20px auto 0;
}

.zhanghuanquan {
    font-weight: 900;
    font-size: 25px;
    border-left: 5px solid #ff6700;
    padding-left: 10px;
    margin: 10px 0 0 10px;
}

.line {
    margin-top: 10px;
}
.user_msg_anp_box_txt {
    margin-left: 10px;
    display: flex;
    flex-direction: column;  
    flex: 1;  /* 让文本区域填充剩余空间 */
}
.user_msg_anp_box_txt span{
    opacity: 0.5;
    font-size: 13px;
}
.but{
    width: 98px;
    height: 28px;
    line-height: 28px;
    border: #bbb 1px solid;
    color: #333;
    background: #f8f8f8;
    background: -webkit-gradient(linear,0 0,0 100%,from(#fff),to(#e8e8e8));
    margin-right: 20px;
}
.but:hover{
    cursor: pointer;
    background: -webkit-gradient(linear,0 0,0 100%,from(#e8e8e8),to(#fff));
}
</style>