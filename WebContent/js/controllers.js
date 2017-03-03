var urlServer = "http://carolinapinzon.com:8080/Recreovias/";
var figuraBarrios = [];
var nombreBarrios = [];
var recreovias = [{
    nombre: "Altablanca",
    horarios: [{
        dia: "I",
        s: 6.5,
        e: 8.5
    }, {
        dia: "D",
        s: 8,
        e: 13
    }],
    lat: 4.7343547,
    lng: -74.0286526,
    direccion: "Calle 156 B No. 8 – 40",
    active: "inactive"
}, {
    nombre: "Atahualpa",
    horarios: [{
        dia: "M",
        s: 18,
        e: 20
    }, {
        dia: "S",
        s: 8,
        e: 10
    }],
    lat: 4.6892228,
    lng: -74.1456496,
    direccion: "Carrera 113 No. 23g – 15",
    active: "inactive"
}, {
    nombre: "Candelaria",
    horarios: [{
        dia: "I",
        s: 6.5,
        e: 8.5
    }],
    lat: 4.5688999,
    lng: -74.1471671,
    direccion: "Calle 62 Sur No. 22l – 27",
    active: "inactive"
}, {
    nombre: "Casona",
    horarios: [{
        dia: "M",
        s: 7,
        e: 9
    }],
    lat: 4.6067916,
    lng: -74.1789153,
    direccion: "Calle 58m Con Cra 78j Sur",
    active: "inactive"
}, {
    nombre: "Castilla",
    horarios: [{
        dia: "M",
        s: 7,
        e: 9
    }],
    lat: 4.6385092,
    lng: -74.1443652,
    direccion: "Carrera 75 No. 8a – 50",
    active: "inactive"
}, {
    nombre: "Cayetano Cañizares",
    horarios: [{
        dia: "M",
        s: 18,
        e: 20
    }, {
        dia: "S",
        s: 8,
        e: 10
    }],
    lat: 4.6244277,
    lng: -74.1619908,
    direccion: "Calle 42 Sur Con Cra 80",
    active: "inactive"
}, {
    nombre: "CC Centro Mayor",
    horarios: [{
        dia: "M",
        s: 8,
        e: 10
    }],
    lat: 4.593187,
    lng: -74.1235673,
    direccion: "Autopista Sur Con Calle 38a Sur",
    active: "inactive"
}, {
    nombre: "CC Fiesta Suba",
    horarios: [{
        dia: "M",
        s: 18.5,
        e: 20.5
    }],
    lat: 4.744585,
    lng: -74.092605,
    direccion: "Av  Suba (CALLE 147) No 101 – 56",
    active: "inactive"
}, {
    nombre: "Central Piloto",
    horarios: [{
        dia: "M",
        s: 7,
        e: 9
    }],
    lat: 4.7378226,
    lng: -74.1012386,
    direccion: "Transversal 112b Con Calle 122d",
    active: "inactive"
}, {
    nombre: "Ciudad Montes",
    horarios: [{
        dia: "M",
        s: 6.5,
        e: 8.5
    }, {
        dia: "D",
        s: 9,
        e: 12
    }],
    lat: 4.602972,
    lng: -74.116606,
    direccion: "Calle 10 Sur No. 39 – 29",
    active: "inactive"
}, {
    nombre: "Eduardo Santos",
    horarios: [{
        dia: "M",
        s: 7,
        e: 9
    }],
    lat: 4.5980237,
    lng: -74.090323,
    direccion: "Calle 4 A Bis No. 18b-30",
    active: "inactive"
}, {
    nombre: "El Jazmín",
    horarios: [{
        dia: "I",
        s: 6.5,
        e: 8.5
    }],
    lat: 4.6100768,
    lng: -74.1158928,
    direccion: "Diagonal 2 No. 45a – 09",
    active: "inactive"
}, {
    nombre: "El Porvenir",
    horarios: [{
        dia: "S",
        s: 8,
        e: 10
    }],
    lat: 4.643204,
    lng: -74.1884046,
    direccion: "Carrera 100 No. 52 – 24 Sur",
    active: "inactive"
}, {
    nombre: "El Salitre PRD",
    horarios: [{
        dia: "S",
        s: 8,
        e: 10
    }],
    lat: 4.6634898,
    lng: -74.0871128,
    direccion: "Transv 48 No. 63 – 65",
    active: "inactive"
}, {
    nombre: "El Sol",
    horarios: [{
        dia: "D",
        s: 8,
        e: 13
    }],
    lat: 4.6144456,
    lng: -74.1154976,
    direccion: "Carrera 50, Entre Calles 2 Y 3",
    active: "inactive"
}, {
    nombre: "El Tunal",
    horarios: [{
        dia: "M",
        s: 8,
        e: 10
    }, {
        dia: "D",
        s: 8,
        e: 13
    }],
    lat: 4.5739585,
    lng: -74.1332742,
    direccion: "Calle 48b Sur No. 22a – 70",
    active: "inactive"
}, {
    nombre: "Éxito Colina",
    horarios: [{
        dia: "D",
        s: 8,
        e: 13
    }],
    lat: 4.7349965,
    lng: -74.0662227,
    direccion: "Av. Boyacá (Cra 72) No. 146a – 25",
    active: "inactive"
}, {
    nombre: "Éxito Suba",
    horarios: [{
        dia: "D",
        s: 8,
        e: 13
    }],
    lat: 4.748355,
    lng: -74.097373,
    direccion: "Calle 145 No. 105b – 58",
    active: "inactive"
}, {
    nombre: "Hayuelos",
    horarios: [{
        dia: "I",
        s: 6.5,
        e: 8.5
    }],
    lat: 4.6626438,
    lng: -74.1303295,
    direccion: "Av. Calle 20 No. 73 – 39",
    active: "inactive"
}, {
    nombre: "La Andrea",
    horarios: [{
        dia: "I",
        s: 7,
        e: 9
    }],
    lat: 4.509937,
    lng: -74.1112808,
    direccion: "Calle 75 B Sur No. 2 – 11",
    active: "inactive"
}, {
    nombre: "La Gaitana",
    horarios: [{
        dia: "I",
        s: 6.5,
        e: 8.5
    }],
    lat: 4.7409649,
    lng: -74.109047,
    direccion: "Carrera 116 Con Calle 132",
    active: "inactive"
}, {
    nombre: "La Serena",
    horarios: [{
        dia: "M",
        s: 6.5,
        e: 8.5
    }],
    lat: 4.7101747,
    lng: -74.09323,
    direccion: "Carrera 86 No. 90a – 00",
    active: "inactive"
}, {
    nombre: "Madelena",
    horarios: [{
        dia: "M",
        s: 18.5,
        e: 20.5
    }, {
        dia: "S",
        s: 8,
        e: 10
    }],
    lat: 4.5890735,
    lng: -74.159503,
    direccion: "Cra 66 Con Cl 60 A Sur",
    active: "inactive"
}, {
    nombre: "MetroRecreo CC",
    horarios: [{
        dia: "M",
        s: 18.5,
        e: 20.5
    }, {
        dia: "D",
        s: 8,
        e: 13
    }],
    lat: 4.6326493,
    lng: -74.200789,
    direccion: " Carrera 97 C No. 69a - 08 Sur",
    active: "inactive"
}, {
    nombre: "Milenta Tejar",
    horarios: [{
        dia: "S",
        s: 8,
        e: 10
    }],
    lat: 4.6161727,
    lng: -74.1249372,
    direccion: "Carrera 55 Calle 18 Sur",
    active: "inactive"
}, {
    nombre: "Molinos",
    horarios: [{
        dia: "M",
        s: 6.5,
        e: 8.5
    }],
    lat: 4.5500294,
    lng: -74.1083897,
    direccion: "Calle 48p Sur No. 4 – 30",
    active: "inactive"
}, {
    nombre: "Nacional",
    horarios: [{
        dia: "D",
        s: 8,
        e: 13
    }],
    lat: 4.6241379,
    lng: -74.0651253,
    direccion: "Carrera 7 Entre Calles 36 Y 39",
    active: "inactive"
}, {
    nombre: "Naranjos",
    horarios: [{
        dia: "I",
        s: 6.5,
        e: 8.5
    }, {
        dia: "D",
        s: 8,
        e: 13
    }],
    lat: 4.6075422,
    lng: -74.1927892,
    direccion: "Calle 70f Sur No. 80j – 86",
    active: "inactive"
}, {
    nombre: "Plaza de las Américas CC",
    horarios: [{
        dia: "M",
        s: 18.5,
        e: 20.5
    }, {
        dia: "I",
        s: 7,
        e: 9
    }],
    lat: 4.6185902,
    lng: -74.1363109,
    direccion: "Carrera 71d No. 6 - 94 Sur",
    active: "inactive"
}, {
    nombre: "Plaza de Bolivar",
    horarios: [{
        dia: "D",
        s: 9,
        e: 12
    }],
    lat: 4.5981259,
    lng: -74.0782322,
    direccion: "Calles 10 Y 11 Con Carreras 7 Y 8",
    active: "inactive"
}, {
    nombre: "Salitre Plaza CC",
    horarios: [{
        dia: "I",
        s: 6.5,
        e: 8.5
    }],
    lat: 4.6546454,
    lng: -74.1098505,
    direccion: "Carrera 68 B No. 24 – 39",
    active: "inactive"
}, {
    nombre: "San Andrés",
    horarios: [{
        dia: "M",
        s: 18.5,
        e: 20.5
    }, {
        dia: "I",
        s: 7,
        e: 9
    }, {
        dia: "D",
        s: 8,
        e: 13
    }],
    lat: 4.7126588,
    lng: -74.1109158,
    direccion: "Calle 82 No. 101 – 52",
    active: "inactive"
}, {
    nombre: "San Carlos",
    horarios: [{
        dia: "D",
        s: 8,
        e: 13
    }],
    lat: 4.5734501,
    lng: -74.1056079,
    direccion: "Carrera 12c No. 27a - 70 Sur",
    active: "inactive"
}, {
    nombre: "San Cristobal",
    horarios: [{
        dia: "D",
        s: 8,
        e: 13
    }],
    lat: 4.57319,
    lng: -74.08592,
    direccion: "Calle 13 Sur No. 2a - 50 Este",
    active: "inactive"
}, {
    nombre: "San Rafael CC",
    horarios: [{
        dia: "M",
        s: 6.5,
        e: 8.5
    }],
    lat: 4.7228256,
    lng: -74.0623077,
    direccion: "Av. Calle 134 No. 55 – 32",
    active: "inactive"
}, {
    nombre: "Santa Isabel",
    horarios: [{
        dia: "D",
        s: 9,
        e: 12
    }],
    lat: 4.6001489,
    lng: -74.1011162,
    direccion: "Entre Calles 1c Y 1f Con Carrera 25a",
    active: "inactive"
}, {
    nombre: "Simón Bolivar",
    horarios: [{
        dia: "D",
        s: 8,
        e: 13
    }],
    lat: 4.6586407,
    lng: -74.0941697,
    direccion: "Entre Cll. 53 Y 63 Y Entre Trans. 48 Y Av. 68",
    active: "inactive"
}, {
    nombre: "Timiza",
    horarios: [{
        dia: "M",
        s: 7,
        e: 9
    }, {
        dia: "D",
        s: 8,
        e: 13
    }],
    lat: 4.6112601,
    lng: -74.1549988,
    direccion: "Calle 56a Sur Con Carrera 85",
    active: "inactive"
}, {
    nombre: "Unicentro Occidente CC",
    horarios: [{
        dia: "M",
        s: 18.5,
        e: 20.5
    }, {
        dia: "I",
        s: 7,
        e: 9
    }],
    lat: 4.7240188,
    lng: -74.1141014,
    direccion: "Carrera 111c No 86 – 05",
    active: "inactive"
}, {
    nombre: "Valles de Cafam",
    horarios: [{
        dia: "D",
        s: 8,
        e: 12
    }],
    lat: 4.5086552,
    lng: -74.1184741,
    direccion: "Transversal 44 Este No. 89c – 62 Sur",
    active: "inactive"
}, {
    nombre: "Villa de los Alpes",
    horarios: [{
        dia: "M",
        s: 6.5,
        e: 8.5
    }],
    lat: 4.5608634,
    lng: -74.098657,
    direccion: "Carrera 3b No. 35 – 22 Sur",
    active: "inactive"
}, {
    nombre: "Virrey Sur",
    horarios: [{
        dia: "M",
        s: 6.5,
        e: 8.5
    }],
    lat: 4.5007905,
    lng: -74.1123042,
    direccion: "Carrera 3 Este No. 93a – 03 Sur",
    active: "inactive"
}];

