(function (ng) {
    var mod = ng.module("examenesModule");
    mod.constant("examenesContext", "api/examenes");
    mod.controller('examUpdateCtrl', ['$scope', '$http', 'examenesContext', '$state', '$rootScope',
        function ($scope, $http, examenesContext, $state, $rootScope) {
            $rootScope.edit = true;
            
            $http.get(examenesContext + '/' + $state.params.examId).then(function(response){
                var exam = response.data;
                $scope.nombreExam = exam.nombre;
                $scope.precioExam = exam.precio;
                $scope.recomendacionExam = exam.recomendacion;
            });
            
            $scope.updateExam = function(){
                $http.put(examenesContext + '/' + $state.params.examId, {
                    nombre: $scope.nombreLab,
                    precio : $scope.precioExam,
                    recomendaciones: $scope.recomendaciones
                }).then(function(response) {
                    $state.go('laboratoriosList', {id: response.data.id}, {reload:true});
                });
            };
           
        }
    ]);
}
)(window.angular);