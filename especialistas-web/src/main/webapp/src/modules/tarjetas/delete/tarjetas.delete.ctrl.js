(function (ng) {
    var mod = ng.module("tarjetaModule");
    mod.constant("tarjetaContext", "api/tarjetas");
    mod.controller('tarjetasDeleteController', ['$scope', '$http', 'tarjetaContext', '$state',
        function ($scope, $http, tarjetaContext, $state) {
            var idTarjeta = $state.params.tarjetaId;
            $scope.deleteAuthor = function () {
                $http.delete(tarjetaContext + '/' + idTarjeta, {}).then(function (response) {
                    $state.go('tarjetasList', {tarjetaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);