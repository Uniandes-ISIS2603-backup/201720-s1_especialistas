(function (ng) {
    var mod = ng.module("hospitalesModule", ['ui.router']);
    mod.constant("hospitalesContext", "api/hospitales");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/hospitales/';
            $urlRouterProvider.otherwise("/hospitalesList");
            $stateProvider.state('hospitales', {
                url: '/hospitales',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'hospitales.html',
                        controller: 'hospitalCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('hospitalesList', {
                url: '/hospitales/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'hospitales.list.html',
                        controller: 'hospitalCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('hospitalDetail', {
                url: '/{hospitalId:int}/detail',
                parent: 'hospitales',
                param: {
                    hospitalId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'hospitales.detail.html',
                        controller: 'hospitalCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);



