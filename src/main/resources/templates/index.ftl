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
<div class="psrtyle"><input class="pstyle" type="text" id="key1"></div>
<div class="psrtyle"><input class="pstyle" type="text" id="val1" value=""></div>
<div class="psrtyle"><input class="pstyle" type="button" value="根据条形码查并复制（只给出查到的第一个）" id="btn1"></div>
<div class="psrtyle"><input class="pstyle" type="text" id="key2"></div>
<div class="psrtyle"><input class="pstyle" type="text" id="val2"></div>
<div class="psrtyle"><input class="pstyle" type="button" id="btn2" value="根据名字查并复制（只给出查到的第一个）"></div>
<div class="psrtyle">
    <input class="pstyle" style="width: 100px" type="button" onclick="javascript:$('div').attr('class','psrtyle');"  value="左">
    <input class="pstyle" style="width: 100px" type="button" onclick="javascript:$('div').attr('class','psctyle');" value="中">
    <input class="pstyle" style="width: 100px" type="button" onclick="javascript:$('div').attr('class','pstyle');"  value="右">
</div>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/jquery.zclip.min.js"></script>
<script src="/js/layer/layer.js"></script>
<script>
    $(function () {
        $.ajaxSetup({
            async: false
        });
        $("#btn1").click(function () {
            $.get("get", {"key": $("#key1").val(), "flag": 1}, function (value) {
                $("#val1").val(value);
            });
        }).zclip({
            path: 'js/ZeroClipboard.swf', //记得把ZeroClipboard.swf引入到项目中
            copy: function () {
               if($('#val1').val()==""){
                   var str;
                   $.get("get", {"key": $("#key1").val(), "flag": 1}, function (value) {
                       str=value;
                   });
                   return str;
               }else{
                   return $('#val1').val();
               }
            },
            afterCopy: function(){//复制成功
                layer.msg("复制成功");
            }
        });
        $("#btn2").click(function () {
            $.get("get", {"key": $("#key2").val(), "flag": 2}, function (value) {
                $("#val2").val(value);
            });
        }).zclip({
            path: 'js/ZeroClipboard.swf', //记得把ZeroClipboard.swf引入到项目中
            copy: function () {
                if($('#val2').val()==""){
                    var str;
                    $.get("get", {"key": $("#key2").val(), "flag": 2}, function (value) {
                        str=value;
                    });
                    return str;
                }else{
                    return $('#val2').val();
                }
            },
            afterCopy: function(){//复制成功
                layer.msg("复制成功");
            }
        });

    })
</script>
</body>
</html>