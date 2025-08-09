<template>
    <div class="orderCreate-box">

        <el-tabs v-model="activeTab" type="border-card">
            <el-tab-pane :label="activeTitle" name="createCandidate">

                <div class="table-wrapper">

                    <el-form ref="candidateForm" :model="candidateForm" :rules="rules" label-width="140px">
                        <el-form-item label="面试岗位" prop="jobId">
                            <el-select v-model="candidateForm.jobId" placeholder="请选择面试岗位" style="width: 360px;">
                                <!-- <el-option label="Java高级工程师" value="1"></el-option>
                                <el-option label="Java架构师" value="2"></el-option>
                                <el-option label="技术总监" value="3 "></el-option> -->
                                <el-option
                                    v-for="item in jobNameList"
                                    :key="item.jobId"
                                    :label="item.jobName"
                                    :value="item.jobId">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="姓名" prop="realName">
                            <el-input v-model="candidateForm.realName" style="width: 360px;" maxlength="12"></el-input>
                        </el-form-item>
                        <el-form-item label="身份证号码" prop="identityNum">
                            <el-input v-model="candidateForm.identityNum" style="width: 360px;" maxlength="18"></el-input>
                        </el-form-item>
                        <el-form-item label="性别" prop="sex">
                            <el-select v-model="candidateForm.sex" placeholder="请选择性别" style="width: 360px;">
                                <el-option label="保密" value="2"></el-option>
                                <el-option label="女" value="0"></el-option>
                                <el-option label="男" value="1"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="手机号" prop="mobile">
                            <el-input v-model="candidateForm.mobile" style="width: 360px;" maxlength="11"></el-input>
                        </el-form-item>
                        <el-form-item label="邮箱" prop="email">
                            <el-input v-model="candidateForm.email" style="width: 360px;"></el-input>
                        </el-form-item>

                        <el-form-item label="出生日期">
                            <el-date-picker value-format="yyyy-MM-dd" type="datetime" placeholder="请选择出生日期" v-model="candidateForm.birthday" style="width: 360px;"></el-date-picker>
                        </el-form-item>
                        

                        <el-form-item>
                            <el-button type="primary" @click="createCandidate">立即提交</el-button>
                            <el-button>取消</el-button>
                        </el-form-item>
                    </el-form>

                </div>

            </el-tab-pane>
        </el-tabs>

    </div>
</template>

<script>

module.exports = {
    data() {
        return {
            activeTab: "createCandidate",
            activeTitle: "",

            candidateForm: {
                jobId: "",
                realName: "",
                identityNum: "",
                sex: "",
                mobile: "",
                email: "",
                birthday: "",
            },
            jobNameList: [],

            rules: {
                jobId: [
                    { required: true, message: '请选择面试岗位', trigger: 'blur' },
                ],
                realName: [
                    { required: true, message: '姓名不能为空', trigger: 'blur' },
                ],
                identityNum: [
                    { required: true, message: '身份证号码不能为空', trigger: 'blur' },
                ],
                sex: [
                    { required: true, message: '性别不能为空', trigger: 'blur' },
                ],
                mobile: [
                    { required: true, message: '手机号不能为空', trigger: 'blur' },
                ],
                email: [
                    { required: true, message: '邮箱不能为空', trigger: 'blur' },
                ],
            },
        }
    },
    mounted() {
        var me = this;

        var candidateId = this.$route.query.candidateId;
        console.log("candidateId = " + candidateId);

        if (app.isNotEmpty(candidateId)) {
            var params = {
                candidateId: candidateId
            }

            me.activeTitle = "编辑候选人"

            this.getCandidate(candidateId);
        } else {
            me.activeTitle = "创建候选人"
        }

        this.initJobNameList();

    },
    methods: {
        getCandidate(candidateId) {
            candidateApi.detail(candidateId).then(response => {
                // console.log(response);
                
                var candidate = response.data;
                candidate.sex = String(candidate.sex);

                console.log(candidate);

                this.candidateForm = candidate;
            });
        },

        createCandidate() {
            console.log(this.candidateForm);

            var candidateForm = this.candidateForm;

            var formName = "candidateForm";
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.$confirm('确认提交内容吗?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.submitContent();
                    }).catch(() => {
                    });;
                } else {
                    console.log('提交错误!!!');
                    return false;
                }
            });
            
        },

        submitContent() {
            var bo = this.candidateForm;
            console.log(bo);

            candidateApi.createOrUpdate(bo).then(response => {
                console.log(response);

                this.$message({
                    showClose: true,
                    message: '候选人信息保存成功!',
                    type: 'success',
                    duration: 2000
                });

                this.clearForm();
            });

        },

        clearForm() {
            this.candidateForm = {
                id: "",
                realName: "",
                identityNum: "",
                sex: "",
                mobile: "",
                email: "",
                birthday: "",
            }
        },

        initJobNameList() {
            jobApi.nameList().then(response => {
                console.log(response);
                var jobNameList = response.data;
                this.jobNameList = jobNameList;
            });
        },

    },
}
</script>

<style>
.orderCreate-box {
    /* padding: 10px; */

    display: flex;
    flex-direction: column;
    justify-content: flex-start;

    /* border: solid 10px #e3e9ef; */

    font-size: 16px;
}

.orderCreate-box .table-wrapper {
    padding: 10px 10px 20px 10px;
}

.orderCreate-box .table-list {
    width: 100%;
    font-size: 15px;
}

.orderCreate-box .search-area {
    margin-bottom: 20px;
}

</style>