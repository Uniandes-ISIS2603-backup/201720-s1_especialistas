(function (ng) {
    var mod = ng.module("citasModule");
    mod.constant("citasContext", "api/citas");
    mod.controller('citasUpdateCtrl', ['$scope', '$http', 'citasContext', '$state', '$rootScope',
        function ($scope, $http, citasContext, $state, $rootScope) {
            $rootScope.edit = true;
            $scope.citaId = $state.params.citasId;
            
            $http.get("api/horas").then(function (response) {
                $scope.horas = response.data;
            });


            $http.get(citasContext + '/' + $scope.citaId).then(function (response) {
                var cita = response.data;
                $scope.currentCita=response.data;
                $scope.especialidadMedico= cita.hora.medico.especializacion;
               
            });

            $scope.updateCita = function () {
            $http.put(citasContext , {
                id: $scope.citaId,
                comentarios: $scope.currentCita.comentarios,
                usuario: $scope.currentCita.usuario,
                ordenesMedicas: $scope.currentCita.ordenesMedicas,
                hora: $scope.horaNueva
                
            }).then(function (response) {
                $state.go('citasList', {citaId: response.data.id}, {reload: true});
            });
            };
        }
    ]);
}
)(angular);
