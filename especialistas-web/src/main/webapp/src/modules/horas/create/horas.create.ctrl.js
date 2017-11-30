(function (ng) {
    var mod = ng.module("horaModule");
    mod.constant("horasContext", "api/horas");
    mod.constant("hospitalesContext", "api/hospitales");
    mod.controller('horaCreateCtrl', ['$scope', '$http', 'horasContext', 'hospitalesContext', '$state',
        function ($scope, $http, horasContext, hospitalesContext, $state) {
            $http.get(hospitalesContext).then(function (response) {
                $scope.hospitales = response.data;
            });
            $scope.createHora = function () {
                horaInicio = $state.params.hora.getTime();
                for (i = 0; i < $state.params.dia; i++)
                    horaInicio += 24 * 3600000;
                $http.get(hospitalesContext + '/' + $scope.idHospital).then(function (response) {
                    hospital = response.data;
                    $http.post(horasContext, {
                        horaInicio: new Date(horaInicio),
                        horaFin: new Date(horaInicio + 20 * 60000),
                        medico: $scope.$parent.$parent.medico,
                        consultorio: hospital.consultorios[0]
                    }).then(function (response) {
                        $state.go('horasList', {horaId: response.data.id}, {reload: true});
                    });

                });
            };
        }
    ]);
}
)(angular);