(function (ng) {
    var mod = ng.module("ordenesMedicasModule");
    mod.constant("ordenesMedicasContext", "api/ordenesMedicas");
    mod.controller('ordenesMedicasDeleteCtrl', ['$scope', '$http', 'ordenesMedicasContext', '$state','$rootScope',
        function ($scope, $http, ordenesMedicasContext, $state,$rootScope) {
            $scope.ordenId = $state.params.ordenesMedicasId;
            $scope.citaParaOrdenId = $rootScope.glovalCitaId;
            $scope.deleteOrden = function () {
                $http.delete(ordenesMedicasContext + '/' + $scope.ordenId, {}).then(function (response) {
                    $state.go('citasDetail', {citasId: $rootScope.glovalCitaId}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);