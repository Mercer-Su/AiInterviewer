<template>
    <div class="orderlist-box">

        <el-tabs v-model="activeTab" type="border-card">
            <el-tab-pane label="职位列表" name="allJobList">

                <div class="table-wrapper">

                    <el-table :data="jobList" border stripe class="table-list">
                        <el-table-column type="index" header-align="center" align="center" width="50"></el-table-column>
                        <el-table-column label="职位名称" header-align="center" align="center" width="180">
                            <template slot-scope="scope">
                                {{scope.row.jobName}}
                            </template>
                        </el-table-column>
                        <el-table-column label="职位描述" header-align="center" align="center" width="220">
                            <template slot-scope="scope">
                                {{scope.row.jobDesc}}
                            </template>
                        </el-table-column>
                        <el-table-column label="状态" header-align="center" align="center" width="80">
                            <template slot-scope="scope">
                                <span><el-tag type="success" v-if="scope.row.status == 1">开启</el-tag></span>
                                <span><el-tag type="danger" v-if="scope.row.status == 0">关闭</el-tag></span>
                            </template>
                        </el-table-column>
                        <el-table-column label="面试官" header-align="center" align="center" width="160">
                            <template slot-scope="scope">
                                {{scope.row.interviewerName}}
                            </template>
                        </el-table-column>
                        <el-table-column label="提示词前缀" header-align="center" align="left" min-width="220">
                            <template slot-scope="scope">
                                {{scope.row.prompt}}
                            </template>
                        </el-table-column>
                        
                        <el-table-column prop="createTime" label="创建时间" width="180" header-align="center" align="center"></el-table-column>
                        <el-table-column prop="updatedTime" label="更新时间" width="180" header-align="center" align="center"></el-table-column>

                        <el-table-column label="操作" width="160" header-align="center" align="center">
                            <template slot-scope="scope">
                                <el-button type="primary" size="mini" plain @click="gotoModifyJob(scope.row.jobId)">编辑</el-button>
                                <el-button type="primary" size="mini" plain @click="deleteJob(scope.row.jobId)">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>

                </div>

                <el-pagination
                    background
                    @current-change="doPageList"
                    layout="total, prev, pager, next"
                    :page-size="jobListPageInfo.pageSize"
                    :total="jobListPageInfo.records">
                    </el-pagination>

            </el-tab-pane>
        </el-tabs>

    </div>
</template>

<script>

module.exports = {
    data() {
        return {
            activeTab: "allJobList",

            dialogUserInfoVisible: false,

            search: {
                userId: "",
                orderId: "",
            },

            jobList: [],
            jobListPageInfo: {
                page: 1,
                pageSize: 10,
                records: 0,     // 总记录数
                total: 0,       // 总页数
            },
            searchBO: {
            },
        }
    },
    mounted() {
        this.initJobList(1, this.jobListPageInfo.pageSize);
    },
    methods: {

        gotoModifyJob(jobId) {
            var targetPath = "/jobMng/jobCreate?jobId=" + jobId;
            this.$parent.$router.push({
                path: targetPath
            });
        },

        doPageList(page) {
            this.initJobList(page, this.jobListPageInfo.pageSize);
        },

        initJobList(page, pageSize) {

            console.log(page);

            var searchBO = this.searchBO;
            searchBO.page = page;
            searchBO.pageSize = pageSize;

            jobApi.list(searchBO).then(response => {
                console.log(response);
                var jobList = response.data.rows;
                this.jobList = jobList;

                this.jobListPageInfo.records = response.data.records;
                this.jobListPageInfo.total = response.data.total;
                this.jobListPageInfo.page = page;
                this.jobListPageInfo.pageSize = pageSize;
            });
        },

        deleteJob(jobId) {

            this.$confirm('确认删除该职位吗？', '提示', {
                confirmButtonText: '确认删除',
                cancelButtonText: '容我三思',
                type: 'warning'
            }).then(() => {

                
                jobApi.delete(jobId).then(response => {
                //     console.log(response);

                    this.$message({
                        showClose: true,
                        message: '职位删除成功!',
                        type: 'success',
                        duration: 2000
                    });

                    this.initJobList(1, this.jobListPageInfo.pageSize);
                });

            }).catch(() => {
            });

        },

    },
}
</script>

<style>
.orderlist-box {
    /* padding: 10px; */

    display: flex;
    flex-direction: column;
    justify-content: flex-start;

    /* border: solid 10px #e3e9ef; */

    font-size: 16px;
}

.orderlist-box .table-wrapper {
    padding: 10px 10px 20px 10px;
}

.orderlist-box .table-list {
    width: 100%;
    font-size: 15px;
}

.orderlist-box .search-area {
    margin-bottom: 20px;
}

</style>