<template>
  <el-dialog v-model="visible" title="修改信息" width="500px" :close-on-click-modal="false">
    <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
      <el-form-item label="原密码" prop="oldPassword">
        <el-input v-model="form.oldPassword" type="password" autocomplete="off" />
      </el-form-item>

      <el-form-item label="新密码" prop="newPassword">
        <el-input v-model="form.newPassword" type="password" autocomplete="off" />
      </el-form-item>

      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input v-model="form.confirmPassword" type="password" autocomplete="off" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="visible = false">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script setup>
import { ref, watch } from 'vue';
import { ElMessage } from 'element-plus';
import { updatePro} from '@/tool/managementapi';

const props = defineProps({
  modelValue: Boolean
});
const emit = defineEmits(['update:modelValue']);

const visible = ref(false);
watch(() => props.modelValue, val => visible.value = val);
watch(visible, val => emit('update:modelValue', val));

const formRef = ref();
const form = ref({
  oldPassword: '',
  newPassword: '',
  confirmPassword: ''
});
const rules = {
  oldPassword: [{ required: true, message: '请输入原密码', trigger: 'blur' }],
  newPassword: [{ required: true, message: '请输入新密码', trigger: 'blur' }],
  confirmPassword: [
    { required: true, message: '请确认密码', trigger: 'blur' },
    {
      validator(rule, value) {
        return value === form.value.newPassword
          ? Promise.resolve()
          : Promise.reject('两次密码不一致');
      },
      trigger: 'blur'
    }
  ]
};

const submitForm = () => {
  formRef.value.validate(async (valid) => {
    if (!valid) return;

    const formData = new FormData();
    formData.append('oldPassword', form.value.oldPassword);
    formData.append('newPassword', form.value.newPassword);
    const user = localStorage.getItem('user')
    try {
      const res = await updatePro({
            oldPassword:form.value.oldPassword,
            newPassword:form.value.newPassword,
            User:user
      });
      console.log(res.data);
      
      if  (res.data !== 200) {
        ElMessage.error("修改失败，注意原密码是否正确！");
        form.value.oldPassword = '';
        form.value.newPassword = '';
        form.value.confirmPassword = '';
        return;
      }
      ElMessage.success('修改成功');
      visible.value = false;
      form.value.oldPassword = '';
      form.value.newPassword = '';
      form.value.confirmPassword = '';
    } catch (err) {
      ElMessage.error('网络错误，修改失败');
      console.error(err);
    }
  });
};
</script>

<style scoped>
.avatar-uploader {
  width: 100px;
  height: 100px;
}
</style>
