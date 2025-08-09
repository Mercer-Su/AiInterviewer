<template>
    <div class="carouselContentCreate-box">

        <el-tabs v-model="activeTab" type="border-card">
            <el-tab-pane label="数字人面试官管理" name="carouselContentCreate">

                <div class="table-wrapper">

                    <el-form ref="interviewerForm" :model="interviewerForm" :rules="rules" label-width="140px">
                        <el-form-item label="数字人形象图" prop="link">
                            <div style="display: flex; flex-direction: row; justify-content: flex-start;">
                                <el-upload
                                    drag
                                    class="course-cover-uploader"
                                    action=""
                                    :http-request="p => uploadAI(p)"
                                    :show-file-list="false">
                                    <i class="el-icon-upload" style="margin: 70px 0 16px;"></i>
                                    <div class="el-upload__text">将形象图拖拽此处<br/><em>点击上传</em></div>
                                    <div class="el-upload__tip" slot="tip" style="margin-top: -16px;">只能上传PNG或JPG文件, 且不超过2MB。建议上传 540*960、720*1280或1080*1920px 的图片</div>
                                </el-upload>
                                <img v-if="interviewerForm.link" :src="interviewerForm.link" class="course-cover">
                            </div>
                        </el-form-item>

                        <el-form-item label="形象图链接" prop="image">
                            <el-input v-model="interviewerForm.image" style="width: 500px;" maxlength="255" show-word-limit>
                                <template slot="prepend"><i class="el-icon-picture"></i></template>
                            </el-input>
                        </el-form-item>

                        <el-form-item label="面试官名称" prop="aiName">
                            <el-input v-model="interviewerForm.aiName" style="width: 500px;" maxlength="12" show-word-limit>
                                <template slot="prepend"><i class="el-icon-s-custom"></i></template>
                            </el-input>
                        </el-form-item>

                        <el-form-item>
                            <el-button type="primary" @click="confirmSubmit">保 存</el-button>
                            <el-button @click="cleanForm">取 消</el-button>
                        </el-form-item>
                    </el-form>

                </div>

                <div class="table-wrapper">

                    <el-table :data="interviewerList" border stripe class="table-list">
                        <el-table-column type="index" header-align="center" align="center" width="50"></el-table-column>
                        <el-table-column label="数字人形象" header-align="center" align="center" width="240">
                            <template slot-scope="scope">
                                <a :href="scope.row.image" target="_blank"><img :src="scope.row.image" class="list-course-cover"></a>
                            </template>
                        </el-table-column>
                        <el-table-column label="面试官名称" header-align="center" align="center" min-width="120">
                            <template slot-scope="scope">
                                {{scope.row.aiName}}
                            </template>
                        </el-table-column>

                        <el-table-column prop="createTime" label="创建时间" width="220" header-align="center" align="center"></el-table-column>
                        <el-table-column prop="updatedTime" label="更新时间" width="220" header-align="center" align="center"></el-table-column>
                        
                        <el-table-column label="操作" width="220" header-align="center" align="center">
                            <template slot-scope="scope">
                                <el-button type="primary" size="mini" plain @click="modifyInterviewer(scope.row.id)">编辑</el-button>
                                <el-button type="primary" size="mini" plain @click="deleteInterviewer(scope.row.id)">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>

                </div>

            </el-tab-pane>
        </el-tabs>

    </div>
</template>

