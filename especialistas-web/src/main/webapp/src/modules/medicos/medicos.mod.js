(function (ng) {
    var mod = ng.module("medicoModule", ['ui.router']);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/medicos/';
            $stateProvider.state('medicosList', {
                url: '/medicos/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'medicos.list.html',
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('medicoDetail', {
                url: '/medicos/{medicoId:int}/detail',
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
            });
        }
    ]);
})(window.angular);



