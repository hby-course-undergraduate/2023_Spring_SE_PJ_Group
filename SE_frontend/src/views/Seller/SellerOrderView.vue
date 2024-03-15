<template>
  <div class="subpage">
    <div class="subpage-header">订单列表</div>

    <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
      <el-tab-pane
          label="未发货"
          name="first"
      >
        <el-table :data="dataObj.tableData_0" style="width: 100%" border>
          <el-table-column prop="orderId" label="订单号" width="100" sortable />
          <el-table-column prop="totalPrice" label="总金额" width="100" />
          <el-table-column prop="buyTime" label="购买时间" width="100" />
          <el-table-column prop="address" label="收货地址" width="100" />
          <el-table-column prop="consignee" label="收货人" width="100" />
          <el-table-column prop="consignee" label="手机号" width="100" sortable />
          <el-table-column prop="goodsName" label="商品名" width="100" />
          <el-table-column prop="unitPrice" label="单件价格" width="100" />
          <el-table-column prop="number" label="商品数量（个）" width="140" />
          <el-table-column>
            <template #default="scope">
              <el-button link type="primary" @click="deliver(scope.$index)">发货</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>

      <el-tab-pane
          label="未支付"
          name="second"
      >
        <el-table :data="dataObj.tableData_1" style="width: 100%" border>
          <el-table-column prop="orderId" label="订单号" width="100" sortable />
          <el-table-column prop="totalPrice" label="总金额" width="100" />
          <el-table-column prop="buyTime" label="购买时间" width="100" />
          <el-table-column prop="address" label="收货地址" width="100" />
          <el-table-column prop="consignee" label="收货人" width="100" />
          <el-table-column prop="consignee" label="手机号" width="100" sortable />
          <el-table-column prop="goodsName" label="商品名" width="100" />
          <el-table-column prop="unitPrice" label="单件价格" width="100" />
          <el-table-column prop="number" label="商品数量（个）" width="140" />
        </el-table>
      </el-tab-pane>

      <el-tab-pane
          label="未收货"
          name="third"
      >
        <el-table :data="dataObj.tableData_2" style="width: 100%" border>
          <el-table-column prop="orderId" label="订单号" width="100" sortable />
          <el-table-column prop="totalPrice" label="总金额" width="100" />
          <el-table-column prop="buyTime" label="购买时间" width="100" />
          <el-table-column prop="address" label="收货地址" width="100" />
          <el-table-column prop="consignee" label="收货人" width="100" />
          <el-table-column prop="consignee" label="手机号" width="100" sortable />
          <el-table-column prop="goodsName" label="商品名" width="100" />
          <el-table-column prop="unitPrice" label="单件价格" width="100" />
          <el-table-column prop="number" label="商品数量（个）" width="140" />
        </el-table>
      </el-tab-pane>

      <el-tab-pane
          label="已收货"
          name="fourth"
      >
        <el-table :data="dataObj.tableData_3" style="width: 100%" border>
          <el-table-column prop="orderId" label="订单号" width="100" sortable />
          <el-table-column prop="totalPrice" label="总金额" width="100" />
          <el-table-column prop="buyTime" label="购买时间" width="100" />
          <el-table-column prop="address" label="收货地址" width="100" />
          <el-table-column prop="consignee" label="收货人" width="100" />
          <el-table-column prop="consignee" label="手机号" width="100" sortable />
          <el-table-column prop="goodsName" label="商品名" width="100" />
          <el-table-column prop="unitPrice" label="单件价格" width="100" />
          <el-table-column prop="number" label="商品数量（个）" width="140" />
        </el-table>
      </el-tab-pane>

      <el-tab-pane
          label="已取消"
          name="fifth"
      >
        <el-table :data="dataObj.tableData_4" style="width: 100%" border>
          <el-table-column prop="orderId" label="订单号" width="100" sortable />
          <el-table-column prop="totalPrice" label="总金额" width="100" />
          <el-table-column prop="buyTime" label="购买时间" width="100" />
          <el-table-column prop="address" label="收货地址" width="100" />
          <el-table-column prop="consignee" label="收货人" width="100" />
          <el-table-column prop="consignee" label="手机号" width="100" sortable />
          <el-table-column prop="goodsName" label="商品名" width="100" />
          <el-table-column prop="unitPrice" label="单件价格" width="100" />
          <el-table-column prop="number" label="商品数量（个）" width="140" />
        </el-table>
      </el-tab-pane>

      <el-tab-pane
          label="已退款"
          name="sixth"
      >
        <el-table :data="dataObj.tableData_5" style="width: 100%" border>
          <el-table-column prop="orderId" label="订单号" width="100" sortable />
          <el-table-column prop="totalPrice" label="总金额" width="100" />
          <el-table-column prop="buyTime" label="购买时间" width="100" />
          <el-table-column prop="address" label="收货地址" width="100" />
          <el-table-column prop="consignee" label="收货人" width="100" />
          <el-table-column prop="consignee" label="手机号" width="100" sortable />
          <el-table-column prop="goodsName" label="商品名" width="100" />
          <el-table-column prop="unitPrice" label="单件价格" width="100" />
          <el-table-column prop="number" label="商品数量（个）" width="140" />
        </el-table>
      </el-tab-pane>

      <el-tab-pane
          label="处理退款"
          name="seventh"
      >
        <el-table :data="dataObj.tableData_6" style="width: 100%" border>
          <el-table-column prop="orderId" label="订单号" width="100" sortable />
          <el-table-column prop="totalPrice" label="总金额" width="100" />
          <el-table-column prop="buyTime" label="购买时间" width="100" />
          <el-table-column prop="address" label="收货地址" width="100" />
          <el-table-column prop="consignee" label="收货人" width="100" />
          <el-table-column prop="consignee" label="手机号" width="100" sortable />
          <el-table-column prop="goodsName" label="商品名" width="100" />
          <el-table-column prop="unitPrice" label="单件价格" width="100" />
          <el-table-column prop="number" label="商品数量（个）" width="140" />
          <el-table-column>
            <template #default="scope">
              <el-button link type="primary" @click="approve(scope.$index)">同意</el-button>
              <el-button link type="primary" @click="disapprove(scope.$index)">拒绝</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script lang="ts" setup>
