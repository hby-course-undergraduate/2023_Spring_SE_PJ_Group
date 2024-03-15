<template>
  <div style="background-image: url('/public/background.jpg'); width: 100vw; height: 100vh; overflow: hidden">
  <el-container>

    <el-main
        style="margin-left: 33vw; width: 35vw; margin-top: 8vh; color: white"
    >
      <div class="RegisterPage">
        <el-form
            ref="RegisterFormRef"
            :model="RegisterForm"
            :rules="rules"
            class="demo-ruleForm"
            hide-required-asterisk="hide-required-asterisk"
            status-icon
            style="margin-top: 3vh; color: white"
        >

          <el-form-item label="用户角色" prop="role" style="color: white" class="item">
            <el-radio-group v-model="RegisterForm.role">
              <el-radio label="商家"  class="iitem"/>
              <el-radio label="顾客"  class="iitem"/>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="用户名称" prop="username" style="margin-top: 4vh;" class="item">
            <el-input v-model="RegisterForm.username" />
          </el-form-item>

          <el-form-item label="登陆密码" prop="password" style="margin-top: 4vh" class="item">
            <el-input v-model="RegisterForm.password" type="password"/>
          </el-form-item>

          <el-form-item label="手机号码" prop="phone" style="margin-top: 4vh" class="item">
            <el-input v-model="RegisterForm.phone" />
          </el-form-item>

          <el-form-item label="身份证号" prop="idCard" style="margin-top: 4vh" class="item">
            <el-input v-model="RegisterForm.idCard" />
          </el-form-item>

          <el-form-item label="电子邮箱" prop="email" style="margin-top: 4vh" class="item">
            <el-input v-model="RegisterForm.email" />
          </el-form-item>

          <el-form-item
            style="margin-top: 8vh;display: grid;place-items: center;"
          >
            <div>
              <el-button
                  type="primary"
                  @click="Register(RegisterFormRef)"
                  style="width: 5vw;"
              >
                注册
              </el-button>
              <el-button
                  @click="resetForm(RegisterFormRef)"
                  style="width: 5vw; margin-left: 2vw"
              >
                重置
              </el-button>
            </div>
          </el-form-item>

        </el-form>
      </div>
    </el-main>

    <el-footer
        style="position: relative; top: 5vh; left: 30vw; width: 50vw; color: lightgrey"
    >
      Copyright © 2023 - 2024 Powered by E-SHOPPING All Rights Reserved 在线购物WEB系统 <Copyrights></Copyrights>
    </el-footer>

  </el-container>
  </div>



</template>


<script lang="ts" setup>

import { reactive, ref } from 'vue'
import type { FormInstance } from 'element-plus'
import service from "../request/index.js"
import {ElMessage} from "element-plus";
import {useRouter} from 'vue-router'
const router = useRouter()

const RegisterFormRef = ref<FormInstance>()
const RegisterForm = reactive({
  role: '',
  username: '',
  password: '',
  phone: '',
  idCard: '',
  email: '',
})

const rules = reactive({
  role: [
    { required: true, message: '用户角色不能为空', trigger: 'change' }
  ],
  username: [
    { required: true, message: '用户名称不能为空', trigger: 'blur' },
    { min: 3, max: 10, message: '用户名称长度应为3-10个字符', trigger: 'blur' },
    { pattern: /^[A-Za-z0-9\_]+$/, message: '用户名称仅能包含英文字符、数字与下划线', trigger: 'blur'}
  ],
  password: [
    { required: true, message: '用户密码不能为空', trigger: 'blur' },
    { min: 6, max: 32, message: '用户密码长度应为6-32个字符', trigger: 'blur' },
    { pattern: /((?=.*[a-z])(?=.*\d)|(?=[a-z])(?=.*[-\_])|(?=.*\d)(?=.*[-\_]))[a-z\d-\_]/, message: '用户密码应至少包含字母、数字或特殊字符（-_）中的两种或以上' , trigger: 'blur' },
  ],
  phone: [
    { required: true, message: '手机号码不能为空', trigger: 'blur' },
    { pattern: /^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/, message: '手机号码格式错误', trigger: 'blur'}
  ],
  idCard: [
    { required: true, message: '身份证号不能为空', trigger: 'blur' },
    { pattern: /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/, message: '身份证号格式错误', trigger: 'blur'}
  ],
  email: [
    { required: true, message: '电子邮箱不能为空', trigger: 'blur' },
    { pattern: /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/, message: '电子邮箱格式错误', trigger: 'blur'}
  ],
})

const Register = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      let $isSeller = RegisterForm.role == "商家" ? true : false;  //1为卖家 0为买家
      service.post('/register', {
        username: RegisterForm.username,
        password: RegisterForm.password,
        phone: RegisterForm.phone,
        idCard: RegisterForm.idCard,
        email: RegisterForm.email,
        isSeller: $isSeller,
      })
      .then(function (res) {
            if (res.status === 200) {
              ElMessage({
                message: '注册成功，请登录',
                type: 'success',
              })
              router.push('/login')
            }
          })
          .catch(function (error) {
        if (error.response) {
          ElMessage.error('注册失败，' + error.response.data.msg)
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

<style>

.RegisterPage{
  color: white;
  margin: auto;
  text-align: center;
  height: 75vh;
  background-color: rgb(255,255,255,0.1);
  box-shadow: 0 0 20px 0 rgba(0,0,0,0.5);
  padding: 50px;
}

.item .el-form-item__label{
  color: lightgrey;
}

.iitem .el-radio__label{
  color: lightgrey;
}
</style>