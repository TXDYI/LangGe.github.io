<template>
    <div class="img_e">
     <swiper
      :spaceBetween="10"
      :freeMode="true"
      :freeModeMomentum="true"
      :freeModeMomentumRatio="1"
      :freeModeSticky="false"
      :resistanceRatio="0.5"
      :navigation="true"
      :modules="[FreeMode,Navigation]"
      class="mySwiper"
    >
      <swiper-slide v-for="(item, index) in props.zuhaoSourceImg?.img" :key="index">
        <img :src="item" alt="缩略图">
      </swiper-slide>
    </swiper>
    </div>
  </template>
  
  <script setup>
  // import { ref } from 'vue';
  import { Swiper, SwiperSlide} from 'swiper/vue';
  import { FreeMode } from 'swiper/modules';
  import { Navigation } from "swiper/modules";
  import 'swiper/css';
  import 'swiper/css/free-mode';
  
  const props = defineProps({
    zuhaoSourceImg: {
      type: Object,
      required: true
    },
  });
  </script>
  
  <style scoped>
.mySwiper .swiper-button-next,
.mySwiper .swiper-button-prev {
  /* 修改按钮颜色 */
  color: #ff7c00;  /* 橙色，可根据需要修改 */
  
  /* 设置按钮大小 */
  width: 40px;
  height: 40px;
  
  /* 如果箭头图标由字体图标渲染，可以设置 font-size */
  font-size: 24px;
}

/* 可选：设置鼠标悬停时的效果 */
.mySwiper .swiper-button-next:hover,
.mySwiper .swiper-button-prev:hover {
  color: #ff9c40; /* 悬停时颜色稍微变亮 */
}
  .mySwiper {
    width: 800px;
    height: 700px;
  }
  .swiper-slide {
    display: flex;
    justify-content: center;
    align-items: center;
    background: #f0f0f0;
    font-size: 18px;
    border: 1px solid #ccc;
    border-radius: 8px;
  }
.swiper-slide img {
    width: 800px;
    height: 700px;
}
.img_e{
    margin: 0 auto;
}
  </style>
  