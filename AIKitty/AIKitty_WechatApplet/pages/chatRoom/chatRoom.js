//获取全局数据
const app = getApp()
// 获取全局唯一的录音管理器 recorderManager
const recorderManager = wx.getRecorderManager();
//语音
const innerAudioContext = wx.createInnerAudioContext();
//设置语音播放其他声音停止
wx.setInnerAudioOption({
    mixWithOther: true, //是否与其他音频混播，设置为 true 之后，不会终止其他应用或微信内的音乐
    obeyMuteSwitch: false //（仅在 iOS 生效）是否遵循静音开关，设置为 false 之后，即使是在静音模式下，也能播放声音
})
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
        hidden: 'hidden',
        plateHidden: 'hidden',
        plateImgUrl: '',
        scrollNum: '',
        text: [],
        animationData: {},
        heightRpx:[],
        audioGoPic:'/images/audio0.png'

    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        /** 屏幕高度适配 全局变量*/

        this.setData({
            heightRpx: app.heightRpxArray
        })
        /** 屏幕高度适配 */
        
        // 添加录音停止触发事件
        recorderManager.onStop((res) => {
            const {
                tempFilePath
            } = res
            this.uploadVoice(tempFilePath);
        });
        //录音被意外中断事件
        recorderManager.onInterruptionBegin((res) => {
            this.audioGoEnd()
        });
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function() {




    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function() {
        //如果有音乐数据显示
        if (app.globalData.music != null && app.globalData.music.length >= 0) {
            this.setData({
                plateHidden: '',
                plateImgUrl: app.globalData.plateImgUrl
            })
            //旋转
            this.getRotate(app.globalData.currentTime)
        }
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
        var that = this

        //麦克风被占用，获取失败，调用audioGoEnd
        wx.getAvailableAudioSources({
            success: function() {
                //录音停止语音播放
                innerAudioContext.stop();
                //销毁
                innerAudioContext.destroy();
                // 开始录音
                recorderManager.start(voiceOptions);
                that.setData({
                    audioGoPic: '/images/audio1.png',
                    hidden: '',

                })

                wx.showLoading({
                    title: ' 说话中...',
                    mask: true,
                })
            },
            fail: setTimeout(function() {
                that.audioGoEnd()
            }, 10000)
        })

    },
    audioGoEnd: function() {
        // 停止录音，停止录音时，会触发onStop事件
        recorderManager.stop(voiceOptions)
        this.setData({
            audioGoPic: '/images/audio0.png',
            //hidden: 'hidden',
            scrollNum: (this.data.text.length + 1) * 3000
        })
        wx.hideLoading()
    },


    // 定义上传声音文件到服务器，调用百度语音识别接口把声音转成文字
    uploadVoice: function(tempFilePath) {
        var _this = this;
        wx.uploadFile({
            url: 'http://www.aischool.org.cn:8888/aischool/getVoiceText',
            //url: 'http://192.168.0.102:8888/aischool/getVoiceText',
            // url: 'http://10.173.10.103:8888/aischool/getVoiceText',
            filePath: tempFilePath,
            name: 'file',
            success: function(res) {
                var data = JSON.parse(res.data);
                //console.log(res)
                var resultData = data;
                console.log(resultData)
                let text = _this.data.text;
                text.push(resultData);
                _this.setData({
                    scrollNum: text.length * 1000,
                    hidden: 'hidden',
                    text
                })
                // 语音识别出文字后，然后调用UNIT进行语义解析
                _this.voiceAnalysis(resultData.chatInfo);
            },
            fail: function(res) {
                var fsilSay = {
                    "role": 0,
                    "say": "网络异常，请稍后再试!"
                };
                //var failSayJson =JSON.stringify(fsilSay);
                let text = _this.data.text;
                text.push(fsilSay);
                //console.log(fsilSay)
                _this.setData({
                    text,
                    hidden: 'hidden'
                })
            }
        })

    },


    voiceAnalysis: function(voiceText) {

        var _this = this;

        wx.request({
            //url: 'http://192.168.0.102:8888/aischool/getRobotRes',
            url: 'http://www.aischool.org.cn:8888/aischool/getRobotRes',
            data: {
                voiceText: voiceText,
                key: 971026
            },
            success: function(res) {
                console.log(res)
                var resultData2 = res.data;
                let text = _this.data.text;
                text.push(resultData2);
                console.log(text)
                _this.setData({
                    scrollNum: text.length * 1000,
                    text

                })
                if (resultData2.data != null) {
                    app.globalData.music = resultData2.data
                    //console.log(app.globalData.music)
                }
                //加载语音
                wx.downloadFile({
                    url: res.data.audioUrl,
                    success: function(res) {
                        // 只要服务器有响应数据，就会把响应内容写入文件并进入 success 回调
                        if (res.statusCode === 200) {
                            //const innerAudioContext = wx.createInnerAudioContext()
                            innerAudioContext.autoplay = true
                            innerAudioContext.src = res.tempFilePath
                            innerAudioContext.onPlay(() => {
                                //console.log('开始播放')

                            })
                            innerAudioContext.onStop(() => {
                                //console.log('停止播放')
                                innerAudioContext.stop()
                                //播放停止，销毁该实例
                                innerAudioContext.destroy()

                            })
                            innerAudioContext.onEnded(() => {
                                //console.log('播放结束')
                                //播放结束，销毁该实例
                                innerAudioContext.destroy()
                                //console.log('已执行destory()')
                            })
                            innerAudioContext.onError((res) => {
                                //console.log(res.errMsg)
                                //console.log(res.errCode)
                                innerAudioContext.destroy()
                            })
                        }
                    }
                })
                // 打开跳转的页面
                if (res.data.navigateUrl != null) {
                    wx.navigateTo({
                        url: res.data.navigateUrl //'/pages/musicPlay/musicPlay'
                    })
                }

            },
            fail: function(res) {
                var fsilSay = {
                    "role": 0,
                    "say": "网络异常，请稍后再试！"
                }
                let text = _this.data.text;
                text.push(fsilSay);
                console.log(text)
                _this.setData({
                    text,
                    hidden: 'hidden'
                })
            }
        })
    },
    //黑胶唱片旋转动画方法
    getRotate: function(musicTime) {
        //@param musicTime：当前播放音乐的总时长（分钟数）
        //转盘
        //动画
        var animation = wx.createAnimation({
            timingFunction: 'liner',
        })
        var n = Math.floor((musicTime * 60) / 5);
        //console.log(n + "hhhhhhhhhhh")
        animation.rotate(360 * (n)).step({
            duration: 5000 * (n)
        })
        this.setData({
            animationData: animation.export()
        })

    },
    //调用打电话功能
    makePhone: function (number) {
        wx.makePhoneCall({
            phoneNumber: number,
            success: function (res) { },
            fail: function (res) { },
            complete: function (res) { },
        })
    }
    
})