<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Puzzle</title>
    <script src="/js/jquery-1.9.1.min.js"></script>
    <script src="/js/vue.min.js"></script>
    <script src="/js/vue-resource.min.js"></script>
    <script src="/js/axios.min.js"></script>
    <style>
        table {
            padding: 0px;
            margin: 0px;
            border-spacing: 0px;
        }

        img {
            padding: 0px;
            margin: 0px;
            border-spacing: 0px;
            display: block;
        }

        #imgView, #imgViewShow {
            float: left
        }
    </style>
</head>
<body>
<div id="box" v-show="boxShow">
    <input type="file" class="file" accept="image/png,image/gif,image/jpeg">
    <input type="button" @click="get()" value="点我获取数据">
    <input type="button" @click="set()" value="点我查看源数据">
    <input type="button" v-show="btnShow" @click="init()" value="点我初始化">
    <input type="button" v-show="btnShow" @click="help()" value="点我初始化并获取帮助">
</div>
<div id="imgView" v-show="imgShow">
    <table cellpadding="0" cellspacing="0">
        <tr>
            <td @click="change(0,0)" v-html="src[0][0]"></td>
            <td @click="change(0,1)" v-html="src[0][1]"></td>
            <td @click="change(0,2)" v-html="src[0][2]"></td>
        </tr>
        <tr>
            <td @click="change(1,0)" v-html="src[1][0]"></td>
            <td @click="change(1,1)" v-html="src[1][1]"></td>
            <td @click="change(1,2)" v-html="src[1][2]"></td>
        </tr>
        <tr>
            <td @click="change(2,0)" v-html="src[2][0]"></td>
            <td @click="change(2,1)" v-html="src[2][1]"></td>
            <td @click="change(2,2)" v-html="src[2][2]"></td>
        </tr>
    </table>
</div>
<div id="imgViewShow" v-show="imgShow">
    <table cellpadding="0" cellspacing="0">
        <tr>
            <td v-html="src[0][0]"></td>
            <td v-html="src[0][1]"></td>
            <td v-html="src[0][2]"></td>
        </tr>
        <tr>
            <td v-html="src[1][0]"></td>
            <td v-html="src[1][1]"></td>
            <td v-html="src[1][2]"></td>
        </tr>
        <tr>
            <td v-html="src[2][0]"></td>
            <td v-html="src[2][1]"></td>
            <td v-html="src[2][2]"></td>
        </tr>
    </table>
