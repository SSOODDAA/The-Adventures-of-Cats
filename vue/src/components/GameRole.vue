<template>
<div class="GameRole">
  <div class="avatar">
    <i :class="'avicon avicon-'+avicon"></i>
  </div>
  <div class="status">
    <div class="item">
      <img id="life" src="../assets/maingame/Life.png"/>
      <span id="lifevalue">60</span>
    </div>
    <div class="item">
      <img id="weight" src="../assets/maingame/Weight.png"/>
      <span id="weightvalue">100</span>
    </div>
    <div class="item" id="score">
      <span id="scorevalue">1023456</span>
    </div>
  </div>
  <div class="time">
    <img id="LeftTime" src="../assets/maingame/LeftTime.png"/>
  </div>
  <span class="timevalue">10min</span>
</div>
</template>

<script>
export default {
  name: "GameRole",
  props:{
    avicon:{
      type:Number,
      required:false,
      default:1,
    },
    //生命值
    lifeValue:{
      type:Number,
      required:false,
      default:0,
    },
    //载重
    weightValue:{
      type: Number,
      required:false,
      default:0,
    },
    //得分
    score:{
      type:Number,//0,1,2,3分别表示有角色以及角色的类型
      required:false,
      default:0,
    }
  }
}
</script>

<style scoped>
.GameRole{
  height: 80px;
  width: 800px;
  display: flex;
}
.avatar{
  width: 70px;
  height: 70px;
  position: relative;
  background:url("../assets/maingame/headbg.png") no-repeat;
  background-size: 100% 100%;
  margin-left:20px;
  margin-top: 20px;
}
.avicon{
  position: absolute;
  top: 5px;
  left: 10px;
  right: 0;
  bottom: 0;
  background-size: 50px 60px;
}
.avicon-1{
  background: url("../assets/chooserole/cat1.png") no-repeat;
  background-size: 50px 60px;
}
.avicon-2{
  background: url("../assets/chooserole/cat2.png") no-repeat;
  background-size: 50px 60px;
}
.avicon-3{
  background: url("../assets/chooserole/cat3.png") no-repeat;
  background-size: 50px 60px;
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
  color:white;
}
#lifevalue{
  color:white;
  position: absolute;
  top: 10px;
  left: 95px;
  font-size: medium;
}
#weightvalue{
  color:white;
  position: absolute;
  top: 35px;
  left: 95px;
  font-size: medium;
}

.time{
  width: 250px;
  height: 20px;
  background: url("../assets/maingame/prograss.png") top right no-repeat;
  background-size: 100% 100%;
  position: absolute;
  top:10px;
  right: 2px;
  text-align: right;
}

#LeftTime{
  width: 200px;
  height: 20px;
}
.timevalue{
  position: absolute;
  top:10px;
  right: 5px;
  color: white;
}
</style>