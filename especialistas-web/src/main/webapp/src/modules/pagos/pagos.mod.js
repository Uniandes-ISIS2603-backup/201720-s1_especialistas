(function (ng) {
    var mod = ng.module("pagoModule", ['ui.router']);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/pagos/';
            $stateProvider.state('pagosList', {
                url: '/pagos/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'pagos.list.html',
                        controller: 'pagoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('pagosCrear', {
                url: '/pagos/nuevo/{usuarioId:int}',
                param:{
                  usuarioId: null  
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'new/pagos.new.html',
                        controller: 'pagosNewController',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('pagosDelete', {
                url: '/pagos/delete/{pagoId:int}',
                param: {
                    pagoId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'delete/pagos.delete.html',
                        controller: 'pagosDeleteController',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);



