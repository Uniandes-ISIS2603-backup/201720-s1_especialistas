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
                        templateUrl: basePath + 'examen.detail_.html',
                        controller: 'examenDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('examenUpdate', {
                url: '/examenes/{examenId}/update',
                params :{
                    examenId :null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'update/exam.update.html',
                        controller: 'examenUpdateCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('examenesList', {
                url: '/examenes',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'get/examenes.list.html',
                        controller: 'examenCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('examenCreate', {
                url: '/examenes',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'post/examen.new.html',
                        controller: 'examenNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('examenDelete', {
                url: '/examenes/{examenId}/delete',
                params :{
                    examenId :null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'delete/examen.delete.html',
                        controller: 'examenDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
})(window.angular);



