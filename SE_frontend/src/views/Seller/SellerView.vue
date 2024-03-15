<template>
  <el-container class="layout-container-demo" style="width: 100vw">
    <el-aside width="250px">
      <el-scrollbar>
        <el-menu
            text-color="white"
            active-text-color="lightblue"
            :router="true"
            style="background-color: #2c3e50"
        >
          <div style="height: 9vh; width: 10vw; place-items: center; margin-left: 1vw; margin-top: 3vh">
            <el-image src='/public/LOGO-white-small.png' fit="scale-down"/>
          </div>

          <el-sub-menu index="1">
            <template #title>
              <el-icon><discount /></el-icon>店铺管理
            </template>
            <el-menu-item index="/apply">申请开店</el-menu-item>
            <el-menu-item index="/close">申请关店</el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="2">
            <template #title>
              <el-icon><box /></el-icon>商品管理
            </template>
            <el-menu-item index="/goods">商品列表</el-menu-item>
            <el-menu-item index="/release_goods">发布商品</el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="3">
            <template #title>
              <el-icon><checked /></el-icon>订单管理
            </template>
            <el-menu-item index="/orders">订单列表</el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="4">
            <template #title>
              <el-icon><histogram /></el-icon>账户管理
            </template>
            <el-menu-item index="/seller_account">商家账户</el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="5">
            <template #title>
              <el-icon><star-filled /></el-icon>活动管理
            </template>
            <el-menu-item index="/seller_activity">活动列表</el-menu-item>
          </el-sub-menu>

        </el-menu>
      </el-scrollbar>
    </el-aside>

    <el-container>

      <el-header style="text-align: right; font-size: 12px; background-color: white">
        <div class="toolbar">

          <el-icon
              style="margin-top: 3px; margin-right: 0.5vw"
              color="black"
              size="17px"
          ><User/></el-icon>

          <span style="color: black; font-size: 17px; margin-right: 0.5vw">{{userName ?userName:'未登录'}}</span>

          <el-dropdown>
            <el-icon style="margin-left: 8px;margin-right: 8px; margin-top: 2px"><ArrowDown/></el-icon>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="LogOut()">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>

        </div>
      </el-header>

      <el-main style="background-color: white">
        <div
            style="margin: 8vh 3vw; background-color: white"
        >
          <router-view/>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script lang="ts" setup>
import {inject} from "vue";
import {useRouter} from 'vue-router'
import {ElMessage} from "element-plus";
import {StarFilled} from "@element-plus/icons-vue";

const router = useRouter();
const logout = inject('logout');
const getCookie = inject('getCookie');
const userName = getCookie('userName');

//商家权限校验
const CheckRole = () => {
  if(getCookie('role') === 'seller'){
    console.log("欢迎回来")
  }
  else{
    ElMessage.error('无权限访问')
    router.push('/login')
  }
}
CheckRole();
function LogOut() {
  logout();
  router.push('/login')
}
</script>

<style scoped>
.layout-container-demo .el-header {
  position: relative;
  border-bottom: 1px solid gray;
}

.layout-container-demo .el-sub-menu {
  background-color: #2c3e50;
}

.layout-container-demo .el-aside {
  height: 100vh;
  color: #2c3e50;
  background: #2c3e50;
}

.layout-container-demo .el-menu {
  border-right: none;
}

.layout-container-demo .el-main {
  padding: 0;
}

.layout-container-demo .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
}

.el-menu-item{
  background-color: #2c3e50;
  color: white;
}

.el-sub-menu:hover{
  background-color: #3e5772;
}

.el-sub-menu__title:hover{
  color: white;
}

.el-menu-item:hover{
  background-color: #3e5772;
  color: white;
}

.el-menu--collapse .el-submenu__title.is-active{
  color: white;
}

.el-submenu__title.is-active{
  color: white;
}
</style> 
