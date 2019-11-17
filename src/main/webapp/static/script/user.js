$(function () {
    //页面加载完成之后执行
    pageInit();
});

function pageInit() {
    //创建jqGrid组件
    var $url = "getUserList.do";

    jQuery("#user_table").jqGrid(
        {
            url: $url,//组件创建完成之后请求数据的url
            datatype: "json",//请求数据返回的类型。可选json,xml,txt
            colNames: ['ID', '用户名称', '用户密码', '电话', '邮箱', '创建时间', '更新时间', '创建人', '修改人'],//jqGrid的列显示名字
            colModel: [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
                {name: 'id', index: 'id', editable: false, hidden: true},
                {name: 'userName', index: 'userName', editable: false, hidden: false},
                {name: 'passWord', index: 'passWord', editable: false, hidden: true},
                {name: 'telPhone', index: 'telPhone', editable: false},
                {name: 'email', index: 'email', editable: false},
                {name: 'createTime', index: 'createTime', editable: false,},
                {name: 'updateTime', index: 'updateTime', editable: false,},
                {name: 'createUser', index: 'createUser', editable: false,},
                {name: 'updateUser', index: 'updateUser', editable: false,}
            ],
            rowNum: 2,//一页显示多少条
            rowList: [1, 2, 3],//可供用户选择一页显示多少条
            pager: '#user_pager',//表格页脚的占位符(一般是div)的id
            sortname: 'id',//初始化的时候排序的字段
            sortorder: "desc",//排序方式,可选desc,asc
            mtype: "post",//向后台请求数据的ajax的类型。可选post,get
            viewrecords: true,
            caption: "用户列表"//表格的标题名字
        });
    /*创建jqGrid的操作按钮容器*/
    /*可以控制界面上增删改查的按钮是否显示*/
    jQuery("#user_table").jqGrid('navGrid', '#user_pager', {edit: false, add: false, del: false});
}