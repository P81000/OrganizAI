const { defineConfig } = require("@vue/cli-service");

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 3000,
  }
});
module.exports = {
  devServer: {
    port: 9090,
  }
}
