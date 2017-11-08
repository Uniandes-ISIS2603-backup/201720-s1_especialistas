(function(ng) {
    var app = angular.module('mainApp', [
        //External dependencies
        'ui.router',
        'ui.bootstrap',
        //Internal modules dependencies
        'medicoModule',
        'horaModule',
        'laboratoriosModule',
        'farmaciaModule',
        'ordenesMedicasModule',
        'citasModule',
        'hospitalesModule',
        'usuarioModule',
        'pagoModule',
        'tarjetaModule',
        'medicamentoModule',
        'ordenesMedicasModule',
        'consultoriosModule'
        
    ]);
    //Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);               
    }]);
    
})(window.angular);