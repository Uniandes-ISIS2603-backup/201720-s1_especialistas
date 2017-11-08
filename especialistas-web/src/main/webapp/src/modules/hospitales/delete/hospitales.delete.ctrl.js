(function (ng) {
    var mod = ng.module("hospitalesModule");
    mod.constant("hospitalesContext", "api/hos");
    mod.controller('hospitalDeleteCtrl', ['$scope', '$http', '$state', 'hospitalesContext',
        function ($scope, $http, $state, hospitalesContext) {
            $scope.deleteHospital = function () {
                print($state.params.hospitalId);
                $http.delete(hospitalesContext + '/' + $state.params.hospitalId, {}).then(function (response) {
                    $state.go('hospitalesList', {id: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);