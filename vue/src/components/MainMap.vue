<template>
  <div class="GamePage">
    <GameRole
        :avicon="roleid"
        :life-value="lifeValue"
        :weight-value="weightValue"
        :score="scoreValue"
        :left-time="count"
    />
    <div class="bottom">
      <div class="left">
        <span class="title">物品栏</span>
        <CarryItem
            v-for="(item,index) in items"
            :key="item+'-'+index"
            :item="item"
            @click="selectItem(index)"
            :selected="isSelected(index)"
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
                :arrived="isArrived(index)"
                :icon="cell"
                :role="isHere(index,roleid)"
            />
          </div>
        </div>
        <div class="right">
          <el-dialog title="房间物品信息" v-model="dialogVisible" center>
            <div class="product-area">
              <RoomProduct
                  v-for="(item,index) in products"
                  :key="item+'-'+index"
                  :item="item"
                  @click="selectRoomItem(index)"
                  :selected="isRoomSelected(index)"
              />
            </div>
            <template #footer>
            <span class="dialog-footer">
              <el-button class="dialogBtn" type="primary" @click="dialogVisible = false">cancel</el-button>
              <el-button class="dialogBtn" type="primary" @click="take()">take</el-button>
            </span>
            </template>
          </el-dialog>
          <div class="OrderBtnArea">
            <el-button class="OrderBtn" @click="lookProducts()">look</el-button>
            <el-button class="OrderBtn" @click="queryItems()">items</el-button>
            <el-button class="OrderBtn" @click="drop()">drop</el-button>
            <el-button class="OrderBtn" @click="use()">use</el-button>
          </div>
          <div class="ControlBtnArea">
            <el-button class="ControlBtn" id="UpBtn" @click="GoAction(1)"></el-button>
            <el-button class="ControlBtn" id="LeftBtn" @click="GoAction(3)"></el-button>
            <el-button class="ControlBtn" id="RightBtn" @click="GoAction(4)"></el-button>
            <el-button class="ControlBtn" id="DownBtn" @click="GoAction(2)"></el-button>
          </div>
        </div>
        <transition name="alan_scale">
          <div class="stop" v-show="isStop">
            <img src="../assets/gamemap/stop.png" alt />
          </div>
        </transition>
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
import GameRole from "@/components/GameRole";
import {ElMessage} from 'element-plus';
export default {
  name: "MainMap",
  components:{
    RoomProduct,
    CarryItem,
    MapCell,
    GameRole,
  },
  data(){
    return{
        Roleid:ref(JSON.parse(sessionStorage.getItem("roleid"))),
    };
  },
  setup(){
    const router = useRouter();    //初始化路由
    let cells=ref([]);      //地图单元格信息
    const ArrivedPath=ref([]);//用户所走的路径，后续可以进行遮盖显示的扩展
    const position=ref(0);//初始时角色生成在右上角
    const items=ref([1,2,3,4,5,6,7,8]);//用户物品栏的物品
    let selectedItem=ref(-1);//选中的物品栏的物品编号
    let selectedRoom=ref(-1);//选中的房间的物品编号
    let products=ref([1,2,3,4,5,6]);//房间里拥有的物品
    let fullProducts=[];//房间刷新物品的全部信息
    const dialogVisible=ref(false);//对话框是否可见
    const lifeValue=ref(0);//生命值
    const weightValue=ref(0);//载重值
    const scoreValue=ref(0);//得分；
    const roleid=ref(2);//角色id
    const TIME_COUNT=ref(300);
    const count=ref();//游戏剩余时间
    const timer=ref();
    const isStop=ref(false);

    /**
     * 查看角色状态
     */
    const queryStatus=()=>{
      var param = new FormData();
      param.append('userid',JSON.parse(sessionStorage.getItem("user")).userid);//用户账号
      request.get("game/query",{
        params:{
          'userid':JSON.parse(sessionStorage.getItem("user")).userid,
        }
      }).then(res=>{
        if(res.state===200){
          lifeValue.value=res.data[0];
          weightValue.value=res.data[1];
          scoreValue.value=res.data[2];
          sessionStorage.setItem("status",JSON.stringify(res.data))//缓存角色的状态信息
        }else{
          this.$message({
            type:"error",
            message:res.message
          })
        }
      })
    }

    /**
     * 查询用户的背包栏信息
     */
    const queryBag=()=>{
      request.get("game/queryBag",{
        params:{
          'userid':JSON.parse(sessionStorage.getItem("user")).userid,
        }
      }).then(res=>{
        if(res.state===200){
          console.log(res.data)
          items.value=[];//清空products的内容
          for(let i=0;i<res.data.length;i++){
            if(res.data[i]!==null){
              items.value.push(res.data[i].id);
            }
          }
        }else{
          ElMessage.error(res.message);
        }
      })
    }

    /**
     * 选中数组编号为index的物品
     * @param index
     * @returns {boolean}
     */
    const selectItem=(index)=>{
      if(selectedItem.value===index){
        selectedItem.value=-1;
      }else{
        selectedItem.value=index;
      }
      console.log(selectedItem.value);
    }

    /**
     * 选中房间的物品
     * @param index
     * @returns {boolean}
     */
    const selectRoomItem=(index)=>{
      if(selectedRoom.value===index){
        selectedRoom.value=-1;
      }else{
        selectedRoom.value=index;
      }
    }

    /**
     * 判断物品栏物品是否被选中
     * @param index
     * @returns {boolean}
     */
    const isSelected=(index)=>{
      return index === selectedItem.value;
    }

    /**
     * 判断房间编号为x的物品是否被选中
     * @param index
     * @returns {boolean}
     */
    const isRoomSelected=(index)=>{
      return index === selectedRoom.value;
    }

    /**
     * 判断是否在经过的路径中
     */
    const isArrived = (index) =>{
      return ArrivedPath.value.findIndex(p => p===index)>-1;
    }

    /**
     * 判断角色位置是否在当前索引位置中,在返回角色id，否则返回0
     * @param index  格子所在位置
     * @param roleid
     * @returns {any|number}
     */
    const isHere=(index,roleid) =>{
      return index === position.value? roleid:0;
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
          position.value=res.data[1];
          queryStatus();//更新用户状态
          return position.value;
        }else{
          this.$message({
            type:"error",
            message:res.message
          })
        }
      })
    }

    /**
     * look命令-查看房间物品信息
     */
    const lookProducts=()=>{
      selectedRoom.value=-1;//将选中的房间物品设为空。
      var param = new FormData()
      param.append('userid',JSON.parse(sessionStorage.getItem("user")).userid)//用户账号
      request.post("game/look",param).then(res=>{
        if(res.state===200){
          products.value=[];//清空products的内容
          for(let i=0;i<res.data.length;i++){
            if(res.data[i]!==null){
              products.value.push(res.data[i]);
            }
          }
          dialogVisible.value=true;
        }else{
          this.$message({
            type:"error",
            message:res.message
          })
        }
      })
    }

    /**
     * take命令-将选中的物品放到背包栏
     */
    const take=()=>{
      var param = new FormData()
      param.append('userid',JSON.parse(sessionStorage.getItem("user")).userid)//用户账号
      let index=[];
      index.push(selectedRoom.value);
      param.append('index',JSON.stringify(index));
      param.append('products',JSON.stringify(products.value));//物品的id列表信息
      request.post("/game/take",param).then(res=>{
        if(res.state===200){
          ElMessage.success("物品拾取成功");
          dialogVisible.value=false;
          queryBag();//更新用户的背包栏
        }else{
          this.$message({
            type:"error",
            message:res.message
          })
        }
      })
    }

    /**
     *drop命令- 丢弃背包栏物品
     */
    const drop=()=>{
      var param = new FormData()
      param.append('userid',JSON.parse(sessionStorage.getItem("user")).userid)//用户账号
      param.append('index',JSON.stringify(selectedItem.value));
      request.put("/game/drop",param).then(res=>{
        if(res.state===200){
          ElMessage.success("物品丢弃成功");
          queryBag();//更新用户的背包栏
        }else{
          this.$message({
            type:"error",
            message:res.message
          })
        }
      })
    }
    /**
     * items命令—查询背包的容量
     */
    const queryItems=()=>{
      var param = new FormData();
      param.append('userid',JSON.parse(sessionStorage.getItem("user")).userid);//用户账号
      request.get("game/items",{
        params:{
          'userid':JSON.parse(sessionStorage.getItem("user")).userid,
        }
      }).then(res=>{
        if(res.state===200){
          ElMessage.success('您的背包容量为'+res.data);
        }else{
          this.$message({
            type:"error",
            message:res.message
          })
        }
      })
    }

    /**
     * use命令-使用选中的背包栏物品
     */
    const use=()=>{
      var param = new FormData()
      param.append('userid',JSON.parse(sessionStorage.getItem("user")).userid)//用户账号
      param.append('choice',selectedItem.value);
      request.put("/game/use",param).then(res=>{
        if(res.state===200){
          this.$message({
            type:"success",
            message:"物品使用成功"
          })
          queryBag();//更新用户的背包栏
        }else{
          this.$message({
            type:"error",
            message:res.message
          })
        }
      })

    }

    /**
     * 暂停或者恢复游戏
     */
    const pauseGame=()=>{
      //如果时间已经耗尽
      if(count.value===0){
        ElMessage.warning('游戏已经结束');
        return;
      }
      isStop.value=!isStop.value;
      console.log(isStop.value);
      if(isStop.value===true){clearInterval(timer.value);}
      else{setTimer(count.value);}
    }
    /**
     * 返回
     */
    const quitGame=()=>{
      //保存用户的当前状态

      router.push({
        path:'/homepage'
      });
    }

    /**
     * 设置倒计时
     */
    const setTimer=(time)=>{
      console.log('settime被调用');
      count.value=time;
      timer.value=setInterval(()=>{
        if(count.value>0 && count.value<=time){
          count.value--;
        }
        else{
          clearInterval(timer.value);
          timer.value=null;
          //时间耗尽，提示用户结束游戏
          ElMessage.success('游戏结束，您最终的得分是 '+scoreValue.value);
          isStop.value=true;
        }

      },1000);
      if(count.value===0){
        console.log("时间耗尽");
      }
    };

    /**
     * 从后端获取数据生成地图，以及角色的位置
     */
    const start=()=>{
      cells.value=null;
      roleid.value=JSON.parse(sessionStorage.getItem("roleid"));//得到角色id值
      // cells.value=JSON.parse(sessionStorage.getItem("cells"))//查询缓存中有无地图信息
      //当缓存中没有地图数据的时候再执行从session中获取的操作
      if(cells.value==null) {
        var param = new FormData()
        param.append('userid',JSON.parse(sessionStorage.getItem("user")).userid)//用户账号
        request.post("/game",param).then(res=>{
          if(res.state===200) {
            cells.value=res.data;
            timer.value=0;
            queryStatus();//查询用户状态
            queryBag();//查询背包状态
            isStop.value=false;
            setTimer(TIME_COUNT.value);//启动计时
            sessionStorage.setItem("cells",JSON.stringify(res.data))//缓存地图信息
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
      roleid,
      cells,
      start,
      queryStatus,
      isArrived,
      GoAction,
      pauseGame,
      queryItems,
      drop,
      isHere,
      quitGame,
      items,
      lookProducts,
      products,
      dialogVisible,
      selectItem,
      selectRoomItem,
      isSelected,
      isRoomSelected,
      queryBag,
      use,
      take,
      lifeValue,
      weightValue,
      scoreValue,
      timer,
      TIME_COUNT,
      count,
      isStop,
    }
  },
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
.btn,
.OrderBtn,
.dialogBtn{
  font-size: large;
  border-style: none;
  background:url(../assets/maingame/ControlBtn.png) no-repeat;
  background-size: 100% 100%;
  width: 150px;
  height: 50px;
}
.dialogBtn{
  width: 100px;
  height: 40px;
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
.product-area{
  width: 500px;
  position: relative;
  margin-left: 70px;
  display: flex;
  flex-wrap: wrap;
}
.bottom{
  height: 495px;
  display: flex;
  align-items: center;
  align-content: center;
}
.stop {
  width: 1260px;
  height: 549px;
  background: rgba(0, 0, 0, 0.7);
  position: absolute;
  top: 100px;
  left: 0px;
}
.stop img {
  width: 20%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border: 0;
}
</style>