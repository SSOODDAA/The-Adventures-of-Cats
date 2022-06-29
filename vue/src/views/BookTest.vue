<template>
  <div>
    <div style="width: 100%;height: 100px;">
     <!-- 添加会员 -->
     <el-button type="info" @click="handleClick3()"   size="mini">菜单权限</el-button>
    <el-button type="primary" @click="sendmessage" style="margin-top: 30px;float: left; margin-left: 10px;margin-bottom: 25px;"><i class="el-icon-s-promotion">发送</i></el-button>
    <el-button type="primary" style="margin-top: 30px;float: left; margin-left: 5px;margin-bottom: 25px;">√全选</el-button>
    <el-button type="primary" style="margin-top: 30px;float: left; margin-left: 5px;margin-bottom: 25px;">×批量删除</el-button>

    </div>

    <!-- 弹出的页面内容 -->
    <!--<el-dialog :v-model="getmessage" append-to-body>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="收件人：">-->
            <!-- 下拉框 -->
          <!--<el-select v-model="value" placeholder="请选择">
            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="">
            <el-input v-model="ruleForm" placeholder="标题信息" style="margin-top: 10px;"></el-input>
        </el-form-item>
        <el-form-item label="">
            <el-card style="height: 610px;">
              <quill-editor v-model="content" ref="myQuillEditor" style="height: 500px;" :options="editorOption">
              </quill-editor>
            </el-card>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')" style="float:right">确定</el-button>
        </el-form-item>
    </el-form>
    </el-dialog>-->
    <!--引入子页面-->
    <el-dialog  :v-model="menuRoleVisible" append-to-body>
    <menu-role v-if="menuRoleVisible" ref="menurole"></menu-role>
    </el-dialog>



  </div>
</template>


<script>

    import MenuRole from "@/views/MenuRole";

    export default {
        components:{
            MenuRole
        },

        data() {

            return {
                //弹框
                menuRoleVisible:false,
                getmessage:false

            }
        },
        methods:{
            //弹框
            sendmessage(){
                this.getmessage = true
            },
            //设置菜单权限
            handleClick3(){

                this.menuRoleVisible = true;

            }

        }

    }
</script>

<style scoped = "less">

</style>