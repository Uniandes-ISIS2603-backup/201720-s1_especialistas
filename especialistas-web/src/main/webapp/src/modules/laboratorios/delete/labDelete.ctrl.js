(function (ng) {
    var mod = ng.module("laboratoriosModule");
    mod.constant("labsContext", "api/laboratorios");
    mod.controller('labDeleteCtrl', ['$scope', '$http', '$state', 'labsContext',
        function ($scope, $http, $state, labsContext) {
            $scope.deleteLab = function () {
                $http.delete(labsContext + '/' + $state.params.laboratorioId).then(function (response) {
                    $state.go('laboratoriosList', {id: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);