var actividades = [{
    nombre: "Gimnasia de Mantenimiento",
    short: "gm"
}, {
    nombre: "Gimnasia Psicofísica",
    short: "gp"
}, {
    nombre: "Estimulación Muscular",
    short: "ef"
}, {
    nombre: "Movilidad Articular",
    short: "ma"
}, {
    nombre: "Actividad Rítmica para Niños y Niñas",
    short: "an"
}, {
    nombre: "Rumba Tropical Folclórica",
    short: "rt"
}, {
    nombre: "Gimnasia Aeróbica Musicalizada",
    short: "ga"
}, {
    nombre: "Artes Marciales Musicalizadas",
    short: "am"
}, {
    nombre: "Pilates para Todos",
    short: "p"
}, {
    nombre: "Taller de Danzas",
    short: "d"
}, {
    nombre: "Gimnasios Saludables al Aire Libre",
    short: "gs"
}];

var recreoviaActive = L.icon({
    iconUrl: 'img/marker_active.svg',

    iconSize: [19, 26], // size of the icon
    iconAnchor: [9, 9], // point of the icon which will correspond to marker's location
});

var recreoviaInactive = L.icon({
    iconUrl: 'img/marker_inactive.svg',

    iconSize: [19, 26], // size of the icon
    iconAnchor: [9, 9], // point of the icon which will correspond to marker's location
});


angular.module('starter.controllers', [])

