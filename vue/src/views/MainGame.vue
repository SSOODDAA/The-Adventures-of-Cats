<template>
<div class="GamePage">
  <GameRole/>
  <div class="bottom">
    <div class="left">
      <span class="title">物品栏</span>
      <CarryItem
          v-for="(item,index) in items"
          :key="item+'-'+index"
          :item="item"
      />
      <el-button class="btn"></el-button>
    </div>
    <MainMap/>
  </div>
</div>
</template>

<script>
import MainMap from "@/components/MainMap";
import GameRole from "@/components/GameRole";
import CarryItem from "@/components/CarryItem";
export default {
  name: "MainGame",
  components: {CarryItem, GameRole, MainMap},
  setup(){
    const items=[1,2,3,4,5,6,7,8];//用户物品栏的物品

    /**
     * 获取用户所携带的物品
     */
    const getItems=()=>{

    }
    return{
      items,
      getItems,
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
  height: 495px;
  display: flex;
  align-items: center;
  align-content: center;
}
.left{
  position: absolute;
  width: 200px;
  height: 495px;
  margin-top: 100px;
  margin-left: 100px;
  margin-right: 300px;
  display: flex;
  flex-wrap: wrap;
}
.title{
  background:url(../assets/maingame/prograss.png) no-repeat;
  background-size: 100% 100%;
  border-style: none;
  width: 200px;
  height: 50px;
  font-size: large;
  color:white;
  text-align: center;
  line-height: 50px;
}
.btn{
  background:url(../assets/maingame/BookBtn.png) no-repeat;
  background-size: 100% 100%;
  border-style: none;
  width: 55px;
  height: 55px;
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