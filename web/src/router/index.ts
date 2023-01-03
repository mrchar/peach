import {RouteRecordRaw, createRouter, createWebHashHistory} from "vue-router"
import LoginPage from "../pages/LoginPage.vue"
import LoginLayout from "../layout/LoginLayout.vue"
import RegisterPage from "../pages/RegisterPage.vue"
import ProfilePage from "../pages/ProfilePage.vue"
import RegisterResultPage from "../pages/RegisterResultPage.vue"

const routes: Array<RouteRecordRaw> = [
    {path: "/", redirect: "/login"},
    {
        path: "/",
        component: LoginLayout,
        children: [
            {path: "/login", component: LoginPage},
            {path: "/register", component: RegisterPage},
            {path: "/register/success", component: RegisterResultPage},
        ],
    },
    {path: "/profile", component: ProfilePage},
]

export default createRouter({
    routes,
    history: createWebHashHistory(),
})