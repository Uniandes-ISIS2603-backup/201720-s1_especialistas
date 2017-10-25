(function (ng) {
    var mod = ng.module("consultoriosModule");
    mod.constant("consultoriosContext", "api/consultorios");
    mod.controller('consultorioCtrl', ['$scope', '$http', 'consultorioContext',
        function ($scope, $http, consultoriosContext) {
            $http.get(consultoriosContext).then(function (response) {
                $scope.consultoriosRecords = response.data;
            });
        }
    ]);
}
)(angular);