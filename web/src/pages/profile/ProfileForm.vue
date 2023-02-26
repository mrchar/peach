<script lang="ts" setup>
import {api, RegisterProfileParams} from "../../api"
import {ElMessage} from "element-plus"
import {Loading, SuccessFilled, WarningFilled} from "@element-plus/icons-vue"
import "element-plus/theme-chalk/index.css"

const emits = defineEmits(["success"])

const params = ref<RegisterProfileParams>({
  name: "",
  gender: "",
  birthday: "",
  phoneNumber: "",
  smsAuthToken: "",
  emailAddress: "",
})

const phonePrefix = "+86"

const phoneNumber = computed<string>({
  get: (): string => {
    return params.value.phoneNumber.substring(phonePrefix.length)
  },
  set: (value) => {
    params.value.phoneNumber = phonePrefix + value
  },
})

function onSubmit() {
  api.auth.registerProfile(params.value)
      .then(() => {
        ElMessage.success("登记成功")
        emits("success")
      })
      .catch(err => {
        ElMessage.error(err.message)
      })
}

function onClickSendButton() {
  api.auth.sendSmsAuthToken(params.value.phoneNumber)
      .then(() => {
        ElMessage.success("发送成功")
      })
}

const smsAuthTokenSuffix = ref<string>("none")

function onSmsAuthTokenChange(token: string) {
  if (token.length === 6) {
    smsAuthTokenSuffix.value = "loading"
    api.auth.verifySmsAuthToken(params.value.phoneNumber, params.value.smsAuthToken)
        .then((res) => {
          if (res) {
            smsAuthTokenSuffix.value = "success"
          } else {
            smsAuthTokenSuffix.value = "invalid"
          }
        })
        .finally(() => {
          if (smsAuthTokenSuffix.value === "loading") {
            smsAuthTokenSuffix.value = "none"
          }
        })
  }
}
</script>

<template>
  <el-form label-width="80px">
    <el-form-item label="用户名">
      <el-input v-model="params.name"/>
    </el-form-item>
    <el-form-item label="性别">
      <el-radio-group v-model="params.gender">
        <el-radio label="male">男</el-radio>
        <el-radio label="female">女</el-radio>
        <el-radio label="">隐私</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="生日">
      <el-date-picker id="profile_register_form_date_picker" v-model="params.birthday"/>
    </el-form-item>
    <el-form-item label="手机号码">
      <el-input v-model="phoneNumber">
        <template #prefix>
          <span>{{ phonePrefix }}</span>
        </template>
      </el-input>
    </el-form-item>
    <el-form-item label="验证码">
      <div class="w-full flex gap-2">
        <el-input v-model="params.smsAuthToken" @change="onSmsAuthTokenChange">
          <template v-if="smsAuthTokenSuffix!=='none'" #suffix>
            <el-icon>
              <SuccessFilled v-if="smsAuthTokenSuffix==='success'" color="green"/>
              <WarningFilled v-if="smsAuthTokenSuffix==='invalid'" color="red"/>
              <Loading v-if="smsAuthTokenSuffix==='loading'" class="loading"/>
            </el-icon>
          </template>
        </el-input>
        <el-button @click="onClickSendButton">发送验证码</el-button>
      </div>
    </el-form-item>
    <el-form-item label="电子邮件">
      <el-input v-model="params.emailAddress"/>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">保存</el-button>
    </el-form-item>
  </el-form>
</template>


<style scoped>
.loading {
  animation: circle infinite 2s linear;
}

@keyframes circle {
  0% {
    transform: rotate(0);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>