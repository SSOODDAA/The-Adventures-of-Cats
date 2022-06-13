<template>
  <div class="loginPage">
    <div :style="fixStyle">
      <div style="text-align: center;"><img src="../assets/title.png" width="800"/></div>
      <div class="loginBox"> <!--控制表单样式 -->
        <div class="left"></div>
        <div class="right">
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
  mounted() {
    window.onresize = () => {
      const windowWidth = document.body.clientWidth
      const windowHeight = document.body.clientHeight
      const windowAspectRatio = windowHeight / windowWidth
      let videoWidth
      let videoHeight
      if (windowAspectRatio < 0.5625) {
        videoWidth = windowWidth
        videoHeight = videoWidth * 0.5625
        this.fixStyle = {
          height: windowWidth * 0.5625 + 'px',
          width: windowWidth + 'px',
          'margin-bottom': (windowHeight - videoHeight) / 2 + 'px',
          'margin-left': 'initial'
        }
      } else {
        videoHeight = windowHeight
        videoWidth = videoHeight / 0.5625
        this.fixStyle = {
          height: windowHeight + 'px',
          width: windowHeight / 0.5625 + 'px',
          'margin-left': (windowWidth - videoWidth) / 2 + 'px',
          'margin-bottom': 'initial'
        }
      }
    }
    window.onresize()
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
*{
  /*初始化 清除页面元素的内外边距*/
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
.loginPage{
  background: url(../assets/Bg.png) no-repeat;
  background-size: 100% 100%;
}
.loginBox{
  width: 800px;
  height: 500px;
  margin: 0px auto;
  background: url(../assets/loginform.png) no-repeat;
  background-size: 100% 100%;
  position: absolute;
  left: 50%;
  right: 50%;
  margin-left: -400px;
  margin-right: -200px;
  display: flex;
}
.left{
  width:125px;
  height: 400px;
  background: white;
  opacity: 0%;
}
.right{
  width: 550px;
  display: flex;
  flex-direction: column;
  align-content: center;
  margin-top: 120px;
}
.loginItem{
  padding: 10px;
}
.loginbtn{
  background:url(../assets/button.png) no-repeat;
  background-size: 100% 100%;
  width: 150px;
  height: 45px;
  border-style: none;
  margin-left: 160px;
  /*margin-right: 50px;*/
}
.loginbtn:hover{
  color: #fff;
  border-radius: 5px;
  border-style: none;
  box-shadow: 0 0 10px steelblue,0 0 25px steelblue,0 0 50px steelblue,0 0 100px steelblue;
}
</style>