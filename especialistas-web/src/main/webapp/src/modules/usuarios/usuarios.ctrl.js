(function (ng) {
    var mod = ng.module("usuarioModule");
    mod.constant("usuariosContext", "api/usuarios");
    mod.controller('usuarioCtrl', ['$scope', '$http', 'usuariosContext', '$state',
        function ($scope, $http, usuariosContext, $state) {
            $http.get(usuariosContext).then(function (response) {
                $scope.usuariosRecords = response.data;
            });
            
            if (($state.params.usuarioId !== undefined) && ($state.params.usuarioId !== null)) {
                $http.get(usuariosContext + '/' + $state.params.usuarioId).then(function (response) {
                    $scope.tarjeta = response.data.tarjeta;
                    $scope.citas = response.data.citas;
                    $scope.currentUsuario = response.data;
                });
            }
        }
    ]);
}
)(angular);