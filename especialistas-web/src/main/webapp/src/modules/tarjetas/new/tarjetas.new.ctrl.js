(function (ng) {
    var mod = ng.module("tarjetaModule");
    mod.constant("tarjetaContext", "api/tarjetas");
    mod.controller('tarjetasNewController', ['$scope', '$http', 'tarjetaContext', '$state', '$rootScope',
        function ($scope, $http, tarjetaContext, $state, $rootScope){
            $rootScope.edit = false;
            $scope.createTarjeta = function (){
                $http.post(tarjetaContext , {
                    numero: $scope.tarjetaNumero,
                    
                }).then(function (response){
                    //tarjeta creado correctamente
                    $state.go('tarjetasList',{
                        tarjetaId: response.data.id
                    },
                    {
                        reload: true
                    }
                    );
                });
            };
        }
    ]);
}
)(angular);