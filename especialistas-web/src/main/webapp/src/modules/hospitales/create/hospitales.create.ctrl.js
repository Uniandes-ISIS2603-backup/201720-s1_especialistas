(function (ng) {
    var mod = ng.module("hospitalesModule");
    mod.constant("hospitalesContext", "api/hospitales");
    mod.controller('hospitalesCreateController', ['$scope', '$http', 'hospitalesContext', '$state', '$rootScope',
        function ($scope, $http, hospitalesContext, $state, $rootScope){
            $rootScope.edit = false;
            $scope.createHospital = function (){
                $http.post(hospitalesContext , {
                    id: $scope.hospitalId,
                    nombre: $scope.hospitalName
                }).then(function (response){
                    //usuario creado correctamente
                    $state.go('hospitalesList',{
                        hospitalId: response.data.id
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