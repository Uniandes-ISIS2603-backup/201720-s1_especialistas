(function (ng) {
    var mod = ng.module("hospitalesModule");
    mod.constant("hospitalesContext", "api/hos");
    mod.controller('hospitalesCreateController', ['$scope', '$http', 'hospitalesContext', '$state', '$rootScope',
        function ($scope, $http, hospitalesContext, $state, $rootScope){
            $rootScope.edit = false;
            $scope.createHospital = function (){
                $http.post(hospitalesContext , {
                    nombre: $scope.hospitalName,
                    ubicacion: {
                        nombre: $scope.hospitalUbicacionName,
                        latitud: $scope.hospitalUbicacionLatitud,
                        longitud: $scope.hospitalUbicacionLongitud
                    }
                }).then(function (response){
                    //usuario creado correctamente
                    $state.go('hospitalesList',{
                        hospitalId: response.data.id
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