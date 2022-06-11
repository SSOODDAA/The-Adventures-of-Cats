<template>
  <div class="loginPage">
    <div :style="fixStyle">
      <div style="text-align: center; padding: 10px;"><img src="../assets/title.png" width="700"/></div>
      <div class="loginBox"> <!--控制表单样式 -->
        <div class="left"></div>
        <div class="right">
          <el-form ref="form" :model="form" size="default" :rules="rules">
            <el-form-item class="loginItem" prop="username">
              <el-input prefix-icon="el-icon-user" v-model="form.username" placeholder="请输入账号"></el-input>
            </el-form-item>
            <el-form-item class="loginItem" prop="password">
              <el-input prefix-icon="el-icon-lock" v-model="form.password" show-password placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item class="loginItem">
              <el-button class="loginbtn" style="display:block;margin:0 auto" type="primary" id="btn" @click="login">登 录</el-button>
              <el-button type="text" @click="$router.push('/register')">前往注册 >> </el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
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
        password:[
          {required:true,message:'请输入密码',trigger:'blur'},
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
  width: 600px;
  height: 400px;
  margin: 0px auto;
  background: url(../assets/loginform.png) no-repeat;
  background-size: 100% 100%;
  position: absolute;
  left: 50%;
  right: 50%;
  margin-left: -300px;
  margin-right: -200px;
  display: flex;

}
.left{
  width:170px;
  height: 400px;
  background: white;
  opacity: 0%;
}
.right{
  width: 430px;
  display: flex;
  flex-direction: column;
  align-content: center;
}
.loginItem{
  width: 280px;
  height: 20px;
  padding: 10px;
  margin: 80px 0px 30px 0px;
}
.loginbtn{
  background:url(../assets/button.png) no-repeat;
  background-size: 100% 100%;
  width: 150px;
  height: 40px;
  border-style: none;
}
.loginbtn:hover{
  color: #fff;
  border-radius: 5px;
  border-style: none;
  box-shadow: 0 0 10px steelblue,0 0 25px steelblue,0 0 50px steelblue,0 0 100px steelblue;
}
</style>