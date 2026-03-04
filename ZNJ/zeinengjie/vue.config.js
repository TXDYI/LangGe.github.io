const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    host: '0.0.0.0', // 允许外部访问
    port: 3000, // 运行端口
    open: false, // 启动后自动打开浏览器
    client: {
      overlay: {
        warnings: false,
        errors: true
      }
    }
  }
})
