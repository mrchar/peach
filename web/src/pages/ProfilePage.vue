<script lang="ts" setup>
import {onMounted, ref} from "vue"
import {useRoute} from "vue-router"
import {Account} from "../api"

const route = useRoute()
const account = ref<Account>({number: "", name: "", user: null})

onMounted(() => {
  const query = route.query
  if (query && query.number && query.name) {
    account.value = {
      number: query.number as string,
      name: query.name as string,
      user: null,
    }
  }
})
</script>

<template>
  <el-descriptions>
    <el-descriptions-item label="账号">
      <span>{{ account.number }}</span>
    </el-descriptions-item>
    <el-descriptions-item label="账户名">
      <span>{{ account.name }}</span>
    </el-descriptions-item>
  </el-descriptions>
</template>