(
        function (ng) {
            var mod = ng.module("hospitalesModule");
            mod.constant("hospitalesContext", "api/hos");
            mod.controller('hospitalUpdateCtrl', ['$scope', '$http', 'hospitalesContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, hospitalesContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idHospital = $state.params.hospitalId;
                    var idUbicacion;

                    //Consulto el autor a editar.
                    $http.get(hospitalesContext + '/' + idHospital).then(function (response) {
                        var hospital = response.data;
                        $scope.hospitalName = hospital.nombre;
                        idUbicacion = hospital.ubicacion.id;
                    });


                    //funciones para el drag and drop de HTML5 nativo
                    $scope.allowDrop = function (ev) {
                        ev.preventDefault();
                    };

                    $scope.drag = function (ev) {
                        ev.dataTransfer.setData("text", ev.target.id);
                    };

                    $scope.dropAdd = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                    };

                    $scope.dropDelete = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                    };

                    $scope.createHospital = function () {
                        /*Se llama a la función newCitas() para buscar cada uno de los ids de los citas
                         en el array que tiene todos los citas y así saber como queda la lista final de los citas asociados al autor.
                         */
                        $http.put(hospitalesContext + "/" ,{
                            id: idHospital,
                            nombre: $scope.hospitalName,
                            ubicacion:{
                                id: idUbicacion,
                                nombre: $scope.hospitalUbicacionName,
                                latitud: $scope.hospitalUbicacionLatitud,
                                longitud: $scope.hospitalUbicacionLongitud
                            }
                        }).then(function (response) {
                            $state.go('hospitalesList', {hospitalId: response.data.id}, {reload: true});
                        });
                    };

                }
            ]);
        }
)(window.angular);