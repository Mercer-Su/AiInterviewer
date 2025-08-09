<template>
    <div class="userinfo-box">

        <div class="userinfo-desc-wrapper">
            <div style="font-size: 28px; font-weight: bold;">账号信息</div>
            <div style="font-size: 12px;">账号相关内容设置</div>
        </div>

        <div class="userinfo-content-wrapper">

            <div style="padding: 16px 20px; background-color: #fff; width: 260px; display: flex; flex-direction: column; justify-content: flex-start;">
                <img src="../../img/arrow.png" width="100px" style="border-radius: 50%; margin-top: 10px; align-self: center;"/>
                <div style="color: #000; font-size: 16px; align-self: center; margin-top: 10px;">觉哥技术团队</div>
                <div style="font-size: 14px; align-self: center; margin-top: 2px; ">ID: 123456789123456789</div>
                <div class="vip-status">VIP状态: 2024-12-25 到期</div>
                <div class="dashed-line"></div>
            </div>

            <div class="account-info-desc">
                <!-- 上 -->
                <div class="account-info-up">
                    <div class="user-account">账号信息</div>
                    <div class="account-split-line"></div>
                    
                    <el-form ref="form" :model="form" label-width="80px" class="account-form">

                        <el-row :gutter="25">
                            <el-col :span="10" >
                                <el-form-item label="昵称">
                                    <el-input class="blank-left"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row :gutter="25">
                            <el-col :span="10">
                                <el-form-item label="手机号">
                                    <span class="blank-left">139****6688</span>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row :gutter="25">
                            <el-col :span="10">
                                <el-form-item label="性别">
                                    <el-radio class="blank-left" v-model="form.sex" label="1">男</el-radio>
                                    <el-radio v-model="form.sex" label="0">女</el-radio>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row :gutter="25">
                            <el-col :span="10">
                                <el-form-item label="个性签名">
                                    <el-input class="blank-left"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        

                        <!-- <el-row :gutter="25">
                            <el-col :span="10" >
                                <el-form-item label="昵称">
                                    <el-input class="blank-left"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="10">
                                <el-form-item label="性别">
                                    <el-radio class="blank-left" v-model="form.sex" label="1">男</el-radio>
                                    <el-radio v-model="form.sex" label="0">女</el-radio>
                                </el-form-item>
                            </el-col>
                        </el-row> -->
                        
                        <el-form-item>
                            <el-button type="primary">修改信息</el-button>
                            <el-button>取消</el-button>
                        </el-form-item>
                    </el-form>
                </div>

                <!-- 下 -->
                <div class="account-info-down">

                </div>
            </div>

        </div>

    </div>
</template>

<script>
module.exports = {
    data() {
        return {
            form: {
                name: '',
            }
        }
    }
}
</script>

<style>
.userinfo-desc-wrapper {
    margin-top: 14px;
    
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
}

.userinfo-content-wrapper {
    margin-top: 20px;

    border: solid 10px #e3e9ef;
    background-color: #eff4f9;

    display: flex;
    flex-direction: row;
    justify-content: flex-start;
}

.vip-status {
    font-size: 14px; 
    align-self: center; 
    margin-top: 2px; 
    color: #ffa419;
    font-weight: 400;
}

.dashed-line {
    margin-top: 10px;
    border-bottom: 1.5px dashed #d8d8d8;
}

.account-info-desc {
    flex-grow: 1; 
    padding: 20px 30px;
}

.blank-left {
    margin-left: 10px;
}

.account-info-up {
    background-color: #fff; 
    height: 500px; 
    display: flex; 
    flex-direction: column;
    justify-content: flex-start;
}

.user-account {
    height: 50px; 
    width: 100%; 
    font-size: 16px; 
    font-weight: bold; 
    display: flex; 
    flex-direction: column; 
    justify-content: center;
    margin-left: 20px;
}

.account-split-line {
    width: 100%; 
    border-bottom: 1px solid #eceaea;
}

.account-form {
    padding: 30px 0;
}

</style>