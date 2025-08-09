<template>
    <div class="allQuestionList-box">

        <el-tabs v-model="activeTab" type="border-card">
            <el-tab-pane label="面试题库" name="allTechTagsList">

                <div class="table-wrapper">

                    <div class="search-area">
                        <el-input placeholder="面试官" v-model="searchBO.aiName" prefix-icon="el-icon-user" clearable style="width: 200px; margin-right: 10px; margin-bottom: 8px;"></el-input>
                        <el-input placeholder="面试题" v-model="searchBO.question" prefix-icon="el-icon-folder-opened" clearable style="width: 200px; margin-right: 10px; margin-bottom: 8px;"></el-input>
                        <el-button type="primary" size="middle" @click="searchQuestion()">搜索</el-button>

                        <el-button type="primary" plain size="middle" @click="createQuestion()">新增面试题</el-button>
                    </div>

                    <el-table :data="questionList" border stripe class="table-list">
                        <el-table-column type="index" header-align="center" align="center" width="50"></el-table-column>
                        <el-table-column label="面试题" header-align="center" align="left" min-width="180">
                            <template slot-scope="scope">{{scope.row.question}}</template>
                        </el-table-column>

                        <el-table-column label="参考答案" header-align="center" align="left" min-width="220">
                            <template slot-scope="scope">{{scope.row.referenceAnswer}}</template>
                        </el-table-column>

                        <el-table-column prop="interviewerName" label="面试官" width="160" header-align="center" align="center"></el-table-column>

                        <el-table-column label="数字人视频" header-align="center" align="center" width="140">
                            <template slot-scope="scope">
                                <!-- <a :href="scope.row.aiSrc" target="_blank">查看</a> -->
                                <video v-if="scope.row.aiSrc" :src="scope.row.aiSrc" controls :autoplayNew="false" class="ai-video">
                                    <!-- <source :src="scope.row.aiSrc" type="video/mp4" /> -->
                                </video>
                            </template>
                        </el-table-column>

                        <el-table-column label="启用" header-align="center" align="center" width="100">
                            <template slot-scope="scope">
                                <el-switch
                                    :value="scope.row.isOn"
                                    :active-value="activeValue"
                                    :inactive-value="inactiveValue"
                                    @change="setQuestionDisplay(scope.row.questionLibId, scope.row.isOn)">
                                </el-switch>
                            </template>
                        </el-table-column>
                        
                        <!-- <el-table-column prop="dateTime" label="创建时间" width="190" header-align="center" align="center"></el-table-column> -->
                        <!-- <el-table-column prop="dateTime" label="更新时间" width="190" header-align="center" align="center"></el-table-column> -->

                        <el-table-column label="操作" width="200" header-align="center" align="center">
                            <template slot-scope="scope">
                                <el-button type="info" size="mini" plain @click="modifyQuestion(scope.row.questionLibId)">修改</el-button>
                                <el-button type="danger" size="mini" plain @click="deleteQuestion(scope.row.questionLibId)">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>

                </div>

                <el-pagination
                    background
                    @current-change="doPageList"
                    layout="total, prev, pager, next"
                    :page-size="questionLibListPageInfo.pageSize"
                    :total="questionLibListPageInfo.records">
                    </el-pagination>

            </el-tab-pane>
        </el-tabs>

        <el-dialog :title="titleOperate" :visible.sync="dialogQuestionVisible" top="3vh" width="50%" >
            
            <el-form ref="questionForm" :model="questionForm" :rules="rules">

                <el-form-item label="关联面试官" prop="interviewerId" label-width="140px">
                    <el-select v-model="questionForm.interviewerId" placeholder="请选择面试官" style="width: 360px;">
                        <el-option
                            v-for="item in interviewerList"
                            :key="item.id"
                            :label="item.aiName"
                            :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="面试题" prop="question" label-width="140px">
                    <el-input v-model="questionForm.question" style="width: 680px;"></el-input>
                </el-form-item>

                <el-form-item label="参考答案" prop="referenceAnswer" label-width="140px">
                    <el-input
                                type="textarea"
                                :rows="6"
                                placeholder="请输入内容"
                                style="width: 680px;"
                                v-model="questionForm.referenceAnswer">
                                </el-input>
                </el-form-item>

                <el-form-item label="数字人口播视频" prop="aiSrc" label-width="140px">
                    <div style="display: flex; flex-direction: row; justify-content: flex-start;">
                        <el-upload
                            drag
                            class="course-cover-uploader"
                            action=""
                            :http-request="p => uploadVideo(p)"
                            :show-file-list="false">
                            <i class="el-icon-upload" style="margin: 70px 0 16px;"></i>
                            <div class="el-upload__text">将视频拖拽到此处<br/><em>点击上传</em></div>
                            <div class="el-upload__tip" slot="tip" style="margin-top: -16px;">只能上MP4文件, 且不超过10兆。建议上传 540*960、720*1280或1080*1920px 的视频</div>
                        </el-upload>
                        <!-- <img v-if="questionForm.aiSrc" :src="questionForm.aiSrc" class="course-cover"> -->

                        <!-- {{ questionForm.aiSrc }} -->
                        <video v-if="questionForm.aiSrc" :src="questionForm.aiSrc" id="myVideo" controls :autoplayNew="false" class="course-cover">
                            <!-- <source :src="questionForm.aiSrc" type="video/mp4" /> -->
                            <!-- <source src="http://127.0.0.1:9000/itzixi/interview/8fdaa25f-abdb-410f-8a9d-298308c55d45.mp4" type="video/mp4" /> -->
                        </video>

                    </div>
                </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogQuestionVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitQuestion">保存</el-button>
            </div>
        </el-dialog>
        
    </div>
