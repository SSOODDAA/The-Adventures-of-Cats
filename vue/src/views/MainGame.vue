<template>
  <MainMap/>
</template>

<script>
import MainMap from "@/components/MainMap";
import GameRole from "@/components/GameRole";
import CarryItem from "@/components/CarryItem";
export default {
  name: "MainGame",
  components: {MainMap},
  setup(){

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
</style>