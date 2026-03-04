<template>
    <div class="weapon_zw">
        <ul class="weapon_zw_ul" v-for="(item,index) in propertyDataItem?.str_" :key="index">
            <li v-if="item!==''">{{ item }}</li>
        </ul>
    </div>
</template>

<script>

export default {
    name: 'WeaponB',
    props: {
        propertyDataItem: {
            type: Object,
            required: true
        }
    }, 
    setup () {
        

        return {}
    }
}
</script>

<style  scoped>
.weapon_zw {
    width: 100%;
    height: 230px;
    display: flex;
    flex-wrap: wrap;  /* 允许超出宽度后换行 */
    align-content: flex-start; /* 让多行对齐到顶部 */
}
.weapon_zw_ul {
    margin-left: 50px;
    display: flex;
    flex-wrap: wrap; /* 让 li 在超出宽度时换行 */
    gap: 10px; /* 让 li 之间有间距 */
}
.weapon_zw_ul li {
    margin-top: 10px;
    list-style: none;
    padding: 5px 10px;
    background: #f4f4f4;
    border-radius: 5px;
    white-space: nowrap; /* 防止 li 内部文本换行 */
}
</style>