(function (ng) {
    var mod = ng.module("consultoriosModule");
    mod.constant("consultoriosContext", "api/hospitales");
    mod.controller('consultorioDeleteController', ['$scope', '$http', 'consultoriosContext', '$state',
        function ($scope, $http, consultoriosContext, $state) {
            var idConsultorio = $state.params.consultorioId;
            var idHospital = $state.params.hospitalId;
            $scope.idConsultorio = idConsultorio;
            $scope.idHospital = idHospital;
            $scope.deleteConsultorio = function () {
                if ($state.params.hospitalId !== null && $state.params.consultorioId !== null 
                        && $state.params.hospitalId !== undefined && $state.params.consultorioId !== undefined){
                    $http.delete(consultoriosContext + '/' + idHospital + '/consultorios/' + idConsultorio, {}).then(function () {
                        $state.go('hospitalesList', {}, {reload: true});
                    });
                }
            };
        }
    ]);
}
)(window.angular);