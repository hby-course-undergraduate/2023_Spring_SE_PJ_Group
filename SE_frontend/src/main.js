import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import VueAxios from 'vue-axios'
import axios from 'axios'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import './assets/main.css'

const app = createApp(App)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.use(router)
app.use(ElementPlus)
app.use(VueAxios, axios)
app.mount('#app')
app.config.globalProperties.$axios = axios;
// 将axios挂载到原型对象上
app.config.globalProperties.$axios = axios;
// 请求时带上cookie
axios.defaults.withCredentials = true;

//路由变化时，修改标签页名称
router.beforeEach((to, from, next) => {
    if (to.meta.title) {
        document.title = to.meta.title
    }
    next()
})
