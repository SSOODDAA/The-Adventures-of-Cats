import {createRouter, createWebHistory} from 'vue-router'
import UserLogin from "@/views/UserLogin";
import MainGame from "@/views/MainGame";
const routes = [
  {
    path: '/',
    name: 'UserLogin',
    component: UserLogin,
  },
  {
    path: '/maingame',
    name: 'MainGame',
    component: MainGame,
  },
]
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router