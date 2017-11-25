(function (ng) {
    var mod = ng.module("ordenesMedicasModule");
    mod.constant("ordenesMedicasContext", "api/ordenesMedicas");
    mod.controller('ordenesMedicasCreateCtrl', ['$scope', '$http', 'ordenesMedicasContext', '$state', '$rootScope',
        function ($scope, $http, ordenesMedicasContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.citaParaOrdenId = $rootScope.glovalCitaId;
           



            $scope.createOrdenMedica = function () {
            $http.post(ordenesMedicasContext , {
                descripcion: $scope.descripcion,
                cita : $rootScope.glovalCita
                
                
                
            }).then(function (response) {
                $state.go('citasDetail', {citasId: response.cita.id}, {reload: true});
            });
            };
        }
    ]);
}
)(angular);
