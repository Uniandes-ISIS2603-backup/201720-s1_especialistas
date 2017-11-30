(function (ng) {
    var mod = ng.module("citasModule");
    mod.constant("citasContext", "api/citas");
    mod.controller('citasCreateCtrl', ['$scope', '$http', 'citasContext', '$state', '$rootScope',
        function ($scope, $http, citasContext, $state, $rootScope) {
            $rootScope.edit = false;
            $http.get("api/especializaciones").then(function (response) {
                $scope.especializaciones = response.data;
            });


            $http.get("api/horas").then(function (response) {
                $scope.horas = response.data;
            });



            $scope.createCita = function () {
                if (($state.params.usuarioId !== undefined) && ($state.params.usuarioId !== null)) {
                    $http.post(citasContext, {
                        comentarios: $scope.comentarios,
                        usuario: {id : $state.params.usuarioId},
                        ordenesMedicas: [],
                        hora: {'id': $scope.$eval($scope.horaNueva).id}
                    }).then(function (response) {
                        $state.go('usuarioDetail', {usuarioId:$rootScope.idUser}, {reload: true});
                    });
                }
            };
        }
    ]);
}
)(angular);
