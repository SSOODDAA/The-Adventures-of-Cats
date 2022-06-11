import axios from 'axios'
import router from "@/router";

const request = axios.create({
  // baseURL: "CaveAdventure",
  timeout: 5000
})

const whiteUrls = ["/users/login", '/users/register']
// request 拦截器
request.interceptors.request.use(config => {
  config.headers['Content-Type'] = 'application/json;charset=utf-8';
  // 取出sessionStorage里面缓存的用户信息
  let userJson = sessionStorage.getItem("user")
  if (!whiteUrls.includes(config.url)) {  // 校验请求白名单
    if(!userJson) {
      router.push("/")
    } else {
      let user = JSON.parse(userJson);
      config.headers['token'] = user.token;  // 设置请求头
    }
  }
  return config
}, error => {
  return Promise.reject(error)
});


// response 拦截器,可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
      let res = response.data;
      // 如果是返回的文件
      if (response.config.responseType === 'blob') {
        return res
      }
      // 兼容服务端返回的字符串数据
      if (typeof res === 'string') {
        res = res ? JSON.parse(res) : res
      }
      // 验证token
      if (res.code === '401') {
        console.error("token过期，重新登录")
        router.push("/login")
      }
      return res;
    },
    error => {
      console.log('err' + error) // for debug
      return Promise.reject(error)
    }
)
export default request