(
        function (ng) {
            var mod = ng.module("usuarioModule");
            mod.constant("usuariosContext", "api/usuarios");
            mod.constant("citasContext", "api/citas");
            mod.controller('usuarioUpdateCtrl', ['$scope', '$http', 'usuariosContext', '$state', 'citasContext', '$rootScope',
                function ($scope, $http, usuariosContext, $state, citasContext, $rootScope) {
                    $rootScope.edit = true;

                    var idUsuario = $state.params.usuarioId;

                    //Consulto el autor a editar.
                    $http.get(usuariosContext + '/' + idUsuario).then(function (response) {
                        var usuario = response.data;
                        $scope.usuarioName = usuario.nombre;
                        $scope.usuarioCedula = usuario.cedula;
                    });

                    


                    

                    $scope.createUsuario = function () {
                        /*Se llama a la función newCitas() para buscar cada uno de los ids de los citas
                         en el array que tiene todos los citas y así saber como queda la lista final de los citas asociados al autor.
                         */
                        
                        $http.put(usuariosContext + "/" + idUsuario, {
                            nombre: $scope.usuarioName,
                            cedula: $scope.usuarioCedula
                        }).then(function (response) {
                            //Usuario created successfully
                            $state.go('usuariosList', {usuarioId: response.data.id}, {reload: true});
                        });
                    };
                }
            ]);
        }
)(window.angular);