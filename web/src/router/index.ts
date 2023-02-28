import {createRouter, createWebHistory, RouteRecordRaw} from "vue-router"
import LoginLayout from "../layout/LoginLayout.vue"
import {useAuth} from "../store/authentication"

const routes: Array<RouteRecordRaw> = [
    {path: "/", redirect: "/profile"},
    {
        path: "/",
        component: LoginLayout,
        children: [
            {path: "/login", component: () => import("../pages/LoginPage.vue")},
            {path: "/register", component: () => import("../pages/RegisterPage.vue")},
            {path: "/register/success", component: () => import("../pages/RegisterResultPage.vue")},
            {path: "/profile/register", component: () => import("../pages/profile/ProfileRegisterPage.vue")},
        ],
    },
    {path: "/profile", component: () => import("../pages/ProfilePage.vue")},
]

export const router = createRouter({
    routes,
    history: createWebHistory(),
})

const doesNotNeedAuthenticated: string[] = ["/register", "/login", "/register/success"]

router.beforeEach((to, from) => {
    const store = useAuth()

    const needAuthenticated = !doesNotNeedAuthenticated.includes(to.path)
    const authenticated = store.authenticated === "Authenticated"

    if (needAuthenticated && !authenticated) {
        return {path: "/login"}
    }

    // 如果访问登录页，但已经登录，则跳转到目标页
    if (!needAuthenticated && authenticated) {
        if (to.query && to.query.referer) {
            return {path: to.query.referer as string}
        }
        return {path: "/"}
    }
})

export default router