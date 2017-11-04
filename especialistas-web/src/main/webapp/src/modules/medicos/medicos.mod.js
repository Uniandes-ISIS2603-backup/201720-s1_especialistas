(function (ng) {
    var mod = ng.module("medicoModule", ['ui.router']);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
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
                url: '/list',
                parent: 'medicos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'medicos.list.html',
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('medicoDetail', {
                url: '/{medicoId:int}/detail',
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
                url: '/create',
                parent: 'medicos',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'create/medicos.create.html',
                        controller: 'medicoCreateCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);



