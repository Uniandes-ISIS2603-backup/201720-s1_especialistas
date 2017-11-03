(function (ng){
    var mod = ng.module("tarjetaModule");
    mod.constant("tarjetaContext","api/tarjetas");
    mod.controller('tarjetaCtrl',['$scope', '$http', 'tarjetaContext',
        function ($scope, $http, tarjetaContext){
            $http.get(tarjetaContext).then(function (response){
                $scope.tarjetasRecords = response.data;
            });
        }
    ]);
})(angular);