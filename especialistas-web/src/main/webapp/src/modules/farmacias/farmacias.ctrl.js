(function (ng) {
    var mod = ng.module("farmaciaModule");
    mod.constant("farmaciasContext", "api/farmacias");
    mod.controller('farmaciasCtrl', ['$scope', '$http', 'farmaciasContext',
        function ($scope, $http,farmaciasContext) {
            $http.get(farmaciasContext).then(function (response) {
                $scope.farmacia = response.data;
            });
        }
    ]);
}
)(angular);