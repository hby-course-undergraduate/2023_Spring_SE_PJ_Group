<template>
  <div style="background-color: #EAEAEA; overflow-x: hidden; width: 100vw; height: 100vh">

    <el-container style="width: 100vw; height: 100vh">

      <el-header class="UserHeader">
        <div style="margin-top: 1.5vh; display: inline-block">
          <div style="display: inline-block; position: relative; bottom: 0.5vh; margin-left: 38vw">
            <el-input placeholder="请输入商品名称" style="height: 4vh; width: 18vw"/>
            <el-button :icon="Search" style="height: 4vh; width: 3vw"></el-button>
          </div>
          <div style="display: inline-block; margin-left: 28vw">
            <a href="/customer">
              <el-icon
                  style="margin-top: 5px; margin-right: 0.5vw"
                  color="black"
                  size="17px"
              >
                <User/>
              </el-icon>
            </a>
            <span style="color: black; font-size: 17px; margin-right: 0.5vw" id="userName">
            <a href="/login" style="color: #181818; position: relative; bottom: 2px">{{ userName }}</a>
          </span>
            <el-dropdown>
              <el-icon style="margin-left: 1vw; margin-top: 8px">
                <ArrowDown/>
              </el-icon>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item style="margin-top: 0.5vh" @click="Personal()">个人中心</el-dropdown-item>
                  <el-dropdown-item style="margin-top: 0.5vh" @click="Cart()">购物车</el-dropdown-item>
                  <el-dropdown-item style="margin-top: 0.5vh" @click="LogOut()">退出登录</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </div>
      </el-header>

      <el-container>
        <el-aside width="25vw">
          <el-card class="UserCard">
            <div class="card-header">
              <el-icon
                  style="margin-top: 5px; margin-right: 0.5vw; display: inline-block; position: relative; top: 3px;"
                  color="black"
                  size="20px"
              >
                <User/>
              </el-icon>
              <p
                  style="font-size: 20px; display: inline-block"
              >个人信息</p>
              <el-button
                  :icon="Edit"
                  style="float: right; width: 3vw;"
                  @click="personalInfoFormVisible = true"
              />

              <el-dialog v-model="personalInfoFormVisible" title="修改个人信息">

                <el-form :model="editUserNameForm" class="EditDialog" :rules="rules" ref="editUserNameFormRef">
                  <el-form-item label="用户名称" prop="username">
                    <el-input v-model="editUserNameForm.username" style="width: 30vw" placeholder="请输入新用户名称"/>
                    <el-button
                        :icon="Edit"
                        style="display: inline-block"
                        @click="EditUserName(editUserNameFormRef)"
                    />
                  </el-form-item>
                </el-form>

                <el-form :model="editPasswordForm" class="EditDialog" :rules="rules" ref="editPasswordFormRef">
                  <el-form-item label="登陆密码" prop="pastPassword">
                    <el-input v-model="editPasswordForm.pastPassword" style="width: 30vw" show-password type="password"
                              placeholder="请输入旧密码"/>
                  </el-form-item>
                  <el-form-item label="登陆密码" prop="presentPassword">
                    <el-input v-model="editPasswordForm.presentPassword" style="width: 30vw" show-password
                              type="password" placeholder="请输入新密码"/>
                    <el-button
                        :icon="Edit"
                        style="display: inline-block; position: relative;"
                        @click="EditPassword(editPasswordFormRef)"
                    />
                  </el-form-item>
                </el-form>

                <el-form :model="editPhoneForm" class="EditDialog" :rules="rules" ref="editPhoneFormRef">
                  <el-form-item label="手机号码" prop="phone">
                    <el-input v-model="editPhoneForm.phone" style="width: 30vw" placeholder="请输入新手机号码"/>
                    <el-button
                        :icon="Edit"
                        style="display: inline-block"
                        @click="EditPhone(editPhoneFormRef)"
                    />
                  </el-form-item>
                </el-form>

                <el-form :model="editEmailForm" class="EditDialog" :rules="rules" ref="editEmailFormRef">
                  <el-form-item label="电子邮箱" prop="email">
                    <el-input v-model="editEmailForm.email" style="width: 30vw" placeholder="请输入新电子邮箱"/>
                    <el-button
                        :icon="Edit"
                        style="display: inline-block"
                        @click="EditEmail(editEmailFormRef)"
                    />
                  </el-form-item>
                </el-form>


              </el-dialog>

            </div>

            <el-divider/>

            <div class="card-content" v-for="i in dataObj.tableData" :key="i">
              <p>用户名称：{{ i.username }}</p>
              <p>手机号码：{{ i.phone }}</p>
              <p>身份证号：{{ i.idCard }}</p>
              <p>电子邮箱：{{ i.email }}</p>
              <p>账户余额：{{ i.amount }} 元</p>
            </div>

            <el-button @click="chargeFormVisible = true" style="float: right; position: relative; bottom: 1vh">充值
            </el-button>
            <el-dialog v-model="chargeFormVisible" title="账户充值" style="width: 30vw;">
              <el-form :model="chargeForm" class="ChargeDialog" ref="chargeFormRef">
                <el-form-item label="充值金额">
                  <el-input-number v-model="chargeForm.amount" :min="0.01"/>
                  <p>（单位：元）</p>
                </el-form-item>
              </el-form>
              <span class="dialog-footer">
                  <el-button
                      @click="charge()"
                      style="float: right; position: relative; bottom: 2vh"
                  >确认充值</el-button>
                </span>
            </el-dialog>

          </el-card>

          <el-card class="UserCard">

            <div class="card-header">
              <el-icon
                  style="margin-top: 5px; margin-right: 0.5vw; display: inline-block; position: relative; top: 3px;"
                  color="black"
                  size="20px"
              >
                <HomeFilled/>
              </el-icon>
              <p
                  style="font-size: 20px; display: inline-block"
              >收货地址</p>
              <el-button
                  :icon="CirclePlus"
                  style="float: right; width: 3vw;"
                  @click="addAddFormVisible = true"
              />
              <el-divider></el-divider>
              <div v-for="i in dataObj.addressData">
                <el-card style="margin-bottom: 2vh;">
                  <div style="float: right; display: inline-block">
                    <el-button :icon="Edit" @click="changeAdd(i); changeAddFormVisible = true" style="border: 0; width: 1vw"></el-button>
                    <el-button :icon="Delete" @click="delAdd(i.id)" style="border: 0; width: 1vw"></el-button>
                  </div>
                  <div style="display: inline-block">
                    <div><p style="width: 6vw; display: inline-block; font-size: 14px">收件人：</p>{{i.consignee}}</div>
                    <div><p style="width: 6vw; display: inline-block; font-size: 14px">收件电话：</p>{{i.consigneePhone}}</div>
                    <div><p style="width: 6vw; display: inline-block; font-size: 14px">收件地址：</p>{{i.address}}</div>
                  </div>
                </el-card>
              </div>

              <el-dialog v-model="addAddFormVisible" title="新增收件地址" style="width: 40vw">
                <el-form :model="addAddForm" :rules="rules" ref="addAddFormRef" style="padding: 1vw">
                  <el-form-item label="收件人" prop="consignee" style="width: 30vw">
                    <el-input v-model="addAddForm.consignee"  placeholder="请输入收件人"/>
                  </el-form-item>
                  <el-form-item label="联系电话" prop="consigneePhone" style="width: 30vw">
                    <el-input v-model="addAddForm.consigneePhone" placeholder="请输入联系电话"/>
                  </el-form-item>
                  <el-form-item label="收件地址" prop="address" style="width: 30vw">
                    <el-input v-model="addAddForm.address" placeholder="请输入收件地址"/>
                  </el-form-item>
                  <span slot="footer" class="dialog-footer">
                    <el-button @click="addAddFormVisible = false" style="float: right">取 消</el-button>
                    <el-button type="primary" @click="addAdd(addAddFormRef)" style="float: right; margin-right: 1vw">确 定</el-button>
                  </span>
                </el-form>
              </el-dialog>



            </div>
          </el-card>

        </el-aside>

        <el-main>
          <el-tabs type="border-card" style="width: 50vw; margin-left: 10vw; margin-top: 5vh; height: 80vh">
            <el-tab-pane label="待支付">
              <el-table
                  :data="dataObj.unpaidData"
                  style="width: 100%"
                  :tree-props="{children: 'order'}"
                  row-key="groupId"
              >
                <el-table-column prop="" label="" width="50"/>
                <el-table-column prop="totalPrice" label="总计金额" width="100" />
                <el-table-column prop="consignee" label="收件人" width="130"/>
                <el-table-column prop="consigneePhone" label="收件电话" width="180"/>
                <el-table-column prop="address" label="收件地址" width="250" />
                <el-table-column prop="buyTime" label="下单时间" width="180" />
                <el-table-column prop="orderId" label="订单编号" width="80"/>
                <el-table-column prop="goodsName" label="商品名称" width="130" />
                <el-table-column prop="unitPrice" label="商品单价" width="100"/>
                <el-table-column prop="number" label="购买数量" width="130" />
                <el-table-column prop="shopName" label="商铺名称" width="130"/>
                <el-table-column fixed="right" label="操作" width="150">
                  <template #default="scope" >
                    <el-button link type="primary" @click="payOrder(scope.$index)">支付</el-button>
                    <el-button link type="primary" @click="cancelOrder(scope.$index)">取消</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-tab-pane>

            <el-tab-pane label="待发货">
              <el-table
                  :data="dataObj.undeliveredData"
                  style="width: 100%"
              >
                <el-table-column prop="orderId" label="订单编号" width="80"/>
                <el-table-column prop="refunding" label="订单状态" width="80"/>
                <el-table-column prop="totalPrice" label="实付金额" width="100" />
                <el-table-column prop="shopName" label="商铺名称" width="130"/>
                <el-table-column prop="goodsName" label="商品名称" width="130" />
                <el-table-column prop="number" label="购买数量" width="130" />
                <el-table-column prop="consignee" label="收件人" width="130"/>
                <el-table-column prop="consigneePhone" label="收件电话" width="180"/>
                <el-table-column prop="address" label="收件地址" width="250" />
                <el-table-column prop="buyTime" label="下单时间" width="180" />
                <el-table-column prop="goodsUnitPrice" label="总计金额" width="180"/>
                <el-table-column fixed="right" label="操作">
                  <template #default="scope">
                    <el-button link type="primary" @click="refundOrder(dataObj.undeliveredData, scope.$index)">退款</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-tab-pane>



            <el-tab-pane label="待收货">
              <el-table
                  :data="dataObj.unreceivedData"
                  style="width: 100%"
              >
                <el-table-column prop="orderId" label="订单编号" width="80"/>
                <el-table-column prop="totalPrice" label="实付金额" width="100" />
                <el-table-column prop="shopName" label="商铺名称" width="130"/>
                <el-table-column prop="goodsName" label="商品名称" width="130" />
                <el-table-column prop="number" label="购买数量" width="130" />
                <el-table-column prop="consignee" label="收件人" width="130"/>
                <el-table-column prop="consigneePhone" label="收件电话" width="180"/>
                <el-table-column prop="address" label="收件地址" width="250" />
                <el-table-column prop="buyTime" label="下单时间" width="180" />
                <el-table-column prop="goodsUnitPrice" label="总计金额" width="180"/>
                <el-table-column fixed="right" label="操作" width="200">
                  <template #default="scope">
                    <el-button link type="primary" @click="recOrder(scope.$index)">确认收货</el-button>
                    <el-button link type="primary" @click="refundOrder(dataObj.unreceivedData, scope.$index)">退货退款</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-tab-pane>


            <el-tab-pane label="已完成">
              <el-table
                  :data="dataObj.receivedData"
                  style="width: 100%"
              >
                <el-table-column prop="orderId" label="订单编号" width="80"/>
                <el-table-column prop="totalPrice" label="实付金额" width="100" />
                <el-table-column prop="shopName" label="商铺名称" width="130"/>
                <el-table-column prop="goodsName" label="商品名称" width="130" />
                <el-table-column prop="number" label="购买数量" width="130" />
                <el-table-column prop="consignee" label="收件人" width="130"/>
                <el-table-column prop="consigneePhone" label="收件电话" width="180"/>
                <el-table-column prop="address" label="收件地址" width="250" />
                <el-table-column prop="buyTime" label="下单时间" width="180" />
                <el-table-column prop="goodsUnitPrice" label="总计金额" width="180"/>
                <el-table-column fixed="right" label="操作">
                  <template #default="scope">
                    <el-button link type="primary" @click="delOrder(dataObj.receivedData, scope.$index)">删除</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-tab-pane>

            <el-tab-pane label="已撤销">
              <el-table
                  :data="dataObj.cancelledData"
                  style="width: 100%"
              >
                <el-table-column prop="orderId" label="订单编号" width="80"/>
                <el-table-column prop="totalPrice" label="实付金额" width="100" />
                <el-table-column prop="shopName" label="商铺名称" width="130"/>
                <el-table-column prop="goodsName" label="商品名称" width="130" />
                <el-table-column prop="number" label="购买数量" width="130" />
                <el-table-column prop="consignee" label="收件人" width="130"/>
                <el-table-column prop="consigneePhone" label="收件电话" width="180"/>
                <el-table-column prop="address" label="收件地址" width="250" />
                <el-table-column prop="buyTime" label="下单时间" width="180" />
                <el-table-column prop="goodsUnitPrice" label="总计金额" width="180"/>
                <el-table-column fixed="right" label="操作">
                  <template #default="scope">
                    <el-button link type="primary" @click="delOrder(dataObj.cancelledData, scope.$index)">删除</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-tab-pane>

            <el-tab-pane label="已退款">
              <el-table
                  :data="dataObj.refundedData"
                  style="width: 100%"
              >
                <el-table-column prop="orderId" label="订单编号" width="80"/>
                <el-table-column prop="totalPrice" label="实付金额" width="100" />
                <el-table-column prop="shopName" label="商铺名称" width="130"/>
                <el-table-column prop="goodsName" label="商品名称" width="130" />
                <el-table-column prop="number" label="购买数量" width="130" />
                <el-table-column prop="consignee" label="收件人" width="130"/>
                <el-table-column prop="consigneePhone" label="收件电话" width="180"/>
                <el-table-column prop="address" label="收件地址" width="250" />
                <el-table-column prop="buyTime" label="下单时间" width="180" />
                <el-table-column prop="goodsUnitPrice" label="总计金额" width="180"/>
                <el-table-column fixed="right" label="操作">
                  <template #default="scope">
                    <el-button link type="primary" @click="delOrder(dataObj.refundedData, scope.$index)">删除</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-tab-pane>
          </el-tabs>

        </el-main>


      </el-container>

      <el-main
          style="height: 94vh; padding: 5vh 8vw"
      >

      </el-main>

    </el-container>
    <el-dialog v-model="changeAddFormVisible" title="修改收件地址" style="width: 40vw">
      <el-form :model="changeAddForm" :rules="rules" ref="changeAddFormRef" style="padding: 1vw">
        <el-form-item label="收件人" prop="consignee" style="width: 30vw">
          <el-input v-model="changeAddForm.consignee"  placeholder="请输入收件人"/>
        </el-form-item>
        <el-form-item label="联系电话" prop="consigneePhone" style="width: 30vw">
          <el-input v-model="changeAddForm.consigneePhone" placeholder="请输入联系电话"/>
        </el-form-item>
        <el-form-item label="收件地址" prop="address" style="width: 30vw">
          <el-input v-model="changeAddForm.address" placeholder="请输入收件地址"/>
        </el-form-item>
        <span slot="footer" class="dialog-footer">
                    <el-button @click="changeAddFormVisible = false" style="float: right">取 消</el-button>
                    <el-button type="primary" @click="postChange(changeAddFormRef)" style="float: right; margin-right: 1vw">确 定</el-button>
                  </span>
      </el-form>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>

