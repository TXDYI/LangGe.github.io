<template>
    <div>
        <CollapsibleSidebar/>
    </div>
</template>

<script>
import CollapsibleSidebar from '@/components/admin_views/CollapsibleSidebar';
import { getAdminIndex } from '@/tool/managementapi';
export default {
    components:{
        CollapsibleSidebar,
    },
    setup(){
        return {
        }
    },
    mounted(){
        getAdminIndex().then(res=>{
            console.log(res.data)
        }).catch(err=>{
            console.log(err)
        })
    }
}
</script>

<style  scoped>

</style>