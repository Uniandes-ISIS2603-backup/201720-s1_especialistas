(function (ng) {
    // Definición del módulo
    var mod = ng.module("farmaciaModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/farmacias/';
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('farmaciasList', {
                // Url que aparecerá en el browser
                url: '/farmacias/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'farmacias.list.html',
                        controller: 'farmaciaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('farmaciaCreate', {
                url: '/farmacias/create',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'create/farmacia.create.html',
                        controller: 'farmaciaCreateController',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
