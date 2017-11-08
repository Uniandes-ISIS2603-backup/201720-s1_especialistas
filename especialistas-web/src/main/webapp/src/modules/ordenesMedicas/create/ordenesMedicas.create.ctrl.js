(function (ng) {
    var mod = ng.module("ordenesMedicasModule");
    mod.constant("ordenesMedicasContext", "api/ordenesMedicas");
    mod.controller('ordenesMedicasCreateCtrl', ['$scope', '$http', 'ordenesMedicasContext', '$state', '$rootScope',
        function ($scope, $http, ordenesMedicasContext, $state, $rootScope) {
            $rootScope.edit = false;
            
           



            $scope.createOrdenMedica = function () {
            $http.post(ordenesMedicasContext , {
                descripcion: $scope.descripcion
                
                
            }).then(function (response) {
                $state.go('ordenesMedicasList', {ordenMedicaId: response.data.id}, {reload: true});
            });
            };
        }
    ]);
}
)(angular);
