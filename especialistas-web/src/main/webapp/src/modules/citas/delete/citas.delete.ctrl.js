(function (ng) {
    var mod = ng.module("citasModule");
    mod.constant("citasContext", "api/citas");
    mod.controller('citasDeleteCtrl', ['$scope', '$http', 'citasContext', '$state',
        function ($scope, $http, citasContext, $state) {
            $scope.citaId = $state.params.citasId;
            $scope.deleteCita = function () {
                $http.delete(citasContext + '/' + $scope.citaId, {}).then(function (response) {
                    $state.go('citasList', {citaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);