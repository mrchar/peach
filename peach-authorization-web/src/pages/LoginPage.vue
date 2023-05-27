<script lang="ts" setup>
import {onMounted, ref} from "vue"
import {api, LoginParams} from "../api"
import {useRoute, useRouter} from "vue-router"
import "element-plus/theme-chalk/index.css"
import {useAuth} from "../store/authentication"

const store = useAuth()

const router = useRouter()
const route = useRoute()

const loginParams = ref<LoginParams>({
  accountName: "",
  password: "",
})

// 点击登录按钮时调用
const onSubmit = () => {
  api.auth.login(loginParams.value)
      .then((res) => {
        store.setAuthenticated("Authenticated")
            .then(() => {
              if (!res.user || !res.user.name) {
                router.push({path: "/profile/register"})
              } else {
                router.push({path: "/profile", query: res as any})
              }
            })
      })
}

const onClickToRegister = () => {
  if (loginParams.value.accountName) {
    router.push({path: "/register", query: {name: loginParams.value.accountName}})
    return
  } else {
    router.push({path: "/register"})
  }
}

const nameInput = ref()
const passwordInput = ref()

onMounted(() => {
  nameInput.value.focus()

  const query = route.query
  if (query && query.name) {
    loginParams.value.accountName = query.name as string
    passwordInput.value.focus()
  }
})
</script>

<template>
  <el-form label-width="100px">
    <el-form-item label="账户">
      <el-input
          ref="nameInput"
          v-model="loginParams.accountName"
          @keyup.enter="passwordInput.focus()"
      />
    </el-form-item>
    <el-form-item label="密码">
      <el-input
          ref="passwordInput"
          v-model="loginParams.password"
          type="password"
          show-password
          @keyup.enter="onSubmit"
      />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">登录</el-button>
      <el-button @click="onClickToRegister">注册</el-button>
    </el-form-item>
  </el-form>
</template>


<style scoped>

</style>