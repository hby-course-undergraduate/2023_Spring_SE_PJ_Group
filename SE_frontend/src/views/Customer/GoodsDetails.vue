<template>
<div style="background-color: lightgrey">

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
            <a href="/customer" style="color: #181818; position: relative; bottom: 2px">{{userName}}</a>
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
  </el-container>

  <el-card style="width: 70vw; padding: 3vw; margin-top: 6vh; margin-left: 15vw; margin-bottom: 6vh; border-radius: 20px">
    <el-container>
      <el-aside style="height: 20vw; width: 20vw">
        <el-carousel :interval="5000" style="height: 20vw; width: 20vw" >
          <el-carousel-item v-for="i in dataObj.urlList" :key="i" style="width: 20vw; height: 20vw;">
            <el-image :src="i" fit='fill' style="width: 20vw; height: 20vw;"/>
          </el-carousel-item>
        </el-carousel>
      </el-aside>

      <el-main style="margin-left: 5vw; padding: 0">
        <div style="width: 100%; font-size: 28px; display: inline-block">
          <p style="display: inline-block">{{dataObj.tableData.goodsName}}</p>
          <p style="display: inline-block; float: right">￥ {{dataObj.tableData.price}}</p>
        </div>
        <el-divider/>
        <el-descriptions column=1 class="description">
          <el-descriptions-item label="当前库存：">{{dataObj.tableData.number}} 件</el-descriptions-item>\
          <el-descriptions-item label="商品类别："><el-tag>{{dataObj.tableData.tag}}</el-tag></el-descriptions-item>
          <el-descriptions-item label="商品描述：">{{dataObj.tableData.description}}</el-descriptions-item>
        </el-descriptions>
        <el-descriptions column=1 class="description">
          <el-descriptions-item label="活动ID：">{{dataObj.tableData.activityId}}</el-descriptions-item>\
          <el-descriptions-item label="活动规则：">{{dataObj.tableData.activityRule}}</el-descriptions-item>\
        </el-descriptions>
        <div style="width: 100%; text-align: right; margin-top: 15vh">
          <el-button :icon="ShoppingCart" size="large" style="width: 7vw" @click="cartFormVisible = true" >加至购物车</el-button>
          <el-button type="primary" :icon="ShoppingBag" @click="buyFormVisible = true" size="large" style="width: 7vw">立即购买</el-button>
        </div>
      </el-main>
    </el-container>
  </el-card>

  <el-dialog v-model="buyFormVisible" title="立即购买" style="width: 30vw">
    <div>
      所需数量：
      <el-input-number
          style="margin-top: 3vh;"
          v-model="number" :min="1"/>
      （单位：件）
    </div>
    <div style="margin-top: 5vh">
        收货地址：
        <div >
          <el-checkbox v-for="i in dataObj.addressData" style="display: inline-block;" v-model="i.isChosen">
            <el-card style="margin-top: 2vh; width: 20vw; margin-left: 1vw;display: inline-block">
              <div style="display: inline-block">
                <div><p style="width: 8vw; display: inline-block; font-size: 17px">收件人：</p>{{i.consignee}}</div>
                <div style="margin-top: 10px; margin-bottom: 10px"><p style="width: 8vw; display: inline-block; font-size: 17px">收件电话：</p>{{i.consigneePhone}}</div>
                <div><p style="width: 8vw; display: inline-block; font-size: 17px">收件地址：</p>{{i.address}}</div>
              </div>
            </el-card>
          </el-checkbox>
        </div>
    </div>
    <template #footer>
              <span class="dialog-footer">
                <el-button @click="addAddFormVisible = true">新增地址</el-button>
                <el-button @click="buyFormVisible = false">取消</el-button>
                <el-button type="primary" @click="BuyNow(number)">
                  立即下单
                </el-button>
              </span>
    </template>
  </el-dialog>

  <el-dialog v-model="cartFormVisible" title="添加至购物车" style="width: 30vw; height: 35vh">
    所需数量：
    <el-input-number
        style="margin-top: 3vh;"
        v-model="number" :min="1"/>
    （单位：件）
    <template #footer>
              <span class="dialog-footer">
                <el-button @click="cartFormVisible = false">取消</el-button>
                <el-button type="primary" @click="AddToCart(number)">
                  添加至购物车
                </el-button>
              </span>
    </template>
  </el-dialog>
  <el-dialog v-model="addAddFormVisible" title="新增收件地址" style="width: 40vw">
    <el-form :model="addAddForm" :rules="rules" ref="addAddFormRef" style="padding: 1vw">
      <el-form-item label="收件人" prop="consignee" style="width: 30vw">
        <el-input v-model="addAddForm.consignee"  placeholder="请输入收件人"/>
      </el-form-item>
      <el-form-item label="联系电话" prop="consigneePhone" style="width: 30vw">
        <el-input v-model="addAddForm.consigneePhone" placeholder="请输入联系电话"/>
      </el-form-item>
      <el-form-item label="收件地址" prop="address" style="width: 30vw">
        <el-input v-model="addAddForm.address" placeholder="请输入收件地址"/>
      </el-form-item>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addAddFormVisible = false" style="float: right">取 消</el-button>
        <el-button type="primary" @click="addAdd(addAddFormRef)" style="float: right; margin-right: 1vw">确 定</el-button>
      </span>
    </el-form>
  </el-dialog>

