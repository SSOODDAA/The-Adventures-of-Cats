<template>
    <div class = "Book">
        <div class = "bg">
            <!--一组按钮组件-->
            <div class="div_class_buttonGroup">
                 <div v-for="item in btns" :key="item.id" class="div_class_defaultBtn" :class={active:getCurrentClassBtn(item.name)} @click="currentBtn = item.name"> {{item.name}}</div>
            </div>
            <!--<div class="div_item_buttonGroup">
                <div v-for="item in btns" :key="item.id" class="div_item_defaultBtn" :class={active:getCurrentItemBtn(item.name)} @click="currentBtn = item.name"> {{item.name}}</div>
            </div>-->
            <button class = "backbtn"><img src="../assets/Bookimg/backbtn.png" /></button>
        </div>

    </div>
</template>

<script>
import request from "@/utils/request";

export default {
name: "BookShow",
    data(){
        return{
            //默认食物图鉴开始
            currentBtn:'食物图鉴',
            btns:{
                btn_1:{
                    name:'食物图鉴',
                    value:'food',
                    type: '1'
                },
                btn_2:{
                    name:'道具图鉴',
                    value:'tool',
                    type: '2'
                },
                btn_3:{
                    name:'宝藏图鉴',
                    value:'treasure',
                    type: '3'
                },
                btn_4:{
                    name:'NPC图鉴',
                    value:'NPC',
                    type: '4'
                }
            }
        }
    },
    methods:{
        getCurrentClassBtn(data){

            var url = "/handbook"//'获取数据的后台地址'
            request.get(url)
            .then(res => {
                console.log(res)
                if(res.state===200){
                    console.log("success")
                    this.$router.push("/maingame")/*跳转到游戏界面*/
                }
            }).catch(() => {})
            return this.currentBtn === data
        }

    }

}
</script>

<style scoped = "less">
    .Book{
        box-sizing: border-box;
        background: url(../assets/Bg.png) no-repeat;
        background-size: 100% 100%;
        min-height: 200vh;
        background-attachment: fixed;
    }
    .bg{
        width: 906px;
        height: 526px;
        background: url(../assets/Bookimg/Bookbg.png) no-repeat;
        /*使页面居中*/
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        margin: auto;
    }
    .div_class_buttonGroup{
        #display: flex;
        margin-left:-50px;
        margin-top:130px;
    }
    .div_class_defaultBtn{
        #display: flex;
        width: 100px;
        height: 85px;

        #justify-content: center;
        #align-items: center;
        #cursor: pointer;
        margin:0 5rem;
        /*对字体进行设置*/
        font-family: 'Sunshiney';
        font-style: normal;
        font-weight: 400;
        font-size: 20px;
        line-height: 26px;
        text-align: center;

        color: #F5F5F5;
    }
    .div_class_defaultBtn:first-child{
        left: 200px;
        top: 188px;
    }
    /*.div_class_defaultBtn:before{
        position: relative;

        right: 5px;

        content: url(../assets/Bookimg/1.png);
    }*/
    /*大类按钮被点击*/
    .active{

        #background:#fff;
        color: #1274C0;

    }
    /*返回按钮*/
    .backbtn{
        border: none;
        position: absolute;
        background:#3A3E57;
        left: 600px;
        top: 400px;

        opacity: 0.9;
    }

</style>