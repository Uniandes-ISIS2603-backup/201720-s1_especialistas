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
                        
                        var referenceCode = pago.id+referenceCode;
                        
                        $scope.reff = referenceCode;
                        

                        $scope.codigo = $.md5('4Vj8eK4rloUd272L48hsrarnUA~508029~'+referenceCode+'~'+pago.precio+'~COP');
                        

                    });

            



            $scope.pagarAuthor = function () {
                
            };
            $scope.pagoId = idPago;

        }
    ]);
}
)(window.angular);