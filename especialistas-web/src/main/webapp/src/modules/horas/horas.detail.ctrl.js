(function (ng) {
    var mod = ng.module("horaModule");
    mod.constant("horasContext", "api/horas");
    mod.constant("citasContext", "api/citas");
    mod.constant("consultoriosContext", "api/consultorios");
    mod.controller('horaDetailCtrl', ['$scope', '$state', '$rootScope', '$http', 'horasContext', 'citasContext', 'consultoriosContext',
        function ($scope, $state, $rootScope, $http, horasContext, citasContext, consultoriosContext) {
            
            if ($state.params.horaId !== undefined) {
                $http.get(horasContext + '/' + $state.params.horaId).then(function (response) {
                    $scope.hora = response.data;
                    $rootScope.hayCita = false;
                    if ($scope.hora.cita !== undefined) {
                        $rootScope.hayCita = true;
                        $http.get(citasContext + '/' + $scope.hora.cita.id).then(function (response) {
                            $scope.cita = response.data;
                        });
                    }
                    $http.get(consultoriosContext + '/' + $scope.hora.consultorio.id).then(function (response) {
                        $scope.consultorio = response.data;
                    });

                });
            }
        }
    ]);
}
)(angular);