(function (ng) {
    var mod = ng.module("citasModule");
    mod.constant("citasContext", "api/citas");
    mod.controller('citasCreateCtrl', ['$scope', '$http', 'citasContext', '$state', '$rootScope',
        function ($scope, $http, citasContext, $state, $rootScope) {
            $rootScope.edit = false;
            
            $http.get("api/horas").then(function (response) {
                $scope.horas = response.data;
            });



            $scope.createCita = function () {
            $http.post(citasContext , {
                comentarios: $scope.comentarios,
                usuario: null,
                ordenesMedicas: [],
                hora:$scope.horaNueva
                
            }).then(function (response) {
                $state.go('citasList', {citaId: response.data.id}, {reload: true});
            });
            };
        }
    ]);
}
)(angular);
