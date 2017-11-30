(function (ng) {
    var mod = ng.module("horaModule");
    mod.constant("horasContext", "api/horas");
    mod.constant("consultoriosContext", "api/consultorios");
    mod.controller('horaDetailCtrl', ['$scope', '$state', '$http', 'horasContext',
        function ($scope, $state, $http, horasContext) {
            
            if ($state.params.horaId !== undefined) {
                $http.get(horasContext + '/' + $state.params.horaId).then(function (response) {
                    $scope.hora = response.data;
                    $scope.hayCita = false;
                    if ($scope.hora.cita !== undefined) {
                        $scope.hayCita = true;
                        $http.get('api/citas/' + $scope.hora.cita.id).then(function (response) {
                            $scope.cita = response.data;
                        });
                    }
                    $http.get( 'api/consultorios/' + $scope.hora.consultorio.id).then(function (response) {
                        $scope.consultorio = response.data;
                    });

                });
            }
        }
    ]);
}
)(angular);