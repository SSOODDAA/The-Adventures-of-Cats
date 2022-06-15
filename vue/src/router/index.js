import {createRouter, createWebHistory} from 'vue-router'
import UserLogin from "@/views/UserLogin";
import MainGame from "@/views/MainGame";
import UserRegister from "@/views/UserRegister";
import HomePage from "@/views/HomePage";
import ChooseRole from "@/views/ChooseRole";
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
    path: '/maingame',
    name: 'MainGame',
    component: MainGame,
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
  {
    path: '/homepage',
    name: 'HomePage',
    component: HomePage,
  },
  {
    path: '/chooserole',
    name: 'ChooseRole',
    component: ChooseRole,
  }
]
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router