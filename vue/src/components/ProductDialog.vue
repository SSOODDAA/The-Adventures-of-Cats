<template>
  <el-dialog title="房间物品信息" v-model="dialogVisible">
    <CarryItem
        v-for="(item,index) in items"
        :key="item+'-'+index"
        :item="item"
        @click="selectItem(item)"
        :selected="isSelected(item)"
    />
  </el-dialog>
</template>

<script>
import CarryItem from "@/components/CarryItem";

export default {
  name: "ProductDialog",
  components:{
    CarryItem,
  },
  setup(){
    let dialogVisible=false;
    const items=[1,2,3,4,5,6,7,8];//用户物品栏的物品
  }
}
</script>

<style scoped>

</style>