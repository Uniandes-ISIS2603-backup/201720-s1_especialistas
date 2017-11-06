(function (ng) {
    var mod = ng.module("farmaciaModule");
    mod.controller('farmaciaAddMedicamentoCtrl', ['$scope', '$http', 'farmaciasContext','$state','$stateParams',
        function ($scope, $http,farmaciasContext,$state,$stateParams) {
            $http.get("api/medicamentos").then(function (response) {
                $scope.medicamentos = response.data;
            });
            $scope.addMedicamento = function (idMedicamento){
                $http.put(farmaciasContext+"/"+$stateParams.id+"/medicamentos/"+idMedicamento).then(function (response){
                    //usuario creado correctamente
                    $state.go('farmaciasList',{
                        farmaciaId: response.data.id
                    },
                    {
                        reload: true
                    }
                    );
                });
            };
        }
    ]);
}
)(angular);