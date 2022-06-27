<template>
  <div class="map-cell">
    {{icon? icon:''}}
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
  width: 98px;
  height: 98px;
}

</style>