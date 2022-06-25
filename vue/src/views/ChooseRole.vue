<template>
  <div class = "ChooseRole">
    <div :style="fixStyle">
      <div style="text-align: center;"><img src="../assets/title.png" width="800"/></div>
      <button class = "role">
        <img src = "../assets/chooserole/cat1.png" value="普通喵图片" ><br>
        <h3>普通喵</h3>
        <span>看起来最普通的猫咪，</span><br>
        <span>却常有着超乎想象的运气</span>
      </button>
      <button class = "role">
        <img src = "../assets/chooserole/cat2.png" value="间谍喵图片" ><br>
        <h3>间谍喵</h3>
        <span>一只喜欢打扮成绅士的喵，</span><br>
        <span>却偷偷练着武功</span>
      </button>
      <button class = "role">
        <img src = "../assets/chooserole/cat3.png" value="超人喵图片" ><br>
        <h3>超人喵</h3>
        <span>异瞳多了一些神秘，</span><br>
        <span>他的斗篷里好像藏了很多宝贝</span>
      </button>
      <br><button name = "enter" type = "submit" value = "确定" class = "confirmbtn">确定</button>

    </div>
  </div>

</template>

<script>
export default {
name: "ChooseRole"
}
</script>

<style scoped = "less">
.ChooseRole{
  /*100%窗口高度*/
  min-height: 100vh;
  /*背景放置图片*/
  background: url(../assets/Bg.png) no-repeat;
  background-size: 100% 100%;
  /*弹性布局：居中*/
  display: flex;
  justify-content: center;
  align-items: center;
  /*溢出隐藏*/

  background-size: cover;
  background-attachment: fixed;
    overflow: hidden;

}
.role{
    float:left;
    width: 300px;
    height: 400px;
    border:none; /*去掉黑色边框*/
    background:url(../assets/chooserole/choosebg.png) no-repeat;
    background-size: 100% 100%;
    text-align: center;
    /*弹性布局：居中*/
    display: flex;
    justify-content: center;
    align-items: center;
    align-items:center;
    flex-direction:column;

    position:relative;
    margin-right: 50px;


}


.confirmbtn {
    position:absolute;
    background:url(../assets/button.png) no-repeat;
    background-size: 100% 100%;
    width: 150px;
    height: 45px;
    border-style: none;
    margin-left: 160px;
    padding-left: 0em;
    font-size: large;
    color:#FFFFFF;
    margin-bottom: 1.2em;
    margin-left: 1em;
    margin-right: 1em;
    text-align:center;
    bottom:-20px;
    right:680px;
}
.role:hover,.role:focus{
    color: #fff;
    background-blend-mode: luminosity;
    filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
    border-radius: 20px;
}

.confirmbtn:hover{
    color: #fff;
    border-radius: 5px;
    border-style: none;
    box-shadow: 0 0 10px steelblue,0 0 25px steelblue,0 0 50px steelblue,0 0 100px steelblue;

}

</style>