<template>
  <div :class="'map-cell ' + (arrived? 'arrived':'')">
    <i :class="'icon icon-'+icon">
      <i v-if="role" :class="'role role-'+role"></i>
    </i>
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
    },
    arrived:{
      type: Boolean,
      required:false,
      default:false,
    },
    role:{
      type:Number,//0,1,2,3分别表示有角色以及角色的类型
      required:false,
      default:0,
    }
  }
}
</script>
<style scoped>
.map-cell{
  padding:1px;
  width: 78px;
  height: 78px;
  position: relative;
}
.icon,
.role{
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
}
.icon-0{
  background: url("../assets/gamemap/room0.png")no-repeat;
  background-size: 100% 100%;
}
.icon-1{
  background: url("../assets/gamemap/room1.png")no-repeat;
  background-size: 100% 100%;
}
.icon-2{
  background: url("../assets/gamemap/room2.jpg")no-repeat;
  background-size: 100% 100%;
}
.role{
  margin-top: 12px;
  margin-left: 17px;
}
.role-1{
  background: url("../assets/chooserole/cat1.png") no-repeat;
  background-size: 50px 50px;
}
.role-2{
  background: url("../assets/chooserole/cat2.png")no-repeat;
  background-size: 50px 50px;
}
.role-3{
  background: url("../assets/chooserole/cat3.png")no-repeat;
  background-size: 50px 50px;
}
</style>