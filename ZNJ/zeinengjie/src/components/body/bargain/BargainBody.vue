<template>
    <div class="bargain-body">
        <div>
            <ul>
                <li class="bargain_item">
                    <i v-for="(item, index) in response" :key="index">
                        <div class="big_img">
                            <img :src="item.img" :alt="item.gameName">
                        </div>
                        <div class="bargain_item_text">
                            {{item.title}}
                        </div>
                        <div class="bargain_item_test_two">
                            {{ item.gameName }}
                        </div>
                        <span style="margin-left: 10px;">押金：0.00￥</span>
                        <em></em>
                        <span>2小时起租</span>
                        <br>
                        <button class="bargain_item_but" @click="sp(item.id)">
                            <span style="float: left; margin-left: 20px; color: #fff; opacity: 1;">￥<span style="font-size: 20px; color: #fff; font-weight: bolder; opacity: 1;">{{ item.money }}</span></span>
                            <span style="text-decoration: line-through; float: right; margin-right: 30px; color: red; margin-top: 5px; opacity: 1;">5.5</span>
                        </button>
                    </i>
                    <h1 v-show="!response.length">抱歉！，没有商品</h1>
                </li>
            </ul>
            <div class="pagination-container" >
                <el-pagination
                    background
                    layout="prev, pager, next"
                    :total="total"
                    :page-size="pageSize"
                    :current-page="currentPage"
                    @current-change="fetchData"
                />
            </div>
        </div>
    </div>
</template>


<script setup>
import { ref, onMounted,watch } from "vue";
import {BarginsPage,apiClient} from "@/tool/bargainapi";
import { useRouter } from 'vue-router';
const total = ref(7);
const pageSize = ref(20);
const currentPage = ref(1);

const props = defineProps({
    selectedGame: {
    type: String,
    default: "",
  },
});

watch(() => props.selectedGame, async (newValue,oldValue) => {
    console.log("oldValue 发生了变化", oldValue);
    if(newValue){
        getPageCount(newValue);
        fetchData(currentPage.value,newValue);
        console.log("newValue 发生了变化", newValue);
    }
}),{immediate: true};
const response = ref([]);
const fetchData = async (page,gameName) => {
  currentPage.value = page;
  try {
    const res = await apiClient.get(`/bargain/getBargainData/${page}/${pageSize.value}` ,{
        params: { gameName }
    });
    response.value = res.data;
    console.log("fetchData:请求成功",response.value);
  } catch (error) {
    console.error("请求失败", error);
  }
};
const router = useRouter();
const sp=(Gid)=>{
    router.push({path: '/zuhao', query: { id: Gid } });
    }
const getPageCount= async (gameName)=>{
    await BarginsPage(gameName).then(res=>{
        total.value=res.data;
    }).catch(error=>{
        console.log(error); 
        console.log("getPageCount");
    })
}

onMounted(() => {
    console.log("BargainBody钩子");
    total.value=0
});
</script>

<style  scoped>
.big_img {
    width: 130px;
    height: 100px;
    overflow: hidden; /* 防止超出容器 */
    background-color: aliceblue; /* 选填：防止透明图片影响视觉 */
    float: left;
}

.big_img img {
    width: 100%;
    height: 100%;
    object-fit: contain; /* 让图片完整显示，等比缩放 */
}

.pagination-container {
    display: flex;
    justify-content: center;
    margin-top: 20px; /* 可选 */
}

.bargain_item_text {
    font-size: 14px;
    font-style: normal;
    font-weight: bolder;
    margin-left: 140px;
    overflow: hidden;  /* 隐藏超出部分 */
    display: -webkit-box;  
    -webkit-box-orient: vertical;  
    -webkit-line-clamp: 3;  /* 这里的 3 表示最多显示 3 行，多余的部分用省略号 */
    line-height: 1.5em;  /* 根据字体大小调整 */
    height: 4.5em;  /* 1.5em * 3 = 4.5em，确保能显示 3 行 */
}

.bargain_item i span{
    font-size: 12px;
    font-style: normal;
    opacity: 0.5;
}
.bargain_item_but{
    width: 165px;
    border: 1px solid #ff7c00;
    height: 26.5px;
    margin-left: 150px;
    background-image: url('~@/assets/bargain_img/bg-price.png');
    background-repeat: no-repeat;
    cursor: pointer;
}
em{
    border-right: 1px solid black;
    opacity: 0.5;
    margin: 0px 10px 0px 10px;
    font-size: 12px;
    font-style: normal;
}

.bargain-body{
    width: 1800px;
    height: 100%;
    margin: 0 auto;
    border-radius: 20px;
    background: #ffffff;
}
.bargain_item{
    display: flex;
    flex-wrap: wrap;  /* 允许换行 */
    ustify-content: flex-start; /* 靠左排列 */
}
.bargain_item i{
    height: 172px;
    width: 390px;
    padding: 5px 5px 5px 5px;
    margin-bottom: 10px;
    margin-top: 10px;
}
.bargain_item i:hover{
    box-shadow: 0px 0px 0px 3px rgba(0,0,0,0.2);
    border-radius: 15px;
    cursor: pointer;
}
.bargain_item_text:hover{
    color: #ff7c00;
    font-weight: bolder;
}
.bargain_item_test_two{
    font-size: 12px;
    font-style: normal;
    opacity: 0.5;
    margin-left: 140px;
}
</style>