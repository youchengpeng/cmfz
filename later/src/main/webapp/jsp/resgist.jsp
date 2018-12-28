<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/func.js"></script>
<script type="text/javascript">
    $(function () {

        $("#regist").linkbutton({
            onClick: function () {
                $("#form").form("submit", {
                    url: "${pageContext.request.contextPath }/banner/addBanner",
                    onSubmit: function () {
                        // 表单验证 -- 调form的validate方法
                        return $("#form").form("validate");
                    },
                    success: function () {
                        $("#dg").datagrid("load");
                        $.messager.show({
                            title: "标题",
                            msg: "保存成功！"
                        });

                        $("#dialog").dialog("close");
                    }
                });
            }


        });

    });
</script>
<form method="post" id="form" enctype="multipart/form-data">
    <table border="0" bordercolor="blue" width="70%" height="50%" cellspacing="0" align="center">
        <tr>
            <td>题目：</td>
            <td><input id="b1" type="text" name="title" class=""/></td>
        </tr>
        <tr>
            <td>图片：</td>
            <td><input id="b4" type="file" name="file1"/></td>
        </tr>
        <tr>
        <tr>
            <td>状态：</td>
            <td><input id="b2" type="text" name="status"/></td>
        </tr>
        <tr>
            <td>描述：</td>
            <td><input id="b3" type="text" name="description"/></td>
        </tr>
        <tr align="center">
            <td colspan="2">
                <a id="regist">保存</a> <input type="reset" value="重置"/>
            </td>
        </tr>
    </table>
</form>