</template>

<script>
module.exports = {
    data() {
        return {
            activeTab: "allTechTagsList",

            search: {
                question: "",
                referenceAnswer: "",
                interviewerId: "",
                aiSrc: "",
            },

            titleOperate: "新增面试题",
            dialogQuestionVisible: false,

            dialogTagsSortVisible: false,
            questionForm: {
                id: "",
				question: "",
                referenceAnswer: "",
                interviewer: "",
                interviewerId: "",
                aiSrc: "",
            },

            rules: {
                question: [
                    { required: true, message: '请填入面试题标题', trigger: 'blur' },
                ],
                referenceAnswer: [
                    { required: true, message: '请填入面试题参考答案', trigger: 'blur' }
                ],
                interviewerId: [
                    { required: true, message: '请选择需要关联的面试官', trigger: 'blur' }
                ],
                aiSrc: [
                    { required: true, message: '请上传面试题的口播视频', trigger: 'blur' }
                ],
            },

            activeValue: 1,
            inactiveValue: 0,

            questionList: [],
            questionLibListPageInfo: {
                page: 1,
                pageSize: 10,
                records: 0,     // 总记录数
                total: 0,       // 总页数
            },
            searchBO: {
                page: 0,
                pageSize: 0,
                aiName: "",
                question: "",
            },

            interviewerList: [],
        }
    },
    mounted() {
        this.initInterviewerList();
        this.initQuestionLibList(1, this.questionLibListPageInfo.pageSize);
    },
    methods: {

        initQuestionLibList(page, pageSize) {

            var searchBO = this.searchBO;
            searchBO.page = page;
            searchBO.pageSize = pageSize;

            console.log(searchBO);

            questionLibApi.list(searchBO).then(response => {
                console.log(response);
                var questionList = response.data.rows;
                this.questionList = questionList;

                this.questionLibListPageInfo.records = response.data.records;
                this.questionLibListPageInfo.total = response.data.total;
                this.questionLibListPageInfo.page = page;
                this.questionLibListPageInfo.pageSize = pageSize;
            });
        },

        doPageList(page) {
            this.initQuestionLibList(page, this.questionLibListPageInfo.pageSize);
        },

        initInterviewerList() {
            interviewerApi.list().then(response => {
                // console.log(response);
                var interviewerList = response.data;
                this.interviewerList = interviewerList;
            });
        },

        submitQuestion2() {
            var bo = this.questionForm
            console.log(bo);

            this.dialogQuestionVisible = false;
        },

        submitQuestion() {
            var questionForm = this.questionForm;

            var formName = "questionForm";
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
            var bo = this.questionForm;
            console.log(bo);

            questionLibApi.createOrUpdate(bo).then(response => {
                console.log(response);

                this.$message({
                    showClose: true,
                    message: '面试题保存成功!',
                    type: 'success',
                    duration: 2000
                });

                // this.questionLibListPageInfo = {
                //     page: 1,
                //     pageSize: 10,
                //     records: 0,     // 总记录数
                //     total: 0,       // 总页数
                // };
                this.initQuestionLibList(this.questionLibListPageInfo.page, this.questionLibListPageInfo.pageSize);
            });

            this.dialogQuestionVisible = false;
        },

        searchQuestion() {
            var bo = this.searchBO;
            console.log(bo);

            this.initQuestionLibList(1, this.questionLibListPageInfo.pageSize);
        },

        uploadVideo(params) {
            console.log("自定义上传", params);

            const file = params.file;

            var isOK = this.beforeVideoUpload(file);
            if (!isOK) {
                console.log("拦截... isOK = " + isOK);
                return;
            }

            // 封装FormData对象
            var formData = new FormData();
            formData.append("file", params.file);
            
            questionLibApi.uploadInterviewVideo(formData).then(response => {
                console.log(response);
                var videoUrl = response.data;
                this.questionForm.aiSrc = videoUrl;
                // console.log(faceUrl);
            });
        },

        beforeVideoUpload(file) {
            const isMP4 = file.type === 'video/mp4';
            const isLtSize = file.size / 1024 / 1024 < 10;

            if (!isMP4) {
                this.$message.error('上传数字人的口播视频只能是 MP4 格式!');
            }
            if (!isLtSize) {
                this.$message.error('上传数字人的口播视频大小不能超过 10MB!');
            }
            return isMP4 && isLtSize;
        },

        deleteQuestion(questionLibId) {
            this.$confirm('确认删除该面试题吗？', '提示', {
                confirmButtonText: '确认删除',
                cancelButtonText: '容我三思',
                type: 'success'
            }).then(() => {
                var params = {
                    questionLibId: questionLibId
                }
                questionLibApi.delete(params).then(response => {
                    console.log(response);

                    this.$message({
                        showClose: true,
                        message: '面试题删除成功!',
                        type: 'success',
                        duration: 2000
                    });

                    this.clearForm();
                    this.initQuestionLibList(1, this.questionLibListPageInfo.pageSize);
                });

            }).catch(() => {
            });
        },

        setQuestionDisplay(questionId, isDisplay) {
            // console.log("questionId = " + questionId);
            // console.log("isDisplay = " + isDisplay);

            if (isDisplay == 1) {
                isDisplay = 0;
                // 取消置顶
                var params = {
                    questionLibId: questionId
                };
                questionLibApi.hide(params).then(response => {
                });
            } else if (isDisplay == 0) {
                isDisplay = 1;
                // 设置置顶
                var params = {
                    questionLibId: questionId
                };
                questionLibApi.show(params).then(response => {
                });
            }

            // 修改设置成功后，再次重新查询
            this.refreshQuestionList(questionId, isDisplay) 
        },

        refreshQuestionList(questionId, isDisplay) {
            console.log("questionId = " + questionId);
            console.log("isDisplay = " + isDisplay);
            
            var questionList = this.questionList;
            for (var i = 0; i < questionList.length; i++) {
                var question = questionList[i];
                if (question.questionLibId == questionId) {
                    question.isOn = isDisplay;
                    break;
                }
            }
        },

        createQuestion() {
            this.titleOperate = "新增面试题";
            this.dialogQuestionVisible = true;
            this.clearForm();
        },

        clearForm() {
            this.questionForm = {
                id: "",
				question: "",
                referenceAnswer: "",
                interviewer: "",
                aiSrc: "",
            }
        },

        modifyQuestion(questionId) {
            console.log("questionId = " + questionId);

            var me = this;
            me.titleOperate = "修改面试题";

            // this.clearForm();
            
            var questionList = this.questionList;
            for (var i = 0 ; i < questionList.length ; i++) {
                var tmp = questionList[i];
                if (tmp.questionLibId == questionId) {
                    me.questionForm = tmp;
                    me.questionForm.id = questionId;
                    break;
                }
            }

            this.dialogQuestionVisible = true;
        },

        deleteTag(tagId) {
            this.$confirm('确认删除该标签吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$message.success('标签删除成功!');
            }).catch(() => {
            });
        },

        setSort() {
            this.dialogTagsSortVisible = true;
        },

        modifyCommentSort(commentId) {
            this.dialogTopicCommentVisible = true;
        },

    },
}
</script>

<style>
.allTechTagsList-box {
    /* padding: 10px; */

    display: flex;
    flex-direction: column;
    justify-content: flex-start;

    /* border: solid 10px #e3e9ef; */

    font-size: 16px;
}

.allTechTagsList-box .table-wrapper {
    padding: 10px 10px 20px 10px;
}

.allTechTagsList-box .table-list {
    width: 100%;
    font-size: 15px;
}

.allTechTagsList-box .search-area {
    margin-bottom: 20px;
}

.allTechTagsList-box .review-topic-title {
    color: #262626;
    font-size: 36px;
    font-weight: 700;

    padding: 10px 20px;
}

.allTechTagsList-box .review-topic-detail {
    padding: 20px;
}

.allTechTagsList-box .topic-comments {
    overflow: hidden;
    text-overflow: ellipsis;
    word-break: break-word;
    display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
}

.ai-video {
    width: 90px;
    height: 160px;
    border-radius: 6px;
}

.allQuestionList-box .el-upload-dragger {
    width: 180px;
    height: 320px;
}

.allQuestionList-box .course-cover {
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