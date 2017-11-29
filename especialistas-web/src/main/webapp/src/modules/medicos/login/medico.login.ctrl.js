(function (ng) {
    var mod = ng.module("medicoModule");
    mod.constant("medicoContext", "api/medicos");
    mod.controller('medLogin', ['$scope', '$http', 'medicoContext', '$state', '$rootScope',
        function ($scope, $http, usuarioContext, $state, $rootScope){
            $rootScope.edit = false;
            $scope.loginMedico = function (){
            $scope.idd=$state.params.usuarioId;    
            if($scope.idd!==undefined)
            {
                $http.get(usuarioContext+'/'+$scope.idd).then(function (response){
                    $rootScope.tipoUser=2;
                    $rootScope.idUser=$scope.idd;
                                        $rootScope.primera=0;
                    console.log($rootScope.tipoUser);
                    console.log($rootScope.idUser);                    
                },function(response){
                   $state.go('logOut');
                });
                    
            }
            };
            $scope.loginMedico();
        }
    ]);
}
)(angular);