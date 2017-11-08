(function (ng) {
    var mod = ng.module("pagoModule");
    mod.constant("pagoContext", "api/pagos");
    mod.controller('pagosDeleteController', ['$scope', '$http', 'pagoContext', '$state',
        function ($scope, $http, pagoContext, $state) {
            var idPago = $state.params.pagoId;
            $scope.deleteAuthor = function () {
                $http.delete(pagoContext + '/' + idPago, {}).then(function (response) {
                    $state.go('pagosList', {pagoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);