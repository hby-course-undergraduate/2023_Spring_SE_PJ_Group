<template>

  <div style="width: 100%; place-items: center">

    <div class="subpage_form">

      <el-upload
          ref="uploadRef"
          :before-upload="beforeUpload"
          :auto-upload="false"
          v-model:file-list="fileList"
          action="aa"
          list-type="picture-card"
          accept=".jpg,.jpeg,.png,.JPG,.JPEG"
          style="margin-top: 5vh"
      >
        <el-icon><Plus /></el-icon>
      </el-upload>

      <el-form
          ref="ApplyFormRef"
          :model="ApplyForm"
          :rules="rules"
          hide-required-asterisk="hide-required-asterisk"
          status-icon
          style="margin-top: 5vh;"
      >

        <el-form-item label="商品名称" prop="goodsName" style="margin-top: 4vh">
          <el-input v-model="ApplyForm.goodsName" />
        </el-form-item>

        <el-form-item label="商品类别" prop="tag" style="margin-top: 4vh">
          <el-input v-model="ApplyForm.tag" />
        </el-form-item>

        <el-form-item label="商品价格" prop="price" style="margin-top: 4vh">
          <el-input-number v-model="ApplyForm.price" :precision="2" :min="0.01" style="width: 19vw;display: inline-block;margin-right: 15px"/>
          <p style="color: darkgray">（单位：元）</p>
        </el-form-item>

        <el-form-item label="库存数量" prop="number" style="margin-top: 4vh">
          <el-input-number v-model="ApplyForm.number" :step="1" :min="1" style="width: 19vw;display: inline-block;margin-right: 15px"/>
        </el-form-item>

        <el-form-item label="商品描述" prop="description" style="margin-top: 4vh">
          <el-input
              v-model="ApplyForm.description"
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

import { reactive, ref, getCurrentInstance } from 'vue'
import type { FormInstance, UploadInstance, UploadUserFile} from 'element-plus'
import service from "../../request"
import {ElMessage} from "element-plus";
import {useRouter} from "vue-router";

const router = useRouter()
const uploadRef = ref<UploadInstance>()
const ApplyFormRef = ref<FormInstance>()
let fileList = []
const ApplyForm = reactive({
  picFile: [new File([], "", undefined)],
  price: '',
  description: '',
  goodsName: '',
  tag: '',
  number: '',
})
const formData = new FormData();

const rules = reactive({
  goodsName: [
    { required: true, message: '商品名称不能为空', trigger: 'blur' }
  ],
  tag: [
    { required: true, message: '商品类别不能为空', trigger: 'blur' }
  ],
  description: [
    { required: true, message: '商品描述不能为空', trigger: 'blur' },
    { max: 128, message: '商品描述长度应在128个字符以内', trigger: 'blur' },
  ],
  price: [
    { required: true, message: '商品价格不能为空', trigger: 'blur' },
  ],
  number: [
    { required: true, message: '库存数量不能为空', trigger: 'blur' },
  ],
})

const Apply = async (formEl: FormInstance | undefined) => {
  uploadRef.value!.submit()
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      console.log(fileList)
      formData.append('price', ApplyForm.price)
      formData.append('description', ApplyForm.description)
      formData.append('goodsName', ApplyForm.goodsName)
      formData.append('tag', ApplyForm.tag)
      formData.append('number', ApplyForm.number)

      service.post('/goods/apply', formData ,{
        headers: {'Content-Type': 'multipart/form-data'}
      })
          .then(function (res) {
            if (res.status === 200) {
              ElMessage.success('申请成功')
              resetForm(formEl)
              router.push('')
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

function beforeUpload (picFile) {
  console.log(picFile)
  formData.append('picFile', picFile);
  return false;
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
  margin: 0 auto auto;
  position: relative;
}

</style>