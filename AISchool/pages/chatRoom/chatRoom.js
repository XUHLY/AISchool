// 获取全局唯一的录音管理器 recorderManager
const recorderManager = wx.getRecorderManager();
// 录音时需要的参数
const voiceOptions = {
    duration: 10000,
    sampleRate: 16000,
    numberOfChannels: 1,
    encodeBitRate: 96000,
    format: 'mp3',
    frameSize: 50
}

Page({

    /**
     * 页面的初始数据
     */
    data: {
        touchedColor: '',
        touchedwidth: '',
        touchedheight: '',
        userText:[],
        aiText:[]

    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        // 添加录音停止触发事件
        recorderManager.onStop((res) => {

            const { tempFilePath } = res
            this.uploadVoice(tempFilePath);
        });
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function() {
//var n=0;
        // setInterval(function () {
        //     this.data.userText = [this.data.userText.length + 1].concat(this.data.userText)
        //  this.data.userText.reverse()
        //     this.setData({
        //         userText: this.data.userText
        //     })
        // }.bind(this), 1000)



    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function() {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function() {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function() {

    },

    audioGoPlay: function() {
        // 开始录音
        recorderManager.start(voiceOptions);
        this.setData({
          touchedColor: '#FFFFFF',
            touchedwidth: '180rpx',
            touchedheight: '180rpx'
        })

        wx.showLoading({
            title: ' 说话中...',
            mask: true,
        })

    },
    audioGoEnd: function() {
        // 停止录音，停止录音时，会触发onStop事件
        recorderManager.stop(voiceOptions)
        this.setData({
          touchedColor: '#EDEDED',
            touchedwidth: '190rpx',
            touchedheight: '190rpx'
        })
        wx.hideLoading()
    },


    // 定义上传声音文件到服务器，调用百度语音识别接口把声音转成文字
    uploadVoice: function (tempFilePath) {
        var _this = this;


        //创建Header，跟服务端进行鉴权，约定一个算法，这样防止有人恶意刷接口。
        //var header = this.createHeader();


        //var str = "noncestr=" + header.noncestr + "&platform=" + header.platform + "&timestamp=" + header.timestamp + "&uid=" + header.uid + "&appkey=" + app.appkey;


       // var sign = md5.hex_md5(str);
       // header['sign'] = sign;


        wx.uploadFile({
          url: 'http://10.173.10.236:8888/getVoiceText',
            filePath: tempFilePath,
            name: 'file',
          //  header: header,

            success: function (res) {


                //var data = JSON.parse(res.data);
                console.log(res)
                // var resultData = data.data;


               // 根据自己的业务逻辑进行处理
              _this.setData({
                userText: [res.data],
              })


            // 语音识别出文字后，然后调用UNIT进行语义解析
            _this.voiceAnalysis(resultData.content);
        },
            fail: function (res) {
                // console.info(res);
                _this.addText({
                    role: 'robot',
                    flex_direction: 'row',
                    message: '网络异常，请稍后再试！'
                });
            }
})

},


    voiceAnalysis: function (content) {


        var header = this.createHeader();


        var str = "content=" + content + "&noncestr=" + header.noncestr + "&platform=" + header.platform + "&timestamp=" + header.timestamp + "&uid=" + header.uid + "&appkey=" + app.appkey;


        var sign = md5.hex_md5(str);


        header['content-type'] = 'application/x-www-form-urlencoded'
        header['sign'] = sign;


        var _this = this;
        wx.request({
          url: 'http://192.168.0.123:8888/getVoiceText',
            data: {
                content: content,
                sessionId: this.sessionId ? this.sessionId : ''
            },
            header: header,
            method: 'POST',
            success: function (res) {
                // 语义解析成功后，可以进行自己的业务逻辑了


                var resultData = res.data.data;
                _this.sessionId = resultData.sessionId;


                // 打开音频播放页面
                wx.navigateTo({
                    url: '/pages/audio/audio'
                })
            },
            fail: function (res) {
                // console.info(res);
                _this.addText({
                    role: 'robot',
                    flex_direction: 'row',
                    message: '网络异常，请稍后再试！'
                });
            }
        })
    },

    createHeader: function () {
        return {
            'timestamp': Date.now(),
            'noncestr': util.guid(),
            'uid': app.userId,
            'platform': app.platform
        }
    },


 

})