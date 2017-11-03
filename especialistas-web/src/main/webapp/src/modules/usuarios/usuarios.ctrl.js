(function (ng) {
    var mod = ng.module("usuarioModule");
    mod.constant("usuariosContext", "api/usuarios");
    mod.controller('usuarioCtrl', ['$scope', '$http', 'usuariosContext',
        function ($scope, $http, usuariosContext) {
            $http.get(usuariosContext).then(function (response) {
                $scope.usuariosRecords = response.data;
            });
        }
    ]);
}
)(angular);