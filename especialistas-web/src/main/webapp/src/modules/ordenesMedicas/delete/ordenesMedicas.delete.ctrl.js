(function (ng) {
    var mod = ng.module("ordenesMedicasModule");
    mod.constant("ordenesMedicasContext", "api/ordenesMedicas");
    mod.controller('ordenesMedicasDeleteCtrl', ['$scope', '$http', 'ordenesMedicasContext', '$state',
        function ($scope, $http, ordenesMedicasContext, $state) {
            $scope.ordenId = $state.params.ordenesMedicasId;
            $scope.deleteOrden = function () {
                $http.delete(ordenesMedicasContext + '/' + $scope.ordenId, {}).then(function (response) {
                    $state.go('citasList', {ordenId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);