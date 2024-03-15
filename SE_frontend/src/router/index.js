import { createRouter, createWebHistory } from 'vue-router'
import HomeView from "../views/HomeView.vue"
import LoginView from "../views/LoginView.vue";
import RegisterView from "../views/RegisterView.vue";

import RootView from "../views/Root/RootView.vue";
import ApplyList from "../views/Root/ApplyList.vue"
import CloseList from "../views/Root/CloseList.vue"
import GoodsList from "../views/Root/GoodsList.vue"
import GoodsModify from "../views/Root/GoodsModify.vue"
import JoinActivity from "../views/Root/JoinActivity.vue";
import NewActivity from "../views/Root/NewActivity.vue";
import ActivityList from "../views/Root/ActivityList.vue";
import RootAccount from "../views/Root/RootAccount.vue";

import SellerView from "../views/Seller/SellerView.vue"
import ApplyView from "../views/Seller/ApplyView.vue"
import CloseView from "../views/Seller/CloseView.vue"
import SellerAccount from "../views/Seller/SellerAccount.vue"
import ReleaseGoods from "../views/Seller/ReleaseGoods.vue"
import ModifyGoods from "../views/Seller/ModifyGoods.vue"
import GoodsView from "../views/Seller/GoodsView.vue"

import CustomerView from "../views/Customer/CustomerView.vue";
import CartView from "../views/Customer/CartView.vue";
import OrderView from "../views/Customer/OrderView.vue";
import GoodsDetails from "../views/Customer/GoodsDetails.vue";
import SellerOrderView from "@/views/Seller/SellerOrderView.vue";
import SellerActivityList from "@/views/Seller/SellerActivityList.vue";



const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home_page',
      component: HomeView,
      meta:{
        title: 'E-SHOPPING 首页'
      }
    },
    {
      path: '/home',
      name: 'home',
      component: HomeView,
      meta:{
        title: 'E-SHOPPING 首页'
      }
    },
    {
      path: '/',
      name: 'login',
      component: LoginView,
      meta:{
        title: 'E-SHOPPING 登录'
      }
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView,
      meta:{
        title: 'E-SHOPPING 登录'
      }
    },
    {
      path: '/register',
      name: 'register',
      component: RegisterView,
      meta:{
        title: 'E-SHOPPING 注册'
      }
    },
    {
      path: '/root',
      name: 'root',
      component: RootView,
      meta:{
        title: '管理员'
      },
      children:[
        {
          path: '/apply_list',
          name: 'apply_list',
          component: ApplyList,
          meta:{
            title: '开店审核'
          }
        },
        {
          path: '/close_list',
          name: 'close_list',
          component: CloseList,
          meta:{
            title: '关店审核'
          }
        },
        {
          path: '/goods_list',
          name: 'goods_list',
          component: GoodsList,
          meta:{
            title: '商品审核'
          }
        },
        {
          path: '/goods_modify',
          name: 'goods_modify',
          component: GoodsModify,
          meta:{
            title: '商品修改审核'
          }
        },
        {
          path: '/root_account',
          name: 'root_account',
          component: RootAccount,
          meta:{
            title: '管理员账户'
          }
        },
        {
          path: '/activity_list',
          name: 'activity_list',
          component: ActivityList,
          meta:{
            title: '活动管理'
          }
        },
        {
          path: '/apply_activity',
          name: 'apply_activity',
          component: NewActivity,
          meta:{
            title: '活动管理'
          }
        },
        {
          path: '/join_activity',
          name: 'join_activity',
          component: JoinActivity,
          meta:{
            title: '活动管理'
          }
        },
      ]
    },
    {
      path: '/seller',
      name: 'seller',
      component: SellerView,
      meta:{
        title: '商家页面'
      },
      children:[
        {
          path: '/apply',
          name: 'apply',
          component: ApplyView,
          meta:{
            title: '申请开店'
          }
        },
        {
          path: '/close',
          name: 'close',
          component: CloseView,
          meta:{
            title: '申请关店'
          }
        },
        {
          path: '/seller_account',
          name: 'seller_account',
          component: SellerAccount,
          meta:{
            title: '商家账户'
          }
        },
        {
          path: '/release_goods',
          name: 'release_goods',
          component: ReleaseGoods,
          meta:{
            title: '发布商品'
          }
        },
        {
          path: '/modify_goods',
          name: 'modify_goods',
          component: ModifyGoods,
          meta:{
            title: '编辑商品'
          }
        },
        {
          path: '/goods',
          name: 'goods',
          component: GoodsView,
          meta:{
            title: '商品列表'
          }
        },
        {
          path: '/orders',
          name: 'orders',
          component: SellerOrderView,
          meta:{
            title: '商品列表'
          }
        },
        {
          path: '/seller_activity',
          name: 'seller_activity',
          component: SellerActivityList,
          meta:{
            title: '活动列表'
          }
        },
      ]
    },
    {
      path: '/customer',
      name: 'customer',
      component: CustomerView,
      meta:{
        title: '个人中心'
      },
    },
    {
        path: '/cart',
        name: 'cart',
        component: CartView,
        meta:{
          title: '购物车'
        }
    },
    {
      path: '/goods_details',
      name: 'goods_details',
      component: GoodsDetails,
      meta:{
        title: '商品详情页'
      }
    },
    {
      path: '/create_order',
      name: 'create_order',
      component: OrderView,
      meta:{
        title: '下单页'
      }
    },
  ]
})

export default router
