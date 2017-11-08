(
        function (ng) {
            var mod = ng.module("usuarioModule");
            mod.constant("usuariosContext", "api/usuarios");
            mod.constant("citasContext", "api/citas");
            mod.controller('usuarioUpdateCtrl', ['$scope', '$http', 'usuariosContext', '$state', 'citasContext', '$rootScope', '$filter',
                function ($scope, $http, usuariosContext, $state, citasContext, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idUsuario = $state.params.usuarioId;

                    // Este arreglo guardara los ids de las citas asociadas a un usuario
                    var idsCita = [];

                    // Este arreglo mostrará los citas una vez esten filtrados visualmente por lo que el autor ya tiene asociado.
                    $scope.allCitasShow = [];

                    //Consulto el autor a editar.
                    $http.get(usuariosContext + '/' + idUsuario).then(function (response) {
                        var usuario = response.data;
                        $scope.usuarioName = usuario.nombre;
                        $scope.usuarioCedula = usuario.cedula;
                        $scope.allCitasUsuario = usuario.citas;
                        $scope.mergeCitas($scope.allCitasUsuario);
                    });

                    /*
                     * Esta función añade los ids de los citas que ya tiene el autor asociado.
                     * @param {type} citas: Son los citas que ya tiene asociado el autor.
                     * @returns {undefined}
                     */
                    $scope.mergeCitas = function (citas) {
                        for (var item in citas) {
                            idsCita.push("" + citas[item].id);
                        }
                        $scope.getCitas(citas);
                    };

                    /*
                     * Esta función recibe como param los citas que tiene el autor para hacer un filtro visual con todos los citas que existen.
                     * @param {type} citas
                     * @returns {undefined}
                     */
                    $scope.getCitas = function (citas) {
                        $http.get(citasContext).then(function (response) {
                            $scope.Allcitas = response.data;
                            $scope.citasUsuario = citas;

                            var filteredCitas = $scope.Allcitas.filter(function (Allcitas) {
                                return $scope.citasUsuario.filter(function (citasUsuario) {
                                    return citasUsuario.id == Allcitas.id;
                                }).length == 0
                            });

                            $scope.allCitasShow = filteredCitas;

                        });
                    };


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
                        //Cuando un cita se añade al autor, se almacena su id en el array idsCita
                        idsCita.push("" + data);
                    };

                    $scope.dropDelete = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                        //Para remover el cita que no se va asociar, por eso se usa el splice que quita el id del cita en el array idsCita
                        var index = idsCita.indexOf(data);
                        if (index > -1) {
                            idsCita.splice(index, 1);
                        }
                    };

                    $scope.createUsuario = function () {
                        /*Se llama a la función newCitas() para buscar cada uno de los ids de los citas
                         en el array que tiene todos los citas y así saber como queda la lista final de los citas asociados al autor.
                         */
                        $scope.newCitas();
                        $http.put(usuariosContext + "/" + idUsuario, {
                            nombre: $scope.usuarioName,
                            cedula: $scope.usuarioCedula
                        }).then(function (response) {
                            if (idsCita.length >= 0) {
                                $http.put(usuariosContext + "/" + response.data.id + "/citas", $scope.allCitasUsuario).then(function (response) {
                                });
                            }
                            //Usuario created successfully
                            $state.go('usuariosList', {usuarioId: response.data.id}, {reload: true});
                        });
                    };

                    $scope.newCitas = function () {
                        $scope.allCitasUsuario = [];
                        for (var ite in idsCita) {
                            for (var all in $scope.Allcitas) {
                                if ($scope.Allcitas[all].id === parseInt(idsCita[ite])) {
                                    $scope.allCitasUsuario.push($scope.Allcitas[all]);
                                }
                            }
                        }
                    };
                }
            ]);
        }
)(window.angular);