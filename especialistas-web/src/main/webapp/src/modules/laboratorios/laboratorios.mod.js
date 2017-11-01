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
            }).state('labCreate', {
                url: '/laboratorios/create',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'post/labs.new.html',
                        controller: 'labNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('labDelete', {
                url: '/laboratorios/delete/{laboratorioId:int}',
                parent: 'laboratorios',
                param: {
                    laboratorioId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'delete/labs.delete.html',
                        controller: 'labDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });

        }]);
})(window.angular);



