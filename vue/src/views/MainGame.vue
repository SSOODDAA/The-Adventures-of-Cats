<template>
<div class="GamePage">
  <div class="top">
    <div class="avatar">
      <img src="../assets/chooserole/cat1.png" width="50px" height="60px"/>
    </div>
    <div class="status">
      <div class="item">
        <img id="life" src="../assets/maingame/Life.png"/>
      </div>
      <div class="item">
        <img id="weight" src="../assets/maingame/Weight.png"/>
      </div>
      <div class="item" id="score">
        <span id="scorevalue">1023456</span>
      </div>
    </div>
    <div class="time">
      <div id="blank"></div>
      <img id="LeftTime" src="../assets/maingame/LeftTime.png"/>
    </div>
  </div>
  <div class="bottom">
    <div class="left">
      <div class="goods"></div>
      <img class="goods" src="../assets/maingame/headbg.png"/>
      <img class="goods" src="../assets/maingame/headbg.png"/>
      <img class="goods" src="../assets/maingame/headbg.png"/>
      <img class="goods" src="../assets/maingame/headbg.png"/>
      <img class="goods" src="../assets/maingame/headbg.png"/>
      <img class="goods" src="../assets/maingame/headbg.png"/>
      <img class="goods" src="../assets/maingame/headbg.png"/>
      <img class="goods" src="../assets/maingame/headbg.png"/>
      <el-button class="btn"></el-button>
    </div>
    <div class="center">
      <div id="MapBorder">
       <div id="MapArea">
         <img class="MapCell" src="../assets/maingame/headbg.png"/>
         <img class="MapCell" src="../assets/maingame/headbg.png"/>
         <img class="MapCell" src="../assets/maingame/headbg.png"/>
         <img class="MapCell" src="../assets/maingame/Stone.png"/>
         <img class="MapCell" src="../assets/maingame/headbg.png"/>
         <img class="MapCell" src="../assets/maingame/headbg.png"/>
         <img class="MapCell" src="../assets/maingame/headbg.png"/>
         <img class="MapCell" src="../assets/maingame/Stone.png"/>
         <img class="MapCell" src="../assets/maingame/headbg.png"/>
         <img class="MapCell" src="../assets/maingame/headbg.png"/>
         <img class="MapCell" src="../assets/maingame/Stone.png"/>
         <img class="MapCell" src="../assets/maingame/headbg.png"/>
         <img class="MapCell" src="../assets/maingame/headbg.png"/>
         <img class="MapCell" src="../assets/maingame/headbg.png"/>
         <img class="MapCell" src="../assets/maingame/headbg.png"/>
         <img class="MapCell" src="../assets/maingame/headbg.png"/>
         <img class="MapCell" src="../assets/maingame/headbg.png"/>
         <img class="MapCell" src="../assets/maingame/headbg.png"/>
         <img class="MapCell" src="../assets/maingame/Stone.png"/>
         <img class="MapCell" src="../assets/maingame/headbg.png"/>
         <img class="MapCell" src="../assets/maingame/headbg.png"/>
         <img class="MapCell" src="../assets/maingame/headbg.png"/>
         <img class="MapCell" src="../assets/maingame/headbg.png"/>
         <img class="MapCell" src="../assets/maingame/headbg.png"/>
         <img class="MapCell" src="../assets/maingame/headbg.png"/>
       </div>
      </div>
    </div>
    <div class="right">
      <div class="OrderBtnArea">
        <el-button class="OrderBtn">look</el-button>
        <el-button class="OrderBtn">take</el-button>
        <el-button class="OrderBtn">drop</el-button>
        <el-button class="OrderBtn">use</el-button>
      </div>
      <div class="ControlBtnArea">
        <el-button class="ControlBtn" id="UpBtn"></el-button>
        <el-button class="ControlBtn" id="LeftBtn"></el-button>
        <el-button class="ControlBtn" id="RightBtn"></el-button>
        <el-button class="ControlBtn" id="DownBtn"></el-button>
      </div>

    </div>
  </div>

</div>
</template>

<script>
export default {
  name: "MainGame"
}
</script>

