(function (ng) {
    var mod = ng.module("horaModule");
    mod.constant("horasContext", "api/horas");
    mod.controller('horaCtrl', ['$scope', '$state', '$http', 'horasContext',
        function ($scope, $state, $http, horasContext) {
            $http.get(horasContext).then(function (response) {
                $scope.agenda = response.data;
            });
            
            if($state.params.horaId !== undefined){
                $http.get(horasContext + '/' + $state.params.horaId).then(function (response) {
                    $scope.horaActual = response.data;
                });
            }
        }
    ]);
}
)(angular);