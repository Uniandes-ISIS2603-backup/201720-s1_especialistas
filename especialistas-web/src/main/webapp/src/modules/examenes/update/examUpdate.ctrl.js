(function (ng) {
    var mod = ng.module("examenesModule");
    mod.constant("examenesContext", "api/examenes");
    mod.controller('examenUpdateCtrl', ['$scope', '$http', 'examenesContext', '$state', '$rootScope',
        function ($scope, $http, examenesContext, $state, $rootScope) {
            $rootScope.edit = true;
            
            $http.get(examenesContext + '/' + $state.params.examenId).then(function(response){
                var exam = response.data;
                $scope.nombreExam = exam.nombre;
                $scope.precioExam = exam.precio;
                $scope.recomendacionExam = exam.recomendacion;
            });
            
            $scope.updateExam = function(){
                $http.put(examenesContext + '/' + $state.params.examenId, {
                    nombre: $scope.nombreExam,
                    precio: $scope.precioExam,
                    recomendacion: $scope.recomendacionExam
                }).then(function(response) {
                    $state.go('examenesList', {id: response.data.id}, {reload:true});
                });
            };           
        }
    ]);
}
)(window.angular);