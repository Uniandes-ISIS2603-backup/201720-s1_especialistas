(function (ng) {
    var mod = ng.module("horaModule");
    mod.constant("horasContext", "api/horas");
    mod.constant("citasContext", "api/citas");
    mod.constant("consultoriosContext", "api/consultorio");
    mod.controller('horaCtrl', ['$scope', '$state', '$http', 'horasContext', 'citasContext', 'consultoriosContext',
        function ($scope, $state, $http, horasContext, citasContext, consultoriosContext) {
            if ($state.params.horaId !== undefined) {
                $http.get(horasContext + '/' + $state.params.horaId).then(function (response) {
                    $scope.hora = response.data;
                });
            }
            if ($scope.horaActual.cita !== null) {
                $http.get(citasContext + '/' + $scope.horaActual.cita.id).then(function (response) {
                    $scope.cita = response.data;
                });
            }
            $http.get(consultoriosContext + '/' + $scope.horaActual.consultorio.id).then(function (response) {
                $scope.consultorio = response.data;
            });
        }
    ]);
}
)(angular);