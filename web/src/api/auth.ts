import axios from "axios"
import {Account, Profile, User} from "../types"

interface NameAndPasswordParams {
    name: string,
    password: string
}

export type RegisterParams = NameAndPasswordParams

/**
 * 注册账户
 *
 * @param params 账户名和密码
 */
export function register(params: RegisterParams): Promise<Account> {
    return axios.post("/register", params)
}

export type LoginParams = NameAndPasswordParams

/**
 * 登录账户
 *
 * @param params 账户名和密码
 */
export function login(params: LoginParams): Promise<Account> {
    return axios.post("/login", params)
}

export interface RegisterProfileParams extends User {
    smsAuthToken: string
}

export function getProfile(): Promise<Profile> {
    return axios.get("/self/profile")
}

/**
 * 登记用户信息
 *
 * @param params 用户信息
 */
export function registerProfile(params: RegisterProfileParams): Promise<User> {
    return axios.put("/self/profile", params)
}

/**
 * 发送手机验证码
 *
 * @param phoneNumber 待验证的手机号码
 */
export function sendSmsAuthToken(phoneNumber: string): Promise<void> {
    return axios.get("/sms/v-code", {params: {phoneNumber: phoneNumber}})
}

/**
 * 验证手机验证码
 *
 * @param phoneNumber 接受验证码的手机号码
 * @param smsAuthToken 接收到的手机验证码
 */
export function verifySmsAuthToken(phoneNumber: string, smsAuthToken: string): Promise<boolean> {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            if (smsAuthToken === "123456") {
                resolve(true)
            } else {
                resolve(false)
            }
        }, 1000)
    })
}

export const auth = {
    register,
    login,
    getProfile,
    registerProfile,
    sendSmsAuthToken,
    verifySmsAuthToken,
}