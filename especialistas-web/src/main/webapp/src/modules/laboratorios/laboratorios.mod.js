(function (ng) {
    var mod = ng.module("laboratoriosModule", ['ui.router']);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/laboratorios/';
            $stateProvider.state('laboratoriosList', {
                url: '/laboratorios/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'laboratorios.list.html',
                        controller: 'laboratorioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);



