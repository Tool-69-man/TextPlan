
<template>
  <!--登录表单的容器-->
  <div class="login_container">
    <!--登录区域-->

    <div class="login_box">
      <el-form :model="loginForm" :rules="loginRules" ref="loginForm" label-width="0px" class="login_form">

       <el-row class="loginRow">
         <el-col :span="4">
           <p class="pText">用户名：</p>
         </el-col>
         <el-col :span="20">
           <el-form-item prop="username">
             <el-input class="loginInput" v-model="loginForm.username" placeholder="请输入用户名称" prefix-icon="el-icon-user-solid"></el-input>
           </el-form-item>
         </el-col>
       </el-row>



        <el-row class="loginRow">
          <el-col :span="4">
            <p class="pText">密码：</p>
          </el-col>
          <el-col :span="20">

            <el-form-item prop="password">
              <el-input class="loginInput" v-model="loginForm.password" placeholder="请输入登录密码" prefix-icon="el-icon-lock"></el-input>
            </el-form-item>
          </el-col>
        </el-row>


        <el-row class="loginRow">
          <el-col :span="6"></el-col>
          <el-col :span="12" id="btnId">

            <el-form-item class="login_btn">
              <el-button type="primary" @click="submitForm('loginForm')">立即创建</el-button>
              <el-button @click="resetForm('loginForm')">重置</el-button>
            </el-form-item>
          </el-col>
          <el-col :span="6"></el-col>
        </el-row>





      </el-form>
    </div>

    <!--<div  class="txt">-->
    <!--<p v-text="getRes"></p>-->
    <!--<el-button type="success" @click="goShow">show</el-button>-->
    <!--</div>-->

  </div>
  <div id="boxBack"></div>
  <img src="../assets/loginback2.jpg"  id="background--custom"/>
</template>
<script>



  import { myGetToken } from '../api/wtoken'
  import Cookies from 'js-cookie'
  import Pie from "../../../z03/src/views/demo03/pie/pie";

  export default {
    name: 'About',
    components: {Pie},
    data () {
      return {
        getRes: '结果集合',
        loginForm: {
          username: 'xiong',
          password: 'man69',
          verifyCode: ''
        },
        loginRules: {
          username: [
            { required: true, message: '请输入用户名称', trigger: 'blur' },
            { min: 4, max: 9, message: '长度在4-9', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 4, max: 9, message: '长度在4-9', trigger: 'blur' }
          ]

        }
      }
    },

    methods: {
      submitForm (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            myGetToken(formName).then(res => {


              if (res.data==="False"){
                ElNotification({
                  title:"无法识别",
                  message: "账户或密码错误",
                  offset: 0
                  // duration: 0,//不会自动关闭
                }) // 如果没数据就不跳

                return;
              }


              //获取token 后 后台 传来 toolTop 的 列表

              this.getRes = res.headers.authorization
              console.log('获取token')
              Cookies.set('mytk', res.headers.authorization)




              this.$router.push({
                name: 'demo01',
              })



            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      resetForm (formName) {
        this.$refs[formName].resetFields()
      }

    }

  }

</script>

<style >



  #background--custom{
    /*background: linear-gradient(20deg, rgba(88, 85, 156, 0.98), rgba(78, 142, 71, 0.84), rgb(150, 46, 43));*/
    /*background: linear-gradient(90deg, rgba(167, 101, 245, 0.49), rgba(109, 176, 231, 0.47), rgba(245, 236, 146, 0.47));*/
    bcakdrop-filter:blur(50px);
    background-size: 300% 300%;
    position:absolute;
    /*filter: blur(22px);*/

    z-index: -1;
    top: 0;
    left: 0;
    height: 100%;
    width: 100%;

  }



  #boxBack{
    width: 450px;
    height: 380px;
    background-color: rgb(255, 255, 255);
    filter:blur(12px);
    opacity: 0.5;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);

  }


  .login_box {
    z-index: 1000;
    width: 450px;
    height: 380px;
    /*background-color: rgb(255, 255, 255);*/

    /*border-radius: 3px;*/
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);

  }

  .login_form {
    position: absolute;
    bottom: 0px;
    width: 100%;
    padding: 0px 20px;
    box-sizing: border-box;
  }

  .login_btn {
    display: flex;
    justify-content: flex-end;
  }


  @import url(https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap);

  form.el-form.login_form {
    top: 120px;
  }

  .loginRow {
    padding-top: 22px;
  }

  .pText {
    text-align: left;
    padding-top: 7px;
    font-size: 18px;
    width: 222px;
    margin-top: 0px;
    line-height: 24px;
    font-family: Lora;
    font-weight: 500;
    font-style: italic;
  }

  #btnId{
    top:20px;
    text-align: center;
    text-decoration: none;
    font-weight: 500;
    font-style: italic;
  }



  .loginInput{
    font-weight: 900;
    font-style: italic;
    line-height: 18px;
    font-size: 14px;
    font-family: Lora;
    border-color: #5e35b1;
    border-style: double;
  }

  div.login_box {
    border-color: #fffde7;
    border-style: solid;
    height: 400px;
  }

