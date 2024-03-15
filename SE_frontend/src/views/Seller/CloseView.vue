<template>

  <div style="width: 100%; place-items: center">
    <div class="s_header">
      <p style="width: 8vw; margin: auto">关店申请</p>
    </div>

    <div class="subpage_form">
      <el-form
          ref="ApplyFormRef"
          :model="ApplyForm"
          :rules="rules"
          hide-required-asterisk="hide-required-asterisk"
          status-icon
          style="margin-top: 5vh;"
      >

        <el-form-item label="关店理由" prop="reason" style="margin-top: 4vh">
          <el-input
              v-model="ApplyForm.reason"
              maxlength="128"
              type="textarea"
              :show-word-limit=true
          />
        </el-form-item>

        <el-form-item
            style="margin-top: 7vh; place-items: center"
        >
          <el-button
              type="primary"
              @click="Apply(ApplyFormRef)"
              style="width: 5vw; margin: auto 1vw auto auto;"
          >
            申请
          </el-button>
          <el-button
              @click="resetForm(ApplyFormRef)"
              style="width: 5vw; margin: auto auto auto 1vw;"
          >
            重置
          </el-button>
        </el-form-item>

      </el-form>
    </div>
  </div>

</template>


<script lang="ts" setup>

import { reactive, ref } from 'vue'
import type { FormInstance } from 'element-plus'
import service from "../../request"
import {ElMessage} from "element-plus";

const ApplyFormRef = ref<FormInstance>()
const ApplyForm = reactive({
  reason: '',
})

const rules = reactive({
  reason: [
    { required: true, message: '关店理由不能为空', trigger: 'blur' },
    { max: 128, message: '关店理由应在128个字符以内', trigger: 'blur' },
  ],
})

const Apply = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      service.post('/shop/cancel', {
        reason: ApplyForm.reason,
      })
          .then(function (res) {
            if (res.status === 200) {
              ElMessage.success('申请成功')
              resetForm(formEl)
            }
          })
          .catch(function (error) {
            if (error.response) {
              ElMessage.error('申请失败，' + error.response.data.msg)
            }
          })
    } else {
      return
    }
  })
}

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}


</script>

<style scoped>

.s_header{
  font-size: 24px;
  color: #606266;
}

.subpage_form{
  width: 45vw;
  border: 1px solid lightgrey;
  border-radius: 20px;
  padding: 3vh 5vw;
  place-items: center;
  overflow: hidden;
  margin: 5vh auto auto;
}

</style>