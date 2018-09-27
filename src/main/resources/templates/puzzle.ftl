<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Puzzle</title>
    <script src="/js/vue.min.js"></script>
    <script src="/js/vue-resource.min.js"></script>
    <script src="/js/axios.min.js"></script>
    <style>
        table{
            padding: 0px;
            margin: 0px;
            border-spacing: 0px;
        }
        img{
            padding: 0px;
            margin: 0px;
            border-spacing: 0px;
            display: block;
        }
    </style>
</head>
<body>
<div id="box" v-show="boxShow">
    <input type="file" class="file" accept="image/png,image/gif,image/jpeg">
    <input type="button" @click="get()" value="点我获取数据">
    <input type="button" @click="set()" value="点我测试数据">
</div>
<div id="imgView" v-show="imgShow">
    <table cellpadding="0" cellspacing="0">
        <tr>
            <td><img v-bind:src="src[0][0]"></td>
            <td><img v-bind:src="src[0][1]"></td>
            <td><img v-bind:src="src[0][2]"></td>
        </tr>
        <tr>
            <td><img v-bind:src="src[1][0]"></td>
            <td><img v-bind:src="src[1][1]"></td>
            <td><img v-bind:src="src[1][2]"></td>
        </tr>
        <tr>
            <td><img v-bind:src="src[2][0]"></td>
            <td><img v-bind:src="src[2][1]"></td>
            <td><img v-bind:src="src[2][2]"></td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    window.onload = function () {
        var box = new Vue({
            el: '#box',
            data: {
                boxShow: true,
                imageData: {},
                baseUrl: 'http://localhost:8081/image/'
            },
            methods: {
                get: function () {
                    // 声明一个FormData对象
                    var formData = new window.FormData();
                    formData.append('file', document.querySelector('input[type=file]').files[0]);
                    // 设置axios的参数
                    var options = {
                        url: 'initFile/1',
                        data: formData,
                        method: 'post',
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    }
                    axios(options).then(function (res) {
                        box.imageData = res.data;

                        for (var i = 0; i < 3; i++) {
                            for (var j = 0; j < 3; j++) {
                                var num = box.imageData.ints[i][j];
                                if (num == 0) {

                                } else {
                                    imgView.src[i][j]=box.baseUrl+box.imageData.fileName+num+".jpg";
                                }
                            }
                        }
                        //box.boxShow = false;
                        imgView.imgShow = true;
                    })
                },
                set: function () {
                    imgView.imgShow = true;
                    for (var i = 0; i < 3; i++) {
                        for (var j = 0; j < 3; j++) {
                            if( imgView.src[i][j]=="0"){

                            }else{
                                imgView.src[i][j] = box.baseUrl + "de6f70b8cbde415e9a1751527f6166ba" + imgView.src[i][j] + ".jpg";
                            }
                            console.log(imgView.src);
                        }
                    }
                }
            }
        });
        var imgView = new Vue({
            el: '#imgView',
            data: {
                imgShow: false,
                src: [["1", "2", "3"], ["4", "5", "6"], ["7","8","0"]]
            }
        });
    }
</script>
</body>
</html>