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
      >
        <el-table-column prop="shopCancelId" label="申请编号" width="180" sortable/>
        <el-table-column prop="shopId" label="商店编号" width="180" />
        <el-table-column prop="createAt" label="申请时间" width="250" />
        <el-table-column prop="orderFinish" label="订单完成情况" width="180" />
        <el-table-column fixed="right" label="操作">
          <template #default="scope">
            <el-button link type="primary" @click="Approve(scope.$index)">同意</el-button>
            <el-button link type="primary" @click="disApprove(scope.$index)">拒绝</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-tab-pane>
  </el-tabs>


</template>

<script lang="ts" setup>
import service from "../../request/index.js"
import { reactive, inject } from 'vue';
import {ElMessage} from "element-plus";
const reload = inject('reload')

const dataObj = reactive({
  tableData_0: [],  //待审核
})

const created = () => {
  service.get('/shop/cancel/approve',{})
      .then(function (res) {
        let res_list = res.data
        let j_0 = 0
        for(let i=0; i<res_list.length; i++){
          if(res_list[i].state === 0){  //待审核
            dataObj.tableData_0[j_0] = res_list[i]
            j_0++
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
  let shopCancelId = dataObj.tableData_0[row_id].shopCancelId
  let orderFinish = dataObj.tableData_0[row_id].orderFinish
  if(orderFinish === false){
    ElMessage.error("有订单尚未完成，无法关店！")
    return
  }
  else{
    service.post('/shop/cancel/approve', {
      shopCancelId: shopCancelId,
      isApproved: true
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
}

function disApprove(row_id){
  let shopCancelId = dataObj.tableData_0[row_id].shopCancelId
  service.post('/shop/cancel/approve', {
    shopCancelId: shopCancelId,
    isApproved: false
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

<style scoped>

</style>