</div>
</template>


<script lang="ts" setup>

import {inject, reactive, ref} from 'vue'
import service from "../../request/index.js"
import {ElMessage} from "element-plus";
import {useRouter} from "vue-router";
import {ShoppingCart, ShoppingBag, Search} from "@element-plus/icons-vue";

const router = useRouter()
const getCookie = inject('getCookie');
const url = '/goods/' + getGoodsId()
const dataObj = reactive({
  tableData: [],
  addressData: [],
  urlList: []
})
let cartFormVisible = ref(false)
let buyFormVisible = ref(false)
let number = 1

function getGoodsId(){
  let temp = window.location.search
  return temp.substring(9, temp.length)
}

const created = () => {
  service.get(url,{})
      .then(function (res) {
        if (res.status === 200) {
          dataObj.tableData = res.data
          for(let i in dataObj.tableData.pic){
            dataObj.urlList[i] = "data:image/png;base64," + dataObj.tableData.pic[i];
          }
        }
      })
  service.get('/delivery-address', {})
      .then(function (res) {
        if (res.status === 200) {
          dataObj.addressData = res.data
          for(let i=0; i<dataObj.addressData.length; i++){
            dataObj.addressData[i].isChosen = false
          }
        }
      })

}
created();

//新增收货地址
const addAddFormVisible = ref(false)
const addAddFormRef = ref<FormInstance>()
const addAddForm = reactive({
  deliveryAddressId: '',
  address: '',
  consignee: '',
  consigneePhone: ''
})
const addAdd = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      service.post('/delivery-address/apply', {
        deliveryAddressId: addAddForm.deliveryAddressId,
        address: addAddForm.address,
        consignee: addAddForm.consignee,
        consigneePhone: addAddForm.consigneePhone
      })
          .then(function (res) {
            if (res.status === 200) {
              ElMessage.success("新增收货地址成功！")
              addAddFormVisible.value = false
              getAddresses()
            }
          })
          .catch(function (error) {
            if (error.response) {
              ElMessage.error("新增收货地址失败，" + error.response.data.msg)
            }
          })
    }
    else {
      return
    }
  })
}

const getAddresses = () => {
  service.get('/delivery-address')  
    .then(function(res) {
      if (res.status === 200) {
        dataObj.addressData = res.data  
      }
    })
    .catch(function(error) {
      if (error.response) {
        ElMessage.error("获取收货地址失败，" + error.response.data.msg)
      }
    })
}

function AddToCart(number){
  if(getCookie('isLogined') !== "true"){
    ElMessage.error("请先登录！")
    router.push('/login')
  }
  else{
    service.post('/cart/add', {
      goodsId: getGoodsId(),
      number: number
    })
        .then(function (res) {
          if (res.status === 200) {
            ElMessage.success('添加成功')
            cartFormVisible.value = false
          }
        })
        .catch(function (error) {
          if (error.response) {
            ElMessage.error('添加失败，' + error.response.data.msg)
          }
        })
  }
}

function BuyNow(number){
  if(getCookie('isLogined') !== "true"){
    ElMessage.error("请先登录！")
    router.push('/login')
  }
  else{
    let addressId = 0
    let count = 0
    for(let i=0; i<dataObj.addressData.length; i++){
      if(dataObj.addressData[i].isChosen === true){
        addressId = dataObj.addressData[i].id
        count++
      }
    }
    if(count !== 1){
      ElMessage.error("只能选择一个收件地址")
      for(let i=0; i<dataObj.addressData.length; i++){
        dataObj.addressData[i].isChosen = false
      }
      return
    }
    if(addressId === 0){
      ElMessage.error("请选择收件地址")
      return
    }
    let array = []
    array[0] = {number: '', goodsId: ''}
    array[0].number = number
    array[0].goodsId = getGoodsId()
    service.post('/goods/purchase', {
      array: array,
      addressId: addressId
    })
        .then(function (res) {
          if (res.status === 200) {
            ElMessage.success('下单成功，跳转至下单页面')
            cartFormVisible.value = false
            let t_url = "/create_order?groupId=" + res.data.group
            router.push(t_url)
          }
        })
        .catch(function (error) {
          if (error.response) {
            ElMessage.error('下单失败，' + error.response.data.msg)
            cartFormVisible.value = false
          }
        })
  }
}

const logout = inject('logout');
const userName = getCookie('userName')
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

</script>

<style>

.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
  text-align: center;
}

.description .el-descriptions__label{
  font-size: 18px;
}

.description{
  margin-top: 5vh;
}

.UserHeader{
  width: 100vw;
  height: 6vh;
  background-color: white;
  border-bottom: 1px solid gray;
}

</style>