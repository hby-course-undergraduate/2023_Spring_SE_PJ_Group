<template>
  <div style="width: 100%; place-items: center">
    <div class="s_header">
      <p style="width: 8vw; margin: auto">发布活动</p>
    </div>

    <div class="subpage_form">
      <el-form ref="activityForm" :model="activity" :rules="rules">
        <el-form-item prop="startTime">
          <el-date-picker
              v-model="activity.startTime"
              placeholder="请选择活动开始时间"
              type="datetime"
          />
        </el-form-item>
        <el-form-item prop="lastTime">
          <el-date-picker
              v-model="activity.endTime"
              placeholder="请选择活动结束时间"
              type="datetime"
          />
        </el-form-item>
        <el-form-item prop="name">
          <el-input
              v-model="activity.name"
              placeholder="请输入活动名称"
              prefix-icon="User"
          ></el-input>
        </el-form-item>
        <el-form-item prop="tags">
          <el-input
              v-model="activity.tags"
              placeholder="请输入商品标签（以逗号分隔多个tag）"
              prefix-icon="User"
          ></el-input>
        </el-form-item>
        <el-form-item prop="fund">
          <el-input
              v-model="activity.fund"
              placeholder="请输入活动资金"
              prefix-icon="EditPen"
          ></el-input>
        </el-form-item>
        <el-form-item prop="fund">
          <el-input
              v-model="activity.registerFund"
              placeholder="请输入最低注册资金"
              prefix-icon="EditPen"
          ></el-input>
        </el-form-item>
        <el-form-item prop="amountArrive">
          <el-input
              v-model="activity.amountArrive"
              placeholder="请输入满金额(单位：元)"
              prefix-icon="Money"
              type="number"
          ></el-input>
        </el-form-item>
        <el-form-item prop="amountMinus">
          <el-input
              v-model="activity.amountMinus"
              placeholder="请输入减金额(单位：元)"
              prefix-icon="Money"
              type="number"
          ></el-input>
        </el-form-item>
        <el-form-item prop="sellNumber">
          <el-input
              v-model="activity.sellNumber"
              placeholder="请输入商店最低月销量"
              prefix-icon="Location"
              type="number"
          ></el-input>
        </el-form-item>
        <el-form-item prop="sellAmount">
          <el-input
              v-model="activity.sellAmount"
              placeholder="请输入商店最低月销售额"
              prefix-icon="Location"
              type="number"
          ></el-input>
        </el-form-item>
        <el-form-item style="margin: 10px 0; text-align: right">
          <el-button autocomplete="off" size="medium" style="position: relative; left: 230px" type="primary"
                     @click="apply"
          >发布活动
          </el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import service from "@/request";
import {ElMessage} from "element-plus";

export default {
  name: 'new_activity',
  data() {
    return {
      activity: {},
      rules: {
        startTime: [
          {required: true, message: '请选择开始时间', trigger: 'blur'},
          {required: true, message: '商店名不能为空', trigger: 'blur'},
        ],
        endTime: [
          {required: true, message: '请选择结束时间', trigger: 'blur'},
          {required: true, message: '主营商品种类不能为空', trigger: 'blur'}
        ],
        name: [
          {required: true, message: '活动名不能为空', trigger: 'blur'},
          {min: 1, max: 12, message: '长度应为 1 到 12 个字符', trigger: 'blur'}
        ],
        tags: [
          {required: true, message: '商店标签不能为空', trigger: 'blur'},
          {min: 1, max: 120, message: '长度在 1 到 128 个字符', trigger: 'blur'}
        ],
        registerFund: [
          {required: true, message: '最低注册资金不能为空', trigger: 'blur'},
          {pattern: /^([1-9][0-9]{1,}.\d*)|([1-9][0-9]{1,})$/, message: '最低注册资金不低于10元', trigger: 'blur'},
        ],
        fund: [
          {required: true, message: '活动资金不能为空', trigger: 'blur'},
          {pattern: /^([1-9][0-9]{1,}.\d*)|([1-9][0-9]{1,})$/, message: '活动资金不低于10元', trigger: 'blur'},
        ],
        amountArrive: [
          {required: true, message: '满减金额不能为空', trigger: 'blur'},
          {pattern: /^([1-9][0-9]{1,}.\d*)|([1-9][0-9]{1,})$/, message: '满金额不低于10', trigger: 'blur'},
        ],
        amountMinus: [
          {required: true, message: '满减金额不能为空', trigger: 'blur'},
          {pattern: /^([1-9][0-9]{0,}.\d*)|([1-9][0-9]{0,})$/, message: '减金额不低于1', trigger: 'blur'},
        ],
        sellNumber: [
          {required: true, message: '最低销售量不能为空', trigger: 'blur'},
          {pattern: /^[1-9][0-9]{0,}$/, message: '最低销售量不低于1元', trigger: 'blur'},
        ],
        sellAmount: [
          {required: true, message: '最低销售额不能为空', trigger: 'blur'},
          {pattern: /^([1-9][0-9]{0,}.\d*)|([1-9][0-9]{0,})$/, message: '最低销售额不低于1', trigger: 'blur'},
        ],
      },
    }
  },
  methods: {
    apply() {
      this.$refs['activityForm'].validate((valid) => {
        if (valid) {
          service.post('/activity/organize', this.activity)
              .then(function (res) {
                if (res.status === 200) {
                  ElMessage.success('活动发布成功')
                }
              })
              .catch(function (error) {
                if (error.response) {
                  ElMessage.error('活动数据请求失败，' + error.response.data.msg)
                }
              })
        }
      })

    }
  }
}
</script>

<style scoped>

.s_header{
  font-size: 24px;
  color: #606266;
}

.subpage_form{
  width: 45vw;
  border: 1px solid lightgrey;
  border-radius: 20px;
  padding: 3vh 5vw;
  place-items: center;
  overflow: hidden;
  margin: 5vh auto auto;
}

</style>
