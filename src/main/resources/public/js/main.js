window.onload = function () {
    var app = new Vue({
        el: '#app',
        data: {
            sortKey: 'createdAt',
            message: 'Hello Vue!',
            notificationResponse: {},
            positions: [],
        },

        methods: {
            getData: function() {

                this.$http.get('/api/notifications').then(function(response) {

                    this.notificationResponse = Object.assign({},this.notificationResponse, response.data);
                    this.notificationResponse.notifications = this.notificationResponse.notifications.reverse();

                }, function(response) {
                    console.log("in error: " + response)
                });

                this.$http.get('/api/route').then(function(response) {

                    this.positions = Object.assign({}, this.positions, response.data);
                    this.positions.length = response.data.length;
                    this.updateCanvas();

                }, function(response) {
                    console.log("in error: " + response.data)
                });
            },
            reset: function (event) {
                this.$http.get('/api/reset').then(function(response) {
                    console.log(response);
                }, function(response) {
                    console.log("in error: " + response)
                });
            },
            updateCanvas: function () {
                var canvas = document.getElementById('canvas'),
                    ctx = canvas.getContext('2d');
                var image = document.getElementById('source');
                ctx.clearRect(0, 0, canvas.width, canvas.height);
                ctx.beginPath();
                ctx.drawImage(image,0,0);
                ctx.closePath();

                var colors = ["#3370d4", "#dc42f4", "#b4e258", "#e8963a"];

                for(var i = 0; i < this.positions.length; i++) {
                    ctx.beginPath();
                    var x = this.positions[i].x;
                    var y = this.positions[i].y;
                    var id = this.positions[i].mobileId;
                    ctx.fillStyle = colors[id % 4];
                    ctx.arc(x,y,20,0,2*Math.PI);
                    ctx.fill();
                    ctx.closePath();
                }
            }
        },
        created: function () {
            this.getData();

            setInterval(function () {
                this.getData();
            }.bind(this), 500);
        }
    });
}/**
 * Created by ahmet on 4/13/17.
 */
