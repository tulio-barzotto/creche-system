var app = angular.module('app', [
    'ui.router',
    'ngAlerts'
]);

app.config(['ngAlertsProvider', function (ngAlertsProvider) {
    // Global empty list text.
    ngAlertsProvider.options.emptyListText = 'Vazio';

    // The queue timeout for new alerts.
    ngAlertsProvider.options.queueTimeout = 3000;

    // The queue location (top||bottom, left||right).
    ngAlertsProvider.options.queueLocation = 'top right';
}]);

app.factory('TokenStore', function ($window) {
  var storageKey = 'auth_token';
  return {
    save: function (token) {
      return $window.localStorage.setItem(storageKey, token);
    },
    get: function () {
      return $window.localStorage.getItem(storageKey);
    },
    clear: function () {
      return $window.localStorage.removeItem(storageKey);
    }
  };
});

app.factory('authInterceptor', function ($rootScope, $q, TokenStore) {
  return {
    request: function (config) {
      config.headers = config.headers || {};
      if (TokenStore.get()) {
        config.headers['X-Auth-Token'] = TokenStore.get();
      }
      return config;
    },
    response: function (response) {
      if (response.status === 401) {
        // handle the case where the user is not authenticated
      }
      return response || $q.when(response);
    }
  };
});

app.controller('HomeController', function ($rootScope, $scope, $state, $http, TokenStore) {
  if ($rootScope.currentUser === undefined) {
    if (TokenStore.get()) {
      $http.get("/api/user/current").then(function (response) {
        console.log('get current user...');
        $rootScope.currentUser = response.data;
      });
    } else {
      console.log('not authenticated');
      $state.go('login');
    }
  }

  $rootScope.logout = function () {
    TokenStore.clear();
    delete $rootScope.currentUser;
    $state.go('login');
  };

});

app.controller('LoginController', function ($rootScope, $scope, $http, $state, TokenStore, ngAlertsMngr) {
    $rootScope.showNavbar = function () {
        return typeof $rootScope.currentUser !== "undefined";
    };
    $rootScope.addAlert = function (message) {
        var obj = { msg: message,
                    type: 'danger'};
        ngAlertsMngr.add(obj);
    };
    if ($rootScope.currentUser) {
        console.log('already logged in');
        $state.go('home');
    }

  $scope.login = function () {
      console.log('login...');
      $http.post('/api/login', {
          username: $scope.username,
          password: $scope.password
      }).then(function successCallback(response) {
          var authToken = response.headers()['x-auth-token'];
          if (authToken) {
              TokenStore.save(authToken);
              //get current user
              return $http.get('/api/user/current').then(function successCallback(response) {
                  $rootScope.currentUser = response.data;
                  $state.go('home');
              });
          }
      }, function errorCallback(response) {
          $scope.addAlert("Usuario e/ou senha invalidos");
      })
  }
});

app.controller('TurmaController', function ($rootScope, $scope, $http, $state, ngAlertsMngr) {
    $scope.turmas = [];
    $rootScope.addAlert = function (message) {
        var obj = { msg: message,
            type: 'danger'};
        ngAlertsMngr.add(obj);
    };
    $scope.getAll = function () {
        $http.get('/api/turmas').then(function successCallback(response) {
            $scope.turmas = response.data;
        }, function errorCallback(response) {
            $scope.turmas = [];
            $scope.addAlert("Erro ao pesquisar as turmas");
        })
    };
    $scope.getAll();
});

app.controller('ResponsavelController', function ($rootScope, $scope, $http, $state, ngAlertsMngr) {
    $scope.responsaveis = [];
    $rootScope.addAlert = function (message) {
        var obj = { msg: message,
            type: 'danger'};
        ngAlertsMngr.add(obj);
    };
    $scope.getAll = function () {
        $http.get('/api/responsaveis').then(function successCallback(response) {
            $scope.responsaveis = response.data;
        }, function errorCallback(response) {
            $scope.responsaveis = [];
            $scope.addAlert("Erro ao pesquisar os responsaveis");
        })
    };
    $scope.edit = function (responsavel) {
        console.log('Editar= ' + responsavel);
        //TODO
    };
    $scope.delete = function (responsavel) {
        console.log('Remover= ' + responsavel);
        //TODO
    };
    $scope.new = function () {
        $state.go('responsaveis-new');
    };
    $scope.getAll();
});

app.controller('FormResponsavelController', function ($rootScope, $scope, $http, $state, ngAlertsMngr) {
    $scope.responsavel = {};
    $rootScope.addAlert = function (message) {
        var obj = { msg: message,
            type: 'danger'};
        ngAlertsMngr.add(obj);
    };
    $scope.submitForm = function () {
        console.log('submit');
        //TODO
    };
    $scope.clearForm = function () {
        $scope.responsavel = {};
    };
});