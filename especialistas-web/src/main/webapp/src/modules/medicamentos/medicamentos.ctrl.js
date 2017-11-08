(function (ng) {
    var mod = ng.module("medicamentoModule");
    mod.constant("medicamentoContext", "api/medicamentos");
    mod.controller('medicamentoCtrl', ['$scope', '$http', 'medicamentoContext',
        function ($scope, $http,medicamentoContext) {
            $http.get(medicamentoContext).then(function (response) {
                $scope.medicamentos = response.data;
            });
        }
    ]);
}
)(angular);