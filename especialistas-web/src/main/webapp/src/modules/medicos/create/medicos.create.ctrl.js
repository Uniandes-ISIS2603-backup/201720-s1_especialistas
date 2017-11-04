(function (ng) {
    var mod = ng.module("medicoModule");
    mod.constant("medicosContext", "api/medicos");
    mod.controller('medicoCreateCtrl', ['$scope', '$http', 'medicosContext', '$state', '$rootScope',
        function ($scope, $http, medicosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createMedico = function () {
                $http.post(medicosContext, {
                    nombre: $scope.nombreMedico,
                    especializacion: $scope.especializacionMedico,
                    agenda : []
                }).then(function (response) {
                    $state.go('medicosList', {medicoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);