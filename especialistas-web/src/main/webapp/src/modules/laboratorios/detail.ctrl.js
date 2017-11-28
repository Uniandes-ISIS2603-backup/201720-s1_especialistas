(function (ng) {
    var mod = ng.module("laboratoriosModule");
    mod.constant("labsContext", "api/laboratorios");
    mod.controller('labDetailCtrl', ['$scope', '$http', '$state','labsContext', '$rootScope',
        function ($scope, $http, $state, labsContext,$rootScope) {
            $http.get(labsContext + '/' + $state.params.laboratorioId).then(function (response) {
                    $scope.laboratorioActual = response.data;
                    $scope.examenesLab = response.data.examenes;
            });
                $rootScope.idlab=$state.params.laboratorioId;
        }
    ]);
}
)(angular);