.controller('AppCtrl', ['$scope', 'authService', '$ionicPopup', '$http', 'jwtHelper', 'angularAuth0', 'sharedModal', '$ionicModal', '$filter', 'sharedLocation', function($scope, authService, $ionicPopup, $http, jwtHelper, angularAuth0, sharedModal, $ionicModal, $filter, sharedLocation) {
    var userProfile = JSON.parse(localStorage.getItem('profile')) || {};
    var modalMap;
    var meMarker = null;
    $scope.barriosModalMap = [];
    $ionicModal.fromTemplateUrl('templates/modal.html', {
        scope: $scope
    }).then(function(modal) {
        if (localStorage.getItem('additionalInfo') === null) {
            modal.show();
            modalMap = L.map('mapModal', {
                zoomControl: true
            }).setView([4.657344, -74.103621], 12);
            L.tileLayer('https://api.mapbox.com/styles/v1/cps543/civx5juan00172kr32fijky9r/tiles/256/{z}/{x}/{y}?access_token=pk.eyJ1IjoiY3BzNTQzIiwiYSI6ImNpcWIzanpoaTAzbG5maGtrNHp3d3FlN2wifQ.Y5HWNIY5yTomYqtqXOyiHQ', {
                maxZoom: 19
            }).addTo(modalMap);
            sharedModal.setModal(modal, modalMap);

            if (nombreBarrios.length != 0) {
                var myStyle = {
                    "color": "#333333",
                    "weight": 1,
                    "opacity": 1,
                    "fillOpacity": 0.1
                };

                angular.forEach(figuraBarrios, function(feature, key) {
                    //console.log(figura);
                    var geojsonBarrio = L.geoJson(feature, {
                        style: myStyle
                    });
                    geojsonBarrio.id = key;
                    //console.log(geojsonBarrio);
                    geojsonBarrio.on('click', clickBarrio);
                    geojsonBarrio.addTo(modalMap);
                    $scope.barriosModalMap.push(geojsonBarrio);
                    //mymap.fitBounds(geojsonBarrio.getBounds());
                    var option = angular.element('<option value="' + nombreBarrios[key] + '"></option>');
                    angular.element(document.querySelector('#barrioList')).append(option);
                });
            }

            if (sharedLocation.getLocation() !== null) {
                refreshMeMarker(sharedLocation.getLocation());
            }
        }
    });


    $scope.forms = {};
    $scope.formData = {
        barrio: "",
        birthdate: ""
    };
    // With the new view caching in Ionic, Controllers are only called
    // when they are recreated or on app start, instead of every page change.
    // To listen for when this page is active (for example, to refresh data),
    // listen for the $ionicView.enter event:
    //$scope.$on('$ionicView.enter', function(e) {
    //});

    $scope.logout = authService.logout;
    if (!angular.equals({}, userProfile)) {
        if (userProfile.user_id.startsWith("auth0")) {
            $scope.username = userProfile.user_metadata.name;
            $scope.profilePicture = userProfile.picture;
        } else {
            $scope.username = userProfile.name;
            if (userProfile.picture_large) {
                $scope.profilePicture = userProfile.picture_large;
            } else {
                $scope.profilePicture = userProfile.picture;
            }
        }
    }
    if (nombreBarrios.length == 0) {
        NProgress.start();
        $http({
                method: 'GET',
                url: encodeURI(urlServer + 'data/barrios.json'),
                headers: {
                    "content-type": "application/json"
                }
            })
            .success(function(data, status, headers, config) {
                angular.forEach(angular.fromJson(data).features, function(feature) {
                    var option = angular.element('<option value="' + feature.properties.NOMB_BARR + '"></option>');
                    //$('#barrioList').append(option);
                    angular.element(document.querySelector('#barrioList')).append(option);
                    nombreBarrios.push(feature.properties.NOMB_BARR);
                    figuraBarrios.push(feature.geometry);
                });
                if (localStorage.getItem('additionalInfo') === null) {
                    var myStyle = {
                        "color": "#333333",
                        "weight": 1,
                        "opacity": 1,
                        "fillOpacity": 0.1
                    };

                    angular.forEach(angular.fromJson(data).features, function(feature, key) {
                        //console.log(figura);
                        var geojsonBarrio = L.geoJson(feature.geometry, {
                            style: myStyle
                        });
                        geojsonBarrio.id = key;
                        //console.log(geojsonBarrio);
                        geojsonBarrio.on('click', clickBarrio);
                        geojsonBarrio.addTo(modalMap);
                        $scope.barriosModalMap.push(geojsonBarrio);
                        //mymap.fitBounds(geojsonBarrio.getBounds());
                    });
                }
                NProgress.done();
            })
            .error(function(data, status, headers, config) {
                
            });
    }


    $scope.$on('location:updated', function(event, data) {
        refreshMeMarker(data);
    });
    var prevBarrioSelected = null;

    function clickBarrio(e) {
        if (prevBarrioSelected != null) {
            prevBarrioSelected.setStyle({
                "color": "#333333",
                "weight": 1,
                "opacity": 1,
                "fillOpacity": 0.1
            });
        }
        var styleSelected = {
            "color": "#333333",
            "weight": 1,
            "opacity": 1,
            "fillOpacity": 0.5
        };
        $scope.barriosModalMap[e.target.id].setStyle(styleSelected);
        modalMap.fitBounds($scope.barriosModalMap[e.target.id].getBounds());
        $scope.$apply(function() {
            $scope.formData.barrio = nombreBarrios[e.target.id];
        });
        prevBarrioSelected = $scope.barriosModalMap[e.target.id];
        //console.log(e.target.id);
    }

    $scope.changeBarrio = function() {
        if (!$scope.forms.additionalInformationform.barrio.$invalid) {
            var event = {
                target: {
                    id: nombreBarrios.indexOf($scope.formData.barrio)
                }
            };
            clickBarrio(event);
        }
    }

    function refreshMeMarker(latLng) {
        if (modalMap != undefined) {
            if (meMarker == null) {
                var me = L.icon({
                    iconUrl: 'img/me.svg',
                    shadowUrl: 'img/me_shadow.svg',

                    iconSize: [18, 18], // size of the icon
                    shadowSize: [18, 18], // size of the shadow
                    iconAnchor: [9, 9], // point of the icon which will correspond to marker's location
                    shadowAnchor: [8, 8] // the same for the shadow
                });
                meMarker = L.marker(latLng, {
                    icon: me
                }).addTo(modalMap);
                modalMap.setView(latLng, 12);
            } else {
                meMarker.setLatLng(latLng);
            }
        }
    }

    $scope.sendAdditionalInfo = function() {
        if ($scope.forms.additionalInformationform.birthdate.$invalid) {
            return $ionicPopup.alert({
                title: 'Error al registrarse',
                template: 'La fecha de nacimiento no es válida'
            });
        }
        if ($scope.forms.additionalInformationform.barrio.$invalid) {
            return $ionicPopup.alert({
                title: 'Error al registrarse',
                template: 'El barrio no es válido'
            });
        }
        var token = authService.refreshToken();
        var body = {
            user_metadata: {
                barrio: $scope.formData.barrio,
                fechaNacimiento: $scope.forms.additionalInformationform.birthdate.$modelValue,
                sex: $scope.formData.sex
            }
        };
        $http({
                method: 'PATCH',
                url: 'https://recreovias.auth0.com/api/v2/users/' + userProfile.user_id,
                headers: {
                    authorization: "Bearer " + token
                },
                data: body,
                json: true
            })
            .success(function(data, status, headers, config) {
                sharedModal.getModal().modal.hide();
                localStorage.setItem('additionalInfo', true);
            })
            .error(function(data, status, headers, config) {
                

            });
        var gender = $scope.formData.sex;
        var distancias = "";
        var barrioFigure = figuraBarrios[nombreBarrios.indexOf($scope.formData.barrio)];
        var centroidPt = turf.centroid(barrioFigure);
        angular.forEach(recreovias, function(recreovia, index) {
            var from = {
                "type": "Feature",
                "properties": {},
                "geometry": {
                    "type": "Point",
                    "coordinates": [recreovia.lng, recreovia.lat]
                }
            };
            var distance = turf.distance(centroidPt, from, "kilometers");
            distancias += distance.toFixed(3);
            if (index < recreovias.length - 1) {
                distancias += "-";
            }
        });
        var body = {
            "id_auth": userProfile.user_id,
            "birthdate": $filter('date')($scope.forms.additionalInformationform.birthdate.$modelValue, "yyyy-MM-ddT00:00:00.000'Z'"),
            "sex": $scope.formData.sex,
            "barrio": $scope.formData.barrio,
            "distancias": distancias
        }
        $http({
                method: 'POST',
                url: encodeURI(urlServer + 'user'),
                headers: {
                    "content-type": "application/json"
                },
                data: body
            })
            .success(function(data, status, headers, config) {
                
            })
            .error(function(data, status, headers, config) {
                
            });
    }

    $scope.closeModal = function() {
        sharedModal.getModal().modal.hide();
    }

    navigator.geolocation.getCurrentPosition(onSuccessCurrent, onError);
    navigator.geolocation.watchPosition(onSuccessWatch, onError);

    function onSuccessCurrent(position) {
        lat = position.coords.latitude;
        lng = position.coords.longitude;
        sharedLocation.setLocation([lat, lng]);
        /*var me = L.icon({
            iconUrl: 'img/me.svg',
            shadowUrl: 'img/me_shadow.svg',

            iconSize:     [18, 18], // size of the icon
            shadowSize:   [18, 18], // size of the shadow
            iconAnchor:   [9, 9], // point of the icon which will correspond to marker's location
            shadowAnchor: [8, 8]  // the same for the shadow
        });
        meMarker = L.marker([lat, lng], {icon: me}).addTo(mapActividad);
        mapActividad.setView([lat, lng], 12);*/
    }

    function onSuccessWatch(position) {
        lat = position.coords.latitude;
        lng = position.coords.longitude;
        //meMarker.setLatLng([lat, lng]); 
        sharedLocation.setLocation([lat, lng]);
    }

    function onError(error) {
        if (error.code == 1) { //User denied Geolocation

        }
    }

}])

