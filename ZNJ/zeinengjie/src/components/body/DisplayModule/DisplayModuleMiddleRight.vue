<template>
    <div class="display_middle_right">
        <div class="top">
            <swiper
                :modules="[EffectCube,  Autoplay]"
                :slides-per-view="1"
                :loop="true"
                :autoplay="{ delay: 2000 }"
                :pagination="{ clickable: true }"
                effect="cube"
                class="mySwiper"
             >
            <swiper-slide v-for="(img, index) in images" :key="index">
                <img :src="img" alt="轮播图" class="slide-img" />
                <div class="zhuan">
                    <span style="font-size: 10px; color:orange; opacity: 0.8;">
                        今日最低,<span style="color: red;">精选最牛号</span>
                    </span>
                    <br>
                    <i style="font-size: 15px; opacity: 0.9;">今日抢购人数：<i style="color: red; font-size: 12px; opacity: 0.7;">10万+</i></i>
                    <button class="bt_qiang">
                        <i style="font-size: 15px; font-weight: bolder; margin-top: 5px; display: block; float: left; color: red;">￥0.5/时</i>
                        <img src="@/assets/lbt/qiang-icon.png" alt="" class="qiang-icon">
                    </button>
                </div>
            </swiper-slide>
            </swiper>
            <div>
                <img src="@/assets/lbt/tj-tit.png" alt="" class="jrtj">
                <br>
                <div style="font-size: 12px; margin:10px 0px 40px 20px; ">
                    每日抢购，快来抢购吧！数量有限！
                </div>
                <i style="font-size: 12px; margin:50px 0px 0px 80px; opacity: 0.5;">
                    剩余租赁 3 次
                </i>
                <br>
                <button class="but_jrzq" @click="z_qu">进入专区</button>
            </div>
        </div>
        <div class="bottom">
            <div class="bottom_ph">
                <i>
                    <img src="@/assets/ico/gb.png" alt="射击排行" class="ph_ico_img">
                    <span class="bottom_ph_text">射击排行</span>
                </i>
                <ul>
                    <li>1、穿越火线</li>
                    <li>2、三角洲</li>
                    <li>3、绝地求生</li>
                    <li>4、CS:GO2</li>
                    <li>5、无畏契约</li>
                    <li>6、生死狙击</li>
                </ul>
            </div>
            <div class="bottom_ph">
                <i>
                    <img src="@/assets/ico/yb.png" alt="动作排行"  class="ph_ico_img">
                    <span class="bottom_ph_text">动作排行</span>
                </i>
                <ul>
                    <li>1、永结无间</li>
                    <li>2、地下城勇士</li>
                    <li>3、拳皇9</li>
                    <li>4、阿尔登法环6</li>
                    <li>5、只狼：影逝二度</li>
                    <li>6、黑神话：悟空</li>
                </ul>
            </div>
            <div class="bottom_ph">
                <i>
                    <img src="@/assets/ico/jb.png" alt="策略排行"  class="ph_ico_img">
                    <span class="bottom_ph_text">策略排行</span>
                </i>
                <ul>
                    <li>1、文明6</li>
                    <li>2、文明7</li>
                    <li>3、废土帝国：人类复兴</li>
                    <li>4、X4：基石</li>
                    <li>5、全面战争：法老</li>
                    <li>6、星际争霸</li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { Swiper, SwiperSlide } from "swiper/vue";
import "swiper/css";
import "swiper/css/effect-cube"; /* 引入 Cube 3D 效果样式 */
import "swiper/css/pagination";
import { useRouter } from "vue-router";
import { EffectCube, Autoplay } from "swiper/modules";
const router = useRouter();
       const images = ref([
                require('@/assets/lbt/xz_a.png'),
                require('@/assets/lbt/xz_b.png'),
                require('@/assets/lbt/xz_c.png'),
                require('@/assets/lbt/xz_d.png'),
                ]);
        const z_qu = () => {
            router.push({ path: '/BargainView', query: { gameName: '穿越火线'} });
        }
</script>

<style scoped>
.bottom_ph ul li{
    margin-top: 8px;
    width: 100%;
    font-style: normal;
    font-size: 14px;
}
.bottom_ph ul li:hover{
    box-shadow: 0px 0px 1px rgba(196, 196, 196, 0.3);
    color: orange;
    cursor: pointer;
    font-weight: bold;
}
.bottom_ph_text{
    display: inline;
    margin-left: 10px;
    font-style: normal;
    font-weight: bolder;
}
.ph_ico_img{
    height: 35px;
}
.bottom_ph{
    width: 30%;
    height: 90%;
    border-right: 1px solid rgba(0, 0, 0, 0.2);

    margin-top: 5px;
}
.but_jrzq{
    height: 40px;
    width: 100px;
    border-radius: 20px;
    margin: 10px 0px 0px 70px;

}
.but_jrzq:hover{
    background-color: black;
    color: white;
    cursor: pointer;
}
.jrtj{
    height: 30px;
    margin:40px 0px 0px 50px
}
.qiang-icon{
    width: 40px;
    height: 30px;
    float: right;
    border-radius: 10px;
}
.bt_qiang{
    width: 100px;
    height: 30px;
    border: 1px solid red;
    margin-top: 5px;
    margin-left: 27px;
    cursor: pointer;
    border-radius: 10px;
}
.zhuan{
    width: 150px;
    height: 90px;
    background-color: aliceblue;
    border-radius: 20px;
    margin-left: 17%;
    box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.2); /* 添加灰色阴影 */
    margin-bottom: 10px;
}
.slide-img {
  width: 90px;
  height: 90px;
  border-radius: 10px;
  margin-left: 30%;
}
.mySwiper {
  width: 200px; /* 这里可以调整 Swiper 组件的大小 */
  height: 200px;
  float: right;
  margin-right: 80px;
  margin-top: 5px;
  /* box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.2);  */
}
.display_middle_right{
    width: 69%;
    height: 100%;
    /* border: 1px solid red; */
    float:right;
}
.top{
    width: 100%;
    height: 45%;
    margin-top: 10px;
    background-color: #FFCBE9;
    border-radius: 15px;
}
.bottom{
    width: 100%;
    height: 45%;
   background-color: #FFE60D;
   border-radius: 15px;
    margin-top: 25px;
    display: flex;
    justify-content: space-evenly; /* 均匀分配 */
}
</style>