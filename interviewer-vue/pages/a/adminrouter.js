window.myrouter = {

    routes: [

        { path: '/', component: httpVueLoader('candidate/list.vue') },
		{ path: '/dashboard', component: httpVueLoader('dashboard.vue') },

        { path: '/candidateMng/candidateList', component: httpVueLoader('candidate/list.vue') },
        { path: '/candidateMng/candidateCreate', component: httpVueLoader('candidate/create.vue') },
        { path: '/candidateMng/interviewRecord', component: httpVueLoader('candidate/interviewRecord.vue') },
		
        { path: '/jobMng/jobList', component: httpVueLoader('job/list.vue') },
		{ path: '/jobMng/jobCreate', component: httpVueLoader('job/create.vue') },

        { path: '/aiMng/aiSettings', component: httpVueLoader('settings/aiMng.vue') },
        { path: '/aiMng/questionLib', component: httpVueLoader('settings/questionLib.vue') },
	],

    // role=1 表示只有admin权限才显示；role=2 表示只有普通用户权限才显示
    menuList: [

        { title: '候选人管理', path: '/candidateMng', index: 'candidateMng', icon: 'el-icon-s-order', children: [
                { title: '候选人列表', path: '/candidateMng/candidateList', index: 'candidateList',  children: [], role: 1, },
                { title: '创建候选人', path: '/candidateMng/candidateCreate', index: 'candidateCreate',  children: [], role: 1, },
                { title: '面试记录', path: '/candidateMng/interviewRecord', index: 'interviewRecord', children: [], role: 1, },
            ], role: 1, 
        },
        { title: '职位管理', path: '/jobMng', index: 'jobMng', icon: 'el-icon-s-help', children: [
                { title: '职位列表', path: '/jobMng/jobList', index: 'jobList', children: [], role: 1, },
                { title: '创建职位', path: '/jobMng/jobCreate', index: 'jobCreate', children: [], role: 1, },
            ], role: 1,
        },
        { title: 'AI面试官', path: '/aiMng', index: 'aiMng', icon: 'el-icon-s-tools', children: [
                { title: '面试官设置', path: '/aiMng/aiSettings', index: 'aiSettings',  children: [], role: 1, },
                { title: '面试题库', path: '/aiMng/questionLib', index: 'questionLib',  children: [], role: 1, },
                { title: '模拟面试', path: '/aiMng/doInterview', index: 'doInterview',  children: [], role: 1, },
            ], role: 1,
        },
    ]
}