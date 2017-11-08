(function (ng) {
    var mod = ng.module("hospitalesModule");
    mod.constant("hospitalesContext", "api/hos");
    mod.controller('hospitalDeleteController', ['$scope', '$http', 'hospitalesContext', '$state',
        function ($scope, $http, hospitalesContext, $state) {
            var idHospital = $state.params.hospitalId;
            $scope.deleteHospital = function () {
                $http.delete(hospitalesContext + '/' + idHospital, {}).then(function (response) {
                    $state.go('hospitalesList', {hospitalId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);