.controller('InitialCtrl', ['$scope', '$state', '$ionicSlideBoxDelegate', 'authService', '$ionicPopup', '$http', '$location', '$ionicModal', function($scope, $state, $ionicSlideBoxDelegate, authService, $ionicPopup, $http, $location, $ionicModal) {
    if (nombreBarrios.length == 0) {
        $http({
                method: 'GET',
                url: encodeURI(urlServer + 'data/barrios.json'),
                headers: {
                    "content-type": "application/json"
                }
            })
            .success(function(data, status, headers, config) {
                angular.forEach(angular.fromJson(data).features, function(feature) {
                    var option = angular.element('<option value="' + feature.properties.NOMB_BARR + '"></option>');
                    //$('#barrioList').append(option);
                    nombreBarrios.push(feature.properties.NOMB_BARR);
                    figuraBarrios.push(feature.geometry);
                });
            })
            .error(function(data, status, headers, config) {
                
            });
    }

    $scope.signup = {
        name: 'Usuario Prueba',
        email: '',
        birthdate: '',
        password1: 'xxx',
        password2: 'xxx'
    };
    /*$scope.signup = {
      name: 'Carolina Pinzón',
      email: 'c.pinzon543@uniandes.edu.co',
      birthdate: new Date(),
      sex: 'F',
      password1: '123',
      password2: '123'
    };*/
    $scope.login = {
        email: '',
        password: ''
    };
    /*$scope.login = {
      email: 'c.pinzon543@uniandes.edu.co',
      password: '123'
    };*/
    $scope.forms = {};
    $scope.videoSrc = 'img/video.mp4';

    $scope.showVideo = function() {
        var videoShowed = localStorage.getItem('video');
        console.log(videoShowed);
        if(!videoShowed) {
            $scope.playVideo();
        }
    }

    $scope.playVideo = function() {
        $ionicModal.fromTemplateUrl('templates/videoPopover.html', {
            scope: $scope
        }).then(function(modal) {
        	$scope.videoModal = modal;
        	$scope.videoModal.show();
        });
    }
    $scope.closeModal = function() {
    	$scope.videoModal.hide();
    }

    $scope.goToEntrar = function() {
        $ionicSlideBoxDelegate.slide(2);
    }

    $scope.goToRegistrar = function() {
        $ionicSlideBoxDelegate.slide(1);
    }

    $scope.registrarse = function() {
        //$state.go('app.actividad');
        /*if ($scope.forms.signupform.name.$invalid) {
            return $ionicPopup.alert({
                title: 'Error al registrarse',
                template: 'El nombre no puede estar vacío'
            });
        }*/
        if ($scope.forms.signupform.email.$invalid) {
            return $ionicPopup.alert({
                title: 'Error al registrarse',
                template: 'El email ingresado no es válido'
            });
        }
        /*if($scope.forms.signupform.birthdate.$invalid) {
          return $ionicPopup.alert({
            title: 'Error al registrarse',
            template: 'La fecha de nacimiento no es válida'
          });
        }
        if($scope.forms.signupform.sex.$invalid) {
          return $ionicPopup.alert({
            title: 'Error al registrarse',
            template: 'Debe seleccionar un sexo'
          });
        }
        if ($scope.forms.signupform.password1.$invalid || $scope.forms.signupform.password2.$invalid) {
            if ($scope.signup.password1 == '') {
                return $ionicPopup.alert({
                    title: 'Error al registrarse',
                    template: 'La contraseña no puede estar vacía'
                });
            }
            return $ionicPopup.alert({
                title: 'Error al registrarse',
                template: 'Las contraseñas no coinciden'
            });
        }*/
        $http({
                method: 'POST',
                url: 'https://recreovias.auth0.com/dbconnections/signup',
                headers: {
                    "content-type": "application/json"
                },
                data: {
                    client_id: AUTH0_CLIENT_ID,
                    email: $scope.signup.email,
                    password: $scope.signup.password1,
                    user_metadata: {
                        name: $scope.signup.name
                    },
                    connection: 'Username-Password-Authentication',
                    email_verified: false
                }
            })
            .success(function(data, status, headers, config) {
                if (status === 200) {
                    authService.costumlogin($scope.signup.email, $scope.signup.password1);
                }
            })
            .error(function(data, status, headers, config) {
                if (data.code == 'user_exists') {
                    return $ionicPopup.alert({
                        title: 'Error al registrarse',
                        template: 'El email ya se encuentra registrado'
                    });
                }
                if (data) {
                    return $ionicPopup.alert({
                        title: 'Error al registrarse',
                        template: data.description
                    });
                }
                $ionicPopup.alert({
                    title: 'Error al registrarse',
                    template: 'Servidor no disponible. Por favor intente más tarde'
                });
            });
    }

    $scope.entrar = function() {
        //authService.login($scope.username, $scope.password);
        //$state.go('app.actividad');
        if ($scope.forms.loginform.email.$invalid) {
            return $ionicPopup.alert({
                title: 'Error al registrarse',
                template: 'El email ingresado no es válido'
            });
        }
        if ($scope.forms.loginform.password.$invalid) {
            return $ionicPopup.alert({
                title: 'Error al registrarse',
                template: 'La contraseña no puede estar vacía'
            });
        }
        authService.costumlogin($scope.login.email, $scope.login.password);
    }

    $scope.loginWithFacebook = authService.loginWithFacebook;

    $scope.loginWithGoogle = authService.loginWithGoogle;
}])

