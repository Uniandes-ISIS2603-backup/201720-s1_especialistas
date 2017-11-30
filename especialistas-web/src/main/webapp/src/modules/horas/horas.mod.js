(function (ng) {
    var mod = ng.module("horaModule", ['medicoModule', 'ui.router']);

    mod.config(['$stateProvider', function ($stateProvider) {
            var basePath = 'src/modules/horas/';
            $stateProvider.state('horas', {
                abstract : true,
                url: '/agenda',
                parent: 'medicoDetail',
                params: {
                    "medicoId": null
                },
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'horas.html',
                        controller: 'horaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('horasList', {
                url: '',
                parent: 'horas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'horas.list.html',
                        controller: 'horaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('horaDetail', {
                url: '/{horaId: int}',
                parent: 'horas',
                params: {
                    "horaId": null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'horas.list.html',
                        controller: 'horaCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'horas.detail.html',
                        controller: 'horaDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('horasCreate', {
                url: '/nuevo',
                parent: 'horas',
                params: {
                    "hora" : null,
                    "dia": 0
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'create/horas.create.html',
                        controller: 'horaCreateCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('horaDelete', {
                url: '/{horaId: int}/borrar',
                parent: 'horas',
                params: {
                    "horaId": null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'delete/horas.delete.html',
                        controller: 'horaDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);