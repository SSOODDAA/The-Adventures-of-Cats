<template>
  <div class="MainMap">
    <div class="BoardBG">
      <div class="MapBoard">
        <MapCell
            v-for="(cell,index) in cells"
            :key="cell+'-'+index"
            :icon="cell"
            :arrived="isArrived(index)"
            :role="isHere(index,Roleid)"
        />
      </div>
    </div>
    <div class="right">
      <div class="OrderBtnArea">
        <el-button class="OrderBtn" @click="look()">look</el-button>
        <el-button class="OrderBtn">take</el-button>
        <el-button class="OrderBtn">drop</el-button>
        <el-button class="OrderBtn">use</el-button>
      </div>
      <div class="ControlBtnArea">
        <el-button class="ControlBtn" id="UpBtn" @click="GoAction(1)"></el-button>
        <el-button class="ControlBtn" id="LeftBtn" @click="GoAction(3)"></el-button>
        <el-button class="ControlBtn" id="RightBtn" @click="GoAction(4)"></el-button>
        <el-button class="ControlBtn" id="DownBtn" @click="GoAction(2)"></el-button>
      </div>
    </div>
  </div>
</template>

<script>
import MapCell from "@/components/MapCell";
import {ref} from "vue";
import request from "@/utils/request";

export default {
  name: "MainMap",
  components:{
    MapCell
  },
  data(){
    return{
       Roleid:ref(JSON.parse(sessionStorage.getItem("roleid"))),
    };
  },
  setup(){
    let cells=ref([]);
    const ArrivedPath=ref([]);
    const position=ref(0);//初始时角色生成在右上角

    //判断是否在经过的路径中
    const isArrived = (index) =>{
      return ArrivedPath.value.findIndex(p => p===index)>-1;
    }
    //查看房间中的物品
    const look=() =>{

    }
    /**
     * 判断角色位置是否在当前索引位置中,在返回角色id，否则返回0
     * @param index  格子所在位置
     * @param Roleid
     * @returns {any|number}
     */
    const isHere=(index,Roleid) =>{
      return index === position.value? Roleid:0;
    }
    /**
     * 向上移动
     */
    const GoUp=() =>{
      var param = new FormData()
      param.append('userid',JSON.parse(sessionStorage.getItem("user")).userid)//用户账号
      param.append('action',1)//用户账号
      request.put("/game/move",param).then(res=>{
        if(res.state===200){
          console.log(res.data)
        }else{
          this.$message({
            type:"error",
            message:res.message
          })
        }
      })
    }
    const GoAction=(action) =>{
      var param = new FormData()
      param.append('userid',JSON.parse(sessionStorage.getItem("user")).userid)//用户账号
      param.append('action',action)//用户账号
      request.put("/game/move",param).then(res=>{
        if(res.state===200){
          console.log(res.data);
          let npctype=res.data[0];
          position.value=res.data[1];
          let life=res.data[2];
          return npctype,position,life;
        }else{
          this.$message({
            type:"error",
            message:res.message
          })
        }
      })
    }
    /**
     * 向下移动*
     */
    const GoDown=() =>{
      var param = new FormData()
      param.append('userid',JSON.parse(sessionStorage.getItem("user")).userid)//用户账号
      param.append('action',2)//用户账号
      request.put("/game/move",param).then(res=>{
        if(res.state===200){
          console.log(res.data);
          let npctype=res.data[0];
          position.value=res.data[1];
          let life=res.data[2];
          return npctype,position,life;
        }else{
          this.$message({
            type:"error",
            message:res.message
          })
        }
      })
    }

    /**
     * 从后端获取数据生成地图，以及角色的位置
     */
    const start=()=>{
      cells.value=JSON.parse(sessionStorage.getItem("cells"))
      //当缓存中没有地图数据的时候再执行从session中获取的操作
      if(cells.value==null) {
        var param = new FormData()
        param.append('userid',JSON.parse(sessionStorage.getItem("user")).userid)//用户账号
        request.post("/game",param).then(res=>{
          if(res.state===200) {
            cells.value=res.data;
            sessionStorage.setItem("cells",JSON.stringify(res.data))//缓存地图信息
            console.log("地图初始化成功");
          }else{
            this.$message({
              type:"error",
              message:res.message
            })
          }
        })
      }
    }
    start();
    return {
      cells,
      start,
      isArrived,
      GoUp,
      GoAction,
      GoDown,
      isHere,
      look,
    }
  },
}
</script>

<style scoped>
.MainMap{
  width: 1000px;
  height: 495px;
  margin-left: 200px;
}
.BoardBG{
  width: 560px;
  height: 520px;
  position: absolute;
  margin-top: -40px;
  background:url(../assets/maingame/MapBorder.png) no-repeat;
  background-size: 100% 100%;
}
.MapBoard{
  position: absolute;
  display: flex;
  flex-wrap: wrap;
  width: 400px;
  height: 400px;
  margin-left: 78px;
  margin-top: 60px;
}
.right{
  position: absolute;
  margin-left: 700px;
  width: 200px;
  height: 400px;
}
.OrderBtnArea{
  height: 200px;
}
.OrderBtn{
  width: 150px;
  height: 50px;
  border-style: none;
  background:url(../assets/maingame/ControlBtn.png) no-repeat;
  background-size: 100% 100%;
  --el-button-text-color: white;
  margin-bottom: 10px;
}
.OrderBtn+.OrderBtn{
  margin-left: 0px;
}
.ControlBtn:hover{
  color: #fff;
  border-radius: 5px;
  border-style: none;
  box-shadow: 0 0 10px royalblue,0 0 25px royalblue,0 0 50px royalblue,0 0 100px royalblue;
}
.ControlBtnArea{
  height: 245px;
  margin-top: 50px;
}
.ControlBtn{
  border-style: none;
  width: 50px;
  height: 50px;
}
#UpBtn{
  background:url(../assets/maingame/UpBtn.png) no-repeat;
  background-size: 100% 100%;
  margin-left: 50px;
  margin-right: 75px;
}
#LeftBtn{
  background:url(../assets/maingame/LeftBtn.png) no-repeat;
  background-size: 100% 100%;
  margin-left: 0px;
}
#RightBtn{
  background:url(../assets/maingame/RightBtn.png) no-repeat;
  background-size: 100% 100%;
  margin-left: 50px;
}
#DownBtn{
  background:url(../assets/maingame/DownBtn.png) no-repeat;
  background-size: 100% 100%;
  margin-left: 50px;
}
</style>