.controller('ActividadCtrl', function($scope, $interval, $ionicSlideBoxDelegate, $ionicPopup, $ionicSideMenuDelegate, $ionicModal, sharedModal, sharedLocation, $filter, $http, $state) {
    $scope.sunRotate = {
        'rotate': false
    }
    $scope.showDetectarButton = true;
    $scope.recreovia = "Parque nacional";
    $scope.nombresRecreovias = recreovias;
    $scope.nombreActividades = actividades;
    $scope.startActivity = false;
    var interval;
    var accumulatedMilliseconds = 0;
    $scope.intervalCountSeconds = 0;
    $scope.intervalCountMinutes = 0;
    $scope.intervalCountHours = 0;
    $scope.calories = 0;
    $scope.show0Seconds = true;
    $scope.show0Minutes = true;
    $scope.showHours = false;
    $scope.showResume = false;
    $scope.showEmptyStars = [true, true, true, true, true];
    $scope.rating = undefined;
    $scope.confirmPopup;
    $scope.likeDislike = {
        like: "notSelected",
        dislike: "notSelected"
    };
    $scope.formData = {
        parque: "0",
        actividad: "0"
    };
    var fechaInicio;
    var meMarker = null;
    $scope.markers = [];

    $scope.clickLike = function() {
        $scope.likeDislike.like = "selected";
        $scope.likeDislike.dislike = "notSelected";
        sendActividad(true);
        setTimeout(function() {
            resetState();
            $scope.confirmPopup.close();
        }, 100);

    }
    $scope.clickDislike = function() {

        $scope.likeDislike.dislike = "selected";
        $scope.likeDislike.like = "notSelected";
        sendActividad(false);
        setTimeout(function() {
            resetState();
            $scope.confirmPopup.close();
        }, 100);
    }

    function sendActividad(calificacion) {
        var userProfile = JSON.parse(localStorage.getItem('profile')) || {};
        var now = new Date();
        var horario = "sm";
        if (now.getDay() == 0 || now.getDay() == 6) {
            horario = "fs";
        } else {
            if (now.getHours() > 12) {
                horario = "sn";
            }
        }
        var duracion = "";
        if ($scope.showHours) {
            duracion += $scope.intervalCountHours + ":";
        }
        if ($scope.show0Minutes) {
            duracion += "0";
        }
        duracion += $scope.intervalCountMinutes + ":";
        if ($scope.show0Seconds) {
            duracion += "0";
        }
        duracion += $scope.intervalCountSeconds;
        var body = {
            "id_auth": userProfile.user_id,
            "parque": recreovias[parseInt($scope.formData.parque)].nombre,
            "actividad": actividades[parseInt($scope.formData.actividad)].short,
            "horario": horario,
            "duracion": duracion,
            "calorias": $scope.calories,
            "calificacion": calificacion,
            "fecha": $filter('date')(now, "EEEE MMM d,yyyy")
        }
        $http({
                method: 'POST',
                url: encodeURI(urlServer + 'activity'),
                headers: {
                    "content-type": "application/json"
                },
                data: body
            })
            .success(function(data, status, headers, config) {
                
                $state.go('app.historial');
            })
            .error(function(data, status, headers, config) {
                
            });
    }

    var mapActividad;
    $scope.loadMap = function() {

        mapActividad = L.map('mapActividad', {
            zoomControl: true
        }).setView([4.657344, -74.103621], 12);
        L.tileLayer('https://api.mapbox.com/styles/v1/cps543/civx5juan00172kr32fijky9r/tiles/256/{z}/{x}/{y}?access_token=pk.eyJ1IjoiY3BzNTQzIiwiYSI6ImNpcWIzanpoaTAzbG5maGtrNHp3d3FlN2wifQ.Y5HWNIY5yTomYqtqXOyiHQ', {
            maxZoom: 19
        }).addTo(mapActividad);

        angular.forEach($scope.listaRecreovias, function(recreovia, key) {
            if (key == 0) {
                L.marker([recreovia.lat, recreovia.lng], {
                    icon: recreoviaInactive
                }).addTo(mapActividad);
            } else {
                L.marker([recreovia.lat, recreovia.lng], {
                    icon: recreoviaActive
                }).addTo(mapActividad);
            }
        });

    }

    function updateList(recreoviasFiltered) {
        angular.forEach($scope.markers, function(marker) {
            mapActividad.removeLayer(marker);
        });
        $scope.markers = [];
        var now = moment();

        angular.forEach(recreoviasFiltered, function(recreovia, key) {
            var active = false;
            var hours = now.hours();
            if (now.minutes() > 30) {
                hours += 0.5;
            }
            var popupContent = '<div class="popupRecreovia"><div class="nombre">' + recreovia.nombre + '</div><p class="direccion">' + recreovia.direccion + '</p><hr>';
            angular.forEach(recreovia.horarios, function(horario) {
                if (hours >= horario.s && hours < horario.e) {
                    if (horario.dia == "D" && now.day() == 0) {
                        active = true;
                    } else if (horario.dia == "M" && (now.day() == 2 || now.day() == 4)) {
                        active = true;
                    } else if (horario.dia == "I" && (now.day() == 3 || now.day() == 5)) {
                        active = true;
                    } else if (horario.dia == "S" && now.day() == 6) {
                        active = true;
                    }
                }
                popupContent += '<div class="tableContainer"><table><tr><td>';
                if (horario.dia == "M") {
                    popupContent += "Martes y Jueves";
                } else if (horario.dia == "I") {
                    popupContent += "Miércoles y Viernes";
                } else if (horario.dia == "S") {
                    popupContent += "Sábado";
                } else if (horario.dia == "D") {
                    popupContent += "Domingos y Festivos";
                }
                popupContent += '</td><td>';
                //horario
                popupContent += $filter('horarioRecreovia')(horario.s) + " - " + $filter('horarioRecreovia')(horario.e);
                popupContent += '</td></tr></table></div>';
            });
            popupContent += '</div>';
            if (!active) {
                var marker = L.marker([recreovia.lat, recreovia.lng], {
                    icon: recreoviaInactive
                }).addTo(mapActividad);
                marker.bindPopup(popupContent);
                marker.on('click', onPopupClick);
                marker._icon.id = key;
                $scope.markers.push(marker);
            } else {
                var marker = L.marker([recreovia.lat, recreovia.lng], {
                    icon: recreoviaActive
                }).addTo(mapActividad);
                marker.bindPopup(popupContent);
                marker.on('click', onPopupClick);
                marker._icon.id = key;
                $scope.markers.push(marker);
            }
            if (active) {
                recreovia.active = "active";
            } else {
                recreovia.active = "inactive";
            }
        });
        $scope.$apply(function() {
            $scope.recreovias = recreoviasFiltered;
        });
    }

    function onPopupClick(e) {
        $scope.$apply(function() {
            $scope.formData.parque = e.target._icon.id;
        });
    }

    $scope.deletePopup = function() {
        var confirmPopup = $ionicPopup.confirm({
            title: 'Borrar actividad',
            template: '¿Está seguro que desea borrar la actividad?'
        });

        confirmPopup.then(function(res) {
            if (res) {
                resetState();
            } else {
                
            }
        });
    }

    function resetState() {
        $scope.sunRotate['rotate'] = false;
        $scope.showDetectarButton = true;
        $scope.startActivity = false;
        $scope.show0Seconds = true;
        $scope.show0Minutes = true;
        $scope.showHours = false;
        $scope.showResume = false;
        $scope.intervalCountSeconds = 0;
        $scope.intervalCountMinutes = 0;
        $scope.intervalCountHours = 0;
        $scope.calories = 0;
        $scope.showEmptyStars = [true, true, true, true, true];
        $scope.rating = undefined;
        accumulatedMilliseconds = 0;
        $scope.likeDislike.like = "notSelected";
        $scope.likeDislike.dislike = "notSelected";
        $interval.cancel(interval);
    }

    $scope.disableSwipe = function() {
        $ionicSlideBoxDelegate.enableSlide(false);
    };

    $scope.$on('$ionicView.enter', function() {
        $ionicSideMenuDelegate.canDragContent(false);
        if (sharedLocation.getLocation() !== null) {
            refreshMeMarker(sharedLocation.getLocation());
        }
        updateList(recreovias);
    });

    $scope.$on('$ionicView.leave', function() {
        $ionicSideMenuDelegate.canDragContent(true);
    });

    $scope.start = function() {
        if (localStorage.getItem('additionalInfo') === null) {
            sharedModal.getModal().modal.show();
        } else {
            if (!$scope.startActivity) {
                $scope.startActivity = true;
                fechaInicio = new Date();
                interval = $interval(timer, 1000);
            }
        }
    }

    function timer() {
        var now = new Date();
        var milliseconds = now.getTime() - fechaInicio.getTime() + accumulatedMilliseconds;
        $scope.intervalCountHours = parseInt(milliseconds / 3600000);
        $scope.intervalCountMinutes = parseInt(milliseconds / 60000 - $scope.intervalCountHours * 60);
        $scope.intervalCountSeconds = parseInt(milliseconds / 1000 - $scope.intervalCountMinutes * 60 - $scope.intervalCountHours * 3600);
        if ($scope.intervalCountSeconds < 10) {
            $scope.show0Seconds = true;
        } else {
            $scope.show0Seconds = false;
        }
        if ($scope.intervalCountMinutes < 10) {
            $scope.show0Minutes = true;
        } else {
            $scope.show0Minutes = false;
        }
        if ($scope.intervalCountHours > 0) {
            $scope.showHours = true;
        } else {
            $scope.showHours = false;
        }
        calculateCalories();
    }

    $scope.pause = function() {
        $interval.cancel(interval);
        $scope.showResume = true;
        var now = new Date();
        accumulatedMilliseconds = now.getTime() - fechaInicio.getTime() + accumulatedMilliseconds;
    }

    $scope.resume = function() {
        fechaInicio = new Date();
        $scope.showResume = false;
        interval = $interval(timer, 1000);
    }

    $scope.stop = function() {
        $scope.pause();
        $scope.showConfirm();
    }

    function calculateCalories() {
        var now = new Date();
        var milliseconds = now.getTime() - fechaInicio.getTime() + accumulatedMilliseconds;
        $scope.calories = parseInt(milliseconds / 10000);
    }

    $scope.showConfirm = function() {
        $scope.confirmPopup = $ionicPopup.confirm({
            title: 'Califica esta actividad para continuar',
            templateUrl: 'templates/finalizarPopup.html',
            scope: $scope,
            buttons: [{
                text: 'Cancelar',
                onTap: function() {
                    return "Cancelar";
                }
            }]
        });

        $scope.confirmPopup.then(function(res) {
            /*if(res !== undefined) {
              console.log('You are sure');
              resetState();
              $ionicSlideBoxDelegate.slide(0);
            } else {*/
            if (res == "Cancelar") {
                $scope.resume();
                
            } else {
                resetState();
            }
        });
    };

    $scope.$on('location:updated', function(event, data) {
        refreshMeMarker(data);
    });

    function refreshMeMarker(latLng) {
        if (meMarker == null) {
            var me = L.icon({
                iconUrl: 'img/me.svg',
                shadowUrl: 'img/me_shadow.svg',

                iconSize: [18, 18], // size of the icon
                shadowSize: [18, 18], // size of the shadow
                iconAnchor: [9, 9], // point of the icon which will correspond to marker's location
                shadowAnchor: [8, 8] // the same for the shadow
            });
            meMarker = L.marker(latLng, {
                icon: me
            }).addTo(mapActividad);
            mapActividad.setView(latLng, 12);
        } else {
            meMarker.setLatLng(latLng);
        }
    }
})

.controller('HistorialCtrl', function($scope, $state, $ionicModal, sharedModal, $http, $filter) {
    $scope.actividades = [];
    $scope.searchRecreovias = "";
    var actividadesAll = [];
    $scope.goToNewActivity = function() {
        if (localStorage.getItem('additionalInfo') === null) {
            sharedModal.getModal().modal.show();
        } else {
            $state.go('app.historialNueva');
        }
    }

    $scope.$on('$ionicView.enter', function() {
        $scope.searchRecreovias = "";
        var userProfile = JSON.parse(localStorage.getItem('profile')) || {};
        NProgress.start();
        $http({
                method: 'GET',
                url: encodeURI(urlServer + 'activity?id=' + userProfile.user_id)
            })
            .success(function(data, status, headers, config) {
                var ordered = $filter('orderBy')(angular.fromJson(data), function(actividad) {
                    //var date = $filter('date')(new Date(actividad.fecha),'EEEE MMM d,yyyy');
                    var date = new Date(actividad.fecha);
                    return -(date.getFullYear() + date.getMonth() + date.getDate());
                });
                $scope.actividades = ordered;
                actividadesAll = angular.copy(ordered);
                NProgress.done();
            })
            .error(function(data, status, headers, config) {
                
            });
    });

    $scope.filter = function() {
        var actividadesFiltered = [];
        angular.forEach(actividadesAll, function(actividad) {
            //actividad, parque, fecha
            if (accentFold(actividad.fecha.toLowerCase()).includes(accentFold($scope.searchRecreovias.toLowerCase())) || accentFold(actividad.parque.toLowerCase()).includes(accentFold($scope.searchRecreovias.toLowerCase())) || accentFold($filter('actividadShort')(actividad.actividad).toLowerCase()).includes(accentFold($scope.searchRecreovias.toLowerCase()))) {
                actividadesFiltered.push(actividad);
            }
        });
        $scope.actividades = actividadesFiltered;
    }

    function accentFold(inStr) {
        return inStr.replace(/([àáâãäå])|([ç])|([èéêë])|([ìíîï])|([ñ])|([òóôõöø])|([ß])|([ùúûü])|([ÿ])|([æ])/g, function(str, a, c, e, i, n, o, s, u, y, ae) {
            if (a) return 'a';
            else if (c) return 'c';
            else if (e) return 'e';
            else if (i) return 'i';
            else if (n) return 'n';
            else if (o) return 'o';
            else if (s) return 's';
            else if (u) return 'u';
            else if (y) return 'y';
            else if (ae) return 'ae';
        });
    }
})