<style scoped>
.GamePage{
  box-sizing: border-box;
  background: url(../assets/Bg.png) no-repeat;
  background-size: 100% 100%;
  min-height: 200vh;
  background-attachment: fixed;
}
.top{
  height: 80px;
  display: flex;
}
.avatar{
  width: 70px;
  height: 100px;
  padding-left: 7px;
  text-align: left;
  background:url("../assets/maingame/headbg.png") no-repeat;
  margin-left: 7px;
  margin-top: 25px;
}
.status{
  margin-top: 10px;
}
.item{
  width: 200px;
  height: 10px;
  background:url("../assets/maingame/StatusBar.png") no-repeat;
  background-size: 100% 100%;
  padding: 5px;
  margin-bottom: 5px;
  margin-top:0px ;
}
#life{
  height: 10px;
  position: absolute;
  width: 100px;
}
#weight{
  height: 10px;
  position: absolute;
  width: 150px;
}
#score{
  width: 200px;
  height: 20px;
  color:white;
  background:url("../assets/maingame/ScoreBg.png") no-repeat;
  background-size: 100% 100%;
}
#scorevalue{
  margin-left: 60px;
}
/*.time{*/
/*  width: 300px;*/
/*  height: 30px;*/
/*  margin-left: 700px;*/
/*  !*margin-right: 0px;*!*/
/*  background:url("../assets/maingame/prograss.png") no-repeat;*/
/*  background-size: 100% 100%;*/
/*}*/
/*#blank{*/
/*  height: 20px;*/
/*  width: 50px;*/
/*}*/
#LeftTime{
  width: 250px;
  height: 20px;
  margin-left: 720px;
}
.bottom{
  /*width: 1300px;*/
  height: 495px;
  display: flex;
  align-items: center;
  align-content: center;
}
.left{
  width: 200px;
  height: 495px;
  margin-left: 10px;
}
.goods{
  padding: 10px;
  margin-left: 8px;
}
.btn{
  background:url(../assets/maingame/BookBtn.png) no-repeat;
  background-size: 100% 100%;
  border-style: none;
  width: 55px;
  height: 55px;
  border-style: none;
  margin-top: 30px;
  margin-left: 60px;
}
.btn:hover{
  color: #fff;
  border-radius: 5px;
  border-style: none;
  box-shadow: 0 0 10px sandybrown,0 0 25px sandybrown,0 0 50px sandybrown,0 0 100px sandybrown;
}
.center{
  width: 900px;
  height: 495px;
}
#MapBorder {
  position: absolute;
  margin-left: 150px;
  margin-top: -20px;
  width: 525px;
  height: 510px;
  background:url(../assets/maingame/MapBorder.png) no-repeat;
  background-size: 100% 100%;
}
#MapArea {
  width: 375px;
  height: 375px;
  margin-left: 74px;
  margin-top: 57px;
}
.MapCell{
  width: 75px;
}
.right{
  width: 200px;
  height: 495px;
  margin-top: 50px;
  margin-right: 50px;
}
.OrderBtnArea{
  height: 200px;
}
.OrderBtn{
  width: 150px;
  height: 50px;
  border-style: none;
  background:url(../assets/maingame/ControlBtn.png) no-repeat;
  background-size: 100% 100%;
  --el-button-text-color: white;
  /*margin-left: 50px;*/
  margin-bottom: 10px;
}
.OrderBtn+.OrderBtn{
  margin-left: 0px;
}
.ControlBtn:hover{
  color: #fff;
  border-radius: 5px;
  border-style: none;
  box-shadow: 0 0 10px royalblue,0 0 25px royalblue,0 0 50px royalblue,0 0 100px royalblue;
}
.ControlBtnArea{
  height: 245px;
  margin-top: 50px;
}
.ControlBtn{
  border-style: none;
  width: 50px;
  height: 50px;
}
#UpBtn{
  background:url(../assets/maingame/UpBtn.png) no-repeat;
  background-size: 100% 100%;
  margin-left: 50px;
  margin-right: 75px;
}
#LeftBtn{
  background:url(../assets/maingame/LeftBtn.png) no-repeat;
  background-size: 100% 100%;
  margin-left: 0px;
}
#RightBtn{
  background:url(../assets/maingame/RightBtn.png) no-repeat;
  background-size: 100% 100%;
  margin-left: 50px;
}
#DownBtn{
  background:url(../assets/maingame/DownBtn.png) no-repeat;
  background-size: 100% 100%;
  margin-left: 50px;
}
</style>