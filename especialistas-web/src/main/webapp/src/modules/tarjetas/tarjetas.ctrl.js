(function (ng){
    var mod = ng.module("tarjetaModule");
    mod.constant("tarjetaContext","api/tarjetas");
    mod.controller('tarjetaCtrl',['$scope', '$http', 'tarjetaContext', '$state',
        function ($scope, $http, tarjetaContext, $state){
            $http.get(tarjetaContext).then(function (response){
                $scope.tarjetasRecords = response.data;
            });
            
            if (($state.params.tarjetaId !== undefined) && ($state.params.tarjetaId !== null)) {
                $http.get(tarjetaContext + '/' + $state.params.tarjetaId).then(function (response) {
                    $scope.currentTarjeta = response.data;
                });
            }
        }
    ]);
})(angular);