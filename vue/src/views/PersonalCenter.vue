<template>
  <div class="personPage">
    <div :style="fixStyle">
<!--      <div style="text-align: center;"><img src="../assets/title.png" width="800"/></div>-->
      <el-form class="personform" ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item class="loginItem">
          <span style="color: skyblue;font-size: large">账号：</span>
          <el-input v-model="form.username" style="font-size: large;width: 78%"></el-input>
        </el-form-item>
        <el-form-item class="loginItem" >
          <span style="color: skyblue;font-size: large">密码：</span>
          <el-input v-model="form.pwd" style="font-size: large;width:78%" show-password></el-input>
        </el-form-item>
        <el-form-item class="loginItem">
          <span style="color: skyblue;font-size: large">最高分：</span>
          <el-input v-model="form.best" disabled style="font-size: large;width: 75%"></el-input>
        </el-form-item>
        <el-form-item class="loginItem">
          <el-button class="btn" type="primary" @click="update">保 存</el-button>
          <el-button class="btn" type="primary" @click="$router.push('/homepage')">返 回</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "PersonalCenter",
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
  created() {
    let str= sessionStorage.getItem("user")||"{}"
    this.form=JSON.parse(str)
  },
  methods:{
    update(){
      this.$refs['form'].validate((valid) => {
        if(valid) {
          request.put("/users",this.form).then(res =>{
            console.log(this.form)
            if(res.state===200){
              this.$message({
                type:"success",
                message:"个人信息更新成功"
              })
              sessionStorage.setItem("user",JSON.stringify(this.form))//缓存用户信息
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
.personPage{
  background: url(../assets/Bg.png) no-repeat;
  background-size: 100% 100%;
}

.personform{
  width: 600px;
  height: 400px;
  background: url(../assets/personform.png) no-repeat;
  background-size: 100% 100%;
  position: absolute;
  left: 50%;
  right: 50%;
  margin-left: -300px;
  margin-top: 100px;
}
.loginItem{
  margin-top: 60px;
  margin-left: -15px;
  width: 600px;
  font-size: large;
}
.btn{
  background:url(../assets/button.png) no-repeat;
  background-size: 100% 100%;
  width: 150px;
  height: 45px;
  border-style: none;
  margin-left: 80px;
  margin-top: -20px;
}
.btn:hover{
  color: #fff;
  border-radius: 5px;
  border-style: none;
  box-shadow: 0 0 10px steelblue,0 0 25px steelblue,0 0 50px steelblue,0 0 100px steelblue;
}
</style>