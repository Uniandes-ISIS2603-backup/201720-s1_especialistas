(function (ng) {
    var mod = ng.module("usuarioModule", ['ui.router']);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/usuarios/';
            $stateProvider.state('usuariosList', {
                url: '/usuarios/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'usuarios.list.html',
                        controller: 'usuarioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('usuariosCrear', {
                url: '/usuarios/nuevo',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'new/usuarios.new.html',
                        controller: 'usuariosNewController',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('usuariosDelete', {
                url: '/usuarios/delete/{usuarioId:int}',
                param: {
                    authorId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + '/delete/usuarios.delete.html',
                        controller: 'usuariosDeleteController',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('usuarioDetail', {
                url: '/{usuarioId:int}/detail',
                param: {
                    authorId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'usuarios.detail.html',
                        controller: 'usuarioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('usuarioUpdate', {
                url: '/update/{usuarioId:int}',
                param: {
                    usuarioId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'new/usuarios.new.html',
                        controller: 'usuarioUpdateCtrl'
                    }
                }
            });
        }
    ]);
})(window.angular);