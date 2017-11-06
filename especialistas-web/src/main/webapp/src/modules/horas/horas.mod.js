(function (ng) {
    var mod = ng.module("horaModule", ['medicoModule', 'ui.router']);
 
    mod.config(['$stateProvider', function ($stateProvider) {
            var basePath = 'src/modules/horas/';
            $stateProvider.state('horaDetail', {
            url: '/{horaId: int}/informacion',
                parent: 'medicoDetail',
                params : {
                    "horaId" : null
                },
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'horas.detail.html',
                        controller: 'horaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);