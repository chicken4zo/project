let names = [];
let counts = [];
let totalCount = 0;
let persents = [];

window.addEventListener('DOMContentLoaded', function () {
    $.ajax({
        url: "chartpage.chart",
        type: "GET",
        success: function (data) {
            $.each(data, function (index, obj) {
                let i = names.indexOf(obj.address);

                if (i !== -1) {
                    counts[i] += 1;
                } else {
                    names.push(obj.address);
                    counts.push(1);
                }
            })
            getInfo();
            getUserChart();
        }
    })
})

const getInfo = () => {
    counts.forEach((item) => {
        totalCount += item;
    })

    counts.forEach((item) => {
        persents.push(((item / totalCount) * 100));
    })

    let sum = persents.reduce((a, b) => (a + b));
    console.log(sum);
}


const getUserChart = () => {
    const chart = Highcharts.chart('user-container', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: '지역별 유저 수'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        accessibility: {
            point: {
                valueSuffix: '%'
            }
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            colorByPoint: true,
            data: [{
                name: names[0],
                y: persents[0],
            }, {
                name: names[1],
                y: persents[1],
            }, {
                name: names[2],
                y: persents[2],
            }, {
                name: names[3],
                y: persents[3],
            }, {
                name: names[4],
                y: persents[4],
            }, {
                name: names[5],
                y: persents[5],
            }, {
                name: names[6],
                y: persents[6],
            }, {
                name: names[7],
                y: persents[7],
            }, {
                name: names[8],
                y: persents[8],
            }]
        }]
    });
}



