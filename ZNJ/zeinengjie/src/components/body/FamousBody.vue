<template>
    <div class="body_famous">
      <div class="block_body">
        <div class="game_img">
          <img :src="item" alt="" />
        </div>
        <div class="product_right">
            <div class="quick_select_top">
            </div>
            <div class="quick_buy_bottom">
              <div v-for="(item,index) in gameshow" :key="index">
                <FamousBock :gameshow="item"/>
              </div>
            </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import FamousBock from './FamousBock';
  export default {
    name:"FamousBody",
    components:{
      FamousBock
    },
    props: {
      item: String,
      gameshow: {
      type: Array,
      required: true, // 确保这个属性必须传递
      default: () => [] // 防止空值
  }
    },
    methods: {
    },
  };
  </script>
  
  <style scoped>
ul{
  list-style-type: none;
}
  .quick_select_top{
    height: 15%;
    /* border: 1px solid red; */
  }
  .quick_select_top ul li{
    float: left;
    height: 30px;
    width: max-content;
    background-color: rgb(244, 244, 244);
    border: 1px solid rgb(144, 144, 144);
    border-radius: 30px;
    margin-right: 20px;
    padding:9px 20px 0px 20px;
    font-size: 14px; 
    margin-top: 15px;
    color: #666;
  }
  .quick_select_top ul li:hover{
    background-color: rgb(16, 17, 17);
    color: aliceblue;
    cursor: pointer;
  }
  .quick_buy_bottom{
    height: 84.3%;
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: space-around;
  }

  .product_right{
    height: 508px;
    width: 85.5%;
    /* background-color: bisque; */
    float: right;
    border-radius: 20px;
  }
  .block_body {
    height: 508px;
    width: 1800px;
    background-color: rgb(252, 252, 252);
    margin: 0 auto;
    border-radius: 20px;
    box-shadow: 10px 10px 5px rgba(0, 0, 0, 0.5);

  }
  .game_img {
    height: 508px;
    width: 14.5%;
    border-radius: 20px;
    overflow: hidden;
    float: left;
  }
  .body_famous {
    margin-top: 10px;
  }
  .game_img img {
    height: 508px;
  }
  </style>
  