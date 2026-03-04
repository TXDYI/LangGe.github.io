<template>
    <div id="home">
      <HeadConstruction />
      <br>
      <br>
      <br>
      <br>
      <HomeDisplayModule/>
      <FamousBody :item="imgSrc[0]" :gameshow="cyhx"/>
      <FamousBody :item="imgSrc[1]" :gameshow="csgo"/>
      <FamousBody :item="imgSrc[2]" :gameshow="jdqs"/>
      <FamousBody :item="imgSrc[3]" :gameshow="wwqy"/>
      <div>
        <PageFooter/>
      </div>
    </div>
  </template>
  <script>
  import HeadConstruction from '@/components/head/HeadConstruction';
  import FamousBody from '@/components/body/FamousBody';
  import PageFooter from '@/components/footer/PageFooter';
  import HomeDisplayModule from '@/components/body/DisplayModule/HomeDisplayModule'; 
  import { getGameShow } from '@/tool/backgroundapi';
  import { ElMessage } from 'element-plus'
  export default {
    name: 'HomeView',
    components: {
      HeadConstruction,
      FamousBody,
      PageFooter,
      HomeDisplayModule,
    },
    data() {
      return {
        imgSrc: [
          require('@/assets/cyhx.png'),
          require('@/assets/csgo.png'),
          require('@/assets/jdqs.png'),
          require('@/assets/wwqy.png'),
        ],
        cyhx: [],
        csgo: [], 
        jdqs: [],
        wwqy: [],
        gameshowData: Object,
      };
    },
     mounted() {
      console.log("HomeView");
       this.getGameShowFun();
    },
    methods: {
      
      async  getGameShowFun(){
          try{
            await getGameShow().then(result=>{
                  if(result.data){
                        this.gameshowData= result.data;
                        for(let item of this.gameshowData){
                          if(item.gameName==="穿越火线"){
                            this.cyhx.push(item);
                          }else if(item.gameName==="CS:GO2"){
                            this.csgo.push(item);
                          }else if(item.gameName==="绝地求生"){
                            this.jdqs.push(item);
                          }else if(item.gameName==="无畏契约"){
                            this.wwqy.push(item);
                          }
                        }
                  }
                     console.log("getGameShowFun",result.data);
                    }).catch(error=>{
                    ElMessage.error("服务器错误！")
                    console.log(error);
                });
            }catch(e){
                  console.log(e);
          }
      }
    }
  };
  </script>
  
  <style>
  * {
    margin: 0;
    padding: 0;
  }
  body {
    background-color: rgb(35, 39, 54);
  }
  ul{
    list-style-type: none;
  }
  </style>
  