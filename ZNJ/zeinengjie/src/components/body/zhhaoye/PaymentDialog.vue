<template>
    <el-dialog v-model="dialogVisible" title="支付中心" width="400px" center>
      <!-- 支付金额 -->
      <div class="payment-content">
        <div class="amount">
          <span>支付金额：</span>
          <span>{{ money.money }}￥ {{ money.time }}小时</span> 
        </div>
        
        <!-- 选择支付方式 -->
        <div class="pay-method">
          <span>支付方式：</span>
          <el-radio-group v-model="payMethod">
            <el-radio label="wechat">微信支付</el-radio>
            <el-radio label="alipay">支付宝</el-radio>
          </el-radio-group>
        </div>
      </div>

      <div class="qrcode">
        <img :src="wechat" alt="微信" v-show="payMethod === 'wechat'">
        <img :src="alipay" alt="支付宝" v-show="payMethod === 'alipay'">
      </div>
      <!-- 底部按钮 -->
      <template #footer>
        <el-button @click="closeDialog">取消</el-button>
        <el-button type="primary" @click="submitPayment">确认支付</el-button>
      </template>
    </el-dialog>
  </template>
  
  <script>
  import { ElMessage } from "element-plus";
  import { orderSubmit } from "@/tool/backgroundapi";
  export default {
    props: {
      visible: Boolean, // 父组件传递的控制显示/隐藏
      money:Object,
    },
    data() {
      return {
        dialogVisible: this.visible,
        amount: 10, // 默认支付金额
        payMethod: "wechat", // 默认支付方式
        wechat: require("@/assets/qrcode/wechat.jpg"),
        alipay: require("@/assets/qrcode/alipay.jpg"),
        balance: this.money,
      };
    },
    watch: {
      visible(newVal) {
        this.dialogVisible = newVal;
        console.log("visible");
        
      },
    },
    methods: {
      submitPayment() {
        console.log(`支付金额: ${this.money} 元, 支付方式: ${this.payMethod}`);
        
        // TODO: 调用后端支付接口
        this.$emit("paySuccess", { amount: this.money, method: this.payMethod });
        this.shoppingFun();
        // 关闭弹窗
        this.dialogVisible = false;
        
      },
      closeDialog() {
        this.$emit("paySuccess", { Message:"取消支付" });
        this.dialogVisible = false;
      },
      shoppingFun(){
        orderSubmit(
          {
            money: this.money.money,
            orderId:this.money.orderId,
            time:this.money.time,
            user:this.money.user,
          }
        ).then(res=>{
          if(res.status==200){
            ElMessage.success("支付成功");
            
          }else{
            ElMessage.error("支付失败")
          }
          console.log(res);
          
        }).catch(err=>{
          ElMessage.error("服务器错误")
          console.log(err);
        })
      }
    },
  };
  </script>
  
  <style scoped>
  .payment-content {
    display: flex;
    flex-direction: column;
    gap: 15px;
  }
.qrcode{
    width: 100%;
    height: 200px;
}
.qrcode img{
    display: block;
    height: 200px;
    margin: 0 auto;
}
  .amount {
    display: flex;
    align-items: center;
    font-size: 16px;
  }
  
  .pay-method {
    display: flex;
    align-items: center;
    font-size: 16px;
  }
  </style>
  