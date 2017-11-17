(function (ng) {
    var mod = ng.module("consultoriosModule", ['ui.router']);
    mod.constant("consultoriosContext", "api/consultorios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/consultorios/';
            $urlRouterProvider.otherwise("/consultoriosList");
            $stateProvider.state('consultorios', {
                url: '/consultorios',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'consultorios.html',
                        controller: 'consultorioCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            }).state('consultoriosList', {
                url: '/consultorios/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'consultorios.list.html',
                        controller: 'consultorioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('consultoriosCreate', {
                url: '/consultorios/create',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'create/consultorios.create.html',
                        controller: 'consultoriosCreateController',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('consultorioDelete', {
                url: '/consultorios/delete/{consultorioId:int}',
                param: {
                    consultorioId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + '/delete/consultorios.delete.html',
                        controller: 'consultorioDeleteController',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('consultorioDetail', {
                url: '/{consultorioId:int}/detail',
                parent: 'consultorios',
                param: {
                    consultorioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'consultorios.detail.html',
                        controller: 'consultorioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('consultorioUpdate', {
                url: '/{consultorioId:int}/udpate',
                parent: 'consultorios',                
                params: {
                    medicoId : null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'create/consultorios.create.html',
                        controller: 'consultorioUpdateCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);



