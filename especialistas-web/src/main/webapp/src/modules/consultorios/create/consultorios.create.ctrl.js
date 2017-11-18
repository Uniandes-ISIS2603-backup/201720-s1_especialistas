(function (ng) {
    var mod = ng.module("consultoriosModule");
    mod.constant("consultoriosContext", "api/hospitales");
    mod.controller('consultoriosCreateController', ['$scope', '$http', 'consultoriosContext', '$state', '$rootScope',
        function ($scope, $http, consultoriosContext, $state, $rootScope){
            $rootScope.edit = false;
            $scope.createConsultorio = function(){
                if($state.params.hospitalId !== undefined && $state.params.hospitalId !== null){
                    $http.post(consultoriosContext + '/' + $state.params.hospitalId + '/consultorios',{
                        numero: $scope.consultorioNumber
                    }).then(function (response){
                       $state.go('hospitalesList',{});
                    });
                }
            };
        }
    ]);
}
)(angular);