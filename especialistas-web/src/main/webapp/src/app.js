(function(ng) {
    var app = angular.module('mainApp', [
        //External dependencies
        'ui.router',
        'ui.bootstrap',
        //Internal modules dependencies
        'medicoModule',
        'laboratoriosModule',
        'farmaciaModule',
        'citasModule',
        'consultoriosModule',
        'hospitalesModule'
    ]);
    //Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);               
    }]);
    
})(window.angular);