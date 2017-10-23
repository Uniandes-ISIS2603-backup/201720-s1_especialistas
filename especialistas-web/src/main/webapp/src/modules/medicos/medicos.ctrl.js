(function (ng) {
    var mod = ng.module("medicoModule");
    mod.constant("medicosContext", "api/medicos");
    mod.controller('medicoCtrl', ['$scope', '$http', 'medicosContext',
        function ($scope, $http, medicosContext) {
            $http.get(medicosContext).then(function (response) {
                $scope.medicosRecords = response.data;
            });
        }
    ]);
}
)(angular);