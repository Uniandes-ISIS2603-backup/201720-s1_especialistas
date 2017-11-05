(function (ng) {
    var mod = ng.module("farmaciaModule");
    mod.constant("farmaciaContext", "api/farmacias");
    mod.controller('farmaciaCreateController', ['$scope', '$http', 'farmaciaContext', '$state', '$rootScope',
        function ($scope, $http, farmaciaContext, $state, $rootScope){
            $rootScope.edit = false;
            $scope.createFarmacia = function (){
                $http.post(farmaciaContext , {
                    nombre: $scope.farmaciaName,
                    radio: $scope.farmaciaRadio,
                    ubicacion:{
                        nombre: $scope.ubicacionName,
                        latitud: $scope.latitudUbicacion,
                        longitud: $scope.longitudUbicacion
                    }
                }).then(function (response){
                    //usuario creado correctamente
                    $state.go('farmaciasList',{
                        farmaciaId: response.data.id
                    },
                    {
                        reload: true
                    }
                    );
                });
            };
        }
    ]);
}
)(angular);