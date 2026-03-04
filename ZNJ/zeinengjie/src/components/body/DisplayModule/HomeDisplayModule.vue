<template>
    <div class="display_module">
        <div class="display_module_boy">
            <div class="module_left">
                <ul>
                    <li v-for="(item,index) in exposureObj" :key="index" @click="toBargainView(item.titleText)">
                        <img :src="item.exposureImg" alt="">
                        <div class="left_item">
                            <p style="font-size: 20px; font-weight: 900;">{{ item.titleText }}</p>
                            <i>{{item.explainText }}</i>
                        </div>
                        <img class="gd_icon_img" style="height: 20px; width: 20px;" :src="imgs" alt="">
                    </li>
                </ul>
            </div>
            <div class="module_middle">
                <DisplayModuleMiddleLeft/>
                <DisplayModuleMiddleRight/>
            </div>
            <div class="module_right">
                <DisplayModuleMiddleUserMsg/>
            </div>
        </div>
    </div>
</template>

<script>
import DisplayModuleMiddleLeft from './DisplayModuleMiddleLeft';
import DisplayModuleMiddleRight from './DisplayModuleMiddleRight';
import DisplayModuleMiddleUserMsg from './DisplayModuleMiddleUserMsg';
import { getexposure } from '@/tool/home';
    export default {
        name: "HomeDisplayModule",
        data() {
            return {
                imgs: String,
                exposureObj: [],
                exposureData: Object,
            }
        },
        components: {
            DisplayModuleMiddleLeft,
            DisplayModuleMiddleRight,
            DisplayModuleMiddleUserMsg,
        },
        mounted () {
            console.log('HomeDisplayModule已挂载');
             this.getexposure();
        },
        methods: {
           async getexposure(){
                await getexposure()
                .then(res => {
                    if(res.data){
                        for(let item of res.data){
                            this.exposureObj.push(item);
                        }
                        this.imgs="https://static.uhaozu.com/zuhao/images/index-new2/gd-icon.png";
                        console.log("getexposure",res.data);
                    }
                }).catch(err => {
                    console.log(err);
                });
            },
            toBargainView(data){
                this.$router.push({ path: '/BargainView', query: { gameName: data} });
            }
        }
    }
</script>

<style  scoped>
.gd_icon_img{
    margin-left: 80px;
    transition: all 0.3s ease-in-out;
}
.module_left ul li:hover img {
    filter: brightness(1.2); /* 提高亮度，让图片更突出 */
}
.display_module_boy{
    width: 1800px;
    height: 508px;
    /* border: 1px solid red; */
    margin: 0 auto;
    margin-top: 20px;
    border-radius: 20px;
}
.module_left{
    width: 400px;
    height: 508px;
    background-color: aliceblue;
    float: left;
    border-radius: 20px 0px 0px 20px;
}
.module_middle{
    width: 1000px;
    height: 508px;
    background-color: aliceblue;
    float: left;
}
.module_right{
    width: 400px;
    height: 508px;
    background-color: aliceblue;
    float: left;
    border-radius: 0px 20px 20px 0px;
}
.module_left ul li img{
    width: 42px;
    height: 42px;
}
.module_left ul li {
    width: 340px;
    height: 64px;
    /* border-bottom: 1px solid black; */
    display: flex;
    align-items: center;
}
.module_left ul{
    width: 360px;
    height: 470px;
    margin: 10px;
    padding: 10px;
    border-radius: 20px;
    box-shadow: 
        0 0 10px 5px rgba(211, 211, 211, 0.5), /* 浅灰色阴影 */
        1px 1px 1px 1px black; /* 保留原有的黑色阴影 */
}
.module_left ul li{
    font-size: 12px;
    transition: all 0.2s ease-in-out; /* 平滑过渡 */

}
.module_left ul li:hover {
    color: orange;
    cursor: pointer;
    transform: translateY(-5px); /* 悬浮效果，向上浮动5px */
    transform: translateY(-5px) translateX(5px); /* 向上浮动5px，同时向右移动5px */

}
.left_item{
    width: 200px;
    height: 42px;
    margin-left: 10px;
}
.left_item i{
    margin-right: 10px;
}

</style>