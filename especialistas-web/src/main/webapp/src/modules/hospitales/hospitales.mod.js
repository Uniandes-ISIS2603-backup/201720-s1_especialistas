(function (ng) {
    var mod = ng.module("hospitalesModule", ['ui.router']);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/hospitales/';
            $stateProvider.state('hospitalesList', {
                url: '/hospitales/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'hospitales.list.html',
                        controller: 'hospitalCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);



