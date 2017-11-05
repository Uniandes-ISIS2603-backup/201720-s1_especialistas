(function (ng) {
    var mod = ng.module("usuarioModule");
    mod.constant("usuarioContext", "api/usuarios");
    mod.controller('usuariosNewController', ['$scope', '$http', 'usuarioContext', '$state', '$rootScope',
        function ($scope, $http, usuarioContext, $state, $rootScope){
            $rootScope.edit = false;
            $scope.createUsuario = function (){
                $http.post(usuarioContext , {
                    nombre: $scope.usuarioName,
                    cedula: $scope.usuarioCedula
                }).then(function (response){
                    //usuario creado correctamente
                    $state.go('usuariosList',{
                        usuarioId: response.data.id
                    },
                    {
                        reload: true
                    }
                    );
                });
            };
        }
    ]);
}
)(angular);