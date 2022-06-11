import {createRouter, createWebHistory} from 'vue-router'
import UserLogin from "@/views/UserLogin";
const routes = [
  {
    path: '/',
    name: 'UserLogin',
    component: UserLogin,
  }
]
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router