<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>查询日文商品</title>
    <style>
        .pstyle{
            width: 445px;
            height: 65px;
            margin-top: 15px;
            margin-left:auto;
        }
        .psrtyle{
            width: 445px;
            height: 65px;
            margin-top: 15px;
            margin-right:auto;
        }
        .psctyle{
            width: 445px;
            height: 65px;
            margin-top: 15px;
            margin-left:auto;
            margin-right:auto;
        }
        .leftcla{
            text-align: left;
        }
        .cencla{
            text-align: center;
        }
        .rigcla{
            text-align: right;
        }
    </style>
</head>
<body class="cencla" id="bodeId">

<div class="psrtyle"><input class="pstyle" type="button" value="测试" id="btn1"></div>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="/js/layer/layer.js"></script>
<script>
    $(function () {
        $("#btn1").click(function () {
            $.ajax({
                url: "/6/post",
                type: "post",
                dataType:"html",
                contentType: "application/json;charset=utf-8",
                data: {"s":"1","ss":["1","2"]},
                async: false,
                success: function (value) {
                    layer.msg(JSON.stringify(value));
                }
            });
        });
    })
</script>
</body>
</html>