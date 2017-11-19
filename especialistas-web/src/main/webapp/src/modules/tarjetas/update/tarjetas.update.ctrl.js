(
        function (ng) {
            var mod = ng.module("tarjetaModule");
            mod.constant("tarjetasContext", "api/tarjetas");
            mod.constant("citasContext", "api/citas");
            mod.controller('tarjetaUpdateCtrl', ['$scope', '$http', 'tarjetasContext', '$state', 'citasContext', '$rootScope',
                function ($scope, $http, tarjetasContext, $state, citasContext, $rootScope) {
                    $rootScope.edit = true;

                    var idTarjeta = $state.params.tarjetaId;

                    

                    //Consulto el autor a editar.
                    $http.get(tarjetasContext + '/' + idTarjeta).then(function (response) {
                        var tarjeta = response.data;
                        $scope.tarjetaNumero = tarjeta.numero;
                        $scope.tarjetaUsuario = tarjeta.usuario;
                    });


                    $scope.createTarjeta = function () {
                        /*Se llama a la función newCitas() para buscar cada uno de los ids de los citas
                         en el array que tiene todos los citas y así saber como queda la lista final de los citas asociados al autor.
                         */
                        $http.put(tarjetasContext + "/" + idTarjeta, {
                            numero: $scope.tarjetaNumero,
                            usuario: $scope.tarjetaUsuario
                        }).then(function (response) {
                            //Tarjeta created successfully
                            $state.go('tarjetasList', {tarjetaId: response.data.id}, {reload: true});
                        });
                    };
                }
            ]);
        }
)(window.angular);