(function (ng) {
    var mod = ng.module("pagoModule");
    mod.constant("pagoContext", "api/pagos");
    mod.controller('pagosNewController', ['$scope', '$http', 'pagoContext', '$state', '$rootScope',
        function ($scope, $http, pagoContext, $state, $rootScope){
            $rootScope.edit = false;
            $scope.createPago = function (){
                
                if (($state.params.usuarioId !== undefined) && ($state.params.usuarioId !== null)) {
                    $http.post(pagoContext , {
                        ref: $scope.pagoRef,
                        precio: $scope.pagoPrecio,
                        pai: $scope.pagoPaga,
                        metodo: $scope.pagoMetodo,
                        usuario:{
                            id: $state.params.usuarioId,
                            nombre: $state.params.usuarioNombre
                        }

                    }).then(function (response){
                        //pago creado correctamente
                        $state.go('pagosList',{
                            pagoId: response.data.id
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