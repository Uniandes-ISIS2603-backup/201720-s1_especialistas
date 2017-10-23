(function (ng) {
    // Definición del módulo
    var mod = ng.module("farmaciaModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/farmacias/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            $urlRouterProvider.otherwise("farmaciasList");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('farmaciaList', {
                // Url que aparecerá en el browser
                url: '/farmacia/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'farmacias.list.html',
                        controller: 'farmaciaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
