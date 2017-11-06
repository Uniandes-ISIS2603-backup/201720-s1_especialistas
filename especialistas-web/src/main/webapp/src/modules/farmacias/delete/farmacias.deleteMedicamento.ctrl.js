(function (ng) {
    var mod = ng.module("farmaciaModule");
    mod.controller('farmaciaDeleteMedicamentoCtrl', ['$scope', '$http', 'farmaciasContext','$state','$stateParams',
        function ($scope, $http,farmaciasContext,$state,$stateParams) {
            $http.get(farmaciasContext+"/"+$stateParams.id+"/medicamentos").then(function (response) {
                $scope.currentMedicamentos = response.data;
            });
            $scope.deleteMedicamento = function (idMedicamento){
                $http.delete(farmaciasContext+"/"+$stateParams.id+"/medicamentos/"+idMedicamento).then(function (response){
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