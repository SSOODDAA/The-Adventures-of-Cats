import {createRouter, createWebHistory} from 'vue-router'
import UserLogin from "@/views/UserLogin";
import MainGame from "@/views/MainGame";
import UserRegister from "@/views/UserRegister";
const routes = [
  {
    path: '/',
    name: 'UserLogin',
    component: UserLogin,
  },
  // {
  //   path: '/login',
  //   name: 'UserLogin',
  //   component: UserLogin,
  // },
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
]
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router