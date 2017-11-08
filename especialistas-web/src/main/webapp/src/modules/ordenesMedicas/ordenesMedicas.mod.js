(function (ng) {
    var mod = ng.module("ordenesMedicasModule", ['ui.router']);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/ordenesMedicas/';
            var basePathMedicamentos = 'src/modules/medicamentos/';
            var basePathExamenes = 'src/modules/examenes/';
            
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
            });
        }
        
        
    ]);
})(window.angular);