(function (ng) {
    var mod = ng.module("farmaciaModule");
    mod.constant("farmaciasContext", "api/farmacias");
    mod.controller('farmaciaCtrl', ['$scope', '$http', 'farmaciasContext',
        function ($scope, $http,farmaciasContext) {
            $http.get(farmaciasContext).then(function (response) {
                $scope.farmacias = response.data;
            });
        }
    ]);
}
)(angular);