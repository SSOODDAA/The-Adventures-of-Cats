import {createRouter, createWebHistory} from 'vue-router'
import UserLogin from "@/views/UserLogin";
import UserRegister from "@/views/UserRegister";
import HomePage from "@/views/HomePage";
import PersonalCenter from "@/views/PersonalCenter";
import ChooseRole from "@/views/ChooseRole";
import MainGame from "@/views/MainGame";
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
  },
  {
    path: '/maingame',
    name: 'MainGame',
    component: MainGame,
  }
]
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router