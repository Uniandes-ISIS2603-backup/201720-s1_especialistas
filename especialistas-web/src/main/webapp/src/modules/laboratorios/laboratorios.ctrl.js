(function (ng) {
    var mod = ng.module("laboratoriosModule");
    mod.constant("labsContext", "api/laboratorios");
    mod.controller('laboratorioCtrl', ['$scope', '$http', 'labsContext',
        function ($scope, $http, labsContext) {
            $http.get(labsContext).then(function (response) {
                $scope.laboratoriosRecords = response.data;
            });
        }
    ]);
}
)(angular);