(function (ng) {
    var mod = ng.module("laboratoriosModule");
    mod.constant("laboratoriosContext", "api/laboratorios");
    mod.controller('examenAddCtrl', ['$scope', '$http', 'laboratoriosContext', '$state', '$rootScope',
        function ($scope, $http, laboratoriosContext, $state, $rootScope) {
            $rootScope.edit = true;
            
            $http.get(laboratoriosContext + '/' + $state.params.laboratorioId).then(function(response){
                var lab = response.data;
            });

            $scope.addExam = function () {
                $http.put(laboratoriosContext + '/' + $state.params.laboratorioId + '/addExamen', {
                    nombre: $scope.nombreExam,
                    precio: $scope.precio,
                    recomendacion: $scope.recomendacion
                }).then(function (response) {
                    $state.go('laboratoriosList', {id: response.data.id}, {reload: true});
                });
            };

        }
    ]);
}
)(window.angular);