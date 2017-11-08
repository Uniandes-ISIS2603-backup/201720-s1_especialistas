(function (ng) {
    var mod = ng.module("laboratoriosModule");
    mod.constant("laboratoriosContext", "api/laboratorios");
    mod.controller('labUpdateCtrl', ['$scope', '$http', 'laboratoriosContext', '$state', '$rootScope',
        function ($scope, $http, laboratoriosContext, $state, $rootScope) {
            $rootScope.edit = true;
            
            $http.get(laboratoriosContext + '/' + $state.params.laboratorioId).then(function(response){
                var lab = response.data;
                $scope.nombreLab = lab.nombre;
                $scope.nombreUbicacion = lab.ubicacion.nombre;
                $scope.latitud = lab.ubicacion.latitud;
                $scope.longitud = lab.ubicacion.longitud;
            });
            
            $scope.updateLab = function(){
                $http.put(laboratoriosContext + '/' + $state.params.laboratorioId, {
                    nombre: $scope.nombreLab,
                    ubicacion:{
                        nombre: $scope.nombreUbicacion,
                        latitud: $scope.latitud,
                        longitud: $scope.longitud
                    }
                }).then(function(response) {
                    $state.go('laboratoriosList', {id: response.data.id}, {reload:true});
                });
            };
           
        }
    ]);
}
)(window.angular);