import {inject, reactive, ref} from 'vue'
import {useRouter} from 'vue-router'
import service from "../../request/index.js"
import {ElMessage, FormInstance} from "element-plus";
import {Edit, Search, CirclePlus, Delete} from '@element-plus/icons-vue'

const reload = inject('reload')
const getCookie = inject('getCookie')
const userName = getCookie('userName')
const router = useRouter();
const dataObj = reactive({
  tableData: [],
  addressData: [],
  unpaidData: [],
  sortedUnpaidData: [],
  receivedData: [],
  undeliveredData: [],
  unreceivedData: [],
  cancelledData: [],
  refundedData: [],
})

//登出
const logout = inject('logout')
function LogOut() {
  logout();
  router.push('/login')
}

//修改个人信息
const personalInfoFormVisible = ref(false)
const rules = reactive({
  username: [
    {required: true, message: '用户名称不能为空', trigger: 'blur'},
    {min: 3, max: 10, message: '用户名称长度应为3-10个字符', trigger: 'blur'},
    {pattern: /^[A-Za-z0-9\_]+$/, message: '用户名称仅能包含英文字符、数字与下划线', trigger: 'blur'}
  ],
  pastPassword: [
    {required: true, message: '用户密码不能为空', trigger: 'blur'},
    {min: 6, max: 32, message: '用户密码长度应为6-32个字符', trigger: 'blur'},
    {
      pattern: /((?=.*[a-z])(?=.*\d)|(?=[a-z])(?=.*[-\_])|(?=.*\d)(?=.*[-\_]))[a-z\d-\_]/,
      message: '用户密码应至少包含字母、数字或特殊字符（-_）中的两种或以上',
      trigger: 'blur'
    },
  ],
  presentPassword: [
    {required: true, message: '用户密码不能为空', trigger: 'blur'},
    {min: 6, max: 32, message: '用户密码长度应为6-32个字符', trigger: 'blur'},
    {
      pattern: /((?=.*[a-z])(?=.*\d)|(?=[a-z])(?=.*[-\_])|(?=.*\d)(?=.*[-\_]))[a-z\d-\_]/,
      message: '用户密码应至少包含字母、数字或特殊字符（-_）中的两种或以上',
      trigger: 'blur'
    },
  ],
  phone: [
    {required: true, message: '手机号码不能为空', trigger: 'blur'},
    {
      pattern: /^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/,
      message: '手机号码格式错误',
      trigger: 'blur'
    }
  ],
  email: [
    {required: true, message: '电子邮箱不能为空', trigger: 'blur'},
    {pattern: /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/, message: '电子邮箱格式错误', trigger: 'blur'}
  ],
  consignee: [
    {required: true, message: '联系人不能为空', trigger: 'blur'},
  ],
  address: [
    {required: true, message: '收件地址不能为空', trigger: 'blur'},
  ],
  consigneePhone: [
    {required: true, message: '联系电话不能为空', trigger: 'blur'},
    {
      pattern: /^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/,
      message: '联系电话格式错误',
      trigger: 'blur'
    }
  ],
})
//修改用户名
const editUserNameForm = reactive({
  username: '',
})
const editUserNameFormRef = ref<FormInstance>()
const EditUserName = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      service.put('/user/profile/username', {
        username: editUserNameForm.username,
      })
          .then(function (res) {
            if (res.status === 200) {
              ElMessage.success('修改成功！')
              reload()
            }
          })
          .catch(function (error) {
            if (error.response) {
              ElMessage.error('修改失败，' + error.response.data.msg)
            }
          })
    } else {
      return
    }
  })
}
//修改密码
const editPasswordForm = reactive({
  pastPassword: '',
  presentPassword: '',
})
const editPasswordFormRef = ref<FormInstance>()
const EditPassword = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {


      service.put('/user/profile/password', {
        pastPassword: editPasswordForm.pastPassword,
        presentPassword: editPasswordForm.presentPassword,
      })
          .then(function (res) {
            if (res.status === 200) {
              ElMessage.success('密码修改成功，请重新登录！')
              LogOut();
            }
          })
          .catch(function (error) {
            if (error.response) {
              ElMessage.error('修改失败，' + error.response.data.msg)
            }
          })
    } else {
      return
    }
  })
}
//修改手机号
const editPhoneForm = reactive({
  phone: '',
})
const editPhoneFormRef = ref<FormInstance>()
const EditPhone = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      service.put('/user/profile/phone', {
        pastPhone: dataObj.tableData[0].phone,
        presentPhone: editPhoneForm.phone,
      })
          .then(function (res) {
            if (res.status === 200) {
              ElMessage.success('修改成功！')
              reload()
            }
          })
          .catch(function (error) {
            if (error.response) {
              ElMessage.error('修改失败，' + error.response.data.msg)
            }
          })
    } else {
      return
    }
  })
}
//修改电子邮箱
const editEmailForm = reactive({
  email: '',
})
const editEmailFormRef = ref<FormInstance>()
const EditEmail = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      service.put('/user/profile/email', {
        pastEmail: dataObj.tableData[0].email,
        presentEmail: editEmailForm.email,
      })
          .then(function (res) {
            if (res.status === 200) {
              ElMessage.success('修改成功！')
              reload()
            }
          })
          .catch(function (error) {
            if (error.response) {
              ElMessage.error('修改失败，' + error.response.data.msg)
            }
          })
    } else {
      return
    }
  })
}
//充值
const chargeFormVisible = ref(false)
const chargeForm = reactive({
  amount: ''
})
function charge() {
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
//新增收货地址
const addAddFormVisible = ref(false)
const addAddFormRef = ref<FormInstance>()
const addAddForm = reactive({
  deliveryAddressId: '',
  address: '',
  consignee: '',
  consigneePhone: ''
})
const addAdd = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      service.post('/delivery-address/apply', {
        deliveryAddressId: addAddForm.deliveryAddressId,
        address: addAddForm.address,
        consignee: addAddForm.consignee,
        consigneePhone: addAddForm.consigneePhone
      })
          .then(function (res) {
            if (res.status === 200) {
              ElMessage.success("新增收货地址成功！")
              reload()
            }
          })
          .catch(function (error) {
            if (error.response) {
              ElMessage.error("新增收货地址失败，" + error.response.data.msg)
            }
          })
    }
    else {
      return
    }
  })
}
//删除收货地址
function delAdd(id){
  service.post('/delivery-address/cancel', {
    id: id
  })
      .then(function (res) {
        if (res.status === 200) {
          ElMessage.success("删除成功！")
          reload()
        }
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error("删除失败，" + error.response.data.msg)
        }
      })
}
//修改收货地址
let changeAddFormVisible = ref(false)
const changeAddFormRef = ref<FormInstance>()
let changeAddForm = reactive({
  deliveryAddressId: '',
  address: '',
  consignee: '',
  consigneePhone: ''
})
function changeAdd(i){
  changeAddForm.deliveryAddressId = i.id
  changeAddForm.address = i.address
  changeAddForm.consignee = i.consignee
  changeAddForm.consigneePhone = i.consigneePhone
}
const postChange = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      service.post('/delivery-address/change', {
        id: changeAddForm.deliveryAddressId,
        address: changeAddForm.address,
        consignee: changeAddForm.consignee,
        consigneePhone: changeAddForm.consigneePhone
      })
          .then(function (res) {
            if (res.status === 200) {
              ElMessage.success("修改成功！")
              reload()
            }
          })
          .catch(function (error) {
            if (error.response) {
              ElMessage.error("修改失败，" + error.response.data.msg)
            }
          })
    } else {
      return
    }
  })
}
//支付订单
function payOrder(row){
  let group = dataObj.unpaidData[row].groupId
  let t_url = "/create_order?groupId=" + group
  router.push(t_url)
}
//撤销订单
function cancelOrder(row){
  let group = dataObj.unpaidData[row].groupId
  service.post('/order/cancel', {
    group: group
  })
      .then(function (res) {
        if (res.status === 200) {
          ElMessage.success("取消成功！")
          reload()
        }
      })
      .catch(function (error) {
        if (error.response) {
          ElMessage.error("取消失败，" + error.response.data.msg)
        }
      })
}
//删除订单
function delOrder(data, row){
  let orderId = data[row].orderId
  service.post('/order/delete', {
    orderId: orderId,
  })
      .then(function (res) {
        if (res.status === 200) {
          ElMessage.success("删除成功！")
          reload()
        }
      })
}
//退款订单
function refundOrder(data, row){
  let orderId = data[row].orderId
  service.post('/order/refund', {
    orderId: orderId,
  })
      .then(function (res) {
        if (res.status === 200) {
          ElMessage.success("申请退款成功！")
          reload()
        }
      })
}
//确认收货
function recOrder(row){
  let orderId = dataObj.unreceivedData[row].orderId
  service.post('/order/receipt', {
    orderId: orderId,
  })
      .then(function (res) {
        if (res.status === 200) {
          ElMessage.success("确认收货成功！")
          reload()
        }
      })
}

