(function (ng) {
    var mod = ng.module("medicoModule");
    mod.constant("medicosContext", "api/authors");
    mod.controller('medicoUpdateCtrl', ['$scope', '$http', 'medicosContext', '$state', '$rootScope',
        function ($scope, $http, medicosContext, $state, $rootScope) {
            $rootScope.edit = true;
            $scope.medicoId = $state.params.medicoId;
            
            $http.get("api/especializaciones").then(function (response) {
                $scope.especializaciones = response.data;
            });


            $http.get(medicosContext + '/' + $scope.medicoId).then(function (response) {
                var medico = response.data;
                $scope.nombreMedico = medico.nombre;
                $scope.especializacionMedico = medico.especializacion;
                $scope.tempAgenda = medico.agenda;
            });

            $scope.createMedico = function () {
            $http.put(medicosContext + "/" + $scope.medicoId, {
                nombre: $scope.nombreMedico,
                especializacion: $scope.especializacionMedico,
            }).then(function (response) {
                $state.go('horasList', {medicoId: response.data.id}, {reload: true});
            });
            };
        }
    ]);
}
)(angular);

