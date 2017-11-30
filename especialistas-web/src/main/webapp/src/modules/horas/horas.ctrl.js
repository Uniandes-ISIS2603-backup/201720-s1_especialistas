(function (ng) {
    var mod = ng.module("horaModule");
    mod.constant("medicosContext", "api/medicos");
    mod.controller('horaCtrl', ['$scope', '$state', '$http', 'medicosContext',
        function ($scope, $state, $http, medicosContext) {
            if($state.params.medicoId !== undefined){
                $http.get(medicosContext + '/' + $state.params.medicoId + '/agenda').then(function (response) {
                    $scope.agenda = response.data;
                    dia = new Date();
                    dia = new Date($scope.agenda[0].horaInicio);
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
                    $scope.darIdHora = function (hora, dia) {
                        for(i in $scope.agenda){
                            if ($scope.agenda.hasOwnProperty(i)) {
                                temp = new Date($scope.agenda[i].horaInicio);
                                if(temp.getHours() === hora.getHours() && temp.getMinutes() === hora.getMinutes() && temp.getDay() === dia){
                                    return $scope.agenda[i].id;
                                }
                            }
                        }
                        return -1;
                    }
                    $scope.hayCita = function (hora, dia) {
                        return $scope.darIdHora(hora, dia) !== -1
                    }
                    
                    
                });
            }
        }
    ]);
}
)(angular);