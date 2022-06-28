<template>
<div class="GamePage">
  <GameRole></GameRole>
  <div class="bottom">
    <div class="left">
      <img
          v-for="(good,index) in goods"
          :key="good+'-'+index"
          src="../assets/maingame/headbg.png"
          class="goods"
      />
      <el-button class="btn"></el-button>
    </div>
    <MainMap></MainMap>
  </div>

</div>
</template>

<script>
import MainMap from "@/components/MainMap";
import GameRole from "@/components/GameRole";
export default {
  name: "MainGame",
  components: {GameRole, MainMap},
  setup(){
    let goods=[1,2,3,4,5,6,0,0];//用户物品栏的物品
    return{
      goods,
    }

  }
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
  margin-top:10px;
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
#MapArea {
  width: 375px;
  height: 375px;
  margin-left: 74px;
  margin-top: 57px;
}
.MapCell{
  width: 75px;
}
</style>