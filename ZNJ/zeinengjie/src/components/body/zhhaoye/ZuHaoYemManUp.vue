<template>
    <div class="zh-hao-yem-man-up"  v-show="lock">
        <div class="content">
            <div class="content-a">
                <router-link to="/" >首页</router-link>
                <span class="dy">&gt;</span>
                <router-link to="/BargainView" >大厅</router-link>
            </div>
            <div class="swiper-container">
                    <!-- 主要轮播图 -->
                    <swiper
                    :spaceBetween="10"
                    :navigation="false"
                    :thumbs="{ swiper: thumbsSwiper }"
                    :modules="[Thumbs, Navigation]"
                    class="main-swiper"
                    >
                    <swiper-slide v-for="(image, index) in props.zuhaoSource?.img " :key="index">
                        <img :src="image" alt="主图">
                    </swiper-slide>
                    </swiper>

                    <!-- 缩略图轮播 -->
                    <swiper
                    @swiper="setThumbsSwiper"
                    :spaceBetween="40"
                    :slidesPerView="4"
                    :freeMode="true"
                    :watchSlidesProgress="true"
                    :modules="[Thumbs]"
                    class="thumbs-swiper"
                    >
                    <swiper-slide v-for="(image, index) in props.zuhaoSource?.img" :key="index">
                        <img :src="image" alt="缩略图">
                    </swiper-slide>
                    </swiper>
            </div>
            <span style="display: block; margin-top: 150px; opacity: 0.5; font-size: 12px;">已租<span style="color: red;">100万+</span></span>
        </div>
        <div class="content-right">
                <span class="content-right-title">{{title}}</span>
                <br>
                <span class="content-right-title-game-name_qu">{{ game_name_pu }}</span>
                <br>
                <ul class="ul_items">
                        <li
                            v-for="(item, index) in rentOptions"
                            :key="index"
                            class="li_item"
                            :class="{ selected: selectedIndex === index }"
                            @click="selectItem(index)"
                        >
                            <span>{{ item.type }}</span>
                            <br />
                            <span class="span_item_price">￥{{ item.price }}</span>
                            <span>/{{ item.unit }}</span>
                            <br />
                            <span v-if="item.defaultText" class="default-text">{{ item.defaultText }}</span>
                            <span v-if="item.hoverText" class="hover-text">{{ item.hoverText }}</span>
                        </li>
                        </ul>
                <div class="num_box">
                    <input type="button" value="-" class="but_RedT" @click="but_RedT_"  v-show="selectedIndex === 1"/>
                    <span class="but_num"  v-show="selectedIndex === 1">{{wq_num}}</span>
                    <input type="button" value="+" class="but_add" @click="but_add_"  v-show="selectedIndex === 1"/>
                    <span v-show="selectedIndex === 1">/时</span>
                </div>
                <el-button :plain="true" @click="open4" class="but_goumai">
                    <span class="ljzh">立即租号</span>
                </el-button>
                <el-drawer v-model="drawer" title="I am the title" :with-header="false">
                    <BuyNow :rentOption="rentOption" :data_reship_fun="props.data_reship" :orderById="props.zuhaoSource.id"/>
                </el-drawer>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted ,watch} from 'vue';
import { Swiper, SwiperSlide } from "swiper/vue";
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/thumbs";
import { Navigation, Thumbs } from "swiper/modules";
import BuyNow from "./BuyNow";
import { ElMessage } from 'element-plus'

const title = ref("【特价】可排位 幻神三件套 音效卡 6盘6烈 幻兽");
const game_name_pu = ref("穿越火线/南部/湖南一区");
// 缩略图 Swiper 实例
const thumbsSwiper = ref(null);
const wq_num = ref(1);
const drawer = ref(false)
const setThumbsSwiper = (swiper) => {
  thumbsSwiper.value = swiper;
};
const selectedIndex = ref(null); // 记录选中的索引
onMounted(() => {
  console.log("ZuHaoYemManUp钩子");
  
});
const rentOptions = ref([
  { type: "夜租", price: "5.00", unit: "夜", defaultText: "22:00:00-08:00:00", hoverText: "平均￥0.49/时",daqu:game_name_pu},
  { type: "时租", price: "1.20", unit: "时", defaultText: "一小时起租", wq_num: wq_num,daqu:game_name_pu},
  { type: "日租", price: "11.00", unit: "日", defaultText: "24小时起租", hoverText: "平均￥0.47",daqu:game_name_pu},
  { type: "周租", price: "60.00", unit: "周", defaultText: "七日爽翻天", hoverText: "平均￥0.46",daqu:game_name_pu }
]);

const props = defineProps({
  data_reship: {
    type: Object,
    required: true,
  },
  zuhaoSource:{
    type: Object,
    required: true,
  },
});

