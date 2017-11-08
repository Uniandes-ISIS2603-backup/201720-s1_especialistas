(function (ng) {
    var mod = ng.module("ordenesMedicasModule");
    mod.constant("ordenesContext", "api/ordenesMedicas");
    mod.controller('ordenesMedicasCtrl', ['$scope', '$http', 'ordenesContext','$state',
        function ($scope, $http,ordenesContext,$state) {
            $http.get(ordenesContext).then(function (response) {
                $scope.ordenesMedicas = response.data;
            });
            
        if (($state.params.ordenesMedicasId !== undefined) && ($state.params.ordenesMedicasId !== null)) {
                $http.get(ordenesContext + '/' + $state.params.ordenesMedicasId).then(function (response) {
                    
                    $scope.currentOrden = response.data;
                });
            }
          
        }
    ]);
}
)(angular);

