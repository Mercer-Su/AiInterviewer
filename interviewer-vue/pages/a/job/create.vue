<template>
    <div class="orderCreate-box">

        <el-tabs v-model="activeTab" type="border-card">
            <el-tab-pane :label="activeTitle" name="createJob">

                <div class="table-wrapper">

                    <el-form ref="jobForm" :rules="rules" :model="jobForm" label-width="140px">
                        <el-form-item label="职位名" prop="jobName">
                            <el-input v-model="jobForm.jobName" style="width: 360px;"></el-input>
                        </el-form-item>

                        <el-form-item label="职位描述" prop="jobDesc">
                            <el-input
                                type="textarea"
                                :rows="3"
                                placeholder="请输入内容"
                                style="width: 360px;"
                                v-model="jobForm.jobDesc">
                                </el-input>
                        </el-form-item>

                        <el-form-item label="状态" prop="status">
                            <el-radio class="blank-left" v-model="jobForm.status" label="1" style="margin-left: 30px;">开启</el-radio>
                            <el-radio v-model="jobForm.status" label="0">关闭</el-radio>
                        </el-form-item>

                        <el-form-item label="面试官" prop="interviewerId">
                            <el-select v-model="jobForm.interviewerId" placeholder="请选择面试官" style="width: 360px;">
                                <!-- <el-option label="觉哥技术团队" value="1"></el-option>
                                <el-option label="郑耀先" value="2"></el-option>
                                <el-option label="周志乾" value="3 "></el-option> -->
                                <el-option
                                    v-for="item in interviewerList"
                                    :key="item.id"
                                    :label="item.aiName"
                                    :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="提示词前缀" prop="prompt">
                            <el-input
                                type="textarea"
                                :rows="5"
                                placeholder="请输入内容"
                                style="width: 360px;"
                                v-model="jobForm.prompt">
                                </el-input>
                        </el-form-item>
                        

                        <el-form-item>
                            <el-button type="primary" @click="confirmSubmit">立即提交</el-button>
                            <el-button @click="cleanForm">取消</el-button>
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
            activeTab: "createJob",
            activeTitle: "",

            jobForm: {
                id: "",
                jobName: "",
                jobDesc: "",
                status: "",
                interviewerId: "",
                prompt: "",
            },

            rules: {
                jobName: [
                    { required: true, message: '请填写职位名称', trigger: 'blur' },
                ],
                jobDesc: [
                    { required: true, message: '请填写职位描述', trigger: 'blur' }
                ],
                status: [
                    { required: true, message: '请选择职位的当前状态', trigger: 'blur' },
                ],
                interviewerId: [
                    { required: true, message: '请选择负责该职位的面试官', trigger: 'blur' }
                ],
                prompt: [
                    { required: true, message: '请填入提示词供AI智能分析面试结果', trigger: 'blur' }
                ],
            },

            interviewerList: [],
        }
    },
    mounted() {
        var me = this;

        var jobId = this.$route.query.jobId;
        console.log("jobId = " + jobId);

        if (app.isNotEmpty(jobId)) {
            var params = {
                jobId: jobId
            }

            me.activeTitle = "编辑职位"

            this.getJobDetail(jobId);
        } else {
            me.activeTitle = "创建职位"
        }

        this.initInterviewerList();
    },
    methods: {

        getJobDetail(id) {

            jobApi.detail(id).then(response => {
                // console.log(response);
                var job = response.data;
                job.status = job.status.toString();
                this.jobForm = job;
                console.log(job);

                // this.jobForm = {
                //     id: job.id,
                //     jobName: job.jobName,
                //     jobDesc: job.jobDesc,
                //     status: job.status,
                //     interviewerId: job.interviewerId,
                //     prompt: job.prompt,
                // }
            });

        },
        
        confirmSubmit() {
            var jobForm = this.jobForm;

            var formName = "jobForm";
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
            var bo = this.jobForm;
            console.log(bo);

            jobApi.createOrUpdate(bo).then(response => {
                console.log(response);

                this.$message({
                    showClose: true,
                    message: '职位信息保存成功!',
                    type: 'success',
                    duration: 2000
                });

                this.cleanForm();
            });
        },

        initInterviewerList() {
            interviewerApi.list().then(response => {
                // console.log(response);
                var interviewerList = response.data;
                this.interviewerList = interviewerList;
            });
        },

        cleanForm() {
            this.jobForm = {
                id: "",
                jobName: "",
                jobDesc: "",
                status: "",
                interviewerId: "",
                prompt: "",
            }
        }
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