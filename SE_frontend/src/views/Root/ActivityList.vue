<template>
  <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">

    <el-tab-pane
        label="正在进行"
        name="first"
    >
      <el-container style="height: 600px">
        <el-scrollbar height="600px">
          <el-table :data="dataObj.tableData_0" border height="600px">
            <el-table-column label="开始时间" prop="startTime" width="100"/>
            <el-table-column label="结束时间" prop="endTime" width="100"/>
            <el-table-column label="活动名称" prop="name" width="100"/>
            <el-table-column label="商品标签" prop="tags" width="150"/>
            <el-table-column label="满" prop="amountArrive" width="40"/>
            <el-table-column label="减" prop="amountMinus" width="40"/>
            <el-table-column label="投入资金" prop="fund" width="100"/>
            <el-table-column label="商店最低注册资金" prop="registerFund" width="150"/>
            <el-table-column label="商店最低销售额" prop="sellNumber" width="150"/>
            <el-table-column label="商店最低销售量" prop="sellAmount" width="150"/>
            <el-table-column label="活动序列号" prop="id" width="200"/>
          </el-table>
        </el-scrollbar>
      </el-container>
    </el-tab-pane>

    <el-tab-pane
        label="未开始"
        name="second"
    >
      <el-container style="height: 600px">
        <el-scrollbar height="600px">
          <el-table :data="dataObj.tableData_1" border height="600px">
            <el-table-column label="开始时间" prop="startTime" width="100"/>
            <el-table-column label="结束时间" prop="endTime" width="100"/>
            <el-table-column label="活动名称" prop="name" width="100"/>
            <el-table-column label="商品标签" prop="tags" width="150"/>
            <el-table-column label="满" prop="amountArrive" width="40"/>
            <el-table-column label="减" prop="amountMinus" width="40"/>
            <el-table-column label="投入资金" prop="fund" width="100"/>
            <el-table-column label="商店最低注册资金" prop="registerFund" width="150"/>
            <el-table-column label="商店最低销售额" prop="sellNumber" width="150"/>
            <el-table-column label="商店最低销售量" prop="sellAmount" width="150"/>
            <el-table-column label="活动序列号" prop="id" width="200"/>
          </el-table>
        </el-scrollbar>
      </el-container>
    </el-tab-pane>

    <el-tab-pane label="已结束" name="third">
      <el-container style="height: 600px">
        <el-scrollbar height="600px">
          <el-table :data="dataObj.tableData_2" border height="600px">
            <el-table-column label="开始时间" prop="startTime" width="100"/>
            <el-table-column label="结束时间" prop="endTime" width="100"/>
            <el-table-column label="活动名称" prop="name" width="100"/>
            <el-table-column label="商品标签" prop="tags" width="150"/>
            <el-table-column label="满" prop="amountArrive" width="40"/>
            <el-table-column label="减" prop="amountMinus" width="40"/>
            <el-table-column label="投入资金" prop="fund" width="100"/>
            <el-table-column label="商店最低注册资金" prop="registerFund" width="150"/>
            <el-table-column label="商店最低销售额" prop="sellNumber" width="150"/>
            <el-table-column label="商店最低销售量" prop="sellAmount" width="150"/>
            <el-table-column label="活动序列号" prop="id" width="200"/>
          </el-table>
        </el-scrollbar>
      </el-container>
    </el-tab-pane>
  </el-tabs>
</template>


<script lang="ts" setup>
import service from "../../request/index.js"
import {reactive} from 'vue';
import {ElMessage} from "element-plus";


const dataObj = reactive({
  tableData_0: [],  //正在进行的活动
  tableData_1: [],  //未开始的活动
  tableData_2: [],  //已结束的活动

})

const activeName = "first"


const created = () => {
  service.get('/activity',{})
      .then(function (res) {
        let res_list = res.data
        let j_0 = 0
        let j_1 = 0
        let j_2 = 0
        for(let i=0; i<res_list.length; i++){
          if(res_list[i].state === 1 ){
            dataObj.tableData_0[j_0] = res_list[i]
            j_0++
          }
          else if(res_list[i].state === 0 ){
            dataObj.tableData_1[j_1] = res_list[i]
            j_1++
          }
          else if(res_list[i].state === 2 ){
            dataObj.tableData_2[j_2] = res_list[i]
            j_2++
          }
        }
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error('活动数据请求失败，' + error.response.data.msg)
        }
      })
}

created();

</script>
