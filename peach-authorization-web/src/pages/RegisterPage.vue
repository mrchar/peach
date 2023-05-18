<script lang="ts" setup>
import {onMounted, ref} from "vue"
import {api, LoginParams} from "../api"
import {useRoute, useRouter} from "vue-router"

const router = useRouter()
const route = useRoute()

const registerParams = ref<LoginParams>({
  name: "",
  password: "",
})


const onClickRegister = () => {
  api.auth.register(registerParams.value)
      .then((res) => {
        router.push({path: "/register/success", query: res as any})
      })
}

const nameInput = ref()
const passwordInput = ref()

onMounted(() => {
  nameInput.value.focus()
  const query = route.query
  if (query && query.name) {
    registerParams.value.name = query.name as string
    passwordInput.value.focus()
  }
})
</script>

<template>
  <el-form label-width="100px">
    <el-form-item label="账户名">
      <el-input
          ref="nameInput"
          v-model="registerParams.name"
          @keyup.enter="passwordInput.focus()"
      ></el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input
          ref="passwordInput"
          v-model="registerParams.password"
          type="password"
          show-password
          @keyup.enter="onClickRegister"
      ></el-input>
    </el-form-item>
    <el-form-item>
      <el-alert>
        <span>已经有账户了？现在就去</span>
        <router-link to="/login">登录</router-link>
        <span>！</span>
      </el-alert>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onClickRegister">注册</el-button>
      <el-button @click="router.back()">取消</el-button>
    </el-form-item>
  </el-form>
</template>