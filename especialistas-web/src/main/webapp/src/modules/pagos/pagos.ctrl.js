(function (ng) {
    var mod = ng.module("pagoModule");
    mod.constant("pagosContext", "api/pagos");
    mod.controller('pagoCtrl', ['$scope', '$http', 'pagosContext',
        function ($scope, $http, pagosContext) {
            $http.get(pagosContext).then(function (response) {
                $scope.pagosRecords = response.data;
            });
        }
    ]);
}
)(angular);