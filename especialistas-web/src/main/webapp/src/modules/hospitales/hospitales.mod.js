(function (ng) {
    var mod = ng.module("hospitalesModule", ['ui.router']);
    mod.constant("hospitalesContext", "api/hospitales");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/hospitales/';
            $urlRouterProvider.otherwise("/hospitalesList");
            $stateProvider.state('hospitales', {
                url: '/hospitales',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'hospitales.html',
                        controller: 'hospitalCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            }).state('hospitalesList', {
                url: '/hospitales/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'hospitales.list.html',
                        controller: 'hospitalCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('hospitalesCreate', {
                url: '/hospitales/create',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'create/hospitales.create.html',
                        controller: 'hospitalesCreateController',
                        controllerAs: 'ctrl'
                    }
                },
                ncyBreadcrumb: {
                    parent: 'hospitalesList',
                    label:'hospital nuevo'
                }
            }).state('hospitalDelete', {
                url: '/hospitales/delete/{hospitalId:int}',
                param: {
                    authorId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + '/delete/hospitales.delete.html',
                        controller: 'hospitalDeleteController',
                        controllerAs: 'ctrl'
                    }
                },
                ncyBreadcrumb: {
                    parent: 'hospitalesList',
                    label:'hospital'
                }
            }).state('hospitalDetail', {
                url: '/{hospitalId:int}/detail',
                parent: 'hospitales',
                param: {
                    hospitalId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'hospitales.detail.html',
                        controller: 'hospitalCtrl',
                        controllerAs: 'ctrl'
                    },
                    'mapView': {
                        controller: 'HospitalMapCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'hospitales.map.html'
                    }
                },
                ncyBreadcrumb: {
                    parent: 'hospitalesList',
                    label:'hospital'
                }
            }).state('hospitalUpdate', {
                url: '/{hospitalId:int}/udpate',
                parent: 'hospitales',                
                params: {
                    medicoId : null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'create/hospitales.create.html',
                        controller: 'hospitalUpdateCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                ncyBreadcrumb: {
                    parent: 'hospitalesList',
                    label:'hospital'
                }
            });
        }
    ]);
})(window.angular);



