//音乐管理器
const backgroundAudioManager = wx.getBackgroundAudioManager()
//动画
var animation = wx.createAnimation({
    timingFunction: 'liner',
})
var IntervalId //旋转动画定时器id
var n = 0 //动画圈数

Page({

    data: {
        poster: 'https://api.bzqll.com/music/tencent/pic?id=000PLHrM2luXiz&key=579621905',
        name: '',
        author: '李建',
        src: 'https://api.bzqll.com/music/tencent/url?id=000PLHrM2luXiz&key=579621905',
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
    },

    onLoad: function(options) {
        this.setData({
            name: options.name,
        })

        this.getMusic()

    },

    onShow: function() {

        this.getRotate()
    },


    onReady(e) {

    },

    //黑胶唱片旋转动画方法
    getRotate: function() {
        this.animation = animation

        this.setData({
            animationData: animation.export()
        })
        
        //连续动画需要添加定时器,所传参数每次+1就行
        IntervalId = setInterval(function() {
            n = n + 1;
            this.animation.rotate(360 * (n)).step({
                duration: 5000 * (n)
            })
            this.setData({
                animationData: this.animation.export()
            })
        }.bind(this), 100)

        console.log(IntervalId);
    },

    //音乐方法
    getMusic: function() {
        backgroundAudioManager.title = this.data.name
        backgroundAudioManager.epname = this.data.name
        backgroundAudioManager.singer = this.data.author
        backgroundAudioManager.coverImgUrl = this.data.poster
        // 设置了 src 之后会自动播放
        backgroundAudioManager.src = this.data.src
        //backgroundAudioManager.play()
        this.setData({
            audioStatus: 1,
            play: '/images/pause.png'
        })

    },


    listTap() {
        this.setData({
            listWidthBar: '40rpx',
            listHeightBar: '40rpx'
        })
        wx.showActionSheet({
            itemList: ['A', 'B', 'C', 'A', 'B', 'A'],
            success(res) {
                console.log(res.tapIndex)
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
        this.setData({
            prevWidthBar: '80rpx',
            prevHeightBar: '80rpx'
        })
    },

    playTap() {
        //如果是播放就停止   
        if (this.data.audioStatus) {
            
            clearInterval(IntervalId) //清除定时器
           
            this.animation.step()
            this.setData({
                audioStatus: 0,
                play: '/images/play.png',
                animationData: this.animation.export() //唱片停止转动

            })

            backgroundAudioManager.pause()
            // 如果是停止就播放 
        } else {
            this.setData({
                audioStatus: 1,
                play: '/images/pause.png'
            })
            backgroundAudioManager.play()
            //唱片继续转动
            //连续动画需要添加定时器,所传参数每次+1就行
            IntervalId = setInterval(function() {
                n = n + 1;
                this.animation.rotate(360 * (n)).step({
                    duration: 5000 * (n)
                })
                this.setData({
                    animationData: this.animation.export()
                })
            }.bind(this), 100)

        }
      //  console.log(this.data.audioStatus)
    },

    nextTap() {
        this.setData({
            nextWidthBar: '65rpx',
            nextHeightBar: '65rpx'
        })
    },
    nextTapEnd() {
        this.setData({
            nextWidthBar: '80rpx',
            nextHeightBar: '80rpx'
        })
    },

    loopTap() {
        if (this.data.loopStatus) {
            this.setData({
                loopStatus: 0,
                loop: '/images/loop.png'
            })
        } else {
            this.setData({
                loopStatus: 1,
                loop: '/images/random.png'
            })
        }
    }
})