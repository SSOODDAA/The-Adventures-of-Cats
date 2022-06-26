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
            <el-input prefix-icon="Lock" style="font-size: large;" v-model="form.pwd" show-password placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item class="loginItem" prop="confirm">
            <el-input prefix-icon="Lock" style="font-size: large;" v-model="form.confirm" show-password placeholder="请确认密码"></el-input>
          </el-form-item>
          <el-form-item class="loginItem">
            <el-button class="loginbtn" style="font-size: large;" type="primary" id="btn" @click="register">注 册</el-button>
            <el-button type="text" style="font-size: large" @click="$router.push('/login')">返回登录 >> </el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "UserRegister",
  data() {
    return {
      fixStyle:'',
      form:{},
      rules:{
        username:[
          {required:true,message:'请输入用户名',trigger:'blur'},
          {min:3,max:8,message: '长度在3-8之间',tigger:'blur'},
        ],
        pwd:[
          {required:true,message:'请输入密码',trigger:'blur'},
          {min:3,max:8,message: '长度在3-8之间',tigger:'blur'},
        ],
        confirm: [
          {required: true, message: '请确认密码', trigger: 'blur'},
          {min:3,max:8,message: '长度在3-8之间',tigger:'blur'},
        ],
      },
    }
  },
  methods:{
    register(){
      if(this.form.pwd !==this.form.confirm){
        this.$message({
          type: "error",
          message: '2次密码输入不一致！'
        })
        return
      }
      this.$refs['form'].validate((valid) => {
        if(valid) {
          request.post("/users",this.form).then(res =>{
            if(res.state===200){
              this.$message({
                type:"success",
                message:"注册成功"
              })
              //缓存成功更新当前路由,进入登录界面
              this.$router.push("/login")
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
  display: flex;
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
  padding: 5px;
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