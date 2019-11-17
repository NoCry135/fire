<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>fire-app</title>
    <script src="/static/js/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="/static/js/layer/layer.js"></script>
    <script src="/static/js/jquery.jqGrid.min.js"></script>

    <!-- jqGrid组件基础样式包-必要 -->
    <link rel="stylesheet" href="/static/jqgrid/css/ui.jqgrid.css" />

    <!-- jqGrid主题包-非必要 -->
    <!-- 在jqgrid/css/css这个目录下还有其他的主题包，可以尝试更换看效果 -->
    <link rel="stylesheet" href="/static/jqgrid/css/css/redmond/jquery-ui-1.8.16.custom.css" />

    <!-- jqGrid插件包-必要 -->
    <script type="text/javascript" src="/static/jqgrid/js/jquery.jqGrid.src.js"></script>

    <!-- jqGrid插件的多语言包-非必要 -->
    <!-- 在jqgrid/js/i18n下还有其他的多语言包，可以尝试更换看效果 -->
    <script type="text/javascript" src="/static/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <title>http://blog.mn886.net/jqGrid</title>

    <!-- 本页面初始化用到的js包，创建jqGrid的代码就在里面 -->
    <script type="text/javascript" src="/static/script/user.js"></script>

    <script type="text/javascript">

        // //获取申请单查询参数
        // var getSearchParams = function () {
        //     var data = {}
        //     return data
        // };
        //
        // function initGrid() {
        //     var $url = "userListPage.do";
        //     jQuery("#user_table").jqGrid({
        //         serializeGridData: function (postData) {
        //             postData.curPage = postData.page;
        //             postData.pageSize = postData.rows;
        //             postData.sortFields = postData.sidx;
        //             postData.sortType = postData.sord;
        //             return postData;
        //         },
        //         url: $url,
        //         datatype: 'json',
        //         postData: getSearchParams(),
        //         height: 200,
        //         mtype: 'POST',
        //         /*  shrinkToFit: false,*/
        //         colNames: ['ID', '用户名称', '用户密码', '电话', '邮箱', '创建时间', '更新时间', '创建人', '修改人'],
        //         colModel: [
        //             {name: 'id', index: 'id', editable: false, hidden: true},
        //             {name: 'userName', index: 'userName', editable: false, hidden: false},
        //             {name: 'passWord', index: 'passWord', editable: false, hidden: true},
        //             {name: 'telPhone', index: 'telPhone', editable: false},
        //             {name: 'email', index: 'email', editable: false},
        //             {name: 'createTime', index: 'createTime', editable: false,},
        //             {name: 'updateTime', index: 'updateTime', editable: false,},
        //             {name: 'createUser', index: 'createUser', editable: false,},
        //             {name: 'updateUser', index: 'updateUser', editable: false,},
        //         ],
        //         viewrecords: true,
        //         rowNum: 2,
        //         rowList: [10, 20, 30],
        //         pager: user - grid - pager,
        //         jsonReader: {
        //             page: "pageIndex",
        //             total: "pages",
        //             pageSize: "pageSize",
        //             records: "totalItems",
        //             root: "items"
        //         },
        //         altRows: true,
        //         multiselect: true,
        //         multiboxonly: true,
        //         sortname: 'createTime',
        //         sortorder: "desc",
        //         loadComplete: function () {
        //             var table = this;
        //
        //         },
        //         autowidth: true,
        //         shrinkToFit: true,
        //         autoScroll: false,
        //         loadError: function (xhr, status, error) {
        //         }
        //     });
        // }


    </script>
</head>

<body>
<h2>222</h2>
<div>
    <table id="user_table" title="应用管理">
    </table>
    <div id="user_pager"></div>
    <br>
</div>


</body>



