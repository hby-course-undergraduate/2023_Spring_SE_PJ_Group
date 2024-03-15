<template>
  <div style="background-color: black; width: 100vw">

    <el-header class="UserHeader">
      <div style="margin-top: 1.5vh; display: inline-block">
        <div style="display: inline-block">
          <a href="/" style="display: inline-block">
            <el-image src="/public/LOGO-black-small.png" style="width: 5vw; display: inline-block"></el-image>
          </a>
        </div>
        <div style="display: inline-block; position: relative; bottom: 1vh; margin-left: 33vw">
          <el-input placeholder="请输入商品名称" style="height: 4vh; width: 18vw"/>
          <el-button :icon="Search" style="height: 4vh; width: 3vw"></el-button>
        </div>
        <div style="display: inline-block; margin-left: 28vw; position: relative; bottom: 1vh;">
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

    <div class="CartPage">
      <div>
        <h1
        style="color: black;display: inline-block; margin-left: 3vw; margin-top: 2vh"
        >购物车</h1>

        <el-button
            style="margin-left: 60vw; width: 5vw; position: relative; bottom: 5px"
            size="large"
            @click="buyFormVisible = true"
        >结算</el-button>
      </div>

      <el-divider></el-divider>

      <div>
          <ul v-infinite-scroll="load" class="infinite-list" style="overflow: auto">
            <li v-for="i in dataObj.tableData" :key="i" class="infinite-list-item">
              <el-checkbox v-model="i.isBuying" style="margin-left: 3vw" :label="i.goodsId"><br></el-checkbox>
              <el-container>
                <el-aside width="8vw">
                  <el-image :src="i.src" fit='fill' style="width: 5vw; height: 5vw; margin-left: 3vw; display: inline-block"/>
                </el-aside>
                <el-main>
                  <div style="display: inline-block; margin-left: 3vw">
                      <div style="font-size: 18px; position: relative; bottom: 2vh">
                        <p style="width: 13vw; display: inline-block">{{i.goodsName}}</p>
                        <p style="width: 13vw; display: inline-block">￥ {{i.price}}</p>
                        <div style="width: 20vw; display: inline-block "><el-input-number v-model="i.number" :min="0" style="display: inline-block" @change="ifZero(i.id, i.number)"/></div>
                        <el-button style="display: inline-block" @click="DeleteCart(i.id)">删除</el-button>
                      </div>
                    <div style="color: #606266">
                      <p style="display: inline-block">{{i.description}}</p>
                      <p v-if="i.state!==1" style="display: inline-block; float: right; position: relative; left: 3vw; color: red">该商品已下架，请及时删除</p>
                    </div>
                  </div>
                  <div style="display: inline-block; margin-left: 5vw; position: relative; bottom: 3vh">

                  </div>
                </el-main>
              </el-container>
            </li>
          </ul>
      </div>

      <el-dialog v-model="buyFormVisible" title="立即购买" style="width: 30vw">
        <div style="margin-top: 5vh">
          收货地址：
          <div v-for="i in dataObj.addressData">
            <el-checkbox style="display: inline-block" v-model="i.isChosen"><br></el-checkbox>
            <el-card style="margin-top: 2vh; height: 20vh; width: 20vw; margin-left: 3vw; display: inline-block">
              <div style="display: inline-block">
                <div><p style="width: 5vw; display: inline-block; font-size: 17px">收件人：</p>{{i.consignee}}</div>
                <div><p style="width: 5vw; display: inline-block; font-size: 17px">收件电话：</p>{{i.consigneePhone}}</div>
                <div><p style="width: 5vw; display: inline-block; font-size: 17px">收件地址：</p>{{i.address}}</div>
              </div>
            </el-card>
          </div>
        </div>
        <template #footer>
              <span class="dialog-footer">
                <el-button @click="buyFormVisible = false">取消</el-button>
                <el-button type="primary" @click="createOrder()">
                  立即下单
                </el-button>
              </span>
        </template>
      </el-dialog>

    </div>
  </div>
</template>

<script setup>
import {reactive, ref, inject} from 'vue'
import service from "../../request";
import {ElMessage} from "element-plus";
import {useRouter} from 'vue-router'
import {Search} from '@element-plus/icons-vue'

let buyFormVisible = ref(false)
const router = useRouter()
const logout = inject('logout');
const getCookie = inject('getCookie');
const userName = getCookie('userName')
const count = ref(0)
const reload = inject('reload')
const load = () => {
  count.value += 2
}
const dataObj = reactive({
  tableData: [],
  addressData: []
})

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

const created = () => {
  service.get('/cart',{})
      .then(function (res) {
        let res_list = res.data
        let j_0 = 0
        for(let i=0; i<res_list.length; i++){
          if(res_list[i].valid === true){
            dataObj.tableData[j_0] = res_list[i]
            dataObj.tableData[j_0].src = "data:image/png;base64," + dataObj.tableData[j_0].pic[0]
            dataObj.tableData[j_0].isBuying = false
            j_0++
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

function DeleteCart(cartId){
  deleteGoods(cartId)
  reload()
}

function deleteGoods(cartId){
  service.post('/cart/cancel', {
    cartId: cartId
  })
      .then(function (res) {
        if (res.status === 200) {
          ElMessage.success('已删除')
        }
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error('删除失败，' + error.response.data.msg)
        }
      })
  let index = 0;
  for(index; index<dataObj.tableData.length; index++){
    if(dataObj.tableData[index].id === cartId){
      break;
    }
  }
  dataObj.tableData.splice(index,1)
}

created()

function ifZero(cartId, number){
  if(number === 0){
    DeleteCart(cartId)
  }
}

function createOrder(){
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
  let goodsList = [];
  let deleteList = [];
  for(let i=0,j=0; i<dataObj.tableData.length; i++){
    if(dataObj.tableData[i].isBuying === true){ //要买这个商品
      if(dataObj.tableData[i].state !== 1){ //该商品已下架
        ElMessage.error("下单失败，存在已下架商品")
        return
      }
      else{
        goodsList[j] = {number: '', goodsId: ''}
        deleteList[j] = {id: ''}
        goodsList[j].number = dataObj.tableData[i].number
        goodsList[j].goodsId = dataObj.tableData[i].goodsId
        deleteList[j].id = dataObj.tableData[i].id
        j++
      }
    }
  }
  if(goodsList.length === 0){
    ElMessage.error("请选择要下单的商品")
    return
  }
  service.post('/goods/purchase', {
    array: goodsList,
    addressId: addressId
  })
      .then(function (res) {
        if (res.status === 200) {
          ElMessage.success('下单成功，跳转至下单页面')
          //删除购物车内商品
          for(let i=0; i<deleteList.length; i++){
            DeleteCart(deleteList[i].id)
          }
          let t_url = "/create_order?groupId=" + res.data.group
          router.push(t_url)
        }
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error('下单失败，' + error.response.data.msg)
        }
      })

}

</script>

<style scoped>

.CartPage{
  margin-left: 10vw;
  margin-top: 10vh;
  width: 80vw;
  height: 80vh;
  border-radius: 15px;
  background-color: rgba(255,255,255,0.7);

}
.infinite-list {
  height: 60vh;
  padding-left: 3vw;
  padding-right: 3vw;
  margin: 0;
  list-style: none;
}
.infinite-list .infinite-list-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 20vh;
  background-color: white;
  margin-top: 5vh;
  color: black
}
.infinite-list .infinite-list-item + .list-item {
  margin-top: 10px;
}

</style>