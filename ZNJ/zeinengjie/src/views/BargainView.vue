<template>
    <div class="filter-nav">
      <!-- 🎯 筛选条件标题 -->
      <div class="filter-header">
        <span class="title">条件筛选</span>
        <div class="actions">
          <el-tag closable type="danger" @close="clearSelection(1)" v-if="selectedRegion">{{selectedRegion}}</el-tag>
          <el-tag closable type="danger" @close="clearSelection(2)" v-if="selectedServer">{{selectedServer}}</el-tag>
          <el-button text @click="clearAll">清空筛选</el-button>
        </div>
      </div>
  
      <!-- 🎮 游戏服务器筛选 -->
      <div class="filter-row">
        <span class="label">游戏区服</span>
        <el-select v-model="selectedRegion" placeholder="选择游戏" destroy-on-close>
          <el-option v-for="(item,index) in games" :key="index" :label="item.gameallName" :value="item.gameallName"></el-option>
        </el-select>
        <el-select v-model="selectedServer" placeholder="设备">
          <el-option v-for="item in servers" :key="item" :label="item" :value="item"></el-option>
        </el-select>
        <el-input v-model="searchKeyword" placeholder="🔍 关键词" class="search-input" />
        <el-button icon="el-icon-search" type="primary">搜索</el-button>
      </div>
      
      <!-- 🚀 热门推荐 -->
      <!-- <div class="filter-row">
        <span class="label">🔥 热门推荐</span>
        <div class="hot-tags">
          <el-button v-for="tag in hotTags" :key="tag" text class="hot-tag">{{ tag }}</el-button>
        </div>
      </div> -->
  
      <!-- 💰 价格筛选 -->
      <!-- <div class="filter-row">
        <span class="label">💰 价格范围</span>
        <div class="price-options">
          <el-button v-for="price in priceRanges" :key="price" text class="price-btn">{{ price }}</el-button>
        </div>
        <el-input v-model="priceMin" placeholder="¥ 最低价" class="price-input" />
        <span>-</span>
        <el-input v-model="priceMax" placeholder="¥ 最高价" class="price-input" />
        <el-button type="primary">确定</el-button>
      </div>   -->
      <!-- 🎭 高级筛选 -->
      <!-- <div class="filter-row">
        <span class="label">⚙️ 高级筛选</span>
        <div class="advanced-options">
          <el-button v-for="option in advancedOptions" :key="option" text class="advanced-btn">{{ option }}</el-button>
        </div>
      </div> -->
    </div>
    <div>
      <BargainBody :selected-Game="selectedGame"/>
    </div>
    <div>
      <PageFooter/>
    </div>
  </template>
  
  <script>
  import BargainBody from '@/components/body/bargain/BargainBody';
  import PageFooter from '@/components/footer/PageFooter';
  import {getGameform} from "@/tool/backgroundapi" 
  export default {
    data() {
      return {
        selectedRegion: "",
        selectedArea: "",
        selectedServer: "",
        searchKeyword: "",
        priceMin: "",
        priceMax: "",
        selectedServices: [],
        games: ["穿越火线", "英雄联盟", "王者荣耀"],
        servers: ["PC", "Android", "双端"],
        hotTags: ["幻神", "幻神三件套", "6盘", "6烈", "炫芒蝶刀"],
        priceRanges: ["1元内", "2元内", "3元内", "5元内", "10元内"],
        advancedOptions: ["选择登录方式", "等级"],
        selectedGame: "",
      };
    },
    methods: {
      clearSelection(value) {
        switch (value) {
          case 1:
            this.selectedRegion = "";
            break;
          case 2:
            this.selectedServer = "";
            break;
        }
      },
      clearAll() {
        this.selectedRegion = "";
        this.selectedArea = "";
        this.selectedServer = "";
        this.searchKeyword = "";
        this.priceMin = "";
        this.priceMax = "";
        this.selectedServices = [];
      },
      getGameformFun(){
        getGameform().then((res)=>{
            console.log(res.data);
            this.games=res.data.data;
        }).catch((error)=>{
            console.log("游戏列表获取失败！");
            console.log(error);
        
        })
      },

    },
    watch:{
      selectedRegion:function(newValue, oldValue){
        try{
          if (newValue !== oldValue) {
            console.log("selectedRegion 发生了变化", newValue);
            requestAnimationFrame(() => {
                this.selectedGame = newValue;
           });
          }
        }catch(error){
          console.log(error);
        }
      }
    },
    mounted() {
      const gameName= this.$route.query.gameName;
      this.selectedGame=gameName;
      this.selectedRegion = gameName;
      this.getGameformFun()
      console.log("获取到的参数 gameName:", gameName);
    },
    components: {
      BargainBody,
      PageFooter,
    }
  };
  </script>
  
  <style scoped>
  /* 🎨 总体美化 */
  .filter-nav {
    background: #ffffff;
    padding: 20px;
    border-radius: 12px;
    box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
    color: #333;
    max-width:1750px;
    margin: 20px auto;
  }
  
  /* 🎯 标题部分 */
  .filter-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 2px solid #eee;
    padding-bottom: 10px;
    margin-bottom: 15px;
  }
  
  .title {
    font-size: 18px;
    font-weight: bold;
  }
  
  .actions {
    display: flex;
    gap: 10px;
  }
  
  /* 🎮 筛选项布局 */
  .filter-row {
    display: flex;
    align-items: center;
    flex-wrap: wrap;
    gap: 10px;
    margin-bottom: 15px;
  }
  
  /* 📌 标签 */
  .label {
    font-weight: bold;
    min-width: 100px;
    text-align: right;
    color: #666;
  }
  
  /* 🔍 搜索框优化 */
  .search-input {
    width: 150px;
    border-radius: 6px;
    padding: 5px;
  }
  
  /* 🔥 热门推荐 */
  .hot-tags {
    display: flex;
    gap: 8px;
  }
  
  .hot-tag {
    background: #ff4081;
    color: white;
    border-radius: 10px;
    transition: all 0.3s ease;
  }
  
  .hot-tag:hover {
    background: #d81b60;
  }
  
  /* 💰 价格 */
  .price-options {
    display: flex;
    gap: 5px;
  }
  
  .price-btn {
    background: #4caf50;
    color: white;
    border-radius: 6px;
  }
  
  .price-btn:hover {
    background: #388e3c;
  }
  
  /* 价格输入框 */
  .price-input {
    width: 80px;
    text-align: center;
    border-radius: 6px;
    padding: 5px;
  }
  
  /* 🎭 高级筛选 */
  .advanced-options {
    display: flex;
    gap: 10px;
  }
  
  .advanced-btn {
    background: #673ab7;
    color: white;
    border-radius: 8px;
  }
  
  .advanced-btn:hover {
    background: #512da8;
  }
  </style>
  