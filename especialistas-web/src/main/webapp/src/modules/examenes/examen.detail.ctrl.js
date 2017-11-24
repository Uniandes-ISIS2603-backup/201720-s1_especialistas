(function (ng) {
    var mod = ng.module("examenesModule");
    mod.constant("examContext", "api/examenes");
    mod.controller('examenDetailCtrl', ['$scope', '$http', '$state','examContext',
        function ($scope, $http, $state, examContext) {
            $http.get(examContext + '/' + $state.params.examenId).then(function (response) {
                    $scope.examenActual = response.data;
            });
        }
    ]);
}
)(angular);