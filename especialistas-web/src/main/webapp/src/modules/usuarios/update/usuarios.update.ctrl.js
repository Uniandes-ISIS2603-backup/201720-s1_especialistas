(
        function (ng) {
            var mod = ng.module("usuarioModule");
            mod.constant("usuariosContext", "api/usuarios");
            mod.constant("booksContext", "api/books");
            mod.controller('usuarioUpdateCtrl', ['$scope', '$http', 'usuariosContext', '$state', 'booksContext', '$rootScope', '$filter',
                function ($scope, $http, usuariosContext, $state, booksContext, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idUsuario = $state.params.usuarioId;

                    // Este arreglo guardara los ids de los books asociados y por asociar al autor.
                    var idsBook = [];

                    // Este arreglo mostrará los books una vez esten filtrados visualmente por lo que el autor ya tiene asociado.
                    $scope.allBooksShow = [];

                    //Consulto el autor a editar.
                    $http.get(usuariosContext + '/' + idUsuario).then(function (response) {
                        var usuario = response.data;
                        $scope.usuarioName = usuario.nombre;
                        $scope.usuarioBirthDate = new Date(usuario.birthDate);
                        $scope.usuarioDescription = usuario.description;
                        $scope.usuarioImage = usuario.image;
                        $scope.allBooksUsuario = usuario.books;
                        $scope.mergeBooks($scope.allBooksUsuario);
                    });

                    /*
                     * Esta función añade los ids de los books que ya tiene el autor asociado.
                     * @param {type} books: Son los books que ya tiene asociado el autor.
                     * @returns {undefined}
                     */
                    $scope.mergeBooks = function (books) {
                        for (var item in books) {
                            idsBook.push("" + books[item].id);
                        }
                        $scope.getBooks(books);
                    };

                    /*
                     * Esta función recibe como param los books que tiene el autor para hacer un filtro visual con todos los books que existen.
                     * @param {type} books
                     * @returns {undefined}
                     */
                    $scope.getBooks = function (books) {
                        $http.get(booksContext).then(function (response) {
                            $scope.Allbooks = response.data;
                            $scope.booksUsuario = books;

                            var filteredBooks = $scope.Allbooks.filter(function (Allbooks) {
                                return $scope.booksUsuario.filter(function (booksUsuario) {
                                    return booksUsuario.id == Allbooks.id;
                                }).length == 0
                            });

                            $scope.allBooksShow = filteredBooks;

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
                        //Cuando un book se añade al autor, se almacena su id en el array idsBook
                        idsBook.push("" + data);
                    };

                    $scope.dropDelete = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                        //Para remover el book que no se va asociar, por eso se usa el splice que quita el id del book en el array idsBook
                        var index = idsBook.indexOf(data);
                        if (index > -1) {
                            idsBook.splice(index, 1);
                        }
                    };

                    $scope.createUsuario = function () {
                        /*Se llama a la función newBooks() para buscar cada uno de los ids de los books
                         en el array que tiene todos los books y así saber como queda la lista final de los books asociados al autor.
                         */
                        $scope.newBooks();
                        $http.put(usuariosContext + "/" + idUsuario, {
                            nombre: $scope.usuarioName,
                            birthDate: $scope.usuarioBirthDate,
                            description: $scope.usuarioDescription,
                            image: $scope.usuarioImage
                        }).then(function (response) {
                            if (idsBook.length >= 0) {
                                $http.put(usuariosContext + "/" + response.data.id + "/books", $scope.allBooksUsuario).then(function (response) {
                                });
                            }
                            //Usuario created successfully
                            $state.go('usuariosList', {usuarioId: response.data.id}, {reload: true});
                        });
                    };

                    $scope.newBooks = function () {
                        $scope.allBooksUsuario = [];
                        for (var ite in idsBook) {
                            for (var all in $scope.Allbooks) {
                                if ($scope.Allbooks[all].id === parseInt(idsBook[ite])) {
                                    $scope.allBooksUsuario.push($scope.Allbooks[all]);
                                }
                            }
                        }
                    };
                }
            ]);
        }
)(window.angular);