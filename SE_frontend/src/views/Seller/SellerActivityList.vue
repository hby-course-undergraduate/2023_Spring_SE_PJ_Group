<template>
  <el-container style="height: 600px">
    <el-scrollbar height="600px">
      <el-table :data="dataObj.tableData_0" border height="600px">
        <el-table-column label="开始时间" prop="startTime" width="100"/>
        <el-table-column label="结束时间" prop="endTime" width="100"/>
        <el-table-column label="活动名称" prop="name" width="100"/>
        <el-table-column label="商品标签" prop="tags" width="100"/>
        <el-table-column label="满" prop="amountArrive" width="40"/>
        <el-table-column label="减" prop="amountMinus" width="40"/>
        <el-table-column label="投入资金" prop="fund" width="100"/>
        <el-table-column label="商店最低注册资金" prop="registerFund" width="150"/>
        <el-table-column label="商店最低销售额" prop="sellNumber" width="130"/>
        <el-table-column label="商店最低销售量" prop="sellAmount" width="130"/>
        <el-table-column label="活动序列号" prop="id" width="100"/>
        <el-table-column>
          <template #default="scope">
            <el-button link type="primary" @click="apply(scope.$index)">申请</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-scrollbar>
  </el-container>
</template>


<script lang="ts" setup>
import service from "../../request/index.js"
import {inject, reactive} from 'vue';
import {ElMessage} from "element-plus";

const reload = inject('reload');

const dataObj = reactive({
  tableData_0: [],  //活动
})

function apply(rowIndex) {
  const activityId = dataObj.tableData_0[rowIndex].id;
  service.post('/activity/apply', {
    activityId: activityId,
  })
      .then((res) => {
        if (res.status === 200) {
          ElMessage.success('已申请');
        }
      })
      .catch((error) => {
        if (error.response) {
          ElMessage.error(`申请失败，${error.response.data.msg}`);
        }
      });
  reload();
}

const created = () => {
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

created();

</script>
