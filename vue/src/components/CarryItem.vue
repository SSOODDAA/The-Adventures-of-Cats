<template>
  <div class="carryItem">
    <i v-if="item" :class="'item item-'+item"></i>
  </div>
</template>

<script>
export default {
  name: "CarryItem",
  props:{
    item:{
      type:Number,
      required:false,
      default:0,
    },
  }
}
</script>

<style scoped>
.carryItem{
  padding:1px;
  width: 78px;
  height: 78px;
  position: relative;
  background: url("../assets/gamemap/room0.png")no-repeat;
  background-size: 100% 100%;
  margin-right: 20px;
}
.item{
  position: absolute;
  top: 15px;
  left: 15px;
  right: 0;
  bottom: 0;
  background-size: 45px 45px;
}
.item-1{
  background: url("../assets/Bookimg/1.png")no-repeat;
}
.item-2{
  background: url("../assets/Bookimg/2.png")no-repeat;
}
.item-3{
  background: url("../assets/Bookimg/3.png")no-repeat;
}
.item-4{
  background: url("../assets/Bookimg/4.png")no-repeat;
}
.item-5{
  background: url("../assets/Bookimg/5.png")no-repeat;
}
.item-6{
  background: url("../assets/Bookimg/6.png")no-repeat;
}
.item-7{
  background: url("../assets/Bookimg/7.png")no-repeat;
}
.item-8{
  background: url("../assets/Bookimg/8.png")no-repeat;
}
.item-9{
  background: url("../assets/Bookimg/9.png")no-repeat;
}
.item-10{
  background: url("../assets/Bookimg/10.png")no-repeat;
}
.item-11{
  background: url("../assets/Bookimg/11.png")no-repeat;
}
.item-12{
  background: url("../assets/Bookimg/12.png")no-repeat;
}

</style>