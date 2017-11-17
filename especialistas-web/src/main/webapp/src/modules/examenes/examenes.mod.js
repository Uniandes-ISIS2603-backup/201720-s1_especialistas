(function (ng) {
    var mod = ng.module("examenesModule", ['ui.router']);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/examenes/';
            $stateProvider.state('examDetail', {
                url: '/examenes/{examenId}',
                params :{
                    examenId :null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'examen.detail.html',
                        controller: 'examenDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('examUpdate', {
                url: '/examenes/{examenId}/update',
                params :{
                    examenId :null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'update/exam.update.html',
                        controller: 'examUpdateCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
})(window.angular);



