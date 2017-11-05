(function (ng) {

    var mod = ng.module("farmaciaModule");

    mod.controller("farmaciasDetailCtrl", ['$scope', '$state', '$stateParams', '$http', 'farmaciasContext', function ($scope, $state, $stateParams, $http, context) {

            // carga las ciudades
            $http.get(context+"/"+$stateParams.id).then(function (response) {
                $scope.currentRecord = response.data;
            });
            $http.get(context+"/"+$stateParams.id+"/medicamentos").then(function (response) {
                $scope.currentMedicamentos = response.data;
            });
        }]);
})(window.angular);

