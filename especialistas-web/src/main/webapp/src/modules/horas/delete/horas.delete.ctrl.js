(function (ng) {
    var mod = ng.module("horaModule");
    mod.constant("horasContext", "api/horas");
    mod.controller('horaDeleteCtrl', ['$scope', '$http', 'horasContext', '$state',
        function ($scope, $http, horasContext, $state) {
            $scope.horaId = $state.params.horaId;
            $scope.deleteHora = function () {
                $http.delete(horasContext + '/' + $scope.horaId, {}).then(function (response) {
                    $state.go('horasList', {horaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);