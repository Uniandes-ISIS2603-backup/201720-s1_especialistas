(function (ng) {
    // Definición del módulo
    var mod = ng.module("farmaciaModule", ['ui.router', 'uiGmapgoogle-maps']);
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', function ($stateProvider) {
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
            }).state('farmaciaDetail', {
                url: '/farmacias/:id',
                params:{
                    id:null
                },
                        
                views: {
                    'mainView': {
                        controller: 'farmaciasDetailCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'detail/farmacias.detail.html'
                    },
                    'mapView': {
                        controller: 'FarmaciaMapCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'detail/farmacias.map.html'
                    }
                },
                ncyBreadcrumb: {
                    parent: 'farmaciasList',
                    label:'farmacia'
                }
            }).state('addMedicamento', {
                url: '/farmacias/:id/medicamento/add/',
                params:{
                    id:null
                },          
                views: {
                    'mainView': {
                        controller: 'farmaciaAddMedicamentoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'create/farmacias.addMedicamento.html'
                    }
                }
            }).state('deleteMedicamento', {
                url: '/farmacias/:id/medicamento/delete/',
                params:{
                    id:null
                },          
                views: {
                    'mainView': {
                        controller: 'farmaciaDeleteMedicamentoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'delete/farmacias.deleteMedicamento.html'
                    }
                }
            });
        }
    ]);
})(window.angular);
