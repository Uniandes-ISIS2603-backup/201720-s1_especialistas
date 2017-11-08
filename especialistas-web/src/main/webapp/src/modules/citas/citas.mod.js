(function (ng) {
    var mod = ng.module("citasModule", ['ui.router']);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/citas/';
            var basePathHoras = 'src/modules/horas/';
            var basePathOrdenes = 'src/modules/ordenesMedicas/';
            
            
            $stateProvider.state('citas', {
                url: '/citas',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'citas.html',
                        controller: 'citasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            })
            .state('citasList', {
                url: '/list',
                parent:'citas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'citas.list.html',
                        controller: 'citasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            })
             .state('citasDetail', {
                url: '/{citasId:int}/detail',
                parent: 'citas',
                param: {
                    citasId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'citas.detail.html',
                        controller: 'citasCtrl',
                        controllerAs: 'ctrl'
                    },                    
                    'listView': {
                        templateUrl: basePathOrdenes + 'ordenesMedicas.list.html',
                        controller: 'citasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
        
        
    ]);
})(window.angular);