<template>
    <div class="allSignList-box">

        <el-tabs v-model="activeTab" type="border-card">
            <el-tab-pane label="所有签到" name="allSignList">

                <div class="table-wrapper">

                    <!-- <el-empty 
                        description="暂无订单" 
                        image="../../img/none/no-orders.png" 
                        style="margin-top: 100px; margin-bottom: 200px;" 
                        image-size="300"></el-empty> -->

                    <div class="search-area">
                        <el-input placeholder="签到内容" v-model="search.userId" prefix-icon="el-icon-search" clearable style="width: 200px; margin-right: 10px; margin-bottom: 8px;"></el-input>
                        <el-input placeholder="发布人" v-model="search.userId" prefix-icon="el-icon-search" clearable style="width: 200px; margin-right: 10px; margin-bottom: 8px;"></el-input>
                        
                        <el-select v-model="search.value" placeholder="请选择" style="width: 200px; margin-right: 10px; margin-bottom: 8px;">
                            <el-option
                                v-for="item in search.options"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                        
                        <el-date-picker
                            v-model="search.commentDate"
                            value-format="yyyy-MM-dd"
                            type="daterange"
                            align="right"
                            unlink-panels
                            range-separator="至"
                            start-placeholder="发布日期"
                            end-placeholder="发布日期"
                            :picker-options="pickerOptions"
                            style="width: 360px; margin-right: 10px; margin-bottom: 8px;">
                        </el-date-picker>
                        <el-button type="primary" size="middle" @click="searchUser()">搜索</el-button>
                    </div>

                    <el-table :data="tableData" border stripe class="table-list">
                        <el-table-column type="index" header-align="center" align="center" width="50"></el-table-column>
                        <el-table-column label="心情" header-align="center" align="center" min-width="60">
                            <template slot-scope="scope">
                                <img :src="scope.row.face" style="width: 60px; height: 60px;"/>
                            </template>
                        </el-table-column>

                        <el-table-column label="签到内容" header-align="center" align="left" min-width="220">
                            <template slot-scope="scope">
                                <div class="topic-comments">{{scope.row.titleName}}</div>
                            </template>
                        </el-table-column>

                        <el-table-column prop="commentUser" label="发布人" width="160" header-align="center" align="center"></el-table-column>

                        <el-table-column label="审核状态" header-align="center" align="center" width="150">
                            <template slot-scope="scope">
                                <el-tag type="info" v-if="scope.row.status === 0">待审核</el-tag>
                                <el-tag type="success" v-if="scope.row.status === 1">审核通过</el-tag>
                                <el-tag type="danger" v-if="scope.row.status === 2">审核未通过</el-tag>
                            </template>
                        </el-table-column>

                        
                        <el-table-column prop="dateTime" label="发布时间" width="240" header-align="center" align="center"></el-table-column>

                        <el-table-column label="操作" width="240" header-align="center" align="center">
                            <template slot-scope="scope">
                                <el-button type="success" size="mini" plain @click="pass(scope.row.id)">通过</el-button>
                                <el-button type="danger" size="mini" plain @click="unpass(scope.row.id)">不通过</el-button>
                            </template>
                        </el-table-column>
                    </el-table>

                </div>

                <el-pagination background layout="prev, pager, next" :total="1000"></el-pagination>

            </el-tab-pane>
        </el-tabs>

    </div>
</template>

<script>
module.exports = {
    data() {
        return {
            activeTab: "allSignList",

            dialogTopicCommentVisible: false,
            bookForm: {
                title: "",
                sort: null,
            },

            search: {
                userId: "",
                orderId: "",
                commentDate: "",
                options: [{
                value: '-1',
                label: '全部'
                }, {
                value: '0',
                label: '待审核'
                }, {
                value: '1',
                label: '审核通过'
                }, {
                value: '2',
                label: '审核未通过'
                }],
                value: '-1'
            },

            pickerOptions: {
                shortcuts: [{
                    text: '最近一周',
                    onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                    picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近一个月',
                    onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                    picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近三个月',
                    onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                    picker.$emit('pick', [start, end]);
                    }
                }]
            },

            tableData: [{
                id: "1111",
				titleName: "java高级工程师像这样的一个主从架构可以保证高性能读以及高并发读，如果读库集群再次达到瓶颈则可以继续进行水平扩展",
                commentContent: "java高级工程师相当不错啊~！学到了很多技术啊，哈哈哈哈",
                time: 120,
                counts: 100,
                face: "http://127.0.0.1:5500/img/dailyFace/face_frowning.png",
				commentRate: 4,
                commentUser: "郑耀先",
				status: 1,  // 0 待审核 1 审核通过 2 审核未通过
                sort: 8,
                oldPrice: 2999,
                newPrice: 1599,
                dateTime: "2023-12-12 12:15:15",
			}, {
                id: "2222",
				titleName: "java架构师",
                commentContent: "觉哥技术团队",
                face: "http://127.0.0.1:5500/img/dailyFace/face_frowning.png",
                time: 120,
                counts: 100,
				commentRate: 3,
                commentUser: "四哥",
				status: 2,
                sort: 7,
                oldPrice: 3999,
                newPrice: 2599,
                dateTime: "2023-12-12 12:15:15",
			}, {
                id: "2222",
				titleName: "java架构师",
                commentContent: "觉哥技术团队",
                time: 120,
                counts: 100,
				commentRate: 3,
                commentUser: "四哥",
                face: "http://127.0.0.1:5500/img/dailyFace/face_frowning.png",
				status: 0,
                sort: 3,
                oldPrice: 3999,
                newPrice: 2599,
                dateTime: "2023-12-12 12:15:15",
			}]
        }
    },
    methods: {
        pass(commentId) {
            this.$message.success('该评论审核通过!');
        },

        unpass(commentId) {
            this.$message.success('该评论审核失败!');
        },

        modifyCommentSort(commentId) {
            this.dialogTopicCommentVisible = true;
        },

    },
}
</script>

<style>
.allSignList-box {
    /* padding: 10px; */

    display: flex;
    flex-direction: column;
    justify-content: flex-start;

    /* border: solid 10px #e3e9ef; */

    font-size: 16px;
}

.allSignList-box .table-wrapper {
    padding: 10px 10px 20px 10px;
}

.allSignList-box .table-list {
    width: 100%;
    font-size: 15px;
}

.allSignList-box .search-area {
    margin-bottom: 20px;
}

.allSignList-box .review-topic-title {
    color: #262626;
    font-size: 36px;
    font-weight: 700;

    padding: 10px 20px;
}

.allSignList-box .review-topic-detail {
    padding: 20px;
}

.allSignList-box .topic-comments {
    overflow: hidden;
    text-overflow: ellipsis;
    word-break: break-word;
    display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
}
</style>