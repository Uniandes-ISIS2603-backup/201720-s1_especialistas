        angular.module('farmaciaModule')
            .controller('FarmaciaMapCtrl',['$scope','$stateParams','$http', function($scope,$rootScope,$http) {
       
            $http.get("api/farmacias/"+$rootScope.id).then(function (response) {
                
               var rec=response.data;
                $scope.map = {center: {latitude: rec.ubicacion.latitud, longitude: rec.ubicacion.longitud }, zoom: 12,
                bounds: {
                  northeast: {
                    latitude: 45.1451,
                    longitude: -80.6680
                  },
                  southwest: {
                    latitude: 30.000,
                    longitude: -120.6680
                  }
                } };
              var createRandomMarker = function(i, bounds, idKey) {

              if (idKey == null) {
                idKey = "id";
              }

              var latitude = $scope.map.center.latitude;
              var longitude = $scope.map.center.longitude;
              var ret = {
                latitude: latitude,
                longitude: longitude,
                title: 'm' + i
              };
              ret[idKey] = i;
              return ret;
            };
            var markers = [];
            for (var i = 0; i < 1; i++) {
              markers.push(createRandomMarker(i, $scope.map.bounds));
            }
            $scope.randomMarkers = markers;
            
            $scope.circles = [
            {
                id: 1,
                center: {
                    latitude: $scope.map.center.latitude,
                    longitude: $scope.map.center.longitude
                },
                radius: rec.radio*1000,
                stroke: {
                    color: '#00F',
                    weight: 2,
                    opacity: 1
                },
                fill: {
                    color: '#009',
                    opacity: 0.5
                },
                geodesic: true, // optional: defaults to false
                clickable: true, // optional: defaults to true
            }
        ];
            
            });
            $scope.options = {
              scrollwheel: false
            };

                        $scope.show = true;
            }]);