.controller('HistorialNuevaCtrl', function($scope, $ionicPopup, sharedLocation, $filter, $http, $state) {
    var mapHistorialNueva;
    var meMarker;
    $scope.likeDislike = {
        like: "notSelected",
        dislike: "notSelected"
    };
    $scope.nombresRecreovias = recreovias;
    $scope.nombreActividades = actividades;

    $scope.formData = {
        parque: "0",
        actividad: "0",
        horario: "fs",
        fecha: new Date()
    };

    function updateList(recreoviasFiltered) {
        angular.forEach($scope.markers, function(marker) {
            mapHistorialNueva.removeLayer(marker);
        });
        $scope.markers = [];
        var now = moment();

        angular.forEach(recreoviasFiltered, function(recreovia, key) {
            var active = false;
            var hours = now.hours();
            if (now.minutes() > 30) {
                hours += 0.5;
            }
            var popupContent = '<div class="popupRecreovia"><div class="nombre">' + recreovia.nombre + '</div><p class="direccion">' + recreovia.direccion + '</p><hr>';
            angular.forEach(recreovia.horarios, function(horario) {
                if (hours >= horario.s && hours < horario.e) {
                    if (horario.dia == "D" && now.day() == 0) {
                        active = true;
                    } else if (horario.dia == "M" && (now.day() == 2 || now.day() == 4)) {
                        active = true;
                    } else if (horario.dia == "I" && (now.day() == 3 || now.day() == 5)) {
                        active = true;
                    } else if (horario.dia == "S" && now.day() == 6) {
                        active = true;
                    }
                }
                popupContent += '<div class="tableContainer"><table><tr><td>';
                if (horario.dia == "M") {
                    popupContent += "Martes y Jueves";
                } else if (horario.dia == "I") {
                    popupContent += "Miércoles y Viernes";
                } else if (horario.dia == "S") {
                    popupContent += "Sábado";
                } else if (horario.dia == "D") {
                    popupContent += "Domingos y Festivos";
                }
                popupContent += '</td><td>';
                //horario
                popupContent += $filter('horarioRecreovia')(horario.s) + " - " + $filter('horarioRecreovia')(horario.e);
                popupContent += '</td></tr></table></div>';
            });
            popupContent += '</div>';
            if (!active) {
                var marker = L.marker([recreovia.lat, recreovia.lng], {
                    icon: recreoviaInactive
                }).addTo(mapHistorialNueva);
                marker.bindPopup(popupContent);
                marker.on('click', onPopupClick);
                marker._icon.id = key;
                $scope.markers.push(marker);
            } else {
                var marker = L.marker([recreovia.lat, recreovia.lng], {
                    icon: recreoviaActive
                }).addTo(mapHistorialNueva);
                marker.bindPopup(popupContent);
                marker.on('click', onPopupClick);
                marker._icon.id = key;
                $scope.markers.push(marker);
            }
            if (active) {
                recreovia.active = "active";
            } else {
                recreovia.active = "inactive";
            }
        });
    }

    function onPopupClick(e) {
        $scope.$apply(function() {
            $scope.formData.parque = e.target._icon.id;
        });
    }

    $scope.loadMap = function() {

        mapHistorialNueva = L.map('mapHistorialNueva', {
            zoomControl: true
        }).setView([4.657344, -74.103621], 12);
        L.tileLayer('https://api.mapbox.com/styles/v1/cps543/civx5juan00172kr32fijky9r/tiles/256/{z}/{x}/{y}?access_token=pk.eyJ1IjoiY3BzNTQzIiwiYSI6ImNpcWIzanpoaTAzbG5maGtrNHp3d3FlN2wifQ.Y5HWNIY5yTomYqtqXOyiHQ', {
            maxZoom: 19
        }).addTo(mapHistorialNueva);

        angular.forEach($scope.listaRecreovias, function(recreovia, key) {
            if (key == 0) {
                L.marker([recreovia.lat, recreovia.lng], {
                    icon: recreoviaInactive
                }).addTo(mapHistorialNueva);
            } else {
                L.marker([recreovia.lat, recreovia.lng], {
                    icon: recreoviaActive
                }).addTo(mapHistorialNueva);
            }
        });
    }

    $scope.save = function() {
        if ($scope.formData.fecha == undefined) {
            return $ionicPopup.alert({
                title: 'Error al registrarse',
                template: 'La fecha no es válida'
            });
        }
        if ($scope.formData.duracion == undefined) {
            return $ionicPopup.alert({
                title: 'Error al registrarse',
                template: 'La duración no es válida'
            });
        }
        $scope.showConfirm();
    }

    $scope.clickLike = function() {
        $scope.likeDislike.like = "selected";
        $scope.likeDislike.dislike = "notSelected";
        sendActividad(true);
        setTimeout(function() {
            resetState();
            $scope.confirmPopup.close();
        }, 100);

    }
    $scope.clickDislike = function() {

        $scope.likeDislike.dislike = "selected";
        $scope.likeDislike.like = "notSelected";
        sendActividad(false);
        setTimeout(function() {
            resetState();
            $scope.confirmPopup.close();
        }, 100);
    }

    $scope.showConfirm = function() {
        $scope.confirmPopup = $ionicPopup.confirm({
            title: 'Califica esta actividad para continuar',
            templateUrl: 'templates/finalizarPopup.html',
            scope: $scope,
            buttons: [{
                text: 'Cancelar',
                onTap: function() {
                    return "Cancelar";
                }
            }]
        });

        $scope.confirmPopup.then(function(res) {
            /*if(res !== undefined) {
              console.log('You are sure');
              resetState();
              $ionicSlideBoxDelegate.slide(0);
            } else {*/
            if (res == "Cancelar") {
                
            }
        });
    };

    function sendActividad(calificacion) {
        var userProfile = JSON.parse(localStorage.getItem('profile')) || {};
        var body = {
            "id_auth": userProfile.user_id,
            "parque": recreovias[parseInt($scope.formData.parque)].nombre,
            "actividad": actividades[parseInt($scope.formData.actividad)].short,
            "horario": $scope.formData.horario,
            "duracion": $scope.formData.duracion + ":00",
            "calorias": $scope.formData.duracion * 60 / 10,
            "calificacion": calificacion,
            "fecha": $filter('date')($scope.formData.fecha, "EEEE MMM d,yyyy")
        }
        $http({
                method: 'POST',
                url: encodeURI(urlServer + 'activity'),
                headers: {
                    "content-type": "application/json"
                },
                data: body
            })
            .success(function(data, status, headers, config) {
                $state.go('app.historial');
            })
            .error(function(data, status, headers, config) {
                
            });
    }

    function resetState() {
        $scope.likeDislike.like = "notSelected";
        $scope.likeDislike.dislike = "notSelected";
        $scope.formData = {
            parque: "0",
            actividad: "0",
            horario: "fs",
            fecha: new Date(),
            duracion: undefined
        };
    }

    $scope.$on('location:updated', function(event, data) {
        refreshMeMarker(data);
    });

    $scope.$on('$ionicView.enter', function() {
        if (sharedLocation.getLocation() !== null) {
            refreshMeMarker(sharedLocation.getLocation());
        }
        updateList(recreovias);
    });

    function refreshMeMarker(latLng) {
        if (meMarker == null) {
            var me = L.icon({
                iconUrl: 'img/me.svg',
                shadowUrl: 'img/me_shadow.svg',

                iconSize: [18, 18], // size of the icon
                shadowSize: [18, 18], // size of the shadow
                iconAnchor: [9, 9], // point of the icon which will correspond to marker's location
                shadowAnchor: [8, 8] // the same for the shadow
            });
            meMarker = L.marker(latLng, {
                icon: me
            }).addTo(mapHistorialNueva);
            mapHistorialNueva.setView(latLng, 12);
        } else {
            meMarker.setLatLng(latLng);
        }
    }
})

