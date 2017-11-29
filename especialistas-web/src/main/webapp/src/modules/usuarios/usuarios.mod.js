(function (ng) {
    var mod = ng.module("usuarioModule", ['ui.router']);

    mod.config(['$stateProvider','$urlRouterProvider', function ($stateProvider,$urlRouterProvider) {
            var basePath = 'src/modules/usuarios/';
            $urlRouterProvider.otherwise("logOut");
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
                    usuarioId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + '/delete/usuarios.delete.html',
                        controller: 'usuariosDeleteController',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('usuarioDetail', {
                url: '/usuarios/{usuarioId:int}/detail',
                param: {
                    usuarioId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'usuarios.detail.html',
                        controller: 'usuarioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('usuarioUpdate', {
                url: '/usuarios/update/{usuarioId:int}',
                param: {
                    usuarioId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'new/usuarios.new.html',
                        controller: 'usuarioUpdateCtrl'
                    }
                }
            }).state('logInUser', {
                url:'home',
               params: {
                    usuarioId: null
                },
                 views: {
                    'all':{
                        templateUrl: basePath + 'login/index.html',
                        controller: 'usuariosLogin',
                        controllerAs: 'ctrl'}
                }
            }).state('logOut', {
                url: 'logOut',
                views: {
                    'all':{
                        templateUrl: basePath + 'login/usuarios.login.html',
                        controller: 'usuariosLogin',
                        controllerAs: 'ctrl'}
                }
            });
        }
    ]);
})(window.angular);