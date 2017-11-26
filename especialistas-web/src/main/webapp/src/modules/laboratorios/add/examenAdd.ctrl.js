(function (ng) {
    var mod = ng.module("laboratoriosModule");
    mod.constant("laboratoriosContext", "api/laboratorios");
    mod.controller('examenAddCtrl', ['$scope', '$http', 'laboratoriosContext', '$state', '$rootScope',
        function ($scope, $http, laboratoriosContext, $state, $rootScope) {
            $rootScope.edit = true;
                    $http.get("api/examenes").then(function(response){
                    $scope.examenesList = response.data;
            });

            $scope.addExam = function () {
                $http.post(laboratoriosContext + '/' + $state.params.laboratorioId + '/examenes', $scope.$eval($scope.examenX)).then(function (response) {
                    $state.go('laboratoriosList', {id: response.data.id}, {reload: true});
                });
            };

        }
    ]);
}
)(window.angular);