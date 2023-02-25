<script setup lang="ts">
import zhCn from "element-plus/lib/locale/lang/zh-cn"
import en from "element-plus/lib/locale/lang/en"
import {Sunny, Moon} from "@element-plus/icons-vue"
import "element-plus/theme-chalk/dark/css-vars.css"

const isDark = useDark()
const toggleDark = useToggle(isDark)

const language = ref<string>("zh")
const locale = computed(() => {
  return language.value === "zh" ? zhCn : en
})
</script>

<template>
  <el-config-provider :locale="locale">
    <div class="w-screen h-screen">
      <div class="fixed top-4 right-4">
        <el-switch
            :model-value="isDark"
            :inline-prompt="true"
            :active-icon="Moon"
            :inactive-icon="Sunny"
            @change="toggleDark()"
        />
      </div>
      <div class="container mx-auto">
        <router-view/>
      </div>
    </div>
  </el-config-provider>
</template>
