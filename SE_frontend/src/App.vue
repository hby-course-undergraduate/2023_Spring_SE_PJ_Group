<template>
<!--  <RouterView></RouterView>-->
  <router-view v-if="isRouterAlive"/>
</template>

<script>
import {nextTick, provide, ref} from "vue";

export default {
  setup() {

    //页面刷新
    const isRouterAlive = ref(true)
    const reload = () => {
      isRouterAlive.value = false
      nextTick(() => {
        isRouterAlive.value =true
      })
    }
    provide('reload', reload)

    //cookie
    const setCookie = (key, value, iDay) => {
      var oDate = new Date();
      oDate.setDate(oDate.getDate() + iDay);
      document.cookie = key + '=' + value + ';expires=' + oDate;
    }
    const getCookie = (key) => {
      var cookieArr = document.cookie.split('; ');
      for(var i = 0; i < cookieArr.length; i++) {
        var arr = cookieArr[i].split('=');
        if(arr[0] === key) {
          return arr[1];
        }
      }
      return false;
    }
    const delCookie = (key) => {
      setCookie(key, '', -1);//这里只需要把Cookie保质期退回一天便可以删除
    }
    provide('setCookie', setCookie)
    provide('getCookie', getCookie)
    provide('delCookie', delCookie)

    //登出
    const logout = () => {
      delCookie('isLogined');
      delCookie('userName');
      delCookie('role');
      delCookie('tokenHeader');
    }
    provide('logout', logout)

    return{
      isRouterAlive, reload
    }
  }
}
</script>



<style scoped>
#app{
  height: 100vh;
  width: 100vh;
  margin: 0;
  padding: 0;
  max-width: 100vw !important;
}
</style>
