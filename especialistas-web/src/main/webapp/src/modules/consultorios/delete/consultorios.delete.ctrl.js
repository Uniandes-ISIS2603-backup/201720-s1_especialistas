(function (ng) {
    var mod = ng.module("consultoriosModule");
    mod.constant("consultoriosContext", "api/consultorios");
    mod.controller('consultorioDeleteController', ['$scope', '$http', 'consultoriosContext', '$state',
        function ($scope, $http, consultoriosContext, $state) {
            var idConsultorio = $state.params.consultorioId;
            $scope.deleteConsultorio = function () {
                $http.delete(consultoriosContext + '/' + idConsultorio, {}).then(function (response) {
                    $state.go('consultoriosList', {consultorioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);