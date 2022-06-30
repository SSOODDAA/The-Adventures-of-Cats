<template>
  <div class="left">
    <span class="title">物品栏</span>
    <CarryItem
        v-for="(item,index) in items"
        :key="item+'-'+index"
        :item="item"
        @click="selectItem(item)"
        :selected="isSelected(item)"
    />
    <el-button class="bookbtn"></el-button>
  </div>
  <div class="MainMap">
    <el-button class="btn" type="primary"  @click="pauseGame">暂停游戏</el-button>
    <el-button class="btn" type="primary"  @click="quitGame">返回</el-button>
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
        <el-dialog title="房间物品信息" v-model="dialogVisible" center>
          <div class="product-area">
            <RoomProduct
                v-for="(item,index) in items"
                :key="item+'-'+index"
                :item="item"
            />
          </div>
        </el-dialog>
        <el-button class="OrderBtn" @click="lookProducts()">look</el-button>
        <el-button class="OrderBtn">take</el-button>
        <el-button class="OrderBtn">drop</el-button>
        <el-button class="OrderBtn" @click="useItem(item)">use</el-button>
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
import { useRouter } from "vue-router";
import CarryItem from "@/components/CarryItem";
import RoomProduct from "@/components/RoomProduct";
export default {
  name: "MainMap",
  components:{
    RoomProduct,
    CarryItem,
    MapCell
  },
  data(){
    return{
        // dialogVisible: false,
        Roleid:ref(JSON.parse(sessionStorage.getItem("roleid"))),
    };
  },
  methods:{
    look() {

    }
  },
  setup(){
    //初始化路由
    const router = useRouter();
    let cells=ref([]);
    const ArrivedPath=ref([]);
    const position=ref(0);//初始时角色生成在右上角
    const items=ref([1,2,3,4,5,6,7,8]);//用户物品栏的物品
    let selectedItem=ref(0);
    const roleid=ref(2);
    let products=ref([1,2,3,4,5,6]);//房间里拥有的物品
    const dialogVisible=ref(false);//对话框是否可见
    /**
     * 获取用户所携带的物品
     */
    const getItems=()=>{

    }
    const selectItem=(item)=>{
      selectedItem.value=item;
      return true;
    }
    const isSelected=(item)=>{
      return item === selectedItem.value;
    }
    //判断是否在经过的路径中
    const isArrived = (index) =>{
      return ArrivedPath.value.findIndex(p => p===index)>-1;
    }
    // //查看房间中的物品
    // const look=() =>{
    //   dialogVisible=true;
    // }
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
     * 角色的上下左右移动
     * @param action
     * @constructor
     */
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
          return npctype,position.value,life;
        }else{
          this.$message({
            type:"error",
            message:res.message
          })
        }
      })
    }
    const lookProducts=()=>{
      var param = new FormData()
      param.append('userid',JSON.parse(sessionStorage.getItem("user")).userid)//用户账号
      request.post("game/look",param).then(res=>{
        if(res.state===200){
          products.value=res.data;
          dialogVisible.value=true;
          console.log(products.value);
          console.log(dialogVisible.value);
        }else{
          this.$message({
            type:"error",
            message:res.message
          })
        }
      })
    }
    const pauseGame=()=>{

    }
    const quitGame=()=>{
      console.log("退出游戏被点击了")
      //退出游戏
      router.push({
        path:'/homepage'
      });
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
      GoAction,
      pauseGame,
      isHere,
      quitGame,
      items,
      lookProducts,
      products,
      dialogVisible,
      selectItem,
      isSelected,
      getItems,
      roleid,
    }
  },
}
</script>

<style scoped>
.btn,
.OrderBtn{
  font-size: large;
  border-style: none;
  background:url(../assets/maingame/ControlBtn.png) no-repeat;
  background-size: 100% 100%;
  width: 150px;
  height: 50px;
}
.btn{
  margin-top: -25px;
  margin-left: 40px;
  margin-right: 150px;
  margin-bottom: 20px;
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
.bookbtn{
  background:url(../assets/maingame/BookBtn.png) no-repeat;
  background-size: 100% 100%;
  border-style: none;
  width: 55px;
  height: 55px;
  margin-top: 30px;
  margin-left: 60px;
}
.bookbtn:hover{
  color: #fff;
  border-radius: 5px;
  border-style: none;
  box-shadow: 0 0 10px sandybrown,0 0 25px sandybrown,0 0 50px sandybrown,0 0 100px sandybrown;
}

.OrderBtn:hover,
.btn:hover{
  color: #fff;
  border-radius: 5px;
  border-style: none;
  box-shadow: 0 0 10px steelblue,0 0 25px steelblue,0 0 50px steelblue,0 0 100px steelblue;
}
.MainMap{
  width: 1000px;
  height: 495px;
  margin-left: 400px;
  margin-top: -40px;
}
.BoardBG{
  width: 560px;
  height: 520px;
  position: absolute;
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
/*.pdialog{*/
/*  --el-dialog-width: 30%;*/
/*  font-size: large;*/
/*  color: blue;*/
/*}*/
.dialogitem{
  /*position: absolute;*/
}
.product-area{
  width: 200px;
  position: absolute;
  margin-left: 200px;
  display: flex;
  flex-wrap: wrap;
}
</style>