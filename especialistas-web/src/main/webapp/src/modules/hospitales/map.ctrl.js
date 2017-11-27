        angular.module('farmaciaModule')
            .controller('mapCtrl',['$scope','$stateParams','$http', function($scope,$rootScope,$http) {
       
            $http.get("api/farmacias/"+$rootScope.id).then(function (response) {
                
               var rec=response.data;
                $scope.map = {center: {latitude: rec.ubicacion.latitud, longitude: rec.ubicacion.longitud }, zoom: 12 };
                $scope.cent={latitude: response.data.latitud, longitude: response.data.longitud};
            });
                $scope.show = true;
            }]);