import service from "../../request/index.js";
import { reactive, inject } from 'vue';
import { ElMessage } from "element-plus";

const reload = inject('reload');

const dataObj = reactive({
  tableData_0: [],  //未发货
  tableData_1: [],  //未支付
  tableData_2: [],  //未收货
  tableData_3: [],  //已收货
  tableData_4: [],  //已取消
  tableData_5: [],  //已退款
  tableData_6: [],  //处理退款
})

const activeName = "first"

const created = () => {
  service.post('/order/all/seller', {state: 'all'})
      .then((res) => {
        let res_list = res.data
        let j_0 = 0
        let j_1 = 0
        let j_2 = 0
        let j_3 = 0
        let j_4 = 0
        let j_5 = 0
        for(let i=0; i<res_list.length; i++){
          if(res_list[i].state === '待发货'){  //未发货
            dataObj.tableData_0[j_0] = res_list[i]
            j_0++
          }
          else if(res_list[i].state === '待支付'){ //未支付
            dataObj.tableData_1[j_1] = res_list[i]
            j_1++
          }
          else if(res_list[i].state === '待收货'){ //未收货
            dataObj.tableData_2[j_2] = res_list[i]
            j_2++
          }
          else if(res_list[i].state === '已完成'){ //已收货
            dataObj.tableData_3[j_3] = res_list[i]
            j_3++
          }
          else if(res_list[i].state === '已撤销'){ //已取消
            dataObj.tableData_4[j_4] = res_list[i]
            j_4++
          }
          else if(res_list[i].state === '已退款'){ //已退款
            dataObj.tableData_5[j_5] = res_list[i]
            j_5++
          }
        }
      })
      .catch((error) => {
        if (error.response) {
          ElMessage.error(`请求失败，${error.response.data.msg}`);
        }
      })
  service.get('/order/refund/approve',{})
      .then(function (res) {
        let res_list = res.data
        let j_0 = 0
        for(let i=0; i<res_list.length; i++){
          dataObj.tableData_6[j_0] = res_list[i]
          j_0++
        }
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error('请求失败，' + error.response.data.msg)
        }
      })
};

function deliver(rowIndex) {
  const orderId = dataObj.tableData_0[rowIndex].orderId;
  service.post('/order/deliver', {
    orderId: orderId,
  })
      .then((res) => {
        if (res.status === 200) {
          ElMessage.success('已发货');
        }
      })
      .catch((error) => {
        if (error.response) {
          ElMessage.error(`发货失败，${error.response.data.msg}`);
        }
      });
  reload();
}

function approve(rowIndex) {
  const orderId = dataObj.tableData_6[rowIndex].orderId;
  service.post('/order/refund/approve', {
    orderId: orderId,
    isApproved:true,
  })
      .then((res) => {
        if (res.status === 200) {
          ElMessage.success('已同意');
        }
      })
      .catch((error) => {
        if (error.response) {
          ElMessage.error(`处理失败，${error.response.data.msg}`);
        }
      });
  reload();
}

function disapprove(rowIndex) {
  const orderId = dataObj.tableData_6[rowIndex].orderId;
  service.post('/order/refund/approve', {
    orderId: orderId,
    isApproved:false,
  })
      .then((res) => {
        if (res.status === 200) {
          ElMessage.success('已拒绝');
        }
      })
      .catch((error) => {
        if (error.response) {
          ElMessage.error(`处理失败，${error.response.data.msg}`);
        }
      });
  reload();
}


created();

</script>
<style scoped>
.subpage {
  padding: 20px;
}
.subpage-header {
  font-size: 24px;
  color: #606266;
  margin-bottom: 20px;
}
</style>