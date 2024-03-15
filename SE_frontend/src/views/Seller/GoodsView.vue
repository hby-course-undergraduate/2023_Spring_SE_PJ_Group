<template>
  <div class="subpage">
    <div class="subpage-header">商品列表</div>

    <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">

      <el-tab-pane
          label="在售中"
          name="first"
      >
        <el-table :data="tableData1" style="width: 100%">
          <el-table-column prop="goodsId" label="商品编号" width="180" sortable />
          <el-table-column prop="goodsName" label="商品名称" width="180" />
          <el-table-column prop="price" label="商品价格" width="180" />
          <el-table-column prop="number" label="库存数量（个）" width="180" />
          <el-table-column prop="description" label="商品描述" width="400" />
          <el-table-column fixed="right" label="操作">
            <template #default="scope">
              <el-button link type="primary" @click="jump(scope.$index)">详情页</el-button>
              <el-button link type="primary" @click="modify(scope.$index)">编辑</el-button>
              <el-button link type="primary" @click="deleteGoods(scope.$index)">下架</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>

      <el-tab-pane
          label="待审核"
          name="second"
      >
        <el-table :data="tableData0" style="width: 100%">
          <el-table-column prop="goodsId" label="商品编号" width="180" sortable />
          <el-table-column prop="goodsName" label="商品名称" width="180" />
          <el-table-column prop="price" label="商品价格" width="180" />
          <el-table-column prop="number" label="库存数量（个）" width="180" />
          <el-table-column prop="description" label="商品描述" width="400" />
        </el-table>
      </el-tab-pane>

      <el-tab-pane
          label="已下架"
          name="third"
      >
        <el-table :data="tableData2" style="width: 100%">
          <el-table-column prop="goodsId" label="商品编号" width="180" sortable />
          <el-table-column prop="goodsName" label="商品名称" width="180" />
          <el-table-column prop="price" label="商品价格" width="180" />
          <el-table-column prop="number" label="库存数量（个）" width="180" />
          <el-table-column prop="description" label="商品描述" width="400" />
        </el-table>
      </el-tab-pane>




    </el-tabs>








  </div>
</template>
<script lang="ts" setup>
import service from "../../request/index.js";
import { reactive, inject } from 'vue';
import { useRouter } from "vue-router";
import { ElMessage } from "element-plus";

const reload = inject('reload');
const router = useRouter();

const tableData1 = reactive([]);
const tableData0 = reactive([]);
const tableData2 = reactive([]);
const activeName = "first"

const created = () => {
  service.get('/goods/apply/record', {})
    .then((res) => {
      const resList = res.data;
      tableData1.splice(0, tableData1.length);
      tableData0.splice(0, tableData0.length);
      tableData2.splice(0, tableData2.length);
      for (let i = 0; i < resList.length; i++) {
        if (resList[i].state === 1) { //在架上
          tableData1.push(resList[i]);
        }
        else if(resList[i].state === 0) { //待审核
          tableData0.push(resList[i]);
        }
        else if(resList[i].state === 2) { //已下架
          tableData2.push(resList[i]);
        }
      }
    })
    .catch((error) => {
      if (error.response) {
        ElMessage.error(`请求失败，${error.response.data.msg}`);
      }
    });
};

function deleteGoods(rowIndex) {
  const goodsId = tableData1[rowIndex].goodsId;
  service.post('/goods/cancel', {
    goodsId: goodsId,
  })
    .then((res) => {
      if (res.status === 200) {
        ElMessage.success('已下架');
      }
    })
    .catch((error) => {
      if (error.response) {
        ElMessage.error(`下架失败，${error.response.data.msg}`);
      }
    });
  reload();
}

function jump(rowIndex) {
  const url = `/goods_details?goodsId=${tableData1[rowIndex].goodsId}`;
  router.push(url);
}

function modify(rowIndex) {
  const url = `/modify_goods?goodsId=${tableData1[rowIndex].goodsId}`;
  router.push(url);
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