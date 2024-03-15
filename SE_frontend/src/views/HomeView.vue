<template>
  <div style="background-color: #EAEAEA">

    <el-container>
      <el-header class="UserHeader">
        <div style="margin-top: 1.5vh; display: inline-block">
          <div style="display: inline-block; position: relative; bottom: 0.5vh; margin-left: 38vw">
            <el-input placeholder="请输入商品名称" style="height: 4vh; width: 18vw"/>
            <el-button :icon="Search" style="height: 4vh; width: 3vw"></el-button>
          </div>
          <div style="display: inline-block; margin-left: 28vw">
            <a href="/customer">
              <el-icon
                  style="margin-top: 5px; margin-right: 0.5vw"
                  color="black"
                  size="17px"
              ><User/></el-icon>
            </a>
            <span style="color: black; font-size: 17px; margin-right: 0.5vw" id="userName">
            <a href="/customer" style="color: #181818; position: relative; bottom: 2px">{{userName ?userName:'未登录'}}</a>
          </span>
            <el-dropdown>
              <el-icon style="margin-left: 1vw; margin-top: 8px"><ArrowDown/></el-icon>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item style="margin-top: 0.5vh" @click="Personal()">个人中心</el-dropdown-item>
                  <el-dropdown-item style="margin-top: 0.5vh" @click="Cart()">购物车</el-dropdown-item>
                  <el-dropdown-item style="margin-top: 0.5vh" @click="LogOut()">退出登录</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </div>
      </el-header>

      <el-main
          style="height: 94vh; padding: 5vh 8vw"
      >
        <el-carousel :interval="2000" type="card" height="450px" width="800px" indicator-position="inside" trigger="click">
          <el-carousel-item v-for="item in 4" :key="item">
          </el-carousel-item>
        </el-carousel>

        <div class="recommend">
          <el-tabs v-model="activeName"  @tab-click="handleClick">
            <el-tab-pane
                label="最新商品"
                name="first"
            >
<!--              <p style="font-size: 24px; color: black">最新商品</p>-->
<!--              <el-divider />-->

              <ul v-infinite-scroll="load" class="infinite-list">
                <li v-for="i in dataObj.tableData" :key="i" class="infinite-list-item">
                  <el-card class="shop-card">

                    <div style="display: inline-block; width: 8vw; height: 10vh; position: relative; bottom: 9vh">
                      <p style="font-size: 18px" @click="Jump(i.goodsId)">{{i.goodsName}}</p>
                      <p style="font-size: 18px">￥ {{i.price}}</p>
                    </div>

                    <div style="display: inline-block">
                      <el-image :src="i.src" fit='fill' style="width: 7vw; height: 7vw" @click="Jump(i.goodsId)"/>
                    </div>

                    <div style="font-size: 14px; width: 16vw; position: relative; bottom: 3vh">
                      <p style="margin-top: 1vh">{{i.description}}</p>
                    </div>

                  </el-card>

                </li>
              </ul>
            </el-tab-pane>
            <el-tab-pane
                label="推荐"
                name="second"
            >
              <ul v-infinite-scroll="load" class="infinite-list">
                <li v-for="i in dataObj.tableData_2" :key="i" class="infinite-list-item">
                  <el-card class="shop-card">

                    <div style="display: inline-block; width: 8vw; height: 10vh; position: relative; bottom: 9vh">
                      <p style="font-size: 18px" @click="Jump(i.goodsId)">{{i.goodsName}}</p>
                      <p style="font-size: 18px">￥ {{i.price}}</p>
                    </div>

                    <div style="display: inline-block">
                      <el-image :src="i.src" fit='fill' style="width: 7vw; height: 7vw" @click="Jump(i.goodsId)"/>
                    </div>

                    <div style="font-size: 14px; width: 16vw; position: relative; bottom: 3vh">
                      <p style="margin-top: 1vh">{{i.description}}</p>
                    </div>

                  </el-card>

                </li>
              </ul>
            </el-tab-pane>
            <el-tab-pane
                label="活动"
                name="third"
            >
              <p style="font-size: 24px; color: black">活动列表</p>
              <el-container style="height: 300px; width: 900px; margin-top: 10px" >
                <el-scrollbar height="300px" width="900px">
                  <el-table :data="dataObj.tableData_0" border height="300px" width="900px">
                    <el-table-column label="活动ID" prop="id" width="100"/>
                    <el-table-column label="活动名称" prop="name" width="100"/>
                    <el-table-column label="开始时间" prop="startTime" width="100"/>
                    <el-table-column label="结束时间" prop="endTime" width="100"/>
                    <el-table-column label="活动商品种类" prop="tags" width="200"/>
                    <el-table-column label="满" prop="amountArrive" width="100"/>
                    <el-table-column label="减" prop="amountMinus" width="100"/>
                  </el-table>
                </el-scrollbar>
              </el-container>
              <el-divider />
              <p style="font-size: 24px; color: black">活动商品</p>
              <ul v-infinite-scroll="load" class="infinite-list">
                <li v-for="i in dataObj.tableData_1" :key="i" class="infinite-list-item">
                  <el-card class="shop-card">

                    <div style="display: inline-block; width: 8vw; height: 10vh; position: relative; bottom: 9vh">
                      <p style="font-size: 18px" @click="Jump(i.goodsId)">{{i.goodsName}}</p>
                      <p style="font-size: 18px">￥ {{i.price}}</p>
                    </div>

                    <div style="display: inline-block">
                      <el-image :src="i.src" fit='fill' style="width: 7vw; height: 7vw" @click="Jump(i.goodsId)"/>
                    </div>

                    <div style="font-size: 14px; width: 16vw; position: relative; bottom: 3vh">
                      <p style="margin-top: 1vh">{{i.description}}</p>
                    </div>

                    <div style="font-size: 14px; width: 16vw; position: relative; bottom: 3vh">
                      <p style="margin-top: 1vh">活动 ID : {{i.activityId}}</p>
                    </div>

                  </el-card>

                </li>
              </ul>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script lang="ts" setup>

