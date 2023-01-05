<script setup lang="ts">
import {computed, ref} from "vue"
import zhCn from "element-plus/lib/locale/lang/zh-cn"
import en from "element-plus/lib/locale/lang/en"

const language = ref<string>("zh")
const locale = computed(() => {
  return language.value === "zh" ? zhCn : en
})
</script>

<template>
  <el-config-provider :locale="locale">
    <router-view/>
  </el-config-provider>
</template>
