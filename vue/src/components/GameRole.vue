<template>
<div class="GameRole">
  <div class="avatar">
    <img src="../assets/chooserole/cat1.png" width="50px" height="60px"/>
  </div>
  <div class="status">
    <div class="item">
      <img id="life" src="../assets/maingame/Life.png"/> <span id="lifevalue">60</span>
    </div>

    <div class="item">
      <img id="weight" src="../assets/maingame/Weight.png"/> <span id="weightvalue">60</span>
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
</template>

<script>
export default {
  name: "GameRole",
  props:{
    //生命值
    LifeValue:{
      type:Number,
      required:false,
      default:0,
    },
    //载重
    WeightValue:{
      type: Number,
      required:false,
      default:0,
    },
    //得分
    Score:{
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
  height: 100px;
  padding-left: 7px;
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
  color:white;
}
#lifevalue{
  color:red;
  margin-left: 220px;
}
#weightvalue{
  color:skyblue;
  margin-left: 220px;
}
#LeftTime{
  width: 250px;
  height: 20px;
  margin-left: 880px;
}
</style>