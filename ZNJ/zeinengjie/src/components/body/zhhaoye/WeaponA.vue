<template>
    <div class="wq_a">
        <el-menu
            :default-active="activeIndex2"
            class="el-menu-demo"
            mode="horizontal"
            background-color="#f4f4f4"
            text-color="#000"
            active-text-color="#ff6700"
            @select="handleSelect"
        >
            <div v-for="(item, index) in itemProperty" :key="index">
                <el-menu-item :index="index.toString()">{{ item }}</el-menu-item>
            </div>
        </el-menu>
    </div>
    
    <el-container>
        <el-main>
            <!-- 使用动态组件渲染，根据 currentComponent 和 currentComponentProps 传递对应的 props -->
            <component :is="currentComponent" v-bind="currentComponentProps"/>
        </el-main>
    </el-container>
</template>

<script>
import { shallowRef, ref, watch } from 'vue'
import WeaponB from './WeaponB'

export default {
    name: 'WeaponA',
    props: {
        propertyData: {
            type: String,
            required: true
        }
    },
    setup(props) {
        const activeIndex = ref('0')
        const activeIndex2 = ref('0')
        const currentComponent = shallowRef(WeaponB)
        const itemProperty = ref([])
        const currentComponentProps = ref({})
        const result = ref([]);
        // 提取属性并更新 itemProperty
        const getProperty = () => {
            itemProperty.value = []  // 每次获取时清空旧数据
            const regex = /\{([^}]+)\}/g
            let match
            const str = props.propertyData
            while ((match = regex.exec(str)) !== null) {
                const item = match[1].split(',')  // 按逗号分割
                const title = item[0]  // 获取标题
                const properties = item.slice(1);  // 获取属性
                let str_=[]
                if(properties.length==1&&properties[0].includes('，')){
                    str_ =  properties[0].split('，');
                }else{
                    str_ =  properties[0].split(',');  
                }
                result.value.push({ title, str_ });
                itemProperty.value.push(title)  // 保存标题
            }
            console.log('Updated itemProperty:', itemProperty.value)
            console.log('result:', result.value)
        }

        // 监听 props.propertyData 变化
        watch(() => props.propertyData, () => {
            getProperty()
            currentComponentProps.value = { propertyDataItem: result.value[0]}
        }, { immediate: true })
        // 根据选择的菜单项更新组件
        const handleSelect = (key, keyPath) => {
            console.log(key, keyPath)
            currentComponentProps.value = { propertyDataItem: result.value[key]}
            console.log(currentComponentProps.value);
        }
        return {
            activeIndex,
            activeIndex2,
            handleSelect,
            currentComponent,
            currentComponentProps,
            itemProperty
        }
    }
}
</script>

<style scoped>

</style>
