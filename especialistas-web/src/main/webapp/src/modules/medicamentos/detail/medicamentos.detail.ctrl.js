(function (ng) {

    var mod = ng.module("medicamentoModule");

    mod.controller("medicamentosDetailCtrl", ['$scope', '$state', '$stateParams', '$http', 'medicamentoContext', function ($scope, $state, $stateParams, $http, context) {

            // carga las ciudades
            $http.get(context+"/"+$stateParams.id).then(function (response) {
                $scope.currentRecord = response.data;
            });
            $http.get(context+"/"+$stateParams.id+"/farmacias").then(function (response) {
                $scope.currentFarmacias = response.data;
            });
        }]);
})(window.angular);

