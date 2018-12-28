<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<script type="text/javascript">
    $(function () {
        var toolbar = [{
            iconCls: 'icon-add',
            text: "活跃用户柱状图",
            handler: function () {
                // alert('编辑按钮')
                $("#dialoguser").dialog("open");
                // $("#dguser").datagrid("reload");
            }
        }, '-', {
            text: "用户分布图",
            iconCls: 'icon-add',
            handler: function () {
                $("#dialoguserccc").dialog("open");
            }
        }]

        $('#dguser').edatagrid({
            method: "GET",
            destroyUrl: "${pageContext.request.contextPath}/banner/deleteBanner",
            updateUrl: "${pageContext.request.contextPath}/banner/updateBanner",
            url: '${pageContext.request.contextPath}/banner/showAllBanner',
            columns: [[
                {field: 'id', title: 'ID', width: 100},
                {field: 'title', title: '名称', width: 100},
                {
                    field: 'status', title: '状态', width: 100, editor: {
                        type: "text",
                        options: {required: true}
                    }
                },
                {field: 'pub_date', title: '时间', width: 100, align: 'right'}
            ]],
            fitColumns: true,
            fit: true,
            pagination: true,
            pageList: [1, 3, 5, 7, 9],
            pageSize: 3,
            toolbar: toolbar,
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/' + rowData.img_path + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>描述: ' + rowData.description + '</p>' +
                    '<p>日期: ' + rowData.pub_date + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        });
        //初始化对话框
        $("#dialoguser").dialog({
            title: "分布图",

            width: 650,
            height: 500,
            cache: false,
            collapsible: true,
            maximizable: true,
            resizable: true,
            modal: true,
            closed: true,
            href: "${pageContext.request.contextPath }/jsp/useractived.jsp"
            //工具栏引入对话框

        });

        $("#dialoguserccc").dialog({
            title: "柱状图",

            width: 650,
            height: 500,
            cache: false,
            collapsible: true,
            maximizable: true,
            resizable: true,
            modal: true,
            closed: true,
            href: "${pageContext.request.contextPath }/jsp/china.jsp"
            //工具栏引入对话框

        });

    })

</script>

<table id="dguser"></table>
<div id="dialoguser"> 这是添加对话框</div>
<div id="dialoguserccc"> 这是添加对话框</div>