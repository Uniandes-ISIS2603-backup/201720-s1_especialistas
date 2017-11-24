
(function (ng) {
    var mod = ng.module("examenesModule");
    mod.constant("examenContext", "api/examenes");
    mod.controller('examenDeleteCtrl', ['$scope', '$http', '$state', 'examenContext',
        function ($scope, $http, $state, examenContext) {
            $scope.deleteExam = function(){
                $http.delete(examenContext + '/' + $state.params.examenId, {}).then(function (response) {
                    $state.go('laboratoriosList', {id: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);