</style>

<!--<template>-->
  <!--&lt;!&ndash;登录表单的容器&ndash;&gt;-->
  <!--<div class="login_container">-->
    <!--&lt;!&ndash;登录区域&ndash;&gt;-->
    <!--<div class="login_box">-->
      <!--&lt;!&ndash;头像&ndash;&gt;-->

      <!--&lt;!&ndash;表单&ndash;&gt;-->
      <!--<el-form :model="loginForm" :rules="loginRules" ref="loginForm" label-width="0px" class="login_form">-->
          <!--<el-form-item prop="username">-->
            <!--<el-input v-model="loginForm.username" placeholder="请输入用户名称" prefix-icon="el-icon-user-solid"></el-input>-->
          <!--</el-form-item>-->
          <!--<el-form-item prop="password">-->
            <!--<el-input v-model="loginForm.password" placeholder="请输入登录密码" prefix-icon="el-icon-lock"></el-input>-->
          <!--</el-form-item>-->

          <!--<el-form-item class="login_btn">-->
          <!--<el-button type="primary" @click="submitForm('loginForm')">立即创建</el-button>-->
          <!--<el-button @click="resetForm('loginForm')">重置</el-button>-->
        <!--</el-form-item>-->
      <!--</el-form>-->
    <!--</div>-->

    <!--&lt;!&ndash;<div  class="txt">&ndash;&gt;-->
      <!--&lt;!&ndash;<p v-text="getRes"></p>&ndash;&gt;-->
      <!--&lt;!&ndash;<el-button type="success" @click="goShow">show</el-button>&ndash;&gt;-->
    <!--&lt;!&ndash;</div>&ndash;&gt;-->

  <!--</div>-->
<!--</template>-->
<!--<script>-->



<!--import { myGetToken } from '../api/wtoken'-->
<!--import Cookies from 'js-cookie'-->

<!--export default {-->
  <!--name: 'About',-->
  <!--data () {-->
    <!--return {-->
      <!--getRes: '结果集合',-->
      <!--loginForm: {-->
        <!--username: 'xiong',-->
        <!--password: 'man69',-->
        <!--verifyCode: ''-->
      <!--},-->
      <!--loginRules: {-->
        <!--username: [-->
          <!--{ required: true, message: '请输入用户名称', trigger: 'blur' },-->
          <!--{ min: 4, max: 9, message: '长度在4-9', trigger: 'blur' }-->
        <!--],-->
        <!--password: [-->
          <!--{ required: true, message: '请输入密码', trigger: 'blur' },-->
          <!--{ min: 4, max: 9, message: '长度在4-9', trigger: 'blur' }-->
        <!--]-->

      <!--}-->
    <!--}-->
  <!--},-->

  <!--methods: {-->
    <!--submitForm (formName) {-->
      <!--this.$refs[formName].validate((valid) => {-->
        <!--if (valid) {-->
          <!--myGetToken(formName).then(res => {-->



            <!--this.getRes = res.headers.authorization-->
            <!--console.log('获取token')-->
            <!--Cookies.set('mytk', res.headers.authorization)-->

            <!---->


            <!--this.$router.push({-->
              <!--name: 'demo01',-->
            <!--})-->



          <!--})-->
        <!--} else {-->
          <!--console.log('error submit!!')-->
          <!--return false-->
        <!--}-->
      <!--})-->
    <!--},-->
    <!--resetForm (formName) {-->
      <!--this.$refs[formName].resetFields()-->
    <!--}-->

  <!--}-->

<!--}-->

<!--</script>-->

<!--<style >-->

<!--</style>-->
