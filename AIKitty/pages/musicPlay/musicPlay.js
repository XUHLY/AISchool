//获取全局数据
const app = getApp()
//音乐管理器
const backgroundAudioManager = wx.getBackgroundAudioManager()
//动画
var animation = wx.createAnimation({
    timingFunction: 'liner',
})
var currentIndex = 0
var loopStatus2 = 0
Page({

    data: {
        list: '/images/list.png',
        prev: '/images/prev.png',
        play: '/images/play.png',
        next: '/images/next.png',
        loop: '/images/loop.png',
        headerPic: '/images/headerPic.png',
        audioStatus: 0,
        loopStatus: 0,
        switchBar: {
            prevWidthBar: '65rpx',
            prevHeightBar: '65rpx',
            nextWidthBar: '65rpx',
            nextHeightBar: '65rpx',
            listWidthBar: '40rpx',
            listHeightBar: '40rpx'
        },
        animationData: {},
        time: '',
        currentTime: '',
        progressTime: '',
        progressCurrentTime: '',
        heightRpx:[]
    },

    onLoad: function(options) {

        /** 屏幕高度适配 全局变量*/
        var heightRpxArray = new Array();
        var heightRpx = wx.getSystemInfoSync().windowHeight * 750 / wx.getSystemInfoSync().windowWidth;
        heightRpxArray.push(heightRpx * 0.5);
        heightRpxArray.push(heightRpx * 0.3);
        heightRpxArray.push(heightRpx * 0.1);
        heightRpxArray.push(heightRpx * 0.1);
        this.setData({
            heightRpx: heightRpxArray
        })
        /** 屏幕高度适配 */

        var that = this
        var musicList = JSON.parse(app.globalData.music)
        var m = musicList[currentIndex]
        //加载时判断当前是否有音乐播放
        if (backgroundAudioManager.title == null || backgroundAudioManager.title != m.name) { //无

            that.getMusic(0)

        } else { // 有 

            //格式化时间
            var date = new Date(m.time * 1000);
            //console.log(date)
            var minute = date.getMinutes()
            var second = date.getSeconds()
            var time = minute + ":" + second
            console.log(time)
            //给全局正在播放音乐图片赋值
            app.globalData.plateImgUrl = m.pic
            //给全局正在播放音乐时长赋值
            app.globalData.currentTime = m.time
            this.setData({
                audioStatus: 1,
                play: '/images/pause.png',
                poster: m.pic,
                name: m.name,
                author: m.singer,
                time: time,
                progressTime: m.time
            })

            //调用黑胶唱片旋转
            that.getRotate(m.time)
        }

        //自然播放结束后
        backgroundAudioManager.onEnded(function() {
            var status = loopStatus2
            var index = currentIndex
            if (status == 0) { //顺序循环播放
                if (currentIndex == 5) {
                    currentIndex = 0
                    that.getMusic(currentIndex)
                } else {
                    that.getMusic(currentIndex + 1)
                }
            } else if (status == 1) { //随机播放
                var i = Math.floor(Math.random() * 5);
                console.log(i)
                that.getMusic(i)
            }
        })
        //监听背景音频暂停事件
        backgroundAudioManager.onPause(function() {
            //系统面板点击暂停后得改变页面按钮显示
            that.setData({
                audioStatus: 0,
                play: '/images/play.png',

            })
        })

      //监听背景音频播放事件
      backgroundAudioManager.onPlay(function () {
        //系统面板点击继续播放后得改变页面按钮显示
        that.setData({
          audioStatus: 1,
          play: '/images/pause.png'
        })
      })

        // 监听用户在系统音乐播放面板点击下一曲事件
        backgroundAudioManager.onNext(function() {
            that.nextTapEnd()
        })


        //监听用户在系统音乐播放面板点击上一曲事件
        backgroundAudioManager.onPrev(function() {
            that.prevTapEnd()
        })


    },

    onShow: function() {
        /** 屏幕高度适配 全局变量*/
        this.setData({
            heightRpx: app.heightRpx
        })
        /** 屏幕高度适配 */
        var that = this

        //动态显示音乐播放进度时间
        setInterval(function() {
            var now = backgroundAudioManager.currentTime
            var date = new Date(now * 1000)
            var minute = date.getMinutes()
            var second = date.getSeconds()
            var currentTime = minute + ":" + second
            that.setData({
                currentTime: currentTime,
                progressCurrentTime: now
            })
        }.bind(this), 800)

    },


    onReady(e) {

    },

    //黑胶唱片旋转动画方法
    getRotate: function(musicTime) {
        //@param musicTime：音乐的总时长（分钟数）
        //转盘
        this.animation = animation
        var n = Math.floor((musicTime * 60) / 5);
        //console.log(n + "hhhhhhhhhhh")
        this.animation.rotate(360 * (n)).step({
            duration: 5000 * (n)
        })
        this.setData({
            animationData: this.animation.export()
        })

    },

    //音乐方法
    getMusic: function(index) {
        var that = this
        //给当前播放索引赋值
        currentIndex = index
        //从全局数据里取音乐列表
        var musicList = JSON.parse(app.globalData.music)
        var m = musicList[index]
        console.log(m)
        backgroundAudioManager.title = m.name
        backgroundAudioManager.epname = m.name
        backgroundAudioManager.singer = m.singer
        backgroundAudioManager.coverImgUrl = m.pic
        // 设置了 src 之后会自动播放
        backgroundAudioManager.src = m.url
        backgroundAudioManager.play()
        //格式化时间
        var date = new Date(m.time * 1000);
        //console.log(date)
        var minute = date.getMinutes()
        var second = date.getSeconds()
        var time = minute + ":" + second
        console.log(time)
        //给全局正在播放音乐图片赋值
        app.globalData.plateImgUrl = m.pic
        //给全局正在播放音乐时长赋值
        app.globalData.currentTime = m.time
        this.setData({
            audioStatus: 1,
            play: '/images/pause.png',
            poster: m.pic,
            name: m.name,
            author: m.singer,
            time: time,
            progressTime: m.time
        })

        //调用黑胶唱片旋转
        that.getRotate(m.time)

    },


    listTap() {
        this.setData({
            listWidthBar: '40rpx',
            listHeightBar: '40rpx'
        })
        var that = this
        var musicList = JSON.parse(app.globalData.music)
        wx.showActionSheet({
            itemList: [
                musicList[0].name + "——" + musicList[0].singer,
                musicList[1].name + "——" + musicList[1].singer,
                musicList[2].name + "——" + musicList[2].singer,
                musicList[3].name + "——" + musicList[3].singer,
                musicList[4].name + "——" + musicList[4].singer,
                musicList[5].name + "——" + musicList[5].singer
            ],
            success(res) {
                console.log(res.tapIndex)
                currentIndex = res.tapIndex
                that.getMusic(res.tapIndex)
            },
            fail(res) {
                console.log(res.errMsg)
            }
        })
    },
    listTapEnd() {
        this.setData({
            listWidthBar: '50rpx',
            listHeightBar: '50rpx'
        })
    },

    prevTap() {
        this.setData({
            prevWidthBar: '65rpx',
            prevHeightBar: '65rpx'
        })
    },
    prevTapEnd() {
        var that = this
        this.setData({
            prevWidthBar: '80rpx',
            prevHeightBar: '80rpx'
        })
        if (currentIndex == 0) {
            currentIndex = 5
            that.getMusic(currentIndex)
        } else {
            that.getMusic(currentIndex - 1)
        }
    },

    playTap() {
        //如果是播放就停止   
        if (this.data.audioStatus) {
            this.setData({
                audioStatus: 0,
                play: '/images/play.png',

            })
            backgroundAudioManager.pause()
            // 如果是停止就播放 
        } else {
            this.setData({
                audioStatus: 1,
                play: '/images/pause.png'
            })
            backgroundAudioManager.play()
        }
    },

    nextTap() {
        this.setData({
            nextWidthBar: '65rpx',
            nextHeightBar: '65rpx'
        })
    },
    nextTapEnd() {
        var that = this
        this.setData({
            nextWidthBar: '80rpx',
            nextHeightBar: '80rpx'
        })
        if (currentIndex == 5) {
            currentIndex = 0
            that.getMusic(currentIndex)
        } else {
            that.getMusic(currentIndex + 1)
        }
    },

    loopTap() {
        if (this.data.loopStatus) {
            loopStatus2 = 0
            this.setData({
                loopStatus: 0,
                loop: '/images/loop.png'
            })
        } else {
            loopStatus2 = 1
            this.setData({
                loopStatus: 1,
                loop: '/images/random.png'
            })
        }
    },

    //滑动进度条改变音乐播放进度
    changeMusicProgress(e) {
        //跳到滑动后的位置播放
        backgroundAudioManager.seek(e.detail.value)
    }
})