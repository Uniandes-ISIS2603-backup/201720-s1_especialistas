(function (ng){
    var mod = ng.module("tarjetaModule",['ui.router']);
    
    mod.config(['$stateProvider', function ($stateProvider){
            var basePath = 'src/modules/tarjetas/';
            
            $stateProvider.state('tarjetasList',{
                url: '/tarjetas/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'tarjetas.list.html',
                        controller: 'tarjetaCtrl',
                        controlesAs: 'ctrl'
                    }
                }
            }).state('tarjetasCrear', {
                url: '/tarjetas/nuevo/{usuarioId:int}',
                param:{
                  usuarioId: null  
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'new/tarjetas.new.html',
                        controller: 'tarjetasNewController',
                        controllerAs: 'ctrl'
                    }
                },
                ncyBreadcrumb: {
                            parent: 'tarjetasList',
                            label: 'tarjetas'
                          }
            }).state('tarjetasDelete', {
                url: '/tarjetas/delete/{tarjetaId:int}',
                param: {
                    tarjetaId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'delete/tarjetas.delete.html',
                        controller: 'tarjetasDeleteController',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('tarjetaUpdate', {
                url: '/tarjetas/update/{tarjetaId:int}',
                param: {
                    tarjetaId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'new/tarjetas.new.html',
                        controller: 'tarjetaUpdateCtrl'
                    }
                }
            }).state('tarjetaDetail', {
                url: '/tarjetas/{tarjetaId:int}/detail',
                param: {
                    tarjetaId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'tarjetas.detail.html',
                        controller: 'tarjetaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
    }
        
    ]);
})(window.angular);