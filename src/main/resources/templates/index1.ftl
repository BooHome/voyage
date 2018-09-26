<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>查询日文商品</title>
    <style>
        .pstyle{
            width: 800px;
            height: 65px;
            margin-top: 15px;
            margin-left:auto;
        }
        .psrtyle{
            width: 800px;
            height: 65px;
            margin-top: 15px;
            margin-right:auto;
        }
        .psctyle{
            width: 800px;
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
<div class="psrtyle"><input class="pstyle" type="button" value="根据规格代码或商品代码或商品名称查询" id="btn1"></div>
<div id="content_div">

</div>
<div class="psrtyle">
    <input class="pstyle" style="width: 100px" type="button" onclick="javascript:$('div').attr('class','psrtyle');"  value="左">
    <input class="pstyle" style="width: 100px" type="button" onclick="javascript:$('div').attr('class','psctyle');" value="中">
    <input class="pstyle" style="width: 100px" type="button" onclick="javascript:$('div').attr('class','pstyle');"  value="右">
</div>

<script src="js/jquery-1.7.2.min.js"></script>
<script src="js/jquery.zclip.min.js"></script>
<script src="/js/layer/layer.js"></script>
<script>
    $(function () {
        $.ajaxSetup({
            async: false
        });
        $("#btn1").click(function () {
            if( $("#key1").val()==""){
                layer.msg("输入不能为空");
                return false;
            }
            $.get("gets", {"key": $("#key1").val()}, function (data) {
               console.info(data);
               var inHtml='';
               for(var i=0;i<data.length;i++){
                  inHtml+='<div class="psrtyle"><input id="cp'+i+'" class="pstyle" jap="'+ data[i].jap+'" type="button"  onclick="javascript:oncp(this);"' +
                          'value="【点我复制日文内容】【商品代码:'+ data[i].spdm+' 】【规格代码：'+ data[i].ggdm+'】【日文：'+ data[i].jap+'】" id="btn1"></div>';
               }
                $("#content_div").html(inHtml);
            });
        });
    })
    function  oncp(obj){
        var id=$(obj).attr("id");
        var rvar=$(obj).attr("jap");
        $("#"+id).toggle();
        $("#"+id).toggle().zclip({
            path: 'js/ZeroClipboard.swf', //记得把ZeroClipboard.swf引入到项目中
            copy: function () {
                return rvar;
            },
            afterCopy: function(){//复制成功
                layer.msg("复制成功");
            }
        });
    }
</script>
</body>
</html>