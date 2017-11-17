(function (ng) {
    var mod = ng.module("consultoriosModule");
    mod.constant("consultoriosContext", "api/consultorios");
    mod.controller('consultorioCtrl', ['$scope', '$http', 'consultoriosContext', '$state',
        function ($scope, $http, consultoriosContext, $state) {
            $http.get(consultoriosContext).then(function (response) {
                $scope.consultoriosRecords = response.data;
            });

            if ($state.params.consultorioId !== undefined) {
                $http.get(consultoriosContext + '/' + $state.params.consultorioId).then(function (response) {
                    $scope.currentConsultorio = response.data;
                });
            }
        }
    ]);
}
)(angular);