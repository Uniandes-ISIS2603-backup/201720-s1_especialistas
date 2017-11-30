(function (ng) {
    var mod = ng.module("pagoModule");
    mod.constant("pagoContext", "api/pagos");
    mod.controller('pagosPagarController', ['$scope', '$http', 'pagoContext', '$state',
        function ($scope, $http, pagoContext, $state) {
            var idPago = $state.params.pagoId;

            $http.get(pagoContext + '/' + idPago).then(function (response) {
                        var pago = response.data;
                        $scope.precio = pago.precio;
                        //$scope.usuarioCedula = pago.cedula;
                        

                        var referenceCode = Math.floor(Math.random() * (9999999999 - 100230033 + 1)) + 100230033;
                        
                        referenceCode = pago.id+referenceCode;
                        
                        $scope.reff = referenceCode;
                        
                        $rootScope.idPago = idPago;

                        $scope.codigo = $.md5('4Vj8eK4rloUd272L48hsrarnUA~508029~'+referenceCode+'~'+pago.precio+'~COP');
                        
                        setTimeout(function(){
                            $http.get("http://proyectoseneca.esy.es/confirmationr.php?id=" + $rootScope.idPago).then(function(response){
                                var nani = response.data;
                                if(nani.pago === true){
                                    $http.get(pagoContext + '/' + $rootScope.idPago).then(function (response) {
                                        var pago = response.data;
                                        $rootScope.pagoRef2 = pago.ref;
                                        $rootScope.pagoPrecio2 = pago.precio;
                                        $rootScope.pagoMetodo2 = pago.metodo;
                                        $rootScope.usuarioId2 = pago.usuario.id;
                                        $rootScope.usuarioNombre2 = pago.usuario.nombre;
                                    });

                                    $http.put(pagoContext + '/' + $rootScope.idPago, {
                                        ref: $rootScope.pagoRef2,
                                        precio: $rootScope.pagoPrecio2,
                                        pai: true,
                                        metodo: $rootScope.pagoMetodo2,
                                        usuario:{
                                            id: $rootScope.usuarioId2,
                                            nombre: $rootScope.usuarioNombre2
                                        }
                                    }).then(function(response){});
                                }
                            });
                         }, 300000);

                    });

            



            $scope.pagarAuthor = function () {
                
            };
            $scope.pagoId = idPago;

        }
    ]);
}
)(window.angular);