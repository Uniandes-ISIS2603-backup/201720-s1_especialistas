(function (ng) {
    var mod = ng.module("laboratoriosModule");
    mod.constant("labsContext", "api/laboratorios");
    mod.controller('labNewCtrl', ['$scope', '$http', 'labsContext', '$state', '$rootScope',
        function ($scope, $http, labsContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createLab = function () {
                $http.post(labsContext, {
                    name: $scope.labName,
                    ubicacionName: $scope.nombreUbi,
                    longitud: $scope.longitud,
                    latitud: $scope.latitud
                }).then(function (response) {
                    //Laboratorio created successfully
                    $state.go('laboratoriosList', {labId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);