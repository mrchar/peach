import {Profile} from "../types"
import {api} from "../api"

export type AuthStatus = "Unknown" | "Authenticated" | "Unauthenticated"
export const useAuth = defineStore("auth", () => {
    const profile = ref<Profile | null>()
    const authenticated = ref<AuthStatus>("Unknown")

    let interval: number | undefined

    const setAuthenticated = async (status: AuthStatus) => {
        if (status === "Authenticated") {
            profile.value = await api.auth.getProfile()
            authenticated.value = "Authenticated"

            interval = setInterval(() => {
                api.auth.getProfile()
                    .then(res => {
                        profile.value = res
                        authenticated.value = "Authenticated"
                    })
                    .catch(() => {
                        profile.value = null
                        authenticated.value = "Unauthenticated"
                    })
            }, 60000)
        }

        profile.value = null
        authenticated.value = "Unauthenticated"
        clearInterval(interval)
    }

    return {
        profile,
        authenticated,
        setAuthenticated,
    }
})