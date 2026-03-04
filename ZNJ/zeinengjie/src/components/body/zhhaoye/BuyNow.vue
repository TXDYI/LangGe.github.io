<template>
    <div class="buyNow_r">
        <div class="buyNow_r_box">
            <p>{{ rentOption.daqu}}</p>
            <p>角色：{{ data_reship_fun.gameName}}</p>
            <p>等级：{{ data_reship_fun.GameLv }}</p>
            <p> {{rentOption.price }}￥:{{rentOption.type }}</p>
            <p v-show="rentOption.wq_num>=1"> {{ rentOption.wq_num}}小时</p>
            <p>介绍:{{ rentOption.defaultText}}</p>
            <p v-show="sumPrice">共：{{ sumPrice }}元</p>
            <el-button type="primary" @click="openPayment" class="but_goumai">立即支付</el-button>
            <PaymentDialog :visible="payDialogVisible" @paySuccess="handlePaymentSuccess" :money="moneyData" />
        </div>
    </div>
</template>

<script>
import { ref,watch } from 'vue'
import PaymentDialog from './PaymentDialog.vue'
export default {
    props: {
         rentOption :{
            type: Object,
            required: true
        },
        data_reship_fun : {
            type: Object,
            required: true
        },
        orderById : {
            type: Number,
            required: true
        }
    },
    components: { PaymentDialog },
    setup (props) {
        const sumPrice = ref(0)
        const payDialogVisible=ref(false);
        const moneyData=ref({
            money:0,
            orderId:0,
            time:0,
            user:localStorage.getItem("user")
        });
        watch(() => {
            sumPrice.value = props.rentOption.price *props.rentOption.wq_num
            if(sumPrice.value){
                moneyData.value.money=sumPrice.value;
                moneyData.value.orderId=props.orderById;
            }else{
                moneyData.value.money=props.rentOption.price;
                moneyData.value.orderId=props.orderById;
            }
            if(props.rentOption.type==="夜租"){
                moneyData.value.time=12;//夜租12小时
            }else if(props.rentOption.type==="日租"){
                moneyData.value.time=24;//日租24小时
            }else if(props.rentOption.type==="周租"){
                moneyData.value.time=168;//周租168小时
            }else{
                moneyData.value.time=props.rentOption.wq_num;
            }
            console.log("sumPrice",moneyData.value);
        }, { immediate: true });
        const openPayment = () =>{
            payDialogVisible.value = true;
            
        };
        const handlePaymentSuccess = (data) =>{
            payDialogVisible.value = false;
            console.log("支付成功:", data);
        }
        return {
            sumPrice,
            payDialogVisible,
            openPayment,
            handlePaymentSuccess,
            moneyData
        }
    },
}
</script>

<style scoped>
.ljzh{
    color: #fff;
    font-weight: bolder;
    font-size: 18px;
}
.but_goumai:hover{
    background: #FF4436;
    cursor: pointer;
}
.but_goumai{
    width: 200px;
    height: 44px;
    border: 0;
    background: linear-gradient(to right,#ff9000 0,#ff6700 100%);
    margin-left: 10px;
    margin-top: 70px;
    text-align: center;
    justify-content: center;
}
.buyNow_r{
    padding: 10px;
    width: 100%;
    height: 1080px;
}
.buyNow_r_box{
    text-align: center;
}
.buyNow_r_box p{
    margin-top: 10px;
    background-color: #f4f4f4;
    height: 50px;
}
</style>