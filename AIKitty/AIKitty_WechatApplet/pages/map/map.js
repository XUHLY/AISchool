// map.js
Page({
    data: {
        latitude: '', //纬度
        longitude: '', //经度
        markers: [],
        polyline: [],
        includedpoints:[]
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
  var that = this
        wx.getLocation({
            type: 'wgs84',
            success(res) {
                //const latitude = res.latitude //纬度
                //const longitude = res.longitude //经度
                //const altitude = res.altitude //高度
                //const speed = res.speed //当前速度
                //const accuracy = res.accuracy //
                console.log(res.latitude)
                console.log(res.longitude)
                that.setData({
                    latitude: res.latitude,
                    longitude: res.longitude
                })

            },
            fail(res) {
                that.setData({
                    latitude: '39.90',
                    longitude: '116.40'
                })
                wx.showToast({
                    title: '请打开手机定位开关',
                    icon: 'none',
                    duration: 3000
                })
            }

        })

    },

    pointInfo: function() {
        var that = this
        wx.chooseLocation({
            success(res) {
                //console.log(res.name)
                //console.log(res.address)
                //console.log(res.latitude)
                //console.log(res.longitude)
                console.log(that.data.latitude)
                console.log(that.data.longitude)
                var startLatitude = that.data.latitude
                var startLongitude = that.data.longitude
                var endLatitude = res.latitude
                var endLongitude = res.longitude
                that.setData({
                    markers: [{
                        iconPath: '/resources/others.png',
                        id: 0,
                        latitude: endLatitude,
                        longitude: endLongitude,
                        width: 50,
                        height: 50
                    }],
                    polyline: [{
                        points: [{
                            longitude: startLongitude,
                            latitude: startLatitude
                        }, {
                            longitude: endLongitude,
                            latitude: endLatitude
                        }],
                        color: '#FF0000DD',
                        width: 2,
                        dottedLine: true
                    }],
                    includedpoints: [{
                        longitude: startLongitude,
                        latitude: startLatitude
                    }, {
                            longitude: endLongitude,
                            latitude: endLatitude
                        }]
                })
            }
        })
    }
})