<template>
<div class = "HomePage">
  <div :style="fixStyle">
    <div style="text-align: center;"><img src="../assets/title.png" width="800"/></div>
      <div class = "center">
        <input type = "button" value="新建游戏" @click="newgame"/>
        <input type = "button" value="继续游戏" @click="congame"/>
        <input type = "button" value="个人中心" @click="percen"/>
        <input type = "button" value="退出游戏" @click="login"/>
      </div>
    </div>
</div>
</template>

<script>
export default {
  name: "HomePage",
    methods:{
        newgame(){
            this.$router.push('/chooserole')
        },
        congame(){
            this.$router.push('/maingame')
        },
        percen(){
            this.$router.push('/person')
        },
        login(){
            this.$router.push('/login')
        }

    }
}

</script>

<style scoped = "less">
*{
  /*初始化 清除页面元素的内外边距*/
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
.HomePage{
  /*100%窗口高度*/
  min-height: 100vh;
  /*背景放置图片*/
  background: url(../assets/Bg.png) no-repeat;
  background-size: 100% 100%;
  /*弹性布局：居中*/
  display: flex;
  justify-content: center;
  align-items: center;
  /*溢出隐藏*/
  overflow: hidden;
  background-size: cover;
 /* background-size: 100% 100%;*/
  background-color: #CCCCCC;
  background-attachment: fixed;

}
.center{
  /*相对定位*/
  position: relative;
  z-index:1;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
}
input[type = "button"]{
  background:url(../assets/button.png) no-repeat;
  background-size: 100% 100%;
  width: 150px;
  height: 45px;
  border-style: none;
  margin-left: 160px;
  padding-left: 0em;
  font-size: large;
  color:#FFFFFF;
  margin-bottom: 1.2em;
  margin-left: 1em;
  margin-right: 1em;
}

input[type = "button"]:hover{
    color: #fff;
    border-radius: 5px;
    border-style: none;
    box-shadow: 0 0 10px steelblue,0 0 25px steelblue,0 0 50px steelblue,0 0 100px steelblue;
}
</style>