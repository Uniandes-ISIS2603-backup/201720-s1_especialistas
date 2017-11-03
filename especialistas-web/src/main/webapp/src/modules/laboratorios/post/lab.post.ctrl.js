(function (ng) {
    var mod = ng.module("laboratoriosModule");
    mod.constant("labsContext", "api/laboratorios");
    mod.controller('labNewCtrl', ['$scope', '$http', 'labsContext','$state', '$rootScope',
        function ($scope, $http, labsContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createLab = function () {
                $http.post(labsContext,{
                    nombre: $scope.nombreLab,
                    nombre: $scope.ubicacion.nombre,
                    longitud: $scope.ubicacion.longitud,
                    latitud: $scope.ubicacion.latitud
                }).then(function (response) {
                    //Laboratorio created successfully
                    $state.go('laboratoriosList', {id: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);