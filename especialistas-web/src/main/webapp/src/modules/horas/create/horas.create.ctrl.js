(function (ng) {
    var mod = ng.module("horaModule");
    mod.constant("horasContext", "api/horas");
    mod.constant("consultoriosContext", "api/consultorios");
    mod.controller('horaCreateCtrl', ['$scope', '$http', 'horasContext', 'consultoriosContext', '$state',
        function ($scope, $http, horasContext, consultoriosContext, $state) {
            $http.get(consultoriosContext).then(function (response) {
                $scope.consultorios = response.data;
            });
            
            $scope.createHora = function () {
                $http.post(horasContext, {
                    horaInicio: $scope.horaInicio,
                    horaFin: $scope.horaInicio + 20,
                    medico: $scope.$parent.$parent.medico,
                    consultorio : {'id': $scope.$eval($scope.consultorioHora).id,
                                    'numero': $scope.$eval($scope.consultorioHora).numero}
                }).then(function (response) {
                    $state.go('horasList', {horaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);