var AUTH0_CLIENT_ID = 'kVYdKT6aLZgYn7uGufUGKRZAaulFQSUe';
var AUTH0_CALLBACK_URL = location.href;
var AUTH0_DOMAIN = 'recreovias.auth0.com';

// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js
angular.module('starter', ['ionic', 'starter.controllers', 'auth0.auth0', 'angular-jwt'])

.run(['$ionicPlatform', 'authService', function($ionicPlatform, authService) {
    // Process the auth token if it exists and fetch the profile
    authService.authenticateAndGetProfile();

    // Check is the user authenticated before Ionic platform is ready
    authService.checkAuthOnRefresh();

    $ionicPlatform.ready(function() {
        // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
        // for form inputs)
        if (window.cordova && window.cordova.plugins.Keyboard) {
            cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
            cordova.plugins.Keyboard.disableScroll(true);

        }
        if (window.StatusBar) {
            // org.apache.cordova.statusbar required
            StatusBar.styleDefault();
        }
    });
}])

.config(['$stateProvider', '$urlRouterProvider', 'angularAuth0Provider', function($stateProvider, $urlRouterProvider, angularAuth0Provider) {
    $stateProvider

        .state('initial', {
        url: '/initial',
        templateUrl: 'templates/initial.html',
        controller: 'InitialCtrl'
    })

    .state('app', {
        url: '/app',
        abstract: true,
        templateUrl: 'templates/menu.html',
        controller: 'AppCtrl'
    })

    .state('app.actividad', {
        url: '/actividad',
        views: {
            'menuContent': {
                templateUrl: 'templates/actividad.html',
                controller: 'ActividadCtrl'
            }
        }
    })

    .state('app.historial', {
        url: '/historial',
        views: {
            'menuContent': {
                templateUrl: 'templates/historial.html',
                controller: 'HistorialCtrl'
            }
        }
    })

    .state('app.historialNueva', {
        url: '/historial/nueva',
        views: {
            'menuContent': {
                templateUrl: 'templates/historialNueva.html',
                controller: 'HistorialNuevaCtrl'
            }
        }
    })

    .state('app.recreovias', {
        url: '/recreovias',
        views: {
            'menuContent': {
                templateUrl: 'templates/recreovias.html',
                controller: 'RecreoviasCtrl'
            }
        }
    })

    .state('app.recomendaciones', {
        url: '/recomendaciones',
        views: {
            'menuContent': {
                templateUrl: 'templates/recomendaciones.html',
                controller: 'RecomendacionesCtrl'
            }
        }
    })

    .state('app.perfil', {
        url: '/perfil',
        views: {
            'menuContent': {
                templateUrl: 'templates/perfil.html',
                controller: 'PerfilCtrl'
            }
        }
    })

    .state('app.single', {
        url: '/playlists/:playlistId',
        views: {
            'menuContent': {
                templateUrl: 'templates/playlists.html',
                controller: 'PlaylistCtrl'
            }
        }
    });
    // if none of the above states are matched, use this as the fallback
    $urlRouterProvider.otherwise('/initial');

    angularAuth0Provider.init({
        clientID: AUTH0_CLIENT_ID,
        domain: AUTH0_DOMAIN
    });
}])

