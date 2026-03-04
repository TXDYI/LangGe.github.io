<template>
    <div class="display_middle_left">
        <swiper
                :modules="[ Pagination, Autoplay]"
                :slides-per-view="1"
                :space-between="10"
                :loop="true"
                :autoplay="{ delay: 3000 }"
                :pagination="{ clickable: true }"
                class="mySwiper"
         >
        <swiper-slide v-for="(img, index) in images" :key="index">
        <img :src="img" alt="轮播图" class="slide-img" />
        </swiper-slide>
    </swiper>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { Swiper, SwiperSlide } from "swiper/vue";
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import {Pagination, Autoplay } from "swiper/modules";

// 轮播图数据（必须用 ref()，确保响应式）
const images = ref([
                    require('@/assets/lbt/a.png'),
                    require('@/assets/lbt/b.png'),
                    require('@/assets/lbt/c.png'),
                    require('@/assets/lbt/d.png'),
]);
</script>

<style scoped>
.display_middle_left {
    float: left;
    width: 30%;
    height: 490px;
    border-radius: 20px;
}


.slide-img {
  width: 100%;
  height: 490px;
  border-radius: 10px;
  margin-top: 10px;
}
</style>