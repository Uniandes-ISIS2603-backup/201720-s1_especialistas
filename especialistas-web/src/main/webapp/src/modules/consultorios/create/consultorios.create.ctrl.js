(function (ng) {
    var mod = ng.module("consultoriosModule");
    mod.constant("consultoriosContext", "api/cons");
    mod.controller('consultoriosCreateController', ['$scope', '$http', 'consultoriosContext', '$state', '$rootScope',
        function ($scope, $http, consultoriosContext, $state, $rootScope){
            $rootScope.edit = false;
            $scope.createConsultorio = function (){
                $http.post(consultoriosContext , {
                    numero: $scope.consultorioNumber
                }).then(function (response){
                    //usuario creado correctamente
                    $state.go('consultoriosList',{
                        consultorioId: response.data.id
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