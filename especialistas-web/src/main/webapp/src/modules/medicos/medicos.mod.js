(function (ng) {
    var mod = ng.module("medicoModule", ['ui.router']);

    mod.config(['$stateProvider', function ($stateProvider) {
            var basePath = 'src/modules/medicos/';
            $stateProvider.state('medicosList', {
                url: 'medicos',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'medicos.list.html',
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('medicoDetail', {
                abstract : true,
                url: 'medicos/{medicoId:int}/informacion',
                params: {
                    medicoId : null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'medicos.detail.html',
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('medicosCreate', {
                url: 'medicos/nuevo',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'create/medicos.create.html',
                        controller: 'medicoCreateCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('medicoUpdate', {
                url: 'medicos/{medicoId:int}/cambiar_datos',
                params: {
                    medicoId : null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'create/medicos.create.html',
                        controller: 'medicoUpdateCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('medicoDelete', {
                url: 'medicos/{medicoId:int}/borrar',
                params: {
                    medicoId : null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'delete/medicos.delete.html',
                        controller: 'medicoDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);



