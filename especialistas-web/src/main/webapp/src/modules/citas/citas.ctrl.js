(function (ng) {
    var mod = ng.module("citasModule");
    mod.constant("citasContext", "api/citas");
    mod.controller('citasCtrl', ['$scope', '$http', 'citasContext','$state','$rootScope',
        function ($scope, $http,citasContext,$state,$rootScope) {
            $http.get(citasContext).then(function (response) {
                $scope.citas = response.data;
                
                dia = new Date($scope.citas[0].hora.horaInicio);
                    while(dia.getDay() !== 0)
                        dia.setTime(dia.getTime() - 24*3600*1000);
                    franjas = [];
                    for(i = 7; i <= 17; i++){
                        dia = new Date(dia.getTime());
                        dia.setHours(i);
                        dia.setMinutes(0);
                        franjas.push(dia);
                        dia = new Date(dia.getTime());                        
                        dia.setMinutes(20);
                        franjas.push(dia);
                        dia = new Date(dia.getTime());
                        dia.setMinutes(40);
                        franjas.push(dia);

                    }
                    $scope.franjas = franjas;
                    $scope.hayCita = function (hora, dia) {
                        for(i in $scope.citas){
                            if ($scope.citas.hasOwnProperty(i)) {
                                temp = new Date($scope.citas[i].hora.horaInicio);
                                if(temp.getHours() === hora.getHours() && temp.getMinutes() === hora.getMinutes() && temp.getDay() === dia){
                                    return true;
                                }
                            }
                        }
                        return false;
                    }
                
                
            });
            
        if (($state.params.citasId !== undefined) && ($state.params.citasId !== null)) {
                $http.get(citasContext + '/' + $state.params.citasId).then(function (response) {
                    $scope.currentCita = response.data;
                    $rootScope.glovalCita = response.data;
                    $rootScope.glovalCitaId = response.data.id;
        
                    $scope.horas = response.data.hora;
                    $scope.ordenesMedicas = response.data.ordenesMedicas;
                });
            }
          
        }
    ]);
}
)(angular);