const lock = ref(false);
watch(() => props.zuhaoSource, (newValue, oldValue) => {
  console.log("zuhaoSource 发生了变化", newValue);
  console.log("oldValue：", oldValue);
  if (newValue) {
    rentOptions.value =  [
      { type: "夜租", price: newValue.money*10, unit: "夜", defaultText: "22:00:00-08:00:00", hoverText: "平均￥"+(newValue.money*10/12).toFixed(2)+"/时", daqu: game_name_pu },
      { type: "时租", price: newValue.money, unit: "时", defaultText: "一小时起租", wq_num: wq_num, daqu: game_name_pu },
      { type: "日租", price: newValue.money*23, unit: "日", defaultText: "24小时起租", hoverText: "平均￥"+(newValue.money*23/24).toFixed(2), daqu: game_name_pu },
      { type: "周租", price: newValue.money*144, unit: "周", defaultText: "七日爽翻天", hoverText: "平均￥"+(newValue.money*144/168).toFixed(2), daqu: game_name_pu }
    ];
    title.value = newValue.title;
    game_name_pu.value = newValue.gameName;
    lock.value=true;
  }
}, { immediate: true }); // `immediate: true` 会在初次绑定时立即执行一次



const rentOption = ref({})
const open4 = () => {
    console.log(rentOption.value)
    if(Object.keys(rentOption.value).length===0){
        ElMessage.error('请先选择一个商品！！')
    }else{
        drawer.value = true;
    }
}
const selectItem = (index) => {
  selectedIndex.value = index; // 更新选中索引
  rentOption.value = rentOptions.value[index];
};
const but_add_ = () => {
  wq_num.value++;
};
const but_RedT_ = () => {
    if(wq_num.value > 1){
        wq_num.value--;
    }
};
</script>
<style  scoped>
.num_box{   
    height: 34px;
    margin-top: 20px;
    margin-left: 395px;
}
.li_item.selected {
    background-color: #ff7c00;
    color: white;
    border: 2px solid #ff5000; /* 边框加粗，颜色稍深 */
    box-shadow: 0 6px 16px rgba(255, 80, 0, 0.4); /* 让阴影更明显 */
}
.but_add{
    height: 34px;
    line-height: 34px;
    background: #f9f9f9;
    width: 33px;
    border-color: #ddd;
}
.but_RedT{
    height: 34px;
    line-height: 34px;
    background: #f9f9f9;
    width: 33px;
    border-color: #ddd;
}
.but_num{
    height: 30px;
    line-height: 34px;
    text-align: center;
    padding: 0 4px;
    font-family: arial;
    border-color: #dde;
    display: inline-block;
    width: 40px;
}
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
.span_item_price{
    font-size: 20px;
    margin-top: 20px;
    font-weight: bolder;
}
.li_item:hover{
    background-color: #ff7c00;
    color: #fff;
    cursor: pointer;
}
.default-text-a { display: inline; }
.hover-text-a { display: none; }

.li_item:hover .default-text-a { display: none; }
.li_item:hover .hover-text-a { display: inline; }

.default-text-b { display: inline; }
.hover-text-b { display: none; }

.li_item:hover .default-text-b { display: none; }
.li_item:hover .hover-text-b { display: inline; }

.default-text-c { display: inline; }
.hover-text-c { display: none; }

.li_item:hover .default-text-c { display: none; }
.li_item:hover .hover-text-c { display: inline; }

.li_item{
    width: 170px;
    height: 100px;
    background-color: #eee;
    border-radius: 5px;
    padding: 10px 10px 10px 10px;
}
.ul_items{
    display: flex;
    justify-content: space-evenly; /* 每个子元素的间距相等 */
    margin-top: 20px;
}
.content-right-title-game-name_qu{
    display: block;
    float: left;
    margin-left: 20px;
}
.content-right-title{
    display: block;
    margin-top: 35px;
    font-size: 25px;
    font-weight: bolder;
}
.zh-hao-yem-man-up{
    width: 1190px;
    height: 481px;
    padding: 20px 30px 48px 30px;
    background-color: #fff;
    margin: 10px auto 0 auto;
    border-radius: 10px;
}
.content-a{
    width: 384px;
    height: 32px;
}
a{
    text-decoration: none;
}
a:hover{
    color: orange;
}
.dy{
    margin: 0 5px 0 5px;
}
.swiper-container {
    width: 384px;
    height: 264px;
}

/* 主轮播图样式 */
.main-swiper {
    width: 384px;
    height: 264px;
}

.main-swiper img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

/* 缩略图样式 */
.thumbs-swiper {
  width: 100%;
  height: 100px;
  margin-top: 10px;
}

.thumbs-swiper img {
  width: 100px;
  height: 60px;
  cursor: pointer;
  border: 2px solid transparent;
  transition: 0.3s;
}

/* 选中缩略图时高亮 */
.thumbs-swiper .swiper-slide-thumb-active img {
  border-color: #ff7c00;
}
.content{
    float: left;
}
</style>