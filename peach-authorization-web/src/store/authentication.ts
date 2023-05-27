import {Account, Profile} from "../types"
import {api} from "../api"

export type AuthStatus = "Unknown" | "Authenticated" | "UnAuthenticated"
export const useAuth = defineStore("auth", () => {
    const profile = ref<Account | null>()
    const authenticated = ref<AuthStatus>("Unknown")

    let interval: number | undefined

    const setAuthenticated = async (status: AuthStatus): Promise<Account | null> => {
        if (status === "Authenticated") {
            console.debug("正在检查登录状态")
            profile.value = await api.auth.getProfile()
            console.debug("检查到用户已登录")
            authenticated.value = "Authenticated"

            interval = setInterval(() => {
                api.auth.getProfile()
                    .then(res => {
                        profile.value = res
                        authenticated.value = "Authenticated"
                        console.debug("当前状态为已登录")
                    })
                    .catch(() => {
                        profile.value = null
                        authenticated.value = "UnAuthenticated"
                        console.error("检查用户登录状态失败")
                    })
            }, 60000)
            return profile.value
        }

        profile.value = null
        authenticated.value = "UnAuthenticated"
        clearInterval(interval)
        return profile.value
    }

    return {
        profile,
        authenticated,
        setAuthenticated,
    }
})