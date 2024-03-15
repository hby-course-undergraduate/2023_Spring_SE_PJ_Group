<template>
  <div style="background-image: url('/public/background.jpg'); width: 100vw; height: 100vh; overflow: hidden">

    <el-container style="place-items: center">

      <el-main
          style="width: 25vw; margin-top: 5vh; overflow: hidden"
      >
        <div style="height: 25vh; position: relative; bottom: 10vh">
          <el-image src='/public/LOGO-white.png' fit="contain"/>
        </div>


        <div class="LoginPage">
          <el-form
              :model="LoginForm"
              style="position: center"
          >

            <el-icon
                size="50px"
                style="position: center"
                color="lightgrey"
            >
              <GoodsFilled />
            </el-icon>

            <el-form-item>
              <el-input
                  v-model="LoginForm.username"
                  placeholder="请输入用户名"
                  prefix-icon="UserFilled"
                  size="large"
                  style="width: 20vw; border-radius: 0; margin-top: 4vh"
              />
            </el-form-item>

            <el-form-item>
              <el-input
                  v-model="LoginForm.password"
                  type="password"
                  placeholder="请输入密码"
                  prefix-icon="Lock"
                  size="large"
                  style="width: 20vw; border-radius: 0; margin-top: 2vh"
              />
            </el-form-item>

            <el-form-item>
              <el-button
                  type="primary"
                  style="width: 20vw;border-radius: 0;margin-top: 7vh"
                  @click="Login"
              >登录
              </el-button>
            </el-form-item>

            <a
                href="/register"
                style="text-decoration: underline;color: lightgrey;margin-top: 5vh"
            >
              没有账户，点击注册
            </a>

          </el-form>
        </div>
      </el-main>

      <el-footer
          style="width: auto; color: lightgrey; place-items: center; margin-top: 7vh"
      >
        Copyright © 2023 - 2024 Powered by E-SHOPPING All Rights Reserved 在线购物WEB系统 Copyrights
      </el-footer>

    </el-container>

  </div>




</template>


<script lang="ts" setup>

import { reactive, inject } from "vue";
import { ElMessage } from 'element-plus'
import service from "../request/index.js"
import {useRouter} from 'vue-router'
const router = useRouter()
const setCookie = inject('setCookie')
const getCookie = inject('getCookie')

const LoginForm = reactive({
  role: '',
  username: '',
  password: ''
})

const Login = () => {
  if(getCookie('isLogined')==='true')
    ElMessage.error('请勿重复登录')
  else{
    service.post('/login', {
      username: LoginForm.username,
      password: LoginForm.password
    })
        .then(function (res) {
          if (res.status === 200) { //登录成功
            ElMessage({
              message: '登录成功  ' + LoginForm.username + '，欢迎回来',
              type: 'success',
            })
            if(res.data.isAdmin){
              setCookie('isLogined','true',1)
              setCookie('userName', LoginForm.username, 1)
              setCookie('role', 'admin',1)
              setCookie('tokenHeader', res.data.token,1)
              router.push('/root')
            }
            else if(res.data.isSeller){
              setCookie('isLogined','true',1)
              setCookie('userName', LoginForm.username, 1)
              setCookie('role', 'seller',1)
              setCookie('tokenHeader', res.data.token,1)
              router.push('/seller')
            }
            else{
              setCookie('isLogined','true',1)
              setCookie('userName', LoginForm.username, 1)
              setCookie('role', 'customer',1)
              setCookie('tokenHeader', res.data.token,1)
              router.push('/home')
            }
          }
        })
        .catch(function (error) {
          if (error.response) {
            ElMessage.error('登录失败，' + error.response.data.msg)
          }
          else if (error.request) {
            console.log(error.request);
          } else {
            console.log('Error', error.message);
          }
        })
  }
}

</script>


<style scoped>

.LoginPage{
  text-align: center;
  height: 50vh;
  background-color: rgb(255,255,255,0.1);
  box-shadow: 0 0 20px 0 rgba(0,0,0,0.5);
  padding: 30px;
}





</style>