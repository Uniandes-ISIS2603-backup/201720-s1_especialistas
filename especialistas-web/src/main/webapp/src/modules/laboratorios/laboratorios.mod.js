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
            }).state('labDelete', {
                url: '/delete/{laboratorioId:int}',
                param: {
                    laboratorioId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + '/delete/laboratorio.delete.html',
                        controller: 'labDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('labDetail', {
                url: '/laboratorios/{laboratorioId:int}',
                param: {
                    laboratorioId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'laboratorios.detail.html',
                        controller: 'labDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });

        }]);
})(window.angular);