<script>
module.exports = {
    data() {
        return {
            activeTab: "carouselContentCreate",
            url: "",

            interviewerForm: {
                id: "", 
                link: "",
                image: "",
                aiName: "",
            },

            rules: {
                image: [
                    { required: true, message: '请上传面试官的形象图或填入外部链接', trigger: 'blur' },
                ],
                aiName: [
                    { required: true, message: '面试官的名称不能为空', trigger: 'blur' }
                ],
            },

            interviewerList: [],
            // interviewerList: [{
            //     id: "1001", 
            //     link: "",
            //     image: "https://www.itzixi.com/files/course/cover/38b3c8e5-4ae5-4fb3-a413-68fe9f35e812.png",
            //     aiName: "觉哥技术团队",
            //     createTime: "2023-12-12 12:15:15",
            //     updatedTime: "2023-12-12 12:15:15",
            // },{
            //     id: "1002", 
            //     link: "",
            //     image: "https://www.itzixi.com/files/course/cover/38b3c8e5-4ae5-4fb3-a413-68fe9f35e812.png",
            //     aiName: "郑耀先",
            //     createTime: "2023-12-12 12:15:15",
            //     updatedTime: "2023-12-12 12:15:15",
            // }]
        }
    },
    mounted() {
        var me = this;

        this.initInterviewerList();
    },
    methods: {
        deleteInterviewer(interviewerId) {
            this.$confirm('确认删除该面试官吗？', '提示', {
                confirmButtonText: '确认删除',
                cancelButtonText: '容我三思',
                type: 'success'
            }).then(() => {
                var params = {
                    interviewerId: interviewerId
                }
                interviewerApi.deleteInterviewer(params).then(response => {
                    // console.log(response);

                    this.$message({
                        showClose: true,
                        message: '面试官删除成功!',
                        type: 'success',
                        duration: 2000
                    });

                    this.cleanForm();
                    this.initInterviewerList();
                });

            }).catch(() => {
            });
        },

        modifyInterviewer(interviewerId) {
            var me = this;
            var interviewerList = this.interviewerList;
            for (var i = 0 ; i < interviewerList.length ; i++) {
                var tmp = interviewerList[i];
                if (tmp.id == interviewerId) {
                    me.interviewerForm = tmp;
                    me.interviewerForm.link = tmp.image;
                    break;
                }
            }
            this.initInterviewerList();
        },

        initInterviewerList() {
            interviewerApi.list().then(response => {
                // console.log(response);
                var interviewerList = response.data;
                this.interviewerList = interviewerList;
            });
        },

        uploadAI(params) {
            console.log("自定义上传", params);

            const file = params.file;

            var isOK = this.beforeAIUpload(file);
            if (!isOK) {
                console.log("拦截... isOK = " + isOK);
                return;
            }

            // 封装FormData对象
            var formData = new FormData();
            formData.append("file", params.file);
            
            interviewerApi.uploadInterviewerImage(formData).then(response => {
                console.log(response);
                var faceUrl = response.data;
                this.interviewerForm.link = faceUrl;
                this.interviewerForm.image = faceUrl;
                console.log(faceUrl);
            });
        },

        beforeAIUpload2(file) {
            const isMP4 = file.type === 'video/mp4';
            const isLtSize = file.size / 1024 / 1024 < 10;

            if (!isMP4) {
                this.$message.error('上传数字人只能是 MP4 格式!');
            }
            if (!isLtSize) {
                this.$message.error('上传数字人大小不能超过 10MB!');
            }
            return isMP4 && isLtSize;
        },

        beforeAIUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isPNG = file.type === 'image/png';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG && !isPNG) {
                this.$message.error('上传头像图片只能是 JPG或PNG 格式!');
                return false;
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
                return false;
            }
            return (isJPG || isPNG) && isLt2M;
        },

        confirmSubmit() {
            var interviewerForm = this.interviewerForm;

            var formName = "interviewerForm";
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
            var bo = this.interviewerForm;
            console.log(bo);

            interviewerApi.createOrUpdate(bo).then(response => {
                console.log('11'+response);

                this.$message({
                    showClose: true,
                    message: '数字人面试官保存成功!',
                    type: 'success',
                    duration: 2000
                });
                console.log('22'+response);
                this.cleanForm();
                console.log('33'+response);
                this.initInterviewerList();
                console.log('44'+response);
            });
        },

        cleanForm() {
            this.interviewerForm = {
                id: "", 
                link: "",
                image: "",
                aiName: "",
            }
        }

    },
}
</script>

<style>
.carouselContentCreate-box {
    /* padding: 10px; */

    display: flex;
    flex-direction: column;
    justify-content: flex-start;

    /* border: solid 10px #e3e9ef; */

    font-size: 16px;
}

.carouselContentCreate-box .table-wrapper {
    padding: 10px 10px 20px 10px;
}

.carouselContentCreate-box .table-list {
    width: 100%;
    font-size: 15px;
}

.carouselContentCreate-box .search-area {
    margin-bottom: 20px;
}

/* .carouselContentCreate-box .course-cover-uploader {
    width: 260px;  360  400
    height: 160px;      245
} */

.carouselContentCreate-box .el-upload-dragger {
    width: 180px;
    height: 320px;
}

.carouselContentCreate-box .course-cover {
    /* width: 260px;
    height: 160px; */
    /* width: 360px;
    height: 220px; */
    width: 180px;
    height: 320px;
    margin-left: 10px;
    border-radius: 6px;
}

.list-course-cover {
    width: 135px;
    height: 240px;
    border-radius: 6px;
}

</style>