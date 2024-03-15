<template>

  <div style="width: 100%; place-items: center">
    <div class="s_header">
      <p style="width: 8vw; margin: auto">开店申请</p>
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

        <el-form-item label="商店名称" prop="shopName" style="margin-top: 4vh">
          <el-input v-model="ApplyForm.shopName" />
        </el-form-item>

        <el-form-item label="商品类别" prop="tag" style="margin-top: 4vh">
          <el-input
              v-model="ApplyForm.tag"
              placeholder="示例：食品,家电,母婴用品"
          />
        </el-form-item>

        <el-form-item label="备案地址" prop="address" style="margin-top: 4vh">
          <el-input
              v-model="ApplyForm.address"
              maxlength="32"
              :show-word-limit=true
          />
        </el-form-item>

        <el-form-item label="身份证号" prop="idCard" style="margin-top: 4vh">
          <el-input v-model="ApplyForm.idCard" />
        </el-form-item>

        <el-form-item label="注册资金" prop="fund" style="margin-top: 4vh">
          <el-input-number v-model="ApplyForm.fund" :precision="3" :step="1000" :min="1000" style="width: 19vw;display: inline-block;margin-right: 15px"/>
          <p style="color: darkgray">（单位：元）</p>
        </el-form-item>

        <el-form-item label="商店介绍" prop="introduction" style="margin-top: 4vh">
          <el-input
              v-model="ApplyForm.introduction"
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
  shopName: '',
  tag: '',
  introduction: '',
  fund: '',
  idCard: '',
  address: '',
})

const rules = reactive({
  shopName: [
    { required: true, message: '商店名称不能为空', trigger: 'blur' },
    { max: 12, message: '商店名称长度应在12个字符以内', trigger: 'blur' }
  ],
  tag: [
    { required: true, message: '商品类型不能为空', trigger: 'blur' },
  ],
  introduction: [
    { required: true, message: '商店简介不能为空', trigger: 'blur' },
    { max: 128, message: '商店简介长度应在128个字符以内', trigger: 'blur' },
  ],
  address: [
    { required: true, message: '备案地址不能为空', trigger: 'blur' },
    { max: 32, message: '备案地址长度应在32个字符以内', trigger: 'blur' },
  ],
  idCard: [
    { required: true, message: '身份证号不能为空', trigger: 'blur' },
    { pattern: /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/, message: '身份证号格式错误', trigger: 'blur'}
  ],
  fund: [
    { required: true, message: '注册资金不能为空', trigger: 'blur' },
  ],
})

const Apply = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      service.post('/shop/apply', {
        shopName: ApplyForm.shopName,
        tag: ApplyForm.tag,
        introduction: ApplyForm.introduction,
        fund: ApplyForm.fund,
        idCard: ApplyForm.idCard,
        address: ApplyForm.address,
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