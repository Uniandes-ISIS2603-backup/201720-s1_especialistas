(function (ng) {
    var mod = ng.module("medicamentoModule");
    mod.constant("medicamentoContext", "api/medicamentos");
    mod.controller('medicamentoCreateController', ['$scope', '$http', 'medicamentoContext', '$state', '$rootScope',
        function ($scope, $http, medicamentoContext, $state, $rootScope){
            $rootScope.edit = false;
            $scope.createMedicamento = function (){
                $http.post(medicamentoContext , {
                    nombre: $scope.medicamentoName,
                    precio: $scope.medicamentoPrecio
                }).then(function (response){
                    //usuario creado correctamente
                    $state.go('medicamentosList',{
                        medicamentoId: response.data.id
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