.controller('PerfilCtrl', function($scope, $http, $filter) {
    var nombres = [];

    var chart = new Chartist.Line('.ct-chart', {
        series: [{
            name: 'peso',
            data: [{
                x: new Date(143134652600),
                y: 53
            }, {
                x: new Date(143334652600),
                y: 40
            }, {
                x: new Date(143354652600),
                y: 45
            }, {
                x: new Date(143356652600),
                y: 41
            }, {
                x: new Date(143366652600),
                y: 40
            }, {
                x: new Date(143368652600),
                y: 38
            }, {
                x: new Date(143378652600),
                y: 34
            }, {
                x: new Date(143568652600),
                y: 32
            }, {
                x: new Date(143569652600),
                y: 18
            }, {
                x: new Date(143579652600),
                y: 11
            }]
        }]
    }, {
        axisX: {
            type: Chartist.FixedScaleAxis,
            divisor: 5,
            labelInterpolationFnc: function(value) {
                return moment(value).format('MMM D');
            }
        },
        axisY: {
            onlyInteger: true,
            low: 0
        },
        series: {
            peso: {
                showPoint: false
            }
        }
    });
})

.controller('RecreoviasCtrl', function($scope, $ionicSlideBoxDelegate, $ionicSideMenuDelegate, sharedLocation, $filter) {
    var mapRecreovias;
    var meMarker;
    var lat;
    var lng;

    $scope.recreovias = recreovias;
    $scope.markers = [];
    $scope.searchRecreovias = "";

    $scope.goToMap = function() {
        //$ionicSlideBoxDelegate.slide(0);
        document.getElementById("map").style.zIndex = 1;
        document.getElementById("listContainter").style.zIndex = -1;
        $ionicSideMenuDelegate.canDragContent(false);
    }

    $scope.goToList = function() {
        //$ionicSlideBoxDelegate.slide(1);
        document.getElementById("map").style.zIndex = -1;
        document.getElementById("listContainter").style.zIndex = 1;
        $ionicSideMenuDelegate.canDragContent(true);
    }

    $scope.loadMap = function() {

        mapRecreovias = L.map('map', {
            zoomControl: true
        }).setView([4.657344, -74.103621], 12);
        L.tileLayer('https://api.mapbox.com/styles/v1/cps543/civx5juan00172kr32fijky9r/tiles/256/{z}/{x}/{y}?access_token=pk.eyJ1IjoiY3BzNTQzIiwiYSI6ImNpcWIzanpoaTAzbG5maGtrNHp3d3FlN2wifQ.Y5HWNIY5yTomYqtqXOyiHQ', {
            maxZoom: 19
        }).addTo(mapRecreovias);
    }

    $scope.disableSwipe = function() {
        $ionicSlideBoxDelegate.enableSlide(false);
    };

    $scope.$on('$ionicView.enter', function() {
        if ($ionicSlideBoxDelegate.currentIndex() == 0) {
            $ionicSideMenuDelegate.canDragContent(false);
        }
        $scope.filter();
        if (sharedLocation.getLocation() !== null) {
            refreshMeMarker(sharedLocation.getLocation());
        }
    });

    $scope.$on('$ionicView.leave', function() {
        $ionicSideMenuDelegate.canDragContent(true);
    });

    $scope.filter = function() {
        var recreoviasFiltered = [];
        angular.forEach(recreovias, function(recreovia) {
            if (accentFold(recreovia.nombre.toLowerCase()).includes(accentFold($scope.searchRecreovias.toLowerCase()))) {
                recreoviasFiltered.push(recreovia);
            } else {
                var pushed = false;
                angular.forEach(recreovia.horarios, function(horario) {
                    var dia = "";
                    if (horario.dia == "M") {
                        dia = "Martes y Jueves";
                    } else if (horario.dia == "I") {
                        dia = "Miércoles y Viernes";
                    } else if (horario.dia == "S") {
                        dia = "Sábado";
                    } else if (horario.dia == "D") {
                        dia = "Domingos y Festivos";
                    }
                    if (accentFold(dia).toLowerCase().includes(accentFold($scope.searchRecreovias.toLowerCase())) && !pushed) {
                        pushed = true;
                        recreoviasFiltered.push(recreovia);
                    } else if ((accentFold($filter('horarioRecreovia')(horario.s).toLowerCase()).includes(accentFold($scope.searchRecreovias.toLowerCase())) || accentFold($filter('horarioRecreovia')(horario.e).toLowerCase()).includes(accentFold($scope.searchRecreovias.toLowerCase()))) && !pushed) {
                        pushed = true;
                        recreoviasFiltered.push(recreovia);
                    }
                });
            }
        });
        updateList(recreoviasFiltered);
    }

    function accentFold(inStr) {
        return inStr.replace(/([àáâãäå])|([ç])|([èéêë])|([ìíîï])|([ñ])|([òóôõöø])|([ß])|([ùúûü])|([ÿ])|([æ])/g, function(str, a, c, e, i, n, o, s, u, y, ae) {
            if (a) return 'a';
            else if (c) return 'c';
            else if (e) return 'e';
            else if (i) return 'i';
            else if (n) return 'n';
            else if (o) return 'o';
            else if (s) return 's';
            else if (u) return 'u';
            else if (y) return 'y';
            else if (ae) return 'ae';
        });
    }

    function updateList(recreoviasFiltered) {
        var latlngbounds = new L.latLngBounds();
        if (meMarker !== undefined) {
            latlngbounds.extend(meMarker.getLatLng());
        }
        angular.forEach($scope.markers, function(marker) {
            mapRecreovias.removeLayer(marker);
        });
        $scope.markers = [];
        var now = moment();

        angular.forEach(recreoviasFiltered, function(recreovia, key) {
            var active = false;
            var hours = now.hours();
            if (now.minutes() > 30) {
                hours += 0.5;
            }
            var popupContent = '<div class="popupRecreovia"><div class="nombre">' + recreovia.nombre + '</div><p class="direccion">' + recreovia.direccion + '</p><hr>';
            angular.forEach(recreovia.horarios, function(horario) {
                if (hours >= horario.s && hours < horario.e) {
                    if (horario.dia == "D" && now.day() == 0) {
                        active = true;
                    } else if (horario.dia == "M" && (now.day() == 2 || now.day() == 4)) {
                        active = true;
                    } else if (horario.dia == "I" && (now.day() == 3 || now.day() == 5)) {
                        active = true;
                    } else if (horario.dia == "S" && now.day() == 6) {
                        active = true;
                    }
                }
                popupContent += '<div class="tableContainer"><table><tr><td>';
                if (horario.dia == "M") {
                    popupContent += "Martes y Jueves";
                } else if (horario.dia == "I") {
                    popupContent += "Miércoles y Viernes";
                } else if (horario.dia == "S") {
                    popupContent += "Sábado";
                } else if (horario.dia == "D") {
                    popupContent += "Domingos y Festivos";
                }
                popupContent += '</td><td>';
                //horario
                popupContent += $filter('horarioRecreovia')(horario.s) + " - " + $filter('horarioRecreovia')(horario.e);
                popupContent += '</td></tr></table></div>';
            });
            popupContent += '</div>';
            if (!active) {
                var marker = L.marker([recreovia.lat, recreovia.lng], {
                    icon: recreoviaInactive
                }).addTo(mapRecreovias);
                marker.bindPopup(popupContent);
                $scope.markers.push(marker);
                latlngbounds.extend(marker.getLatLng());
            } else {
                var marker = L.marker([recreovia.lat, recreovia.lng], {
                    icon: recreoviaActive
                }).addTo(mapRecreovias);
                marker.bindPopup(popupContent);
                $scope.markers.push(marker);
                latlngbounds.extend(marker.getLatLng());
            }
            if (active) {
                recreovia.active = "active";
            } else {
                recreovia.active = "inactive";
            }
        });
        if ($scope.markers.length == 1) {
            $scope.markers[0].openPopup();
        }
        mapRecreovias.fitBounds(latlngbounds, {
            padding: [50, 50]
        });
        $scope.$apply(function() {
            $scope.recreovias = recreoviasFiltered;
        });
    }

    $scope.$on('location:updated', function(event, data) {
        refreshMeMarker(data);
    });

    $scope.$on('showRecreovia', function(event, data) {
        console.lgo("showRecreovia");
    });

    function refreshMeMarker(latLng) {
        if (meMarker == null) {
            var me = L.icon({
                iconUrl: 'img/me.svg',
                shadowUrl: 'img/me_shadow.svg',

                iconSize: [18, 18], // size of the icon
                shadowSize: [18, 18], // size of the shadow
                iconAnchor: [9, 9], // point of the icon which will correspond to marker's location
                shadowAnchor: [8, 8] // the same for the shadow
            });
            meMarker = L.marker(latLng, {
                icon: me
            }).addTo(mapRecreovias);
            mapRecreovias.setView(latLng, 12);
        } else {
            meMarker.setLatLng(latLng);
        }
    }

    $scope.mostrarEnMapa = function(index) {
        $scope.goToMap();
        setTimeout(function() {
            var latlngbounds = new L.latLngBounds();
            if (meMarker !== undefined) {
                latlngbounds.extend(meMarker.getLatLng());
            }
            latlngbounds.extend($scope.markers[index].getLatLng());
            mapRecreovias.fitBounds(latlngbounds, {
                padding: [50, 50]
            });
            $scope.markers[index].openPopup();
        }, 0);

    }
})

