//app.js
App({

    /**
     * 当小程序初始化完成时，会触发 onLaunch（全局只触发一次）
     */
    onLaunch: function() {

        // 展示本地存储能力
        var logs = wx.getStorageSync('logs') || []
        logs.unshift(Date.now())
        wx.setStorageSync('logs', logs)


        // 获取用户信息
        wx.getSetting({
            success: res => {
                if (res.authSetting['scope.userInfo']) {
                    // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
                    wx.getUserInfo({
                        success: res => {
                            // 可以将 res 发送给后台解码出 unionId
                            this.globalData.userInfo = res.userInfo

                            // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
                            // 所以此处加入 callback 以防止这种情况
                            if (this.userInfoReadyCallback) {
                                this.userInfoReadyCallback(res)
                            }
                        }
                    })
                }
            }
        })

        /** 屏幕高度适配 */
        //获取当前设备的高度换算成rpx
        var heightRpx = wx.getSystemInfoSync().windowHeight * 750 / wx.getSystemInfoSync().windowWidth;
        var heightRpxArray = new Array();
        heightRpxArray.push(heightRpx * 0.7);
        heightRpxArray.push(heightRpx * 0.3);
        this.heightRpxArray = heightRpxArray;
        /** 屏幕高度适配 */

    },
    
    globalData: {
        userInfo: null,
        music: {},
        plateImgUrl: '',
        currentMusicTime: 0,//记录当前播放音乐的索引
        heightRpxArray: [],//存储了设备高度rpx按7：3比例的数组
    },

    /**
     * 当小程序启动，或从后台进入前台显示，会触发 onShow
     */
    onShow: function(options) {

    },

    /**
     * 当小程序从前台进入后台，会触发 onHide
     */
    onHide: function() {

    },

    /**
     * 当小程序发生脚本错误，或者 api 调用失败时，会触发 onError 并带上错误信息
     */
    onError: function(msg) {

    },
    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function() {

    },
})