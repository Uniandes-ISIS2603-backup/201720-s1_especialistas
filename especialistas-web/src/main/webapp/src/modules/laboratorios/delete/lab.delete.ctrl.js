(function (ng) {
    var mod = ng.module("laboratoriosModule");
    mod.constant("labsContext", "api/laboratorios");
    mod.controller('labDeleteCtrl', ['$scope', '$http', 'labsContext', '$state', '$rootScope',
        function ($scope, $http, labsContext, $state, $rootScope) {
            var idLab = $state.params.laboratorioId;
            $scope.deleteLab = function () {
                $http.delete(labsContext + '/' + idLab, {}).then(function (response) {
                    $state.go('laboratoriosList', {id: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);