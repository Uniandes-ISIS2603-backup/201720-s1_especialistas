(function (ng){
    var mod = ng.module("tarjetaModule",['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider){
            var basePath = 'src/modules/tarjetas';
            
            $stateProvider.state('tarjetasList',{
                url: 'tarjetas/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'tarjetas.list.html',
                        controller: 'tarjetaCtrl',
                        controlesAs: 'ctrl'
                    }
                }
            });
    }
        
    ]);
})(window.angular);