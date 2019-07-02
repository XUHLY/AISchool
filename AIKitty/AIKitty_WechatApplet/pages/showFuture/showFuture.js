const backgroundAudioManager = wx.getBackgroundAudioManager()
Page({

    /**
     * 页面的初始数据
     */
    data: {
        title: '',
        titleArray: ['预见未来', '畅想未来', '智能生活', '智能点餐', '智慧课堂'],
        imgUrl: ['http://www.aischool.org.cn:8888/aischool/05.jpg',
            'http://www.aischool.org.cn:8888/aischool/01.jpg',
            'http://www.aischool.org.cn:8888/aischool/03.jpg',
            'http://www.aischool.org.cn:8888/aischool/02.jpg',
            'http://www.aischool.org.cn:8888/aischool/04.jpg'
        ],
        imgUrlIndex: '',
        index: 0,
        mode0: 'scaleToFill',
        mode1: 'scaleToFill',
        mode2: 'scaleToFill',
        mode3: 'scaleToFill',
        mode4: 'scaleToFill',
        activeMode: 'aspectFit',
        activeClass0: '',
        activeClass1: '',
        activeClass2: '',
        activeClass3: '',
        activeClass4: '',
        text: '',
        textArray: [
            '当下，人工智能技术发展火热，各大科技公司着力发展人工智能，人工智能应用前景十分广阔。未来，人工智能将发挥出无尽优势改变人们的生活，本设计从人工智能畅享出发，带有科幻色彩的设计人工智能在校园生活中的应用。未来的人工智能将为人们生活提供极大便利，广大学子的校园生活也将因此受益。',
            '带有科幻色彩的畅想海报，对未来充满期待，愿世界越来越美好！',
            '未来，人们的生活将被各种新兴技术填满，人工智能+全息投影+增强现实技术会给人们带来极其便利的体验，对于学生，这些将便利学生的生活、学习、出行、就餐、购物等方方面面。',
            '以智能硬件为载体，建立起智能校园一体化服务，全校师生可以通过校园随处可见的智能设施以及自己手持的智能终端设备进行各种活动，提高生活效率。智能餐厅应运而生。',
            '智能课堂会在老师为主，智能机器人为辅的课堂上为学生讲解生动、可视化的课堂教学方案，让学生寓教于学，更清晰的掌握知识。',
        ],
        ishidden: 'hidden',
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        if (options.index != null) {
            this.data.index = options.index
        }
        this.setData({
            title: this.data.titleArray[this.data.index],
            imgUrlIndex: this.data.imgUrl[this.data.index],
        })



        backgroundAudioManager.title = '此时此刻'
        backgroundAudioManager.epname = '此时此刻'
        backgroundAudioManager.singer = '许巍'
        backgroundAudioManager.coverImgUrl = 'http://y.gtimg.cn/music/photo_new/T002R300x300M000003rsKF44GyaSk.jpg?max_age=2592000'
        // 设置了 src 之后会自动播放
        backgroundAudioManager.src = 'http://ws.stream.qqmusic.qq.com/M500001VfvsJ21xFqb.mp3?guid=ffffffff82def4af4b12b3cd9337d5e7&uin=346897220&vkey=6292F51E1E384E061FF02C31F716658E5C81F5594D561F2E88B854E81CAAB7806D5E4F103E55D33C16F3FAC506D1AB172DE8600B37E43FAD&fromtag=46'
    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function() {

    },

    seitchImg: function(e) {
        console.log(e.currentTarget.id)
        var index = e.currentTarget.id
        var activeClassIndex = 'activeClass' + index
        switch (activeClassIndex) {
            case 'activeClass0':
                this.setData({
                    activeClass0: 'active',
                    activeClass1: '',
                    activeClass2: '',
                    activeClass3: '',
                    activeClass4: '',
                    imgUrlIndex: this.data.imgUrl[0],
                    title: this.data.titleArray[0],
                    text: this.data.textArray[0],
                    ishidden: '',
                })
                break;
            case 'activeClass1':
                this.setData({
                    activeClass1: 'active',
                    activeClass0: '',
                    activeClass2: '',
                    activeClass3: '',
                    activeClass4: '',
                    imgUrlIndex: this.data.imgUrl[1],
                    title: this.data.titleArray[1],
                    text: this.data.textArray[1],
                    ishidden: '',
                })
                break;
            case 'activeClass2':
                this.setData({
                    activeClass2: 'active',
                    activeClass0: '',
                    activeClass1: '',
                    activeClass3: '',
                    activeClass4: '',
                    imgUrlIndex: this.data.imgUrl[2],
                    title: this.data.titleArray[2],
                    text: this.data.textArray[2],
                    ishidden: '',
                })
                break;
            case 'activeClass3':
                this.setData({
                    activeClass3: 'active',
                    activeClass0: '',
                    activeClass1: '',
                    activeClass2: '',
                    activeClass4: '',
                    imgUrlIndex: this.data.imgUrl[3],
                    title: this.data.titleArray[3],
                    text: this.data.textArray[3],
                    ishidden: '',
                })
                break;
            case 'activeClass4':
                this.setData({
                    activeClass4: 'active',
                    activeClass0: '',
                    activeClass1: '',
                    activeClass2: '',
                    activeClass3: '',
                    imgUrlIndex: this.data.imgUrl[4],
                    title: this.data.titleArray[4],
                    text: this.data.textArray[4],
                    ishidden: '',
                })
                break;
            default:
                this.setData({

                })


        }

    }
})