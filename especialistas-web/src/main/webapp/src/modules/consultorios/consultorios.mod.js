(function (ng) {
    var mod = ng.module("consultoriosModule", ['ui.router']);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/consultorios/';
            $stateProvider.state('consultoriosList', {
                url: '/consultorios/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'consultorios.list.html',
                        controller: 'consultorioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);



