<template>
  <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick" style="overflow: hidden">

    <el-tab-pane label="商店账户" name="first">
      <div style="width: 100%; height: 85vh">
        <el-card style="width: 75vw; height: 80vh; position: relative;" >
          <p style="color: black; font-size: 20px" id="shop_amount">账户余额： 元</p>
          <el-button style="display:inline-block ;float: left; position: relative ;left: 500px; bottom: 3vh"
                     @click="profit_week_record = true;profit_month_record=false;countProfit()">近一周记录
          </el-button>
          <el-button style="display:inline-block ;float: left; position: relative;left: 250px; bottom: 3vh"
                     @click="profit_month_record = true;profit_week_record=false;countProfit()">近一个月记录
          </el-button>
          <el-button @click="chargeFormVisible = true" style="display:inline-block ;float: right; position: relative; bottom: 3vh">充值</el-button>
          <el-divider/>
          <el-container style="height: 55vh">
            <el-scrollbar height="55vh">
              <el-table
                  :data="profit_week_record? dataObj.tableData_1:profit_month_record?dataObj.tableData_2:dataObj.tableData_0"
                  border height="55vh">
                <el-table-column label="转入方" prop="fromName" width="300"/>
                <el-table-column label="转出方" prop="toName" width="300"/>
                <el-table-column label="类型" prop="type" width="100"/>
                <el-table-column label="数额" prop="amount" width="100"/>
                <el-table-column label="时间" prop="createAt" width="300"/>
              </el-table>
            </el-scrollbar>
          </el-container>
          <p id="profitAll" style="color: black; margin-top: 20px; font-size: 20px">总计：元</p>
          <el-dialog v-model="chargeFormVisible" title="账户充值" style="width: 30vw;">
            <el-form :model="chargeForm" class="ChargeDialog" ref="chargeFormRef">
              <el-form-item label="充值金额">
                <el-input-number v-model="chargeForm.amount" :min="0.01"/><p>（单位：元）</p>
              </el-form-item>
            </el-form>
            <span class="dialog-footer">
          <el-button @click="charge()" style="float: right; position: relative; bottom: 2vh">确认充值</el-button>
        </span>
          </el-dialog>
        </el-card>
      </div>
    </el-tab-pane>

    <el-tab-pane label="个人账户" name="second" default-openeds>
      <div style="width: 100%; height: 60vh">
        <el-card style="width: 30vw; height: 50vh; position: relative; left: 25vw; top: 10vh" >
          <p style="color: black; font-size: 20px" id="seller_amount">账户余额： 元</p>
          <el-button @click="chargeFormVisible = true" style="display:inline-block ;float: right; position: relative; bottom: 3vh">充值</el-button>
          <el-divider/>
          <el-dialog v-model="chargeFormVisible" title="账户充值" style="width: 30vw;">
            <el-form :model="chargeForm" class="ChargeDialog" ref="chargeFormRef">
              <el-form-item label="充值金额">
                <el-input-number v-model="chargeForm.amount" :min="0.01"/><p>（单位：元）</p>
              </el-form-item>
            </el-form>
            <span class="dialog-footer">
          <el-button @click="sellerCharge()" style="float: right; position: relative; bottom: 2vh">确认充值</el-button>
        </span>
          </el-dialog>
        </el-card>
      </div>
    </el-tab-pane>


  </el-tabs>

</template>

<script lang="ts" setup>
import service from "../../request/index.js"
import {reactive, inject, ref} from 'vue';
import {ElMessage} from "element-plus";
const reload = inject('reload')
const activeName = "first"

//充值
const chargeFormVisible = ref(false)
const chargeForm = reactive({
  amount: ''
})

//利润账户
const profit_week_record = ref(false)
const profit_month_record = ref(false)

//流水数据
const dataObj = reactive({
  tableData_0: [],  //利润流水
  tableData_1: [],  //近7天利润流水
  tableData_2: [],  //近一个月利润流水
})

