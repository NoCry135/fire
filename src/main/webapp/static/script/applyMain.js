(function () {

    //页面元素
    var $projectNo = "1004";
    var $projectName = "辽宁中石油";

    //页面按钮
    var $query_apply = $('#query_applyMain_btn');
    var $pass_apply = $('#pass_applyMain_btn');
    var $reject_apply = $('#reject_applyMain_btn');
    var $edit_apply = $('#edit_applyMain_btn');
    var $import_apply = $("#import_applyMain_btn");
    var $downLoad_apply = $("#download_applyMianDemo_Btn");

    //请求地址
    var $queryApply_url = "applyMain/queryApplyMainByPage.do?r=" + Math.random();

    //页面表格
    var apply_table = "#applyMain-grid";
    var applyPager_selector = "#applyMain-grid-pager";


    $.initSellerSiteSelect({
        sellerNoId:"search_sellerNo", siteNoId:"search_siteNo"
    });


    //获取申请单查询参数
    var getSearchParams = function () {
        var data = {
            projectNo: $projectNo,
            batchNo: $("#search_batchNo").val(),
            sellerNo: $("#search_sellerNo").val(),
            siteNo: $("#search_siteNo").val(),
            deliveryType: $("#search_deliveryType").val(),
            applyNo: $("#search_applyNo").val(),
            applyType: $("#search_applyType").val(),
            status: $("#search_status").val()
        }
        return data
    };

    //展示申请单列表
    var initGrid = function () {
        jQuery(apply_table).jqGrid({
            serializeGridData: function (postData) {
                postData.curPage = postData.page;
                postData.pageSize = postData.rows;
                postData.sortFields = postData.sidx;
                postData.sortType = postData.sord;
                return postData;
            },
            url: $queryApply_url,
            datatype: 'json',
            postData: getSearchParams(),
            height: 200,
            mtype: 'POST',
            /*  shrinkToFit: false,*/
            colNames: ['ID', '批次编号', '批次名称', '配送方式', '配送方式', '门店编号', '门店名称', '门店申请单号','单据状态', '单据状态', '单据类型', '单据类型'],
            colModel: [
                {name: 'id', index: 'id', editable: false, hidden: true},
                {name: 'batchNo', index: 'batchNo', editable: false, hidden: true},
                {name: 'batchName', index: 'batchName', editable: false},
                {name: 'deliveryType', index: 'deliveryType', editable: false, hidden: true},
                {name: 'deliveryTypeName', index: 'deliveryTypeName', editable: false},
                {name: 'siteNo', index: 'siteNo', editable: false,},
                {name: 'siteName', index: 'siteName', editable: false,},
                {
                    name: 'applyNo',
                    index: 'applyNo',
                    editable: false,
                    formatter: function typeformatter(cellvalue, options, rowObject) {
                        var selectHtml = '<a href="javascript:gotoApplyDetail(' + $projectNo + ', \'' + rowObject.applyNo + '\' )">' + cellvalue + '</a> ';
                        return selectHtml
                    }
                },
                {name: 'status', index: 'status', editable: false, hidden: true},
                {name: 'statusName', index: 'statusName', editable: false},
                {name: 'applyType', index: 'applyType', editable: false, hidden: true},
                {name: 'applyTypeName', index: 'applyTypeName', editable: false},

            ],
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: applyPager_selector,
            jsonReader: {
                page: "pageIndex",
                total: "pages",
                pageSize: "pageSize",
                records: "totalItems",
                root: "items"
            },
            altRows: true,
            multiselect: true,
            multiboxonly: true,
            sortname: 'createTime',
            sortorder: "desc",
            loadComplete: function () {
                var table = this;
                setTimeout(function () {
                    styleCheckbox(table);
                    updatePagerIcons(table);
                }, 0);
            },
            autowidth: true,
            shrinkToFit: true,
            autoScroll: false,
            loadError: function (xhr, status, error) {
                // $('#appModal').load("/gotoNoPermissionPage.do").modal();
            }
        });
    };


    //查询申请单信息
    var queryApplyData = function () {
        $query_apply.on("click", function () {
            jQuery(apply_table).jqGrid('setGridParam', {
                postData: getSearchParams()
            }).trigger("reloadGrid");
        });

    };

    //通过申请单
    var passApply = function () {
        $pass_apply.on("click", function () {
            var selectedIds = $(apply_table).jqGrid("getGridParam", "selarrrow");
            if (selectedIds.length < 1) {
                bootbox.alert("请选择要通过的申请单");
                return;
            }
            var selectDataIdArr = [];
            var applystatus =new Set();
            for (var i = 0; i < selectedIds.length; i++) {
                var rowData = $(apply_table).jqGrid("getRowData", selectedIds[i]);
                //TODO:需要根据不同的角色做显示判断
                if (rowData.status != 1 && rowData.status != 2) {
                    bootbox.alert(rowData.id + "不是待审批状态，请重新选择");
                    return;
                }
                selectDataIdArr.push(rowData.id);
                applystatus.add(rowData.status);

            }
            if(applystatus.size > 1){
                bootbox.alert("选中的申请单状态不一致");
                return;
            }
           $('#applyMainApproval').load("applyMain/gotoApplyApproval.do?approvalFlag=1&ids=" + selectDataIdArr.join(",")).modal();

           /* bootbox.confirm("确认执行此操作吗", function (result) {
                if (result) {
                    $.blockJoy()
                    $.ajax({
                        url: $passApply_url,
                        type: 'post',
                        dataType: 'json',
                        data: {ids: selectDataIdArr.join(","), projectNo: $projectNo},
                        success: function (result) {
                            $.unblockJoy()
                            if (result.code == 1) {
                                bootbox.alert(result.msg);
                                jQuery(apply_table).trigger("reloadGrid");
                            } else {
                                bootbox.alert(result.msg);
                                return false;
                            }
                        },
                        error: function () {
                            $.unblockJoy()
                            bootbox.alert("操作失败")
                        }
                    });
                }
            })*/
        })

    };

    //驳回
    var rejectApply = function () {

        $reject_apply.on("click", function () {
            var selectedIds = $(apply_table).jqGrid("getGridParam", "selarrrow");
            if (selectedIds.length < 1) {
                bootbox.alert("请选择要驳回的申请单");
                return;
            }
            var selectDataIdArr = [];
            var applystatus = new Set();
            for (var i = 0; i < selectedIds.length; i++) {
                var rowData = $(apply_table).jqGrid("getRowData", selectedIds[i]);
                //TODO:需要根据不同的角色做显示判断
                if (rowData.status != 1 && rowData.status != 2) {
                    bootbox.alert(rowData.id + "不是待审批状态，请重新选择");
                    return;
                }
                selectDataIdArr.push(rowData.id);
                applystatus.add(rowData.status);
            }
            if(applystatus.size > 1){
                bootbox.alert("选中的申请单状态不一致");
            }
            bootbox.confirm("确认执行此操作吗", function (result) {
                if (result) {
                    $.blockJoy()
                    $.ajax({
                        url: $rejectApply_url,
                        type: 'post',
                        dataType: 'json',
                        data: {ids: selectDataIdArr.join(","), projectNo: $projectNo},
                        success: function (result) {
                            $.unblockJoy()
                            debugger;
                            if (result.code == 1) {
                                bootbox.alert(result.msg);
                                jQuery(apply_table).trigger("reloadGrid");
                            } else {
                                bootbox.alert(result.msg);
                                return false;
                            }
                        },
                        error: function () {
                            $.unblockJoy()
                            bootbox.alert("操作失败")
                        }
                    });
                }
            })
        });


    };

    //修改申请单
    var editApply = function () {
        $edit_apply.on("click", function () {
            var batchCount = new Set();
            var selectedIds = $(apply_table).jqGrid("getGridParam", "selarrrow");
            if (selectedIds.length < 1) {
                bootbox.alert("请选择要修改的申请单");
                return;
            }
            if (selectedIds.length > 1) {
                bootbox.alert("请选择一个要修改的申请单");
                return;
            }

            var rowData = $(apply_table).jqGrid("getRowData", selectedIds[0]);
            //TODO:需要结合节点判断
            if (rowData.status != 1 && rowData.status != 2) {
                bootbox.alert("请选择未审批或已驳回的申请单");
                return;
            }

            var url = "applyMain/gotoApplyDetailEdit.do?projectNo=" + $projectNo + "&id=" + rowData.id + "&r=" + Math.random();
            $("#tabs").load(url);
            $(".active").removeClass("active");
            $(this).parent().addClass("active");

        })
    };

    //导入
    var importApply = function () {
        $import_apply.on("click", function () {
            var jsonData = {
                projectNo: $projectNo,
                projectName: $projectName,
            };
            $.importFile({
                url: $importApply_url,
                fileName: "templateFile",
                data: jsonData,
                successFun: function (result) {
                    var a = eval("(" + result + ")");
                    if(a.code == 1){
                        bootbox.alert(a.msg + a.data.message);
                    }else if(a.code == 2) {
                        bootbox.alert(a.msg +"</br>" + a.data.message + "</br>" + "以下数据行出错，请修改后重新导入:</br>" + $.getMapStr(a.data.errorData) + "以下申请单不存在: " + a.data.noExistApplyNos);
                    }else{
                        bootbox.alert(a.msg);
                    }
                    $.closeDialog();
                        jQuery(apply_table).jqGrid('setGridParam', {
                            postData: getSearchParams()
                        }).trigger("reloadGrid");
                }
            });
        });
    };

    var downLoadApply = function (){
        $downLoad_apply.on("click", function () {
            debugger;
            $("#downLoadApply_form").attr("action", $downLoadApply).submit();
            return false;
        });
    }
    //注册事件
    var registerEvent = function () {

        //查询按钮事件
        queryApplyData();

        //通过按钮事件
        passApply();

        //驳回按钮事件
        rejectApply();

        //修改
        editApply();

        //导入按钮事件
        importApply();

        //下载模板
        downLoadApply();

    };

    $(function () {

        //select查询样式
        var config = {
            '.chosen-select': {width: '100%'}
        }
        for (var selector in config) {
            $(selector).chosen(config[selector]);
        }

        initGrid();

        registerEvent();

    });

})(jQuery);


function gotoApplyDetail(projectNo, applyNo) {
    var timestamp = new Date().getTime();
    $('#applyMainModal').load("applyMain/queryApplyDetail.do?projectNo=" + projectNo + "&applyNo=" + applyNo + "&timestamp=" + timestamp).modal();
}
