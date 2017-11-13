(function (ng) {
    var mod = ng.module("citasModule", ['ui.router']);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/citas/';
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
            })
            .state('citasDelete', {
                url: '/citas/{citasId:int}/borrar',
                params: {
                    citasId : null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'delete/citas.delete.html',
                        controller: 'citasDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            })
            .state('citasUpdate', {
                url: '/citas/{citasId:int}/reagendar',
                params: {
                    citasId : null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'update/citas.update.html',
                        controller: 'citasUpdateCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            })
            .state('citasCreate', {
                url: '/citas/nuevo',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'create/citas.create.html',
                        controller: 'citasCreateCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            })
            
        }
        
        
    ]);
})(window.angular);