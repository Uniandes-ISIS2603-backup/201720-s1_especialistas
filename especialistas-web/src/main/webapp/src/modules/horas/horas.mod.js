(function (ng) {
    var mod = ng.module("horaModule", ['medicoModule', 'ui.router']);

    mod.config(['$stateProvider', function ($stateProvider) {
            var basePath = 'src/modules/horas/';
            $stateProvider.state('horasList', {
                url: '/agenda',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'horas.list.html',
                        controller: 'horaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('horaDetail', {
                url: '/agenda',
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