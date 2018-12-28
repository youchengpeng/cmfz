<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../themes/icon.css">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="../js/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        <!--菜单处理-->
        $(function () {
            $.ajax({
                type: 'get',
                url: '${pageContext.request.contextPath}/menu/showAllMenu',
                dataType: "json",
                //相应成功返回的data
                success: function (data) {
                    // alert(111456)
                    //console.log(data);
                    //遍历一级目录，data是原始数据，index表示下标，first表示遍历的数据
                    $.each(data, function (index, first) {
                        //获取一级目录的标题
                        var title = first.title;
                        //循环menu,取二级菜单
                        var menu = "";
                        //遍历二级目录
                        $.each(first.son, function (index, second) {
                            // console.log(second)
                            menu += "<p style='text-align:center'><a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'" + second.iconcls + "'\" onclick=\"getSelect('" + second.title + "','" + second.url + "')\">"
                                + second.title +
                                "</a></p>";

                        });
                        //给一级目录添加内容
                        $('#aa').accordion('add', {
                            title: title,
                            content: menu,
                            iconCls: first.iconcls,
                            selected: false
                        });
                    });
                }
            });

        })

        function getSelect(menu, href) {
            //判断是否存在
            var flag = $('#tt').tabs('exists', menu);
            if (flag) {
                //如果存在  选中
                $('#tt').tabs('select', menu);
            } else {
                //如果不存在，创建一个
                $('#tt').tabs('add', {
                    title: menu,
                    closable: true,
                    selected: true,
                    href: '${pageContext.request.contextPath}/' + href,

                });
            }
        }

    </script>

</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">欢迎您:xxxxx
        &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="#"
                                                                                                              class="easyui-linkbutton"
                                                                                                              data-options="iconCls:'icon-01'">退出系统</a>
    </div>
</div>
<div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;百知教育 htf@zparkhr.com.cn</div>
</div>

<div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    <div id="aa" class="easyui-accordion" data-options="fit:true">

    </div>
</div>
<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
        <div title="主页" data-options="iconCls:'icon-neighbourhood',"
             style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
    </div>
</div>
</body>
</html>