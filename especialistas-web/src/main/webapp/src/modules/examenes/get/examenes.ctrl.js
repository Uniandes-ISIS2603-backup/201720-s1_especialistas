(function (ng) {
    var mod = ng.module("examenesModule");
    mod.constant("examsContext", "api/examenes");
    mod.controller('examenCtrl', ['$scope', '$http','examsContext',
        function ($scope, $http, examsContext) {
            $http.get(examsContext).then(function (response) {
                $scope.examenesRecords = response.data;
            });
        }
    ]);
}
)(angular);