(function (ng) {
    var mod = ng.module("hospitalesModule");
    mod.constant("hospitalesContext", "api/hospitales");
    mod.controller('hospitalCtrl', ['$scope', '$http', 'hospitales  Context',
        function ($scope, $http, hospitalesContext) {
            $http.get(hospitalesContext).then(function (response) {
                $scope.hospitalesRecords = response.data;
            });
        }
    ]);
}
)(angular);