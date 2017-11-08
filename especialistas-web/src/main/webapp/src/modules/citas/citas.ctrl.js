(function (ng) {
    var mod = ng.module("citasModule");
    mod.constant("citasContext", "api/citas");
    mod.controller('citasCtrl', ['$scope', '$http', 'citasContext','$state',
        function ($scope, $http,citasContext,$state) {
            $http.get(citasContext).then(function (response) {
                $scope.citas = response.data;
            });
            
        if (($state.params.citasId !== undefined) && ($state.params.citasId !== null)) {
                $http.get(citasContext + '/' + $state.params.citasId).then(function (response) {
                    $scope.currentCita = response.data;
                    $scope.horas = response.data.hora;
                    $scope.ordenesMedicas = response.data.ordenesMedicas;
                });
            }
          
        }
    ]);
}
)(angular);