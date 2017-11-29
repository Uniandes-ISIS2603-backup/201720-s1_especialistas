(function (ng) {
    var mod = ng.module("usuarioModule");
    mod.constant("usuarioContext", "api/usuarios");
    mod.controller('usuariosLogin', ['$scope', '$http', 'usuarioContext', '$state', '$rootScope',
        function ($scope, $http, usuarioContext, $state, $rootScope){
            $rootScope.edit = false;
            $scope.loginUsuario = function (){
            $scope.idd=$state.params.usuarioId;    
            if($scope.idd!==undefined)
            {
                $http.get(usuarioContext+'/'+$scope.idd).then(function (response){
                    if(response.data.rol=='ADMINISTRADOR')
                    {
                        $rootScope.tipoUser=3;                    
                    }
                    else{
                        $rootScope.tipoUser=1;
                    }
                    console.log($rootScope.tipoUser);
                },function(response){
                   $state.go('logOut');
                });
                    
            }
            };
            $scope.loginUsuario();
        }
    ]);
}
)(angular);