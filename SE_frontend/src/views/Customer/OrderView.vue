<template>
<div style="background-color: white; width: 100vw">
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
          >
            <User/>
          </el-icon>
        </a>
        <span style="color: black; font-size: 17px; margin-right: 0.5vw" id="userName">
            <a href="/login" style="color: #181818; position: relative; bottom: 2px">{{ userName }}</a>
          </span>
        <el-dropdown>
          <el-icon style="margin-left: 1vw; margin-top: 8px">
            <ArrowDown/>
          </el-icon>
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

  <div style="width: 100vw">
    <el-card style="width: 80vw; margin-left: 10vw; margin-top: 5vh; padding: 2vw">
      <div>
        <p style="display: inline-block; font-size: 20px">订单详情</p>
        <el-button style="float: right" type="primary" @click="payOrder()">确认支付</el-button>
        <p style="float: right; display: inline-block; margin-right: 5vw; font-size: 20px">总计金额：<span style="text-decoration: line-through">{{dataObj.neededData.totalPrice + amountMinus*times}}</span>&nbsp;&nbsp;&nbsp;{{dataObj.neededData.totalPrice}} 元</p>
      </div>
      <el-divider></el-divider>

      <div style="width: 67vw; margin-left: 3vw; margin-top: 5vh">
        <p style="font-size: 18px">商品信息</p>
        <el-divider></el-divider>
        <el-table :data="dataObj.neededData.order" style="width: 100%">
          <el-table-column prop="shopName" label="店铺名称" width="200" />
          <el-table-column prop="goodsName" label="商品名称" width="200" />
          <el-table-column prop="unitPrice" label="商品单价（元）" width="200" />
          <el-table-column prop="number" label="购买总数（件）" width="200" />
          <el-table-column prop="totalPrice" label="商品总价（元）" width="200" />
        </el-table>
      </div>

      <div style="width: 67vw; margin-left: 3vw; margin-top: 5vh">
        <p style="font-size: 18px">收件地址</p>
        <el-divider></el-divider>
        <el-descriptions :column="1">
          <el-descriptions-item label="收件人">{{ dataObj.neededData.consignee }}</el-descriptions-item>
          <el-descriptions-item label="手机号">{{ dataObj.neededData.consigneePhone }}</el-descriptions-item>
          <el-descriptions-item label="详细地址">{{ dataObj.neededData.address }}</el-descriptions-item>
        </el-descriptions>
      </div>

      <div style="width: 67vw; margin-left: 3vw; margin-top: 5vh">
        <p style="font-size: 18px">优惠详情</p>
        <el-divider></el-divider>
        {{dataObj.neededData.discounts}}
      </div>

    </el-card>

  </div>


</div>
</template>

<script lang="ts" setup>
import {inject, reactive} from 'vue'
import {useRouter} from 'vue-router'
import service from "../../request/index.js"
import {ElMessage} from "element-plus";
import {Search} from '@element-plus/icons-vue'

const logout = inject('logout');
const getCookie = inject('getCookie')
const userName = getCookie('userName')
const router = useRouter();
const dataObj = reactive({
  tableData: [],
  addressData: [],
  unpaidData: [],
  neededData: []
})
const groupId = getGroupId()
function getGroupId(){
  let temp = window.location.search
  return temp.substring(9, temp.length)
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
let amountArrive = 0
let amountMinus = 0
let times = 0
const created = () => {
  service.get('/user/profile', {})
      .then(function (res) {
        if (res.status === 200) {
          dataObj.tableData[0] = res.data
        }
      })
  service.get('/user/account', {})
      .then(function (res) {
        if (res.status === 200) {
          dataObj.tableData[0].amount = res.data.amount
        }
      })
  service.get('/delivery-address', {})
      .then(function (res) {
        if (res.status === 200) {
          dataObj.addressData = res.data
        }
      })
  //获取待支付订单
  service.get('/order/unpaid', {})
      .then(function (res) {
        if (res.status === 200) {
          dataObj.unpaidData = res.data
          for(let i=0; i<dataObj.unpaidData.length; i++){
            let totalPrice = 0
            for(let j=0; j<dataObj.unpaidData[i].order.length; j++){
              totalPrice += dataObj.unpaidData[i].order[j].totalPrice
            }
            dataObj.unpaidData[i].buyTime = dataObj.unpaidData[0].order[0].buyTime
            dataObj.unpaidData[i].address = dataObj.unpaidData[0].order[0].address
            dataObj.unpaidData[i].consignee = dataObj.unpaidData[0].order[0].consignee
            dataObj.unpaidData[i].consigneePhone = dataObj.unpaidData[0].order[0].consigneePhone
            dataObj.unpaidData[i].orderId = dataObj.unpaidData[i].groupId
            dataObj.unpaidData[i].totalPrice = totalPrice
          }
        }
        for(let i=0; i<dataObj.unpaidData.length; i++){
          if(dataObj.unpaidData[i].groupId.toString() === groupId.toString()){
            dataObj.neededData = dataObj.unpaidData[i]
            if(dataObj.neededData.discounts === 'null'){
              dataObj.neededData.discounts = "未参加任何活动"
            }
            else{
              amountArrive = dataObj.neededData.discounts[0].amountArrive
              amountMinus = dataObj.neededData.discounts[0].amountMinus
              times = dataObj.neededData.discounts[0].times
              dataObj.neededData.discounts = "满" + amountArrive*times + "减" + amountMinus*times
            }
          }
        }
      })


}
function payOrder(){
  service.post('/goods/pay', {
    group: groupId
  })
      .then(function (res) {
        if (res.status === 200) {
          ElMessage.success('支付成功！')
          router.push('/')
        }
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error('支付失败，' + error.response.data.msg)
          if(error.response.data.msg === "账户余额不足"){
            router.push('/customer')
          }
        }
      })
}
created()
</script>

<style scoped>

</style>