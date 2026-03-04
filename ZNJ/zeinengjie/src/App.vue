<template>
  <div id="app">
    <router-view></router-view>  <!-- 这里会动态切换页面 -->
  </div>
</template>

<script>
export default {
  name: 'App',
};
</script>

<style>
body > div[style*="z-index"][style*="ResizeObserver"] {
  display: none !important;
}
</style>
