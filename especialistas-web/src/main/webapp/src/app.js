(function(ng) {
    var app = angular.module('mainApp', [
        //External dependencies
        'ui.router',
        'ui.bootstrap',
        'uiGmapgoogle-maps',
        //Internal modules dependencies
        'medicoModule',
        'horaModule',
        'laboratoriosModule',
        'examenesModule',
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
    app.config(['$qProvider', 'uiGmapGoogleMapApiProvider', function ($qProvider, uiGmapGoogleMapApiProvider) {
            uiGmapGoogleMapApiProvider.configure({
           key: 'AIzaSyC0zuiSaih-_7wSh5WwzKYxgdH4ml9Kgwc',
        v: '3.20', //defaults to latest 3.X anyhow
        libraries: 'weather,geometry,visualization'
    });         
            $qProvider.errorOnUnhandledRejections(false);               
    }]);
    
})(window.angular);