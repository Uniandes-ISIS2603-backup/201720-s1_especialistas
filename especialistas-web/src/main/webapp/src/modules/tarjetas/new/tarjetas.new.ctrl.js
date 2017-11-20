(function (ng) {
    var mod = ng.module("tarjetaModule");
    mod.constant("tarjetaContext", "api/tarjetas");
    mod.controller('tarjetasNewController', ['$scope', '$http', 'tarjetaContext', '$state', '$rootScope',
        function ($scope, $http, tarjetaContext, $state, $rootScope){
            $rootScope.edit = false;
            $scope.createTarjeta = function (){
                
                if (($state.params.usuarioId !== undefined) && ($state.params.usuarioId !== null)) {
                    $http.post(tarjetaContext , {
                        numero: $scope.tarjetaNumero,
                        usuario:{
                            id: $state.params.usuarioId,
                            nombre: $state.params.usuarioNombre
                        }

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
                } 
            };
        }
    ]);
}
)(angular);