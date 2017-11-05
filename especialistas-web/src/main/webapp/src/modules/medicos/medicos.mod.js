(function (ng) {
    var mod = ng.module("medicoModule", ['ui.router']);

    mod.config(['$stateProvider', function ($stateProvider) {
            var basePath = 'src/modules/medicos/';
            var basePathHora = 'src/modules/horas/';
            $stateProvider.state('medicos', {
                url: '/medicos',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'medicos.html',
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('medicosList', {
                url: '/todos',
                parent: 'medicos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'medicos.list.html',
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('medicoDetail', {
                url: '/{medicoId:int}/informacion',
                parent: 'medicos',                
                params: {
                    medicoId : null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'medicos.detail.html',
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl'
                    },
                    'listView': {
                        templateUrl: basePathHora + 'horas.list.html',
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('medicosCreate', {
                url: '/nuevo',
                parent: 'medicos',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'create/medicos.create.html',
                        controller: 'medicoCreateCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('medicoUpdate', {
                url: '/{medicoId:int}/cambiar_datos',
                parent: 'medicos',                
                params: {
                    medicoId : null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'create/medicos.create.html',
                        controller: 'medicoUpdateCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('medicoDelete', {
                url: '/{medicoId:int}/borrar',
                parent: 'medicos',                
                params: {
                    medicoId : null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'delete/medicos.delete.html',
                        controller: 'medicoDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);



