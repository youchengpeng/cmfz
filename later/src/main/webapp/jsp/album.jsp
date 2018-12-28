<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">
    var id1;
    var aid1;
    var tool = [{
        iconCls: 'icon-tip',
        text: "专辑详情",
        handler: function () {
            //弹出详情对话框--需选择专辑栏

            var row = $('#tg').datagrid('getSelected');
            //alert(row)
            console.log(row);
            if (row == null) {
                $.messager.alert('提示', '请您选中要查看的专辑', 'warning');
            }
            if (row.score == null) {
                $.messager.alert('提示', '请您选中要查看的专辑', 'warning');
            } else {
                $('#cover_img').prop('src', '${pageContext.request.contextPath}/coverimg/' + row.cover_img);
                $('#albumdg').dialog({
                    title: '详情',
                    width: 350,
                    height: 500,
                    buttons: [{
                        text: '关闭',
                        iconCls: 'icon-ok',
                        handler: function () {
                            $('#albumdg').dialog('close');
                        }
                    }]
                });
                $('#albumShow').form('load', {
                    title: row.title,
                    score: row.score,
                    broadcast: row.broadcast,
                    cover_img: row.cover_img,
                    author: row.author,
                    brief: row.brief,
                    count: row.count,
                    pub_date: row.pub_date,
                });

            }

        }
    }, '-', {
        iconCls: 'icon-add',
        text: "添加专辑",
        handler: function () {
            $("#addAlbum").dialog("open")
        }
    }, '-', {
        iconCls: 'icon-save',
        text: "添加章节",
        handler: function () {
            //需选择对应的专辑
            var row = $("#tg").treegrid("getSelected");
            if (row.children != null) {
                aid1 = row.id;
                $("#addChapter").dialog("open");
            } else {
                alert("请选择专辑")
            }
        }
    }, '-', {
        iconCls: 'icon-back',
        text: "下载音频",
        handler: function () {
            var row = $("#tg").treegrid("getSelected");
            console.log(row)
            if (row.children == null) {
                //下载--row.url
                $.get(
                    "${pageContext.request.contextPath}/Album/down?url=" + row.url + "&title=" + row.title,

                    function (res) {
                        $.messager.show({
                            title: "温馨提示",
                            msg: "下载成功"
                        });
                    }
                )

            } else {
                alert("请选择音频");
            }

        }
    }, '-', {
        iconCls: 'icon-add',
        text: "导出Excel",
        handler: function () {
            //alert("1212")
            // location.href="${pageContext.request.contextPath}/Album/exportAllAlbum"
            $.ajax({
                type: "GET",//请求⽅式
                url: "${pageContext.request.contextPath}/Album/exportAllAlbum",//请求路径
                //data:"name=张三|{key:value}",//请求参数
                dataType: "",//预期服务器的响应格式
                success: function (result) {
                    console.log(result);
                    $.messager.show({
                        title: "温馨提示",
                        msg: "下载成功"
                    });
                }
            })
        }
    },
        '-', {
            iconCls: 'icon-add',
            text: "导入Excel",
            handler: function () {
                alert("1212")
            }
        }]
    $(function () {
        $('#tg').treegrid({

            url: '${pageContext.request.contextPath}/Album/showAllAlbumByPage',
            idField: 'id',
            treeField: 'title',
            columns: [[
                {field: 'title', title: '名称', width: 180},
                {field: 'url', title: '下载路径', width: 60},
                {field: 'size', title: '大小', width: 80},
                {field: 'duration', title: '时长', width: 80},
                {field: 'uploadDate', title: '更新时间', width: 80}
            ]],
            fitColumns: true,
            fit: true,
            pagination: true,
            pageSize: 20,
            pageList: [20, 25, 30],
            toolbar: tool,
            onDblClickRow: function (row) {
                if (row.children == null) {
                    $("#music").dialog({
                        title: "正在播放" + row.url,
                        iconCls: "icon-large-chart",
                        width: 320,
                        height: 100
                    });
                    $("#musicAudio").prop("src", "${pageContext.request.contextPath}/audio/" + row.url);
                }
            }
        });

        //添加专辑对话框
        $("#addAlbum").dialog({
            title: "请添加专辑",
            width: 400,
            height: 300,
            closed: true,
            cache: false,
            href: '${pageContext.request.contextPath}/jsp/addAlbum.jsp',
            modal: true
        })

        $("#addChapter").dialog({
            title: "请添加章节",
            width: 400,
            height: 300,
            closed: true,
            cache: false,
            href: '${pageContext.request.contextPath}/jsp/addChapter.jsp',
            modal: true
        })

    })
</script>

<table id="tg">

</table>


<div id="addAlbum"></div>

<div id="addChapter"></div>
<div id="music">
    <audio id="musicAudio" controls="controls"></audio>
</div>

<table id="albumtb"></table>
<div id="albumdg" style="display: none">
    <form id="albumShow">
        名称:<input name="title" type="text" class="easyui-validatebox" data-options="required:true"/><br/>
        评分:<input name="score" type="text" class="easyui-validatebox" data-options="required:true"/><br/>
        播音:<input name="broadcast" type="text" class="easyui-validatebox" data-options="required:true"/><br/>
        作者:<input name="author" type="text" class="easyui-validatebox" data-options="required:true"/><br/>
        详细信息:<input name="brief" type="text" class="easyui-validatebox" data-options="required:true"/><br/>
        集数:<input name="count" type="text" class="easyui-validatebox" data-options="required:true"/><br/>
        封面:
        <img id="cover_img" src="#" style="width: 250px;height: 200px"><br/>
        上传时间:<input name="pub_date" type="text" class="easyui-validatebox" data-options="required:true"/><br/>
    </form>
</div>
