(
        function (ng) {
            var mod = ng.module("consultoriosModule");
            mod.constant("consultoriosContext", "api/cons");
            mod.controller('consultorioUpdateCtrl', ['$scope', '$http', 'consultoriosContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, consultoriosContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idConsultorio = $state.params.consultorioId;
                    console.log(idConsultorio);
                    //Consulto el autor a editar.
                    $http.get(consultoriosContext + '/' + idConsultorio).then(function (response) {
                        var consultorio = response.data;
                        $scope.consultorioNumber = consultorio.numero;
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

                    $scope.createConsultorio = function () {
                        /*Se llama a la función newCitas() para buscar cada uno de los ids de los citas
                         en el array que tiene todos los citas y así saber como queda la lista final de los citas asociados al autor.
                         */
                        console.log(idConsultorio + " , " + $scope.consultorioNumber);
                        $http.put(consultoriosContext,{
                            id: idConsultorio,
                            numero: $scope.consultorioNumber
                        }).then(function (response) {
                            $state.go('consultoriosList', {consultorioId: response.data.id}, {reload: true});
                        });
                    };

                }
            ]);
        }
)(window.angular);