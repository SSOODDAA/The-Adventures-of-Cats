<template>
  <div class="map-cell">
<!--    {{icon? icon:''}}-->
    <i v-if="icon" :class="'icon icon-'+icon"></i>
  </div>
</template>

<script>
export default {
  name: "MapCell",
  props:{
    icon:{
      type:Number,
      required:false,
      default:0,
    }
  }
}
</script>

<style scoped>
.map-cell{
  border: 1px solid black;
  width: 78px;
  height: 78px;
  position: relative;
  .icon{
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
  }
  .icon-1{
    background: url("../assets/maingame/headbg.png");
    background-size: 100% 100%;
  }
  .icon-2{
    background: url("../assets/maingame/Stone.png");
    background-size: 100% 100%;
  }

}


</style>