import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    host: '127.0.0.1',//本机地址
    port: '5173',//前端端口
    //反向代理，跨域
    proxy: {
      '/api': {
        target: 'http://localhost:8443/', // 后端部署地址
        changeOrigin: true,// 跨域
        pathRewrite:{
          '^/api': ''
        }
      }
    }
  }
})
