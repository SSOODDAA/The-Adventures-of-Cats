<template>
  <div class="loginPage">
    <div :style="fixStyle">
      <div style="text-align: center;"><img src="../assets/title.png" width="800"/></div>
      <div class="loginBox"> <!--控制表单样式 -->
        <el-form ref="form" size="large" :model="form" :rules="rules">
          <el-form-item class="loginItem" prop="username">
            <el-input prefix-icon="Avatar" style="font-size: large" v-model="form.username" placeholder="请输入账号"></el-input>
          </el-form-item>
          <el-form-item class="loginItem" prop="pwd">
            <el-input prefix-icon="Lock" style="font-size: large" v-model="form.pwd" show-password placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item class="loginItem">
            <el-button class="loginbtn" style="font-size: large;" type="primary" id="btn" @click="login">登 录</el-button>
            <el-button type="text" style="font-size: large" @click="$router.push('/register')">前往注册 >> </el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "UserLogin",
  data() {
    return {
      fixStyle:'',
      form:{},
      rules:{
        username:[
          {required:true,message:'请输入用户名',trigger:'blur'},
        ],
        pwd:[
          {required:true,message:'请输入密码',trigger:'blur'},
        ],
      },
    }
  },
  methods:{
    login(){
      sessionStorage.clear();
      this.$refs['form'].validate((valid) => {
        if(valid) {
          request.get("/users",{
            params:{
              username:this.form.username,
              pwd:this.form.pwd,
            }
          }).then(res =>{
            if(res.state===200){
              this.$message({
                type:"success",
                message:"登录成功"
              })
              sessionStorage.setItem("user",JSON.stringify(res.data))//缓存用户信息
              //登录成功更新当前路由,进入主界面
              this.$router.push("/homepage")
            }else{
              this.$message({
                type:"error",
                message:res.message
              })
            }
          })
        }
          })
    }
  }
}
</script>


<style scoped>
.loginPage{
  box-sizing: border-box;
  background: url(../assets/Bg.png) no-repeat;
  background-size: 100% 100%;
  min-height: 200vh;
  background-attachment: fixed;
}
.loginBox{
  width: 700px;
  height: 450px;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  margin: 0px auto;
  background: url(../assets/loginform.png) no-repeat;
  background-size: 100% 100%;
}
.loginItem{
  padding: 15px;
}
.loginbtn{
  background:url(../assets/button.png) no-repeat;
  background-size: 100% 100%;
  width: 150px;
  height: 45px;
  border-style: none;
  margin-left: 60px;
  margin-right: 60px;
}
.loginbtn:hover{
  color: #fff;
  border-radius: 5px;
  border-style: none;
  box-shadow: 0 0 10px steelblue,0 0 25px steelblue,0 0 50px steelblue,0 0 100px steelblue;
}
</style>