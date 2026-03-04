<template>
    <div class="zh-hao-yem-man-middle">
        <div class="zh-hao-yem-man-middle-text">
            <h3>商品详细</h3>
        </div> 
        <div class="weapon">
            <em></em>
            <span class="zhgss_text">详细</span>
        </div>
        <WeaponA :property-data="propertyData" />
        <div class="weapon">
            <em></em>
            <span class="zhgss_text">游戏账号截图</span>
        </div>
        <div class="imgs">
            <ImagesE :zuhaoSourceImg="zuhaoSourceImg"/>
        </div>
    </div>
</template>

<script>
import { ref } from 'vue';
import WeaponA from './WeaponA';
import ImagesE from './ImagesE';

export default {
    name: 'ZuHaoYemManMiddle',
    props: {
        zuhaoSourceImg: Object,
    },
    setup() {
        const account_overview = ref({
            gameName:"",
            GameLv:"",
        });
        const propertyData = ref("");
        return {
            account_overview,
            propertyData
        };
    },
    components: {
        WeaponA,
        ImagesE,
    },
    methods: {
        account_overview_fun() {
            this.$emit('account_overview_fun', this.account_overview);
        },
    },
    mounted() {
        this.account_overview_fun();
    },
    watch: {
    zuhaoSourceImg: {
      immediate: true,
      handler(newVal) {
        if (newVal) {
            this.propertyData = newVal.content;
            this.account_overview.gameName=newVal.gameCharacterName
            this.account_overview.GameLv=newVal.gamelv
        }
      }
    }
  },
}
</script>

<style scoped>
.weapon {
    margin-top: 10px;
}
.zh-hao-yem-man-middle {
    width: 1190px;
    height: 2000px;
    padding: 20px 30px 48px 30px;
    background-color: #fff;
    margin: 10px auto 0 auto;
    border-radius: 10px;
}
.zh-hao-yem-man-middle-text {
    width: 100%;
    height: 47px;
    font-size: 20px;
}

em {
    border-right: 5px solid orange;
    border-radius: 20px;
    margin-right: 10px;
}
</style>