//权限校验
const CheckRole = () => {
  if (getCookie('role') === 'customer' || getCookie('role') === 'seller') {
    console.log("欢迎回来")
  } else {
    ElMessage.error('无权限访问')
    router.push('/login')
  }
}
const created = () => {
  service.get('/user/profile', {})
      .then(function (res) {
        if (res.status === 200) {
          dataObj.tableData[0] = res.data
        }
      })
  service.get('/user/account', {})
      .then(function (res) {
        if (res.status === 200) {
          dataObj.tableData[0].amount = res.data.amount
        }
      })
  service.get('/delivery-address', {})
      .then(function (res) {
        if (res.status === 200) {
          dataObj.addressData = res.data
        }
      })
  //获取待支付订单
  service.get('/order/unpaid', {})
      .then(function (res) {
        if (res.status === 200) {
          dataObj.unpaidData = res.data
          //整理sortedUnpaidData
          for(let i=0; i<dataObj.unpaidData.length; i++){
            let totalPrice = 0
            for(let j=0; j<dataObj.unpaidData[i].order.length; j++){
              totalPrice += dataObj.unpaidData[i].order[j].totalPrice
            }
            dataObj.unpaidData[i].buyTime = dataObj.unpaidData[0].order[0].buyTime
            dataObj.unpaidData[i].address = dataObj.unpaidData[0].order[0].address
            dataObj.unpaidData[i].consignee = dataObj.unpaidData[0].order[0].consignee
            dataObj.unpaidData[i].consigneePhone = dataObj.unpaidData[0].order[0].consigneePhone
            dataObj.unpaidData[i].orderId = dataObj.unpaidData[i].groupId
            dataObj.unpaidData[i].totalPrice = totalPrice
          }
        }
      })
  //获取未发货订单
  service.post('/order/all/customer', {
    state: "undelivered",
  })
      .then(function (res) {
        if (res.status === 200) {
          dataObj.undeliveredData = res.data
          for(let i=0; i<dataObj.undeliveredData.length; i++){
            if(dataObj.undeliveredData[i].refunding===true){
              dataObj.undeliveredData[i].refunding = "退款中"
            }
            else{
              dataObj.undeliveredData[i].refunding = "待发货"
            }
          }
        }
      })
  //获取待收货订单
  service.post('/order/all/customer', {
    state: "unreceived",
  })
      .then(function (res) {
        if (res.status === 200) {
          dataObj.unreceivedData = res.data
        }
      })
  //获取已完成订单
  service.post('/order/all/customer', {
    state: "received",
  })
      .then(function (res) {
        if (res.status === 200) {
          dataObj.receivedData = res.data
        }
      })
  //获取已撤销订单
  service.post('/order/all/customer', {
    state: "cancelled",
  })
      .then(function (res) {
        if (res.status === 200) {
          dataObj.cancelledData = res.data
        }
      })
  //获取已退款订单
  service.post('/order/all/customer', {
    state: "refunded",
  })
      .then(function (res) {
        if (res.status === 200) {
          dataObj.refundedData = res.data
        }
      })
}

function Cart() {
  router.push('/cart');
}

function Personal() {
  router.push('/customer')
}


CheckRole();
created();

</script>

<style scoped>

.UserHeader {
  width: 100vw;
  height: 6vh;
  background-color: white;
  border-bottom: 1px solid gray;
  text-align: right;
}

.UserCard {
  width: 20vw;
  height: auto;
  margin-left: 5vw;
  margin-top: 5vh;
  box-shadow: #606266;
}

.card-content p {
  margin-left: 1vw;
  margin-bottom: 2vh;
  font-size: 17px;

}

.EditDialog {
  width: 50vw;
  padding: 1vw;
}

.ChargeDialog {
  width: 25vw;
  padding: 1vw;
}


</style>