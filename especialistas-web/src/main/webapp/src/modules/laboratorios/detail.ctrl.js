(function (ng) {
    var mod = ng.module("laboratoriosModule");
    mod.constant("labsContext", "api/laboratorios");
    mod.controller('labDetailCtrl', ['$scope', '$http', '$state','labsContext',
        function ($scope, $http, $state, labsContext) {
            $http.get(labsContext + '/' + $state.params.laboratorioId).then(function (response) {
                    $scope.laboratorioActual = response.data;
            });
        }
    ]);
}
)(angular);