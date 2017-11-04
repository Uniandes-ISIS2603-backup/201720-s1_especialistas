(function (ng) {
    var mod = ng.module("horaModule", ['ui.router']);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/horas/';
            $stateProvider.state('horasList', {
                url: '/agenda',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'horas.list.html',
                        controller: 'horaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            })
        }
    ]);
})(window.angular);