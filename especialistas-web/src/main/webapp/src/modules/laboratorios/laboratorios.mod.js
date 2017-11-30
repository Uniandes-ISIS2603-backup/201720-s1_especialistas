(function (ng) {
    var mod = ng.module("laboratoriosModule", ['ui.router']);

    mod.config(['$stateProvider', function ($stateProvider) {
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
                    },
                    'mapView': {
                        controller: 'labMapCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'lab.map.html'
                    }
                },
                ncyBreadcrumb: {
                    parent: 'laboratoriosList',
                    label:'laboratorio'
                }
            }).state('labCreate', {
                url: '/laboratorios/create',
                views: {
                    'mainView': {                                                         
                        templateUrl: basePath + 'post/laboratorio.new.html',
                        controller: 'labNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('labUpdate', {
                url: '/laboratorios/{laboratorioId:int}/update',
                param: {
                    laboratorioId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'update/laboratorio.update.html',
                        controller: 'labUpdateCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('examAdd', {
                url: '/laboratorios/{laboratorioId:int}/addExam',
                param: {
                    laboratorioId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'add/examen.add.html',
                        controller: 'examenAddCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });

        }]);
})(window.angular);