.controller('RecomendacionesCtrl', function($scope, sharedModal, $ionicPopup, $http, $rootScope, $state) {
    $scope.recomendaciones = [];

    $scope.getRecomendaciones = function() {
        var userProfile = JSON.parse(localStorage.getItem('profile')) || {};
        NProgress.start();
        $http({
                method: 'GET',
                url: encodeURI(urlServer + 'recomendacion?id=' + userProfile.user_id)
            })
            .success(function(data, status, headers, config) {
                procesarRespuesta(angular.fromJson(data));
                NProgress.done();
            })
            .error(function(data, status, headers, config) {

            });
    }

    function procesarRespuesta(data) {
        var recomendaciones = [];
        var first = -1;
        var second = -1;
        var now = new Date();
        var day = now.getDay(); //0 = domingo, 6 = sabado
        var dayP = day;
        if (day == 1) {
            dayP = 2;
        } else if (day == 0) {
            dayP = 7;
        }
        angular.forEach(data, function(recomendacionesDia, index) {
            //if (recomendacionesDia.actividades.length > 0) {
            var titulo;
            if (recomendacionesDia.dia == "M") {
                if (day == 2) {
                    titulo = "Hoy";
                    first = index;
                } else if (day == 1) {
                    second = index;
                    titulo = "Mañana";
                } else {
                    titulo = "Martes";
                }

            } else if (recomendacionesDia.dia == "I") {
                if (day == 3) {
                    titulo = "Hoy";
                    first = index;
                } else if (day == 2) {
                    titulo = "Mañana";
                    second = index;
                } else {
                    titulo = "Miércoles";
                }
            } else if (recomendacionesDia.dia == "J") {
                if (day == 4) {
                    titulo = "Hoy";
                    first = index;
                } else if (day == 3) {
                    titulo = "Mañana";
                    second = index;
                } else {
                    titulo = "Jueves";
                }
            } else if (recomendacionesDia.dia == "V") {
                if (day == 5) {
                    titulo = "Hoy";
                    first = index;
                } else if (day == 4) {
                    titulo = "Mañana";
                    second = index;
                } else {
                    titulo = "Viernes";
                }
            } else if (recomendacionesDia.dia == "S") {
                if (day == 6) {
                    titulo = "Hoy";
                    first = index;
                } else if (day == 5) {
                    titulo = "Mañana";
                    second = index;
                } else {
                    titulo = "Sábado";
                }
            } else {
                if (day == 0) {
                    titulo = "Hoy";
                    first = index;
                } else if (day == 6) {
                    recomendacion.titulo = "Mañana";
                    second = index;
                } else {
                    titulo = "Domingo";
                }
            }
            var dia = {
                titulo: titulo,
                actividades: []
            }
            angular.forEach(recomendacionesDia.actividades, function(actividadP) {
                var a = {
                    actividad: actividadP.actividad,
                    parque: actividadP.parque,
                    horario: actividadP.horario,
                    like: false,
                    dislike: false
                };
                dia.actividades.push(a);
            });
            recomendaciones.push(dia);
            //}
        });
        var recomendacionesOrganizadas = [];

        var move = dayP - 2;
        if (first == -1) {
            first = second;
        }
        angular.forEach(recomendaciones, function(recomendacion, index) {
            var indexP = index + first;
            if (indexP >= recomendaciones.length) {
                indexP = index + first - recomendaciones.length;
            }
            recomendacionesOrganizadas[index] = recomendaciones[indexP];
        });
        $scope.recomendaciones = recomendacionesOrganizadas;
    }
    

    $scope.mostrarParque = function(value) {
        $rootScope.$broadcast('location:updated', value);
        $state.go('app.recreovias');
    }

    $scope.refrescarRecomendaciones = function() {
        var userProfile = JSON.parse(localStorage.getItem('profile')) || {};
        var actividadesLikeDislike = [];
        angular.forEach($scope.recomendaciones, function(recomendacion) {
            angular.forEach(recomendacion.actividades, function(actividad) {
                if (actividad.like || actividad.dislike) {
                    var actividadConDia = actividad;
                    actividadConDia.dia = recomendacion.titulo;
                    actividadesLikeDislike.push(actividad);
                }
            });
        });
        if (actividadesLikeDislike.length == 0) {
            return $ionicPopup.alert({
                title: 'Calificar Recomendaciones',
                template: 'Califica por lo menos una recomendación para refrescar la lista'
            });
        }
        var body = {
            actividades: actividadesLikeDislike
        };

        $http({
                method: 'POST',
                url: encodeURI(urlServer + 'recomendacion?id=' + userProfile.user_id),
                headers: {
                    "content-type": "application/json"
                },
                data: body
            })
            .success(function(data, status, headers, config) {
                NProgress.start();
                $http({
                        method: 'GET',
                        url: encodeURI(urlServer + 'recomendacion?id=' + userProfile.user_id)
                    })
                    .success(function(data, status, headers, config) {
                        procesarRespuesta(angular.fromJson(data));
                        NProgress.done();

                    })
                    .error(function(data, status, headers, config) {
                        
                    });
            })
            .error(function(data, status, headers, config) {
                
            });

    }

    $scope.clickLike = function(indexActividad, indexRecomendacion) {
        if (!$scope.recomendaciones[indexRecomendacion].actividades[indexActividad].like) {
            $scope.recomendaciones[indexRecomendacion].actividades[indexActividad].like = true;
            $scope.recomendaciones[indexRecomendacion].actividades[indexActividad].dislike = false;
        } else {
            $scope.recomendaciones[indexRecomendacion].actividades[indexActividad].like = false;
            $scope.recomendaciones[indexRecomendacion].actividades[indexActividad].dislike = false;
        }
    }

    $scope.clickDislike = function(indexActividad, indexRecomendacion) {
        if (!$scope.recomendaciones[indexRecomendacion].actividades[indexActividad].dislike) {
            $scope.recomendaciones[indexRecomendacion].actividades[indexActividad].like = false;
            $scope.recomendaciones[indexRecomendacion].actividades[indexActividad].dislike = true;
        } else {
            $scope.recomendaciones[indexRecomendacion].actividades[indexActividad].like = false;
            $scope.recomendaciones[indexRecomendacion].actividades[indexActividad].dislike = false;
        }
    }

    $scope.$on('$ionicView.enter', function() {
        if (localStorage.getItem('additionalInfo') === null) {
            sharedModal.getModal().modal.show();
        }
        $scope.getRecomendaciones();
        /*$ionicPopup.alert({
            title: '¿Aceptas las sugerencias?',
            template: 'Por cada sugerencia'
        });*/
    });

})

.filter('horarioRecreovia', function() {
    return function(number) {
        var response = "";
        var numberAM_FM = Math.floor(number);
        if (number > 12) {
            numberAM_FM = numberAM_FM - 12;
        }
        if (numberAM_FM < 10) {
            response += "0";
        }
        response += numberAM_FM + ":";
        var decimal = number - Math.floor(number);
        var minutes = Math.floor(decimal * 60);
        if (minutes < 10) {
            response += "0";
        }
        response += minutes;
        if (number <= 12) {
            response += " am";
        } else {
            response += " pm";
        }
        return response;
    }
})

.filter('actividadShort', function() {
    return function(short) {
        var response = "";
        angular.forEach(actividades, function(actividad) {
            if (actividad.short.toUpperCase() == short.toUpperCase()) {
                response = actividad.nombre;
            }
        });
        return response;
    }
});