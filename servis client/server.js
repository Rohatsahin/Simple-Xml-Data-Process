var nameApp = angular.module('nameApp', ['chieffancypants.loadingBar', 'ngAnimate', "highcharts-ng"]);

nameApp.controller('nameCtrl', ['$scope', '$http', '$window', '$timeout', 'cfpLoadingBar', function ($scope, $http, $window, $timeout, cfpLoadingBar) {

        $scope.sentData = function () {

            $http({
                url: 'http://localhost:8080/rest/xmlUrl',
                method: "POST",
                data: JSON.stringify({"domain": $scope.Xmldata}),
                headers: {'Content-Type': 'application/json'}
            }).success(function (data, status, headers, config) {
                $window.alert("Veri Basari ile Gonderilmistir : " + status);
            }).error(function (data, status, headers, config) {
                $window.alert("Server error " + status);
            });
        }

        $scope.search = function ()
        {

            $http({
                method: 'GET',
                url: 'http://localhost:8080/rest/product/' + $scope.Searchdata
            }).success(function (data) {

                $scope.prices = [];
                $scope.dates = [];
                angular.forEach(data, function (value, key) {
                    console.log(key + ': ' + value.title);
                    $scope.producttitle = value.title;
                    angular.forEach(value.price, function (value, key) {
                        console.log(key + ': ' + value.price);
                        console.log(key + ': ' + value.date);
                        $scope.prices.push(value.price);
                        $scope.dates.push(value.date);

                    });
                });
                $scope.Items = data;
                $scope.highchartsNG = {
                    chart: {
                        type: 'line'
                    },
                    xAxis: {
                        categories: $scope.dates
                    },
                    yAxis: {
                         title: {
                         text: 'Fiyat (TL)'
                         },
                         plotLines: [{
                         value: 0,
                         width: 1,
                         color: '#808080'
                        }]
                          },
                    series: [{
                            name: $scope.producttitle,
                            data: $scope.prices
                        }],
                    legend: {
                        enabled: false
                    },
                    title: {
                        text: $scope.producttitle
                    }
                };



            }).error(function (data, status, headers, config) {
                $window.alert("Error " + status);
            });


        }

        $scope.start = function () {
            cfpLoadingBar.start();
        };

        $scope.complete = function () {
            cfpLoadingBar.complete();
        }


        // fake the initial load so first time users can see it right away:
        $scope.start();
        $scope.fakeIntro = true;
        $timeout(function () {
            $scope.complete();
            $scope.fakeIntro = false;
        }, 750);

    }]);

nameApp.config(function (cfpLoadingBarProvider) {
    cfpLoadingBarProvider.includeSpinner = true;
});

