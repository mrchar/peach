<script lang="ts" setup>
import ProfileForm from "./ProfileForm.vue"
import {useRouter} from "vue-router"

const router = useRouter()

function onSuccess() {
  router.push({path: "/profile"})
}
</script>
<template>
  <el-card header="用户信息">
    <profile-form class="ml-2 mr-8" @success="onSuccess"/>
  </el-card>
</template>

<style scoped>
</style>