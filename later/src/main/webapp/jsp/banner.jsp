<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<script type="text/javascript">
    $(function () {
        var toolbar = [{
            iconCls: 'icon-add',
            text: "添加",
            handler: function () {
                // alert('编辑按钮')
                $("#dialog").dialog("open");
                $("#dg").datagrid("reload");
            }
        }, '-', {
            text: "修改",

            iconCls: 'icon-edit',
            handler: function () {
                //获取选中行
                var row = $("#dg").edatagrid("getSelected");
                if (row != null) {
                    //编辑指定行
                    var index = $("#dg").edatagrid("getRowIndex", row);
                    $("#dg").edatagrid("editRow", index);
                } else {
                    alert("请先选中行")
                }


            }
        }, '-', {
            text: "删除",
            iconCls: 'icon-remove',
            handler: function () {
                $("#dg").edatagrid("destroyRow")
            }
        }, '-', {
            text: "保存",
            iconCls: 'icon-save',
            handler: function () {
                $("#dg").edatagrid("saveRow")

            }
        }]

        $('#dg').edatagrid({
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
        $("#dialog").dialog({
            title: "添加用户",
            width: 300,
            height: 200,
            cache: false,
            collapsible: true,
            maximizable: true,
            resizable: true,
            modal: true,
            closed: true,
            href: "${pageContext.request.contextPath }/jsp/resgist.jsp"
            //工具栏引入对话框

        });

    })

</script>

<table id="dg"></table>
<div id="dialog"> 这是添加对话框</div>