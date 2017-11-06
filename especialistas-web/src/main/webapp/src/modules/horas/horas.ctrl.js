(function (ng) {
    var mod = ng.module("horaModule");
    mod.constant("medicosContext", "api/medicos");
    mod.controller('horaCtrl', ['$scope', '$state', '$http', 'medicosContext',
        function ($scope, $state, $http, medicosContext) {
            if($state.params.medicoId !== undefined){
                $http.get(medicosContext + '/' + $state.params.medicoId + '/agenda').then(function (response) {
                    $scope.agenda = response.data;
                });
            }
        }
    ]);
}
)(angular);