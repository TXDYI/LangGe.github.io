<template>
  <div class="head">
    <div class="head_body">
      <!-- 左侧 Logo -->
      <div class="head_navigation_left">
        <ul class="ul_content_left">
          <li><img class="logo" src="@/assets/logo.png" alt="我是logo"></li>
          <li><span @click="getNormalUserExplain">库</span></li>
        </ul>
      </div>

      <!-- 中间标题 -->
      <div class="znj">
        <span>贼能借</span>
      </div>

      <!-- 右侧导航 -->
      <div class="head_navigation_right">
        <ul class="ul_content_right">
          <li><span v-show="!userName" @click="go_login()">登入/注册</span> <span v-show="userName"> {{userName}}</span></li>
          <li><span>关于我们</span></li>
        </ul>
      </div>
    </div>

    <!-- 选择分类 -->
    <div class="select_Class">
      <ul>
        <li>
          <div class="flex gap-4">
            <div>
              <el-autocomplete
                v-model="state2"
                :fetch-suggestions="querySearch"
                :trigger-on-focus="false"
                clearable
                class="inline-input w-50"
                placeholder="搜索 游戏名称"
                size="small"
                @select="handleSelect"
              />
            </div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { onMounted, ref } from 'vue'
import {getGameform} from "@/tool/backgroundapi";
import { useRouter } from 'vue-router';
export default {
  name: "HeadConstruction",
  setup() {
    const state2 = ref('')
    const restaurants = ref([])
    const loadAll = ref([]);
    const querySearch = (queryString, cb) => {
      const results = queryString
        ? restaurants.value.filter(createFilter(queryString))
        : restaurants.value
        if(results.length==0){
          results.push({ value: "暂无游戏", link: false })
        }
        cb(results)
    }
    const userName = ref(localStorage.getItem('user'));
    const createFilter = (queryString) => {
      return (restaurant) => {
        return restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0
      }
    }
    const router = useRouter();
    const sp=(Gname)=>{
        router.push({ path: '/BargainView', query: { gameName: Gname } });
        }
    const handleSelect = (item) => {
      if(item.link){
        sp(item.value);
      }
    }
    const getGameformFun=()=>{
       getGameform().then((res)=>{
            console.log("Head",res.data);
            for(let i of res.data.data){
              loadAll.value.push({ value: i.gameallName, link: 'https://github.com/babel/babel'})
            }
        }).catch((error)=>{
            console.log("游戏列表获取失败！");
            console.log(error);
        
        })
      };
    onMounted(() => {
      getGameformFun();
      restaurants.value = loadAll.value
    })

    return {
      state2,
      querySearch,
      handleSelect,
      userName
    }

  },
  methods:{
    getNormalUserExplain(){
      this.$router.push("/normaluserexplain")
    },
   go_login(){
            this.$router.push('/login')
    },
  }
}
</script>

<style scoped>
ul {
  list-style-type: none;
}
/* 🔥 头部整体布局 */
.head {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 90px;
  background: linear-gradient(135deg, #1a2a6c, #b21f1f, #fdbb2d); /* 炫酷渐变 */
  color: white;
  z-index: 1000;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.5);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
}

/* 头部主体部分 */
.head_body {
  width: 90%;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
/* 左侧 Logo */
.ul_content_left {
  display: flex;
  align-items: center;
}

.ul_content_left li {
  margin-right: 15px;
  font-size: 22px;
  font-weight: bold;
  cursor: pointer;
}

.logo {
  width: 50px;
}

/* 中间标题 */
.znj {
  font-size: 30px;
  font-weight: bold;
  letter-spacing: 2px;
}

/* 右侧导航 */
.ul_content_right {
  display: flex;
}

.ul_content_right li {
  margin-left: 20px;
  font-size: 18px;
  cursor: pointer;
  transition: 0.3s;
}

.ul_content_right li:hover {
  color: yellow;
}

/* 🔥 选择分类部分 */
.select_Class {
  width: 100%;
  background: rgba(0, 0, 0, 0.2);
  padding: 10px 0;
}

.select_Class ul {
  display: flex;
  justify-content: center;
  gap: 30px;
  font-size: 18px;
}

.select_Class ul li {
  cursor: pointer;
  transition: 0.3s;
}

.select_Class ul li:hover {
  color: yellow;
  transform: scale(1.1);
  text-shadow: 0px 0px 10px rgba(255, 204, 0, 0.8);
}
</style>