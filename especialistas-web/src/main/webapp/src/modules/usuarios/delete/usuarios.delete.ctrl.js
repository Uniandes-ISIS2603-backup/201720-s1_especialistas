(function (ng) {
    var mod = ng.module("usuarioModule");
    mod.constant("usuarioContext", "api/usuarios");
    mod.controller('usuariosDeleteController', ['$scope', '$http', 'usuarioContext', '$state',
        function ($scope, $http, usuarioContext, $state) {
            var idUsuario = $state.params.usuarioId;
            $scope.deleteAuthor = function () {
                $http.delete(usuarioContext + '/' + idUsuario, {}).then(function (response) {
                    $state.go('usuariosList', {authorId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);