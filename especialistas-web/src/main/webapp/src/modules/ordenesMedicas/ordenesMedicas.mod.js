(function (ng) {
    var mod = ng.module("ordenesMedicasModule", ['ui.router']);

    mod.config(['$stateProvider', function ($stateProvider) {
            var basePath = 'src/modules/ordenesMedicas/';
            
            $stateProvider.state('ordenesMedicas', {
                url: '/ordenesMedicas',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'ordenesMedicas.html',
                        controller: 'ordenesMedicasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            })
            .state('ordenesMedicasList', {
                url: '/list',
                parent:'ordenesMedicas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'ordenesMedicas.list.html',
                        controller: 'ordenesMedicasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            })
             .state('ordenesMedicasDetail', {
                url: '/{ordenesMedicasId:int}/detail',
                parent: 'ordenesMedicas',
                param: {
                    ordenesMedicasId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ordenesMedicas.detail.html',
                        controller: 'ordenesMedicasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            })
            .state('ordenesMedicasDelete', {
                url: '/ordenesMedicas/{ordenesMedicasId:int}/borrar',
                params: {
                    ordenId : null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'delete/ordenesMedicas.delete.html',
                        controller: 'ordenesMedicasDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('ordenesMedicasCreate', {
                url: '/ordenesMedicas/nuevo',
                
                views: {
                    'mainView': {
                        templateUrl: basePath + 'create/ordenesMedicas.create.html',
                        controller: 'ordenesMedicasCreateCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
        
        
    ]);
})(window.angular);