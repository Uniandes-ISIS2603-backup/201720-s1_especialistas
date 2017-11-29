(function (ng) {
    var mod = ng.module("ordenesMedicasModule");
    mod.constant("ordenesMedicasContext", "api/ordenesMedicas");
    mod.controller('ordenesMedicasCreateCtrl', ['$scope', '$http', 'ordenesMedicasContext', '$state', '$rootScope',
        function ($scope, $http, ordenesMedicasContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.citaParaOrdenId = $rootScope.glovalCitaId
           $http.get("api/examenes").then(function (response) {
                $scope.examenes = response.data;
            });

               

            $scope.createOrdenMedica = function () {
                
                a=[];
                for(i=0;i<$scope.examenNuevo.length;i++)
                {
                    a.push($scope.$eval($scope.examenNuevo[i]))
                    
                }
                $scope.examenesDeOrdenes=a;
                
            $http.post(ordenesMedicasContext , {
                descripcion: $scope.descripcion,
                cita : $rootScope.glovalCita,
                examenes:a
                
                
                
            }).then(function (response) {
                $state.go('citasDetail', {citasId: $rootScope.glovalCitaId}, {reload: true});
            });
            };
        }
    ]);
}
)(angular);
