<template>
    <div class="dashboard-box">
        
        <el-card shadow="always">
            <div slot="header" class="clearfix">
                <span class="pending-task-title">待处理任务</span>
            </div>

            <div class="all-tasks-wrapper">

                <div class="single-task">
                    <span class="every-title">欢迎来到后台管理</span>
                </div>

                

            </div>
        </el-card>
        
    </div>
</template>

<script>
module.exports = {
    data() {
        return {
            pendingCounts: {}
        }
    },
    mounted() {
        var me = this;

    },
    methods: {
        setPath(index, path) {
            // console.log("setPath index = " + index);
            this.$emit("changemenuformrouteview", index);
            this.$router.push({path: path});
            // this.$emit('updateProjectId','xxxxxx')
        }
    },
}
</script>

<style>
.dashboard-box {
    /* padding: 10px; */
    display: flex;
    flex-direction: column;
    justify-content: flex-start;

    /* border: solid 10px #e3e9ef; */
    font-size: 16px;
}

.dashboard-box .all-tasks-wrapper {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    flex-wrap: wrap;
    margin-top: 10px;
    margin-bottom: 10px;
}

.dashboard-box .pending-task-title {
    /* color: #8b8b8b; */
    color: #119e8f;
    font-weight: bold;
}

.dashboard-box .single-task {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    margin: 30px;
    /* margin-top: 10px; */
    /* margin-bottom: 10px; */

    width: 100px;
    padding: 50px;
    border: 1px solid #d4d4d4;
    border-radius: 5px;
}

.single-task:hover {
    background-color: #f0f0f0;
}

.dashboard-box .every-title {
    font-size: 20px;
    color: #515050;
    align-self: center;
}  

.dashboard-box .every-counts {
    font-size: 50px;
    /* color: #119e8f; */
    font-weight: bold;
    align-self: center;
}
</style>