(function (ng) {
    var mod = ng.module("examenesModule");
    mod.constant("examenesContext", "api/examenes");
    mod.controller('examenNewCtrl', ['$scope', '$http', 'examenesContext', '$state', '$rootScope',
        function ($scope, $http, examenesContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createExam = function () {
                $http.post(examenesContext, {
                    nombre: $scope.examenName,
                    precio: $scope.examenPrecio,
                    recomendacion: $scope.examenRecom

                }).then(function (response) {
                    $state.go('examenesList', {examenId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);