function charge(){
  service.post('/shop/account/recharge', {
    amount: chargeForm.amount
  })
      .then(function (res) {
        if (res.status === 200) {
          ElMessage.success("充值成功！")
          reload()
        }
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error("充值失败，" + error.response.data.msg)
        }
      })
}
function sellerCharge(){
  service.post('/user/account/recharge', {
    amount: chargeForm.amount
  })
      .then(function (res) {
        if (res.status === 200) {
          ElMessage.success("充值成功！")
          reload()
        }
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error("充值失败，" + error.response.data.msg)
        }
      })
}

function countProfit(){
  if(profit_week_record.value){
    let profit=0;
    for(let i=0; i<dataObj.tableData_1.length;i++)
    {
      if(dataObj.tableData_1[i].toType==='shop'){
        profit += dataObj.tableData_1[i].amount
      }
      if(dataObj.tableData_1[i].fromType==='shop'){
        profit -= dataObj.tableData_1[i].amount
      }
    }
    document.getElementById("profitAll").innerText = "近一周总计：" + profit + " 元";
  }
  else if(profit_month_record.value){
    let profit=0;
    for(let i=0; i<dataObj.tableData_2.length;i++)
    {
      if(dataObj.tableData_2[i].toType==='shop'){
        profit += dataObj.tableData_2[i].amount
      }
      if(dataObj.tableData_2[i].fromType==='shop'){
        profit -= dataObj.tableData_2[i].amount
      }
    }
    document.getElementById("profitAll").innerText = "近一月总计：" + profit + " 元";
  }
  else{
    let profit=0;
    for(let i=0; i<dataObj.tableData_0.length;i++)
    {
      if(dataObj.tableData_0[i].toType==='shop'){
        profit += dataObj.tableData_0[i].amount
      }
      if(dataObj.tableData_0[i].fromType==='shop'){
        profit -= dataObj.tableData_0[i].amount
      }
    }
    document.getElementById("profitAll").innerText = "总计：" + profit + " 元";
  }
}

const created = () => {
  service.get('/shop/account',{})
      .then(function (res) {
        console.log(res.data.amount)
        document.getElementById("shop_amount").innerText = "账户余额：" + res.data.amount + " 元"
        document.getElementById("seller_amount").innerText = "账户余额：" + res.data.amount + " 元"
        document.getElementById("profitAll").innerText = "账户余额：" + res.data.amount + " 元"
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error('请求失败，' + error.response.data.msg)
        }
      })
  service.get('/user/account',{})
      .then(function (res) {
        console.log(res.data.amount)
        document.getElementById("seller_amount").innerText = "账户余额：" + res.data.amount + " 元"
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error('请求失败，' + error.response.data.msg)
        }
      })
  service.post('/account-record/shop', {limit: 9999})
      .then(function (res) {
        let res_list = res.data
        let j_0 = 0
        for (let i = 0; i < res_list.length; i++) {
          //利润流水
          dataObj.tableData_0[j_0] = res_list[i]
          j_0++
        }
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error('流水数据请求失败，' + error.response.data.msg)
        }
      })
  service.post('/account-record/shop', {limit: 7})
      .then(function (res) {
        let res_list = res.data
        let j_0 = 0
        for (let i = 0; i < res_list.length; i++) {
          //利润流水
          dataObj.tableData_1[j_0] = res_list[i]
          j_0++
        }
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error('流水数据请求失败，' + error.response.data.msg)
        }
      })
  service.post('/account-record/shop', {limit: 30})
      .then(function (res) {
        let res_list = res.data
        let j_0 = 0
        for (let i = 0; i < res_list.length; i++) {
          //利润流水
          dataObj.tableData_2[j_0] = res_list[i]
          j_0++
        }
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error('流水数据请求失败，' + error.response.data.msg)
        }
      })
}


created();

</script>

