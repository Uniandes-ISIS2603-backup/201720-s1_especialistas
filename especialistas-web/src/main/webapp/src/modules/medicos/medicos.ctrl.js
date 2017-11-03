(function (ng) {
    var mod = ng.module("medicoModule");
    mod.constant("medicosContext", "api/medicos");
    mod.controller('medicoCtrl', ['$scope', '$state', '$http', 'medicosContext',
        function ($scope, $state, $http, medicosContext) {
            $http.get(medicosContext).then(function (response) {
                $scope.medicosRecords = response.data;
            });
            
            if($state.params.medicoId !== undefined){
                $http.get(medicosContext + '/' + $state.params.medicoId).then(function (response) {
                    $scope.medico = response.data;
                    $scope.agenda = response.data.agenda;
                });
            }
        }
    ]);
}
)(angular);