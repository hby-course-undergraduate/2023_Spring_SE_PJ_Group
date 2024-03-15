<template>

  <el-tabs v-model="activeName" class="demo-tabs" style="overflow: hidden" @tab-click="handleClick">

    <el-tab-pane label="商城利润账户" name="first">
      <div style="width: 100%; height: 85vh">
        <el-card style="width: 75vw; height: 80vh; position: relative;">
          <p id="profitAccountAmount" style="color: black; font-size: 20px">账户余额：元</p>
          <el-button style="display:inline-block ;float: left; position: relative ;left: 500px; bottom: 3vh"
                     @click="profit_week_record = true;profit_month_record=false;countProfit()">近一周记录
          </el-button>
          <el-button style="display:inline-block ;float: left; position: relative;left: 250px; bottom: 3vh"
                     @click="profit_month_record = true;profit_week_record=false;countProfit()">近一个月记录
          </el-button>
          <el-button style="display:inline-block ;float: right; position: relative; bottom: 3vh"
                     @click="chargeFormVisible = true">充值
          </el-button>
          <el-divider/>
          <el-container style="height: 55vh">
            <el-scrollbar height="55vh">
              <el-table
                  :data="profit_week_record? dataObj.tableData_2:profit_month_record?dataObj.tableData_3:dataObj.tableData_0"
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
          <el-dialog v-model="chargeFormVisible" style="width: 30vw;" title="账户充值">
            <el-form ref="chargeFormRef" :model="chargeForm" class="ChargeDialog">
              <el-form-item label="充值金额">
                <el-input-number v-model="chargeForm.amount" :min="0.01"/>
                <p>（单位：元）</p>
              </el-form-item>
            </el-form>
            <span class="dialog-footer">
          <el-button style="float: right; position: relative; bottom: 2vh"
                     @click="charge('profit')">确认充值</el-button>
        </span>
          </el-dialog>
        </el-card>
      </div>
    </el-tab-pane>

    <el-tab-pane label="购物中间账户" name="second">
      <div style="width: 100%; height: 85vh">
        <el-card style="width: 75vw; height: 80vh; position: relative; ">
          <p id="middleAccountAmount" style="color: black; font-size: 20px">账户余额：元</p>
          <el-button style="display:inline-block ;float: left; position: relative ;left: 500px; bottom: 3vh"
                     @click="middle_week_record = true;middle_month_record=false;countMiddle()">近一周记录
          </el-button>
          <el-button style="display:inline-block ;float: left; position: relative;left: 250px; bottom: 3vh"
                     @click="middle_month_record = true;middle_week_record=false;countMiddle()">近一个月记录
          </el-button>
          <el-button style="display:inline-block ;float: right; position: relative; bottom: 3vh"
                     @click="chargeFormVisible = true">充值
          </el-button>
          <el-divider/>
          <el-container style="height: 55vh">
            <el-scrollbar height="55vh">
              <el-table
                  :data="middle_week_record? dataObj.tableData_4:middle_month_record?dataObj.tableData_5:dataObj.tableData_1"
                  border height="55vh">
                <el-table-column label="转入方" prop="fromName" width="300"/>
                <el-table-column label="转出方" prop="toName" width="300"/>
                <el-table-column label="类型" prop="type" width="100"/>
                <el-table-column label="数额" prop="amount" width="100"/>
                <el-table-column label="时间" prop="createAt" width="300"/>
              </el-table>
            </el-scrollbar>
          </el-container>
          <p id="middleAll" style="color: black; margin-top: 20px; font-size: 20px">总计：元</p>
          <el-dialog v-model="chargeFormVisible" style="width: 30vw;" title="账户充值">
            <el-form ref="chargeFormRef" :model="chargeForm" class="ChargeDialog">
              <el-form-item label="充值金额">
                <el-input-number v-model="chargeForm.amount" :min="0.01"/>
                <p>（单位：元）</p>
              </el-form-item>
            </el-form>
            <span class="dialog-footer">
          <el-button style="float: right; position: relative; bottom: 2vh"
                     @click="charge('middle')">确认充值</el-button>
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

//充值
const chargeFormVisible = ref(false)
const chargeForm = reactive({
  amount: ''
})
//利润账户
const profit_week_record = ref(false)
const profit_month_record = ref(false)
//中间账户
const middle_week_record = ref(false)
const middle_month_record = ref(false)
//流水数据
const dataObj = reactive({
  tableData_0: [],  //利润流水
  tableData_1: [],  //中间流水
  tableData_2: [],  //近7天利润流水
  tableData_3: [],  //近一个月利润流水
  tableData_4: [],  //近7天中间流水
  tableData_5: [],  //近一个月中间流水
})

function charge(type) {
  service.post('/admin/account/recharge', {
    type: type,
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
    for(let i=0; i<dataObj.tableData_2.length;i++)
    {
      if(dataObj.tableData_2[i].toType==='profit'){
        profit += dataObj.tableData_2[i].amount
      }
      if(dataObj.tableData_2[i].fromType==='profit'){
        profit -= dataObj.tableData_2[i].amount
      }
    }
    document.getElementById("profitAll").innerText = "近一周总计：" + profit + " 元";
  }
  else if(profit_month_record.value){
    let profit=0;
    for(let i=0; i<dataObj.tableData_3.length;i++)
    {
      if(dataObj.tableData_3[i].toType==='profit'){
        profit += dataObj.tableData_3[i].amount
      }
      if(dataObj.tableData_3[i].fromType==='profit'){
        profit -= dataObj.tableData_3[i].amount
      }
    }
    document.getElementById("profitAll").innerText = "近一月总计：" + profit + " 元";
  }
  else{
    let profit=0;
    for(let i=0; i<dataObj.tableData_0.length;i++)
    {
      if(dataObj.tableData_0[i].toType==='profit'){
      profit += dataObj.tableData_0[i].amount
      }
      if(dataObj.tableData_0[i].fromType==='profit'){
        profit -= dataObj.tableData_0[i].amount
      }
    }
    document.getElementById("profitAll").innerText = "总计：" + profit + " 元";
  }
}

function countMiddle(){
  if(middle_week_record.value){
    let middle=0;
    for(let i=0; i<dataObj.tableData_4.length;i++)
    {
      if(dataObj.tableData_4[i].toType === 'middle') {
        middle += dataObj.tableData_4[i].amount
      }
      if(dataObj.tableData_4[i].fromType === 'middle') {
        middle -= dataObj.tableData_4[i].amount
      }
    }
    document.getElementById("middleAll").innerText = "近一周总计：" + middle + " 元";
  }
  else if(middle_month_record.value){
    let middle=0;
    for(let i=0; i<dataObj.tableData_5.length;i++)
    {
      if(dataObj.tableData_5[i].toType === 'middle') {
        middle += dataObj.tableData_5[i].amount
      }
      if(dataObj.tableData_5[i].fromType === 'middle') {
        middle -= dataObj.tableData_5[i].amount
      }
    }
    document.getElementById("middleAll").innerText = "近一月总计：" + middle + " 元";
  }
  else{
    let middle=0;
    for(let i=0; i<dataObj.tableData_1.length;i++)
    {
      if(dataObj.tableData_1[i].toType === 'middle') {
        middle += dataObj.tableData_1[i].amount
      }
      if(dataObj.tableData_1[i].fromType === 'middle') {
        middle -= dataObj.tableData_1[i].amount
      }
    }
    document.getElementById("middleAll").innerText = "总计：" +middle + " 元";
  }
}



const created = () => {
  service.get('/admin/account', {})
      .then(function (res) {
        document.getElementById("profitAccountAmount").innerText = "账户余额：" + res.data.profitAccountAmount + " 元";
        document.getElementById("middleAccountAmount").innerText = "账户余额：" + res.data.middleAccountAmount + " 元";
        document.getElementById("profitAll").innerText = "总计：" + res.data.profitAccountAmount + " 元";
        document.getElementById("middleAll").innerText = "总计：" + res.data.middleAccountAmount + " 元";
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error('请求失败，' + error.response.data.msg)
        }
      })
  service.post('/account-record/profit', {limit: 9999})
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
          ElMessage.error('利润账户流水数据请求失败，' + error.response.data.msg)
        }
      })
  service.post('/account-record/profit', {limit: 7})
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
          ElMessage.error('利润账户流水数据请求失败，' + error.response.data.msg)
        }
      })
  service.post('/account-record/profit', {limit: 30})
      .then(function (res) {
        let res_list = res.data
        let j_0 = 0
        for (let i = 0; i < res_list.length; i++) {
          //利润流水
          dataObj.tableData_3[j_0] = res_list[i]
          j_0++
        }
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error('利润账户流水数据请求失败，' + error.response.data.msg)
        }
      })
  service.post('/account-record/middle', {limit: 9999})
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
          ElMessage.error('中间账户流水数据请求失败，' + error.response.data.msg)
        }
      })
  service.post('/account-record/middle', {limit: 7})
      .then(function (res) {
        let res_list = res.data
        let j_0 = 0
        for (let i = 0; i < res_list.length; i++) {
          //利润流水
          dataObj.tableData_4[j_0] = res_list[i]
          j_0++
        }
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error('中间账户流水数据请求失败，' + error.response.data.msg)
        }
      })
  service.post('/account-record/middle', {limit: 30})
      .then(function (res) {
        let res_list = res.data
        let j_0 = 0
        for (let i = 0; i < res_list.length; i++) {
          //利润流水
          dataObj.tableData_5[j_0] = res_list[i]
          j_0++
        }
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error('中间账户流水数据请求失败，' + error.response.data.msg)
        }
      })
}

created();

</script>
