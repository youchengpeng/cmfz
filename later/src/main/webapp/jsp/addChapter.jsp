<%@page pageEncoding="UTF-8" %>

<script type="text/javascript">

    $(function () {
        $("#aaid").val(aid1);
        console.log(aid1);
        $("#chapterFormUploadDate").datebox({
            editable: false
        })
        $("#chapterFormBtn").linkbutton({
            iconCls: "icon-ok",
            onClick: function () {
                $("#chapterForm").form("submit", {
                    url: "${pageContext.request.contextPath}/Album/addChapter",
                    onSubmit: function () {
                        return $("#chapterForm").form("validate");
                    },
                    success: function () {
                        $("#addChapter").dialog("close");
                        //右下角提示成功
                        $.messager.show({
                            title: "温馨提示",
                            msg: "添加成功"
                        });
                        $("#tg").treegrid("load");
                    }

                })
            }
        })
    })
</script>


<form id="chapterForm" method="post" enctype="multipart/form-data">
    名称：<input type="text" name="title"/><br/>
    音频：<input type="file" name="file1"/><br/>
    <input id="aaid" type="text" name="albid" hidden=true/>
    <a id="chapterFormBtn">确认</a>
</form>