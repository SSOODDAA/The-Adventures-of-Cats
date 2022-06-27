<template>
<div id="board">
  <MapCell v-for="(cell,index) in cells" :key="cell+'-'+index" :icon="cell"/>
</div>
</template>

<script>
import MapCell from "@/components/MapCell";
import {ref} from "vue";

export default {
  name: "MainMap",
  components:{
    MapCell
  },
  setup(){
    const cells=ref([0,0,0,1,0,1,1,1,1,1,2,2,0,2,2,3,0,3,3,3,4,4,4,4,4,5,5,5,5,5]);
    return {
      cells
    }
  },
  // data(){
  //   return {
  //     cells:[1,0,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,0,1,1,1,1,1,0,1]
  //   }
  // }
}
</script>

<style scoped>
#board{
  margin: 0 auto;
  display: flex;
  flex-wrap: wrap;
  width: 500px;
  height: 500px;
}
</style>