.service('authService', ['$rootScope', 'angularAuth0', 'authManager', 'jwtHelper', '$location', '$ionicPopup', '$http', function($rootScope, angularAuth0, authManager, jwtHelper, $location, $ionicPopup, $http) {
    var userProfile = JSON.parse(localStorage.getItem('profile')) || {};
    //console.log(userProfile);

    function login(username, password) {
        angularAuth0.login({
            connection: 'Username-Password-Authentication',
            responseType: 'token',
            email: username,
            password: password,
            popup: false,
            sso: false
        }, onAuthenticated, null);
    }

    function costumlogin(username, password) {
        angularAuth0.signin({
            connection: 'Username-Password-Authentication',
            responseType: 'token',
            email: username,
            password: password,
            popup: false,
            sso: false
        }, onAuthenticated, null);
    }

    function signup(username, password, callback) {
        angularAuth0.signup({
            connection: 'Username-Password-Authentication',
            responseType: 'token',
            email: username,
            password: password,
            popup: false,
            sso: false
        }, callback);
    }

    function loginWithGoogle() {
        angularAuth0.login({
            connection: 'google-oauth2',
            responseType: 'token',
            popup: false
        }, onAuthenticated, null);
    }

    function loginWithFacebook() {
        angularAuth0.login({
            connection: 'facebook',
            responseType: 'token',
            popup: false
        }, onAuthenticated, null);
    }

    // Logging out just requires removing the user's
    // id_token and profile
    function logout() {
        localStorage.removeItem('id_token');
        localStorage.removeItem('profile');
        localStorage.removeItem('additionalInfo');
        authManager.unauthenticate();
        userProfile = {};

        $location.path('/');
    }

    function refreshToken() {
        //TODO-CHECK
        //console.log(angularAuth0);
        var refreshingToken = null;
        var idToken = localStorage.getItem('id_token');
        var refreshToken = localStorage.getItem('refresh_token');
        if (jwtHelper.isTokenExpired(idToken)) {
            //console.log("expired");
            if (refreshingToken === null) {
                angularAuth0.refreshToken(
                    idToken,
                    function(id_token) {
                        localStorage.setItem('id_token', id_token);
                        return id_token;
                    });
            }
        } else {
            return idToken;
        }
    }

    function authenticateAndGetProfile() {
        var result = angularAuth0.parseHash(window.location.hash);
        if (result && result.idToken) {
            onAuthenticated(null, result);
        } else if (result && result.error) {
            onAuthenticated(result.error);
        }

        if (!angular.equals({}, userProfile)) {
            $location.path('/app/actividad');
        } else {
            $location.path('/');
        }
    }

    function onAuthenticated(error, authResult) {
        //console.log('onAuthenticated');
        if (error) {
            //console.log(error);
            return $ionicPopup.alert({
                title: 'Login failed!',
                template: 'Please check your credentials!'
            });
        }

        localStorage.setItem('id_token', authResult.idToken);
        authManager.authenticate();

        angularAuth0.getProfile(authResult.idToken, function(error, profileData) {
            if (error) {
                return console.log(error);
            }

            localStorage.setItem('profile', JSON.stringify(profileData));
            userProfile = profileData;
            if (userProfile.user_metadata !== undefined && userProfile.user_metadata.barrio !== undefined) {
                localStorage.setItem('additionalInfo', true);
            }

            $location.path('/app/actividad');
        });
    }

    function checkAuthOnRefresh() {
        var token = localStorage.getItem('id_token');
        if (token) {
            if (!jwtHelper.isTokenExpired(token)) {
                if (!$rootScope.isAuthenticated) {
                    authManager.authenticate();
                }
            }
        }
    }

    return {
        login: login,
        costumlogin: costumlogin,
        logout: logout,
        signup: signup,
        loginWithGoogle: loginWithGoogle,
        loginWithFacebook: loginWithFacebook,
        checkAuthOnRefresh: checkAuthOnRefresh,
        authenticateAndGetProfile: authenticateAndGetProfile,
        refreshToken: refreshToken
    }
}])

.service('sharedModal', function() {
    var modal = {};

    return {
        getModal: function() {
            return modal;
        },
        setModal: function(modalP, modalMapP) {
            modal = {
                modal: modalP,
                modalMap: modalMapP
            };
        }
    };
})

.service('sharedLocation', function($rootScope) {
    var location = null;

    return {
        getLocation: function() {
            return location;
        },
        setLocation: function(value) {
            location = value;
            $rootScope.$broadcast('location:updated', value);
        }
    };
})

.directive('equals', function() {
    return {
        restrict: 'A', // only activate on element attribute
        require: '?ngModel', // get a hold of NgModelController
        link: function(scope, elem, attrs, ngModel) {
            if (!ngModel) return; // do nothing if no ng-model

            // watch own value and re-validate on change
            scope.$watch(attrs.ngModel, function() {
                validate();
            });

            // observe the other value and re-validate on change
            attrs.$observe('equals', function(val) {
                validate();
            });

            var validate = function() {

                // values
                var val1 = ngModel.$viewValue;
                var val2 = attrs.equals;

                // set validity
                ngModel.$setValidity('equals', !val1 || !val2 || val1 === val2);
            };
        }
    }
})

.directive("listValidator", function() {
    return {
        restrict: "A",
        require: "ngModel",
        link: function(scope, element, attributes, ngModel) {
            //console.log(scope);
            var list = nombreBarrios;
            //console.log("listValidator", attributes.listValidator);
            ngModel.$validators.listValidator = function(modelValue, viewValue) {
                return list.indexOf(viewValue) !== -1;
            }
        },
    };
});