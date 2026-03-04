<template>
    <div>
        <ZuHaoYemManUp  :data_reship="data_reship" :zuhaoSource="zuhaoDataSource"/>
        <ZuHaoYemManMiddle  @account_overview_fun="root_data_fun" :zuhao-source-img="zuhaoImgSource"/>
        <ZuHaoYemManBelow/>
        <PageFooter/>
    </div>
</template>

<script>
import { ref } from 'vue';
import ZuHaoYemManUp from '@/components/body/zhhaoye/ZuHaoYemManUp';
import ZuHaoYemManMiddle from '@/components/body/zhhaoye/ZuHaoYemManMiddle';
import ZuHaoYemManBelow from '@/components/body/zhhaoye/ZuHaoYemManBelow';
import PageFooter from '@/components/footer/PageFooter';
import { getApiZuhaoData } from '@/tool/zuhaoapi';
export default {
    name: 'ZuHaoYeMianViews',
    setup () {
        const data_reship=ref()
        const zuhaoDataSource=ref(null)
        const zuhaoImgSource=ref(null)
        return {
            data_reship,
            zuhaoDataSource,
            zuhaoImgSource,
        }
    },
    methods: {
        root_data_fun(data){
            this.data_reship=data;
        },
        getZuhaoDataFun(zuhaoId){
            return getApiZuhaoData(zuhaoId)
                 .then(result=>{
                    console.log("返回了数据");
                    this.zuhaoDataSource= result.data.data;
                    this.zuhaoImgSource= result.data.data;
                    console.log("zuhaoDataSource=>",this.zuhaoDataSource);
                    console.log("zuhaoImgSource",this.zuhaoImgSource);
                })
                .catch(err => {
                    console.error("请求失败:", err);
                });
        },
    },
    mounted() {
        const id= this.$route.query.id; 
        this.getZuhaoDataFun(id)
        console.log("获取到的参数 id:", id);
    }
    ,components:{
        ZuHaoYemManUp,
        ZuHaoYemManMiddle,
        ZuHaoYemManBelow,
        PageFooter,
    },
}
</script>

<style  scoped>

</style>