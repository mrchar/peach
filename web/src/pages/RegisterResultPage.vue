<script lang="ts" setup>
import {useRoute, useRouter} from "vue-router"

const router = useRouter()
const route = useRoute()
</script>

<template>
  <el-result
      icon="success"
      title="注册成功"
      sub-title="点击下方按钮，跳转到登录页！"
  >
    <template #extra>
      <el-button
          type="primary"
          @click="router.push({path:'/login', query:route.query})"
      >
        下一步
      </el-button>
    </template>
  </el-result>
</template>