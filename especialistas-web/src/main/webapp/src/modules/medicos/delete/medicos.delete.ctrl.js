(function (ng) {
    var mod = ng.module("medicoModule");
    mod.constant("medicosContext", "api/medicos");
    mod.controller('medicoDeleteCtrl', ['$scope', '$http', 'medicosContext', '$state',
        function ($scope, $http, medicosContext, $state) {
            $scope.medicoId = $state.params.medicoId;
            $scope.deleteMedico = function () {
                $http.delete(medicosContext + '/' + $scope.medicoId, {}).then(function (response) {
                    $state.go('medicosList', {medicoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);