import {reactive, ref, inject} from 'vue'
import {useRouter} from "vue-router";
import service from "../request/index.js"
import {ElMessage} from "element-plus";
import {Search} from '@element-plus/icons-vue'

const router = useRouter();
const logout = inject('logout');
const getCookie = inject('getCookie');
const userName = getCookie('userName')
const activeName = "first"


const created = () => {
  service.get('/goods/display',{})
      .then(function (res) {
        if (res.status === 200) {
          let res_list = res.data
          let j_0 = 0
          let j_1 = 0
          for(let i=0; i<res_list.length; i++){
            if(res_list[i].state === 1){
              dataObj.tableData[j_0] = res_list[i]
              if(res_list[i].description.length>40)
              {
                dataObj.tableData[j_0].description =  '商品描述过长，请点击商品名跳转至详情页查看'
              }
              dataObj.tableData[j_0].src = "data:image/png;base64," + dataObj.tableData[j_0].pic[0]
              j_0++
              //筛选参与活动的商品
              if(res_list[i].activityId != null && res_list[i].activityId != 0){
                dataObj.tableData_1[j_1] = res_list[i]
                dataObj.tableData_1[j_1].src = "data:image/png;base64," + dataObj.tableData_1[j_1].pic[0]
                j_1++
              }
            }
          }
        }
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error('查看失败，' + error.response.data.msg)
        }
      })
  service.get('/goods/recommend',{})
      .then(function (res) {
        if (res.status === 200) {
          let res_list = res.data
          let j_0 = 0
          for(let i=0; i<res_list.length; i++){
            if(res_list[i].state === 1){
              dataObj.tableData_2[j_0] = res_list[i]
              if(res_list[i].description.length>40)
              {
                dataObj.tableData_2[j_0].description =  '商品描述过长，请点击商品名跳转至详情页查看'
              }
              dataObj.tableData_2[j_0].src = "data:image/png;base64," + dataObj.tableData_2[j_0].pic[0]
              j_0++
            }
          }
        }
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error('推荐加载失败，' + error.response.data.msg)
        }
      })
  service.get('/activity',{})
      .then(function (res) {
        let res_list = res.data
        let j_0 = 0
        for(let i=0; i<res_list.length; i++){
          if(res_list[i].state === 1 ){
            dataObj.tableData_0[j_0] = res_list[i]
            j_0++
          }
        }
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error('活动数据请求失败，' + error.response.data.msg)
        }
      })
}
const count = ref(0)
const dataObj = reactive({
  tableData: [],
  tableData_0:[],//活动
  tableData_1:[],//活动商品
  tableData_2:[],//推荐商品
})
const load = () => {
  count.value += 1
}
function LogOut(){
  logout();
  router.push('/login');
}
function Cart(){
  router.push('/cart');
}
function Personal(){
  router.push('/customer')
}
function Jump(goodsId){
  let url = "/goods_details?goodsId=" + goodsId
  router.push(url)
}
created();
</script>

<style scoped>

.UserHeader{
  width: 100vw;
  height: 6vh;
  background-color: white;
  border-bottom: 1px solid gray;
}

.el-carousel__item:nth-child(6) {
  box-shadow: gray 0 0 30px;
  background-image: url("/public/4.jpg");
}

.el-carousel__item:nth-child(5) {
  box-shadow: gray 0 0 20px;
  background-image: url("/public/1.png");
}

.el-carousel__item:nth-child(3) {
  box-shadow: gray 0 0 20px;
  background-image: url("/public/3.jpg");
}

.el-carousel__item:nth-child(4) {
  box-shadow: gray 0 0 20px;
  background-image: url("/public/2.jpg");
}

.recommend{
  height: auto;
  margin-top: 5vh;
  padding: 5vh 3vw;
  width: 84vw;
  border-radius: 20px;
  background-color: white;
  place-items: center;
}

.infinite-list {
  padding: 0;
  margin: 0;
  list-style: none;
}

.infinite-list .infinite-list-item {
  height: auto;
  margin: 4vh 3vw;
  display: inline-block;
}

.infinite-list .infinite-list-item + .list-item {
  margin-top: 10px;
}

.shop-card{
  display: inline-block;
  width: 20vw;
  height: 35vh;
  padding: 1vw;
  border-radius: 10px;
  border: 1px solid grey
}

</style>