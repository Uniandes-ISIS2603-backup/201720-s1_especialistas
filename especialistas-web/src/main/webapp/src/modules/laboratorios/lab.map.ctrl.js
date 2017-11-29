        angular.module('laboratoriosModule')
            .controller('labMapCtrl',['$scope','$stateParams','$http', function($scope,$rootScope,$http) {
       
             $scope.map = {center: {latitude: 4.3334, longitude: -74.0454 }, zoom: 10,
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
            $http.get("api/laboratorios/"+$rootScope.laboratorioId).then(function (response) {
                
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
            
            });
            $scope.options = {
              scrollwheel: false
            };

                        $scope.show = true;
            }]);

