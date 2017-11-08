(function (ng) {
    var mod = ng.module("laboratoriosModule");
    mod.constant("laboratoriosContext", "api/laboratorios");
    mod.controller('labNewCtrl', ['$scope', '$http', 'laboratoriosContext', '$state', '$rootScope',
        function ($scope, $http, laboratoriosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createLab = function () {
                $http.post(laboratoriosContext, {
                    nombre: $scope.labName, 
                    ubicacion:{
                       longitud: $scope.ubicacionLongitud,
                        latitud:$scope.ubicacionLatitud,
                        nombre:$scope.ubicacionName
                    }
                }).then(function (response) {
                    //Lab created successfully
                    $state.go('laboratoriosList', {laboratorioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);