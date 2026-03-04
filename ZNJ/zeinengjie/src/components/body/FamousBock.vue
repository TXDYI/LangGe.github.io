<template>
    <div class="bock_famous">
        <div class="big_bock">
            <div class="big_img">
                <img :src="gameshow.img" alt="图片">
            </div>
            <div>
                <el-tooltip class="item" effect="dark" :content="gameshow.title" placement="top">
                    <span class="ellipsis-text">{{ gameshow.title }}</span>
                    </el-tooltip>
                <br>
            </div>
            <div class="details_bock">
                
            </div>
            <div class="basics_conditions">
                <ul>
                    <li>一小时起租</li>
                    <li>免押金</li>
                    <li>销量：1w+</li>
                </ul>
            </div>
            <div class="shopping_bock">
                <ul>
                    <li><span style="font-weight:bolder; color: red; font-size: 25px;">￥{{gameshow.money}}元</span>/时起</li>
                    <li><button class="sp_but" @click="getUser()">立马租玩</button></li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
import { ElMessage } from 'element-plus'
import axios from 'axios';
    export default {
        name: "FamousBock",
        data() {
            return {
                path: 'http://localhost:8081/',
                response: Object,
            }
        },
         props:{
            gameshow:{
                type: Object,
                required: true,
            },
         },
        methods:{
            sp(){
                this.$router.push({ path: '/zuhao', query: { id: this.gameshow.id } });
            },
            getUser(){
                axios.get(this.path+'zuhaoh',{
                    headers: {
                        'Authorization': 'Bearer ' + localStorage.getItem('token') 
                    }
                })                
                .then(response => {
                    console.log('允许访问');
                    console.log(response.data);
                    this.sp()
                }).catch(error =>{
                    ElMessage.error("身份过期！")
                   error.response.status === 401 ? this.$router.push('/login') : console.log(error.response.data);
                });
            },
        },
    }
</script>

<style  scoped>
.big_img {
    height: 170px;
    width: 258px;
    display: flex; /* 让图片居中 */
    justify-content: center;
    align-items: center;
    overflow: hidden; /* 防止溢出 */
    background-color: aliceblue; /* 选填：如果图片有透明部分，给个背景色 */
}

.big_img img {
    max-width: 100%;
    max-height: 100%;
    object-fit: contain; /* 让图片等比例缩小并适应容器 */
}
.ellipsis-text {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2; /* 显示 2 行，多余部分变成 ... */
  overflow: hidden;
  width: 200px;  /* 你可以调整宽度 */
  height: 48px;  /* 2 行高度 */
  line-height: 24px; 
}

.shopping_bock ul{
    width: 255px;
    display: flex;
    align-items: center;
    justify-content: space-between; 
}
.bock_famous{
    float: left;
}
.shopping_bock ul li{
    float: left;
    margin-top: 15px;
}
.sp_but {
    height: 36px;
    width: 92px;
    line-height: 36px;
    text-align: center;
    color: #fff;
    background: linear-gradient(90deg, #FF8E36 0%, #FF4436 100%);
    border-radius: 8px;
    font-weight: 600;
    cursor: pointer;
    font-size: 16px;
}

.big_bock{
    width: 258px;
    height: 388px;
    margin-left: 10px;
    padding: 10px 15px 10px 15px;
    background-color: aliceblue;
    border-radius: 20px;
    transition: box-shadow 0.3s ease, border 0.3s ease;
}
.big_bock:hover{
    box-shadow: 10px 10px 5px rgba(0, 0, 0, 0.5);
    border: 1px solid black;
}
.details_bock ul li{
    font-size: 12px;
    float: left;
    width: 70px;
    border-right: 1px solid gray;
    text-align: center;
}
.details_bock ul li:last-child{
    border-style: none;
}
.details_bock{
    margin-top: 10px;
    width: 255px;
    height: 50px;
    border-radius: 15px;
    display: flex;
  align-items: center;
  background: linear-gradient(to right, rgb(206, 248, 220), #caf9ee);
  justify-content: space-around;
}
.basics_conditions ul li{
    margin-top: 10px;
    float: left;
    font-size: 12px;
    margin-right: 10px;
    border: 1px solid black;
    border-radius: 5px;
    opacity: 0.5;
    padding: 3px 5px 3px 5px;
}
.big_bock img:hover{
    cursor: pointer;
}
</style>