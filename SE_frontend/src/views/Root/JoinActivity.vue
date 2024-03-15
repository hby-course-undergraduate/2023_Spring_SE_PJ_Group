<template>
  <!--  <div class="subpage_header">开店申请</div>-->
  <!--  <el-divider />-->
  <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">

    <el-tab-pane
        label="待审核"
        name="first"
    >
      <el-table
          :data="dataObj.tableData_0"
          style="width: 100%"
          border
      >
        <el-table-column label="商店ID" prop="shopId" width="70"/>
        <el-table-column label="开始时间" prop="startTime" width="100"/>
        <el-table-column label="结束时间" prop="endTime" width="100"/>
        <el-table-column label="活动名称" prop="name" width="100"/>
        <el-table-column label="商品标签" prop="tags" width="100"/>
        <el-table-column label="剩余资金" prop="fund" width="100"/>
        <el-table-column label="满" prop="amountArrive" width="50"/>
        <el-table-column label="减" prop="amountMinus" width="50"/>
        <el-table-column label="投入资金" prop="registerFund" width="100"/>
        <el-table-column label="最低销售额" prop="sellNumber" width="120"/>
        <el-table-column label="最低销售量" prop="sellAmount" width="120"/>
        <el-table-column label="活动序列号" prop="activityId" width="120"/>
        <el-table-column>
          <template #default="scope">
            <el-button link type="primary" @click="Approve(scope.$index)">同意</el-button>
            <el-button link type="primary" @click="disApprove(scope.$index)">拒绝</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-tab-pane>

    <el-tab-pane label="已批准" name="second">
      <el-table
          :data="dataObj.tableData_1"
          style="width: 100%"
      >
        <el-table-column label="商店ID" prop="shopId" width="70"/>
        <el-table-column label="开始时间" prop="startTime" width="100"/>
        <el-table-column label="结束时间" prop="endTime" width="100"/>
        <el-table-column label="活动名称" prop="name" width="100"/>
        <el-table-column label="商品标签" prop="tags" width="100"/>
        <el-table-column label="剩余资金" prop="fund" width="100"/>
        <el-table-column label="满" prop="amountArrive" width="50"/>
        <el-table-column label="减" prop="amountMinus" width="50"/>
        <el-table-column label="投入资金" prop="registerFund" width="100"/>
        <el-table-column label="最低销售额" prop="sellNumber" width="120"/>
        <el-table-column label="最低销售量" prop="sellAmount" width="120"/>
        <el-table-column label="活动序列号" prop="activityId" width="120"/>
      </el-table>
    </el-tab-pane>

    <el-tab-pane label="已拒绝" name="third">
      <el-table
          :data="dataObj.tableData_2"
          style="width: 100%"
      >
        <el-table-column label="商店ID" prop="shopId" width="70"/>
        <el-table-column label="开始时间" prop="startTime" width="100"/>
        <el-table-column label="结束时间" prop="endTime" width="100"/>
        <el-table-column label="活动名称" prop="name" width="100"/>
        <el-table-column label="商品标签" prop="tags" width="100"/>
        <el-table-column label="剩余资金" prop="fund" width="100"/>
        <el-table-column label="满" prop="amountArrive" width="50"/>
        <el-table-column label="减" prop="amountMinus" width="50"/>
        <el-table-column label="投入资金" prop="registerFund" width="100"/>
        <el-table-column label="最低销售额" prop="sellNumber" width="120"/>
        <el-table-column label="最低销售量" prop="sellAmount" width="120"/>
        <el-table-column label="活动序列号" prop="activityId" width="120"/>
      </el-table>
    </el-tab-pane>
  </el-tabs>


</template>

<script lang="ts" setup>
import service from "../../request/index.js"
import { reactive, inject } from 'vue';
import {ElMessage} from "element-plus";
const reload = inject('reload')
const activeName = "first"

const dataObj = reactive({
  tableData_0: [],  //待审核
  tableData_1: [],  //已批准
  tableData_2: []   //已拒绝
})

const created = () => {
  service.get('/activity/approve',{})
      .then(function (res) {
        let res_list = res.data
        let j_0 = 0
        let j_1 = 0
        let j_2 = 0
        for(let i=0; i<res_list.length; i++){
          if(res_list[i].state === 0 && res_list[i].isApproved === 0){  //待审核
            dataObj.tableData_0[j_0] = res_list[i]
            j_0++
          }
          else if(res_list[i].isApproved === 1){ //已批准
            dataObj.tableData_1[j_1] = res_list[i]
            j_1++
          }
          else if(res_list[i].state === 2){ //已拒绝
            dataObj.tableData_2[j_2] = res_list[i]
            j_2++
          }
        }
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error('请求失败，' + error.response.data.msg)
        }
      })
}

function Approve(row_id){
  let activityId = dataObj.tableData_0[row_id].activityId
  let shopId = dataObj.tableData_0[row_id].shopId
  service.post('/activity/approve', {
    activityId: activityId,
    shopId: shopId,
    isApproved: true,
  })
      .then(function (res) {
        if (res.status === 200) {
          ElMessage.success('已批准')
        }
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error('申请失败，' + error.response.data.msg)
        }
      })
  reload()
}

function disApprove(row_id){
  let activityId = dataObj.tableData_0[row_id].activityId
  let shopId = dataObj.tableData_0[row_id].shopId
  service.post('/activity/approve', {
    activityId: activityId,
    shopId: shopId,
    isApproved: false,
  })

      .then(function (res) {
        if (res.status === 200) {
          ElMessage.success('已拒绝')
        }
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error('申请失败，' + error.response.data.msg)
        }
      })
  reload()
}

created();

</script>