</div>
<span id=test></span>
<script type="text/javascript">
    window.onload = function () {
        var box = new Vue({
            el: '#box',
            data: {
                boxShow: true,
                btnShow: false,
                imageData: {},
                baseUrl: 'http://file.ihere.club/'
            },
            methods: {
                get: function () {
                    // 声明一个FormData对象
                    var formData = new window.FormData();
                    formData.append('file', document.querySelector('input[type=file]').files[0]);
                    // 设置axios的参数
                    var options = {
                        url: 'initFile/1' + '?&timestamp=' + (new Date()).getTime(),
                        data: formData,
                        method: 'post',
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    };
                    axios(options).then(function (res) {
                        imgView.imgShow = false;
                        box.imageData = res.data;
                        for (var i = 0; i < 3; i++) {
                            for (var j = 0; j < 3; j++) {
                                var num = box.imageData.ints[i][j];
                                if (num == 0) {
                                    imgView.src[i][j] = '<div style="background-color: white" />';
                                    imgView.tempInitSrc[i][j] = '<div style="background-color: white" />';
                                } else {
                                    imgView.src[i][j] = '<img src="' + box.baseUrl + box.imageData.fileName + num + '.jpg' + '">';
                                    imgView.tempInitSrc[i][j] = '<img src="' + box.baseUrl + box.imageData.fileName + num + '.jpg' + '">';
                                }
                                imgView.tempSrc[i][j] = '<img src="' + box.baseUrl + box.imageData.fileName + num + '.jpg' + '">';
                                imgView.tempTempSrc[i][j] = '<img src="' + box.baseUrl +box.imageData.fileName + num + '.jpg' + '">';
                                imgView.numData[i][j] = num;
                                imgView.tempNumData[i][j] = num;
                            }
                        }
                        imgView.imgShow = true;
                        box.btnShow = true;
                    })
                },
                set: function () {
                    imgViewShow.imgShow = false;
                    for (var i = 0; i < 3; i++) {
                        for (var j = 0; j < 3; j++) {
                            imgViewShow.src[i][j] = '<img src="' + box.baseUrl +box.imageData.fileName + imgViewShow.initData[i][j] + '.jpg' + '">';
                        }
                    }
                    imgViewShow.imgShow = true;
                },
                help: function () {
                    box.init();
                    $.get("solve/" + box.imageData.intsStr + "?&timestamp=" + (new Date()).getTime(), function (res) {
                        var index = 0;
                        setTimeout(function timeTask() {
                            var x, y;
                            for (var i = 0; i < 3; i++) {
                                for (var j = 0; j < 3; j++) {
                                    if (imgView.numData[i][j] == 0) {
                                        x = i;
                                        y = j;
                                    }
                                }
                            }
                            //  LEFT = 0;
                            // RIGHT = 1;
                            //   UP = 2;
                            // DOWN = 3;
                            console.info("x:" + x + "   y:" + y + " res[index]:" + res[index]+" index:"+index);
                            switch (res[index++]) {
                                case 0:
                                    common.move.right(x, y - 1);
                                    break;
                                case 1:
                                    common.move.left(x, y + 1);
                                    break;
                                case 2:
                                    common.move.down(x - 1, y);
                                    break;
                                case 3:
                                    common.move.up(x + 1, y);
                                    break;
                                default:
                                    break;
                            }
                            imgView.imgShow = false;
                            imgView.imgShow = true;
                            if (common.success(imgView.numData)) {
                                imgView.src[2][2] = imgView.tempSrc[2][2];
                                imgView.numData[2][2] = 9;
                                imgView.imgShow = false;
                                imgView.imgShow = true;
                            }
                            if (index < res.length) setTimeout(timeTask, 500);
                        }, 500);
                    });
                },
                init: function () {
                    for (var i = 0; i < 3; i++) {
                        for (var j = 0; j < 3; j++) {
                            imgView.src[i][j] = imgView.tempInitSrc[i][j];
                            imgView.numData[i][j] = imgView.tempNumData[i][j];
                            imgView.tempSrc[i][j] = imgView.tempTempSrc[i][j];
                        }
                    }
                    imgView.imgShow = false;
                    imgView.imgShow = true;
                }
            }
        });
        var imgView = new Vue({
            el: '#imgView',
            data: {
                imgShow: false,
                src: [["1", "2", "3"], ["4", "5", "6"], ["7", "8", "0"]],
                tempSrc: [["1", "2", "3"], ["4", "5", "6"], ["7", "8", "0"]],
                tempTempSrc: [["1", "2", "3"], ["4", "5", "6"], ["7", "8", "0"]],
                tempInitSrc: [["1", "2", "3"], ["4", "5", "6"], ["7", "8", "0"]],
                numData: [["1", "2", "3"], ["4", "5", "6"], ["7", "8", "0"]],
                tempNumData: [["1", "2", "3"], ["4", "5", "6"], ["7", "8", "0"]]
            },
            methods: {
                change: function (i, j) {
                    common.move.getDirection(i, j);
                }
            }
        });
        var imgViewShow = new Vue({
            el: '#imgViewShow',
            data: {
                imgShow: false,
                src: [["1", "2", "3"], ["4", "5", "6"], ["7", "8", "0"]],
                initData: [["1", "2", "3"], ["4", "5", "6"], ["7", "8", "0"]]
            }
        });
        var common = {
            move: {
                getDirection: function (x, y) {
                    //  定义方向
                    //  LEFT = 0;
                    // RIGHT = 1;
                    //   UP = 2;
                    // DOWN = 3;
                    var num = 4;
                    if (x > 0 && imgView.numData[x - 1][y] == 0) {
                        num = 2;
                    }
                    if (x < 2 && imgView.numData[x + 1][y] == 0) {
                        num = 3;
                    }
                    if (y > 0 && imgView.numData[x][y - 1] == 0) {
                        num = 0;
                    }
                    if (y < 2 && imgView.numData[x][y + 1] == 0) {
                        num = 1;
                    }
                    switch (num) {
                        case 0:
                            common.move.left(x, y);
                            break;
                        case 1:
                            common.move.right(x, y);
                            break;
                        case 2:
                            common.move.up(x, y);
                            break;
                        case 3:
                            common.move.down(x, y);
                            break;
                        default:
                            break;
                    }
                    imgView.imgShow = false;
                    imgView.imgShow = true;
                    if (common.success(imgView.numData)) {
                        imgView.src[2][2] = imgView.tempSrc[2][2];
                        imgView.numData[2][2] = 9;
                        imgView.imgShow = false;
                        imgView.imgShow = true;
                    }
                },
                up: function (x, y) {
                    var temp = imgView.src[x][y];
                    var tempSrc = imgView.tempSrc[x][y];
                    var tempNum = imgView.numData[x][y];
                    imgView.src[x][y] = imgView.src[x - 1][y];
                    imgView.tempSrc[x][y] = imgView.tempSrc[x - 1][y];
                    imgView.numData[x][y] = imgView.numData[x - 1][y];
                    imgView.src[x - 1][y] = temp;
                    imgView.tempSrc[x - 1][y] = tempSrc;
                    imgView.numData[x - 1][y] = tempNum;
                },
                down: function (x, y) {
                    var temp = imgView.src[x][y];
                    var tempSrc = imgView.tempSrc[x][y];
                    var tempNum = imgView.numData[x][y];
                    imgView.src[x][y] = imgView.src[x + 1][y];
                    imgView.tempSrc[x][y] = imgView.tempSrc[x + 1][y];
                    imgView.numData[x][y] = imgView.numData[x + 1][y];
                    imgView.src[x + 1][y] = temp;
                    imgView.tempSrc[x + 1][y] = tempSrc;
                    imgView.numData[x + 1][y] = tempNum;
                },
                left: function (x, y) {
                    var temp = imgView.src[x][y];
                    var tempSrc = imgView.tempSrc[x][y];
                    var tempNum = imgView.numData[x][y];
                    imgView.src[x][y] = imgView.src[x][y - 1];
                    imgView.tempSrc[x][y] = imgView.tempSrc[x][y - 1];
                    imgView.numData[x][y] = imgView.numData[x][y - 1];
                    imgView.src[x][y - 1] = temp;
                    imgView.tempSrc[x][y - 1] = tempSrc;
                    imgView.numData[x][y - 1] = tempNum;
                },
                right: function (x, y) {
                    var temp = imgView.src[x][y];
                    var tempSrc = imgView.tempSrc[x][y];
                    var tempNum = imgView.numData[x][y];
                    imgView.src[x][y] = imgView.src[x][y + 1];
                    imgView.tempSrc[x][y] = imgView.tempSrc[x][y + 1];
                    imgView.numData[x][y] = imgView.numData[x][y + 1];
                    imgView.src[x][y + 1] = temp;
                    imgView.tempSrc[x][y + 1] = tempSrc;
                    imgView.numData[x][y + 1] = tempNum;
                }
            },
            success: function (arr) {
                var numStr = "";
                for (var i = 0; i < 3; i++) {
                    for (var j = 0; j < 3; j++) {
                        numStr += arr[i][j];
                    }
                }
                return numStr == "123456780" ? true : false;
            }
        };
    }
</script>
</body>
</html>