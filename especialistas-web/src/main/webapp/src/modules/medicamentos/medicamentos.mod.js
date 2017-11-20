(function (ng) {
    // Definición del módulo
    var mod = ng.module("medicamentoModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', function ($stateProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/medicamentos/';
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('medicamentosList', {
                // Url que aparecerá en el browser
                url: '/medicamentos/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'medicamentos.list.html',
                        controller: 'medicamentoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('medicamentoCreate', {
                url: '/medicamentos/create',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'create/medicamentos.create.html',
                        controller: 'medicamentoCreateController',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('medicamentoDetail', {
                url: '/medicamentos/:id',
                params:{
                    id:null
                },
                        
                views: {
                    'mainView': {
                        controller: 'medicamentosDetailCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'detail/medicamentos.detail.html'
                    }
                }
            });
        }
    ]);
})(window.angular);
