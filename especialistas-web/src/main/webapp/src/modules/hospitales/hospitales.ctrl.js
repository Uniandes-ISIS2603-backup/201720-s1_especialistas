(function (ng) {
    var mod = ng.module("hospitalesModule");
    mod.constant("hospitalesContext", "api/hospitales");
    mod.controller('hospitalCtrl', ['$scope', '$http', 'hospitalesContext', '$state',
        function ($scope, $http, hospitalesContext, $state) {
            $http.get(hospitalesContext).then(function (response) {
                $scope.hospitalesRecords = response.data;
            });

            if ($state.params.hospitalId !== undefined) {
                $http.get(hospitalesContext + '/' + $state.params.hospitalId).then(function (response) {
                    $scope.currentHospital = response.data;
                    $scope.consultorios = response.data.consultorios;
                });
            }
        }
    ]);
}
)(angular);