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
            });
        }
    ]);
})(window.angular);