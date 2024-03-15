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
        <el-table-column prop="goodsId" label="商品编号" width="180" sortable/>
        <el-table-column prop="goodsName" label="商品名称" width="180" />
        <el-table-column prop="price" label="商品价格" width="180" />
        <el-table-column prop="number" label="库存数量（个）" width="180" />
        <el-table-column prop="description" label="商品描述" width="400"/>
        <el-table-column fixed="right" label="操作">
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
        <el-table-column prop="goodsId" label="商品编号" width="180" sortable/>
        <el-table-column prop="goodsName" label="商品名称" width="180" />
        <el-table-column prop="price" label="商品价格" width="180" />
        <el-table-column prop="number" label="库存数量（个）" width="180" />
        <el-table-column prop="description" label="商品描述" width="400"/>
      </el-table>
    </el-tab-pane>

    <el-tab-pane label="已拒绝" name="third">
      <el-table
          :data="dataObj.tableData_2"
          style="width: 100%"
      >
        <el-table-column prop="goodsId" label="商品编号" width="180" sortable/>
        <el-table-column prop="goodsName" label="商品名称" width="180" />
        <el-table-column prop="price" label="商品价格" width="180" />
        <el-table-column prop="number" label="库存数量（个）" width="180" />
        <el-table-column prop="description" label="商品描述" width="400"/>
      </el-table>
    </el-tab-pane>
  </el-tabs>


</template>

<script lang="ts" setup>
import service from "../../request/index.js"
import {reactive, inject} from 'vue';
import {ElMessage} from "element-plus";
const reload = inject('reload')
const activeName = "first"


const dataObj = reactive({
  tableData_0: [],  //待审核
  tableData_1: [],  //已批准
  tableData_2: []   //已拒绝
})

const created = () => {
  service.get('/goods/approve',{})
      .then(function (res) {
        let res_list = res.data
        let j_0 = 0
        let j_1 = 0
        let j_2 = 0
        for(let i=0; i<res_list.length; i++){
          if(res_list[i].state === 0){  //待审核
            dataObj.tableData_0[j_0] = res_list[i]
            j_0++
          }
          else if(res_list[i].state === 1){ //已批准
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
  let goodsId = dataObj.tableData_0[row_id].goodsId
  service.post('/goods/approve', {
    goodsId: goodsId,
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

function disApprove(row_id){
  let goodsId = dataObj.tableData_0[row_id].goodsId
  service.post('/goods/approve', {
    goodsId: goodsId,
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
.subpage_header{
  font-size: 24px;
  color: #606266;
}

</style>