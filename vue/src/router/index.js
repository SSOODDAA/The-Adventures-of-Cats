import {createRouter, createWebHistory} from 'vue-router'
import UserLogin from "@/views/UserLogin";
import HomePage from "@/views/HomePage";
import UserRegister from "@/views/UserRegister";
import PersonalCenter from "@/views/PersonalCenter";
const routes = [
  {
    path: '/',
    name: 'Login',
    redirect: "/login",
    component: UserLogin
  },
  {
    path: '/login',
    name: 'UserLogin',
    component: UserLogin,
  },
  {
    path: '/home',
    name: 'HomePage',
    component: HomePage,
  },
  {
    path: '/register',
    name: 'UserRegister',
    component: UserRegister,
  },
  {
    path: '/person',
    name: 'PersonalCenter',
    component: PersonalCenter,
  },
]
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router