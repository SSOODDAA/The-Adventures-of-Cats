<template>
    <div class="widget-content">
        <tree-transfer :title="title"
                       :from_data='fromData'
                       :to_data='toData'
                       :defaultProps="{label:'label',value:'id'}"
                       @addBtn='add'
                       @removeBtn='remove'
                       @change="changeMode"
                       :mode='mode' height='640px' filter openAll>
        </tree-transfer>
    <el-button type="primary" size="mini" @click="save" style="margin-left:30%;">保存</el-button>
    </div>
</template>
<script>
    import treeTransfer from 'el-tree-transfer' // 引入
    export default {
        components: {
            treeTransfer
        },
        data(){
            return{
                roleId:"",
                title:["已有菜单权限","可选菜单权限"],
                mode: "transfer", // transfer addressList
                    fromData:[],
                    toData:[]
            }
        },
        methods:{
            //初始化数据
            dataInitialization(id){
                let that = this;
                that.roleId = id;
                that.axios
                    .get(
                        "/qsmonitor/menu/getMenuByRole?roleId="+id
                    )
                    .then(res => {
                        that.fromData = res.data.data.fromData;
                        that.toData = res.data.data.toData;
                        console.info("this id ");
                        console.info(res);
                        that.$notify({
                            title: "成功",
                            message: res.data.message,
                            type: "success"
                        });
                    })
                    .catch(err => {
                        that.$notify.error({
                            title: "错误",
                            message: err
                        });
                        console.log(err);
                    });
            },
            // 切换模式 现有树形穿梭框模式transfer 和通讯录模式addressList
            changeMode() {
                if (this.mode == "transfer") {
                    this.mode = "addressList";
                } else {
                    this.mode = "transfer";
                }
            },
            // 减少权限设置
            add(fromData,toData,obj){
                this.fromData = fromData;
                this.toData = toData;
            },
            // 增加权限设置
            remove(fromData,toData,obj){
                this.fromData = fromData;
                this.toData = toData;
            },
            save(){
                let addIds = "";
                this.fromData.forEach((item,i)=>{
                    addIds += item.id+",";
                });
                this.axios
                    .put(
                        "/qsmonitor/menu/setMenu?roleId="+this.roleId+"&addIds="+addIds
                    )
                    .then(res => {
                        if (res.data.data) {
                            this.loading = false;
                        }
                        this.dataInitialization(this.roleId);
                        this.$notify({
                            title: "成功",
                            message: res.data.message,
                            type: "success"
                        });
                    })
                    .catch(err => {
                        console.info(err);
                        this.$notify.error({
                            title: "错误",
                            message: "系统异常"
                        });
                        console.log(err);
                    });
            }
        }
    }
</script>