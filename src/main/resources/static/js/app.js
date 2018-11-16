var app = angular.module('app', [
    'ui.router',
    'ngAlerts',
    'ngMaterial'
]);

app.config(['ngAlertsProvider', function (ngAlertsProvider) {
    // Global empty list text.
    ngAlertsProvider.options.emptyListText = 'Vazio';

    // The queue timeout for new alerts.
    ngAlertsProvider.options.queueTimeout = 3000;

    // The queue location (top||bottom, left||right).
    ngAlertsProvider.options.queueLocation = 'top right';
}]);

app.factory('AlertMessage', function (ngAlertsMngr) {
   return {
       show: function (type, message) {
           var obj = {
               msg: message,
               type: type
           };
           ngAlertsMngr.add(obj);
       }
   };
});

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

app.controller('LoginController', function ($rootScope, $scope, $http, $state, TokenStore, AlertMessage) {
    $rootScope.isLoading = true;
    $rootScope.showNavbar = function () {
        return typeof $rootScope.currentUser !== "undefined";
    };
    if ($rootScope.currentUser) {
        AlertMessage.show('danger', 'Usuário já está logado');
        $state.go('home');
    }

  $scope.login = function () {
      $rootScope.isLoading = true;
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
                  $rootScope.isLoading = false;
                  $state.go('home');
              });
          }
      }, function errorCallback(response) {
          $rootScope.isLoading = false;
          AlertMessage.show('danger', 'Usuario e/ou senha invalidos');
      })
  }
});

app.controller('TurmaController', function ($rootScope, $scope, $http, $state, AlertMessage) {
    $rootScope.isLoading = false;
    $scope.turmas = [];
    $scope.getAll = function () {
        $rootScope.isLoading = true;
        $http.get('/api/turmas').then(function successCallback(response) {
            $scope.turmas = response.data;
            $rootScope.isLoading = false;
        }, function errorCallback(response) {
            $rootScope.isLoading = false;
            $scope.turmas = [];
            AlertMessage.show('danger', 'Erro ao pesquisar as turmas');
        });
    };
    $scope.getAll();
});

app.controller('ResponsavelController', function ($rootScope, $scope, $http, $state, AlertMessage) {
    $rootScope.isLoading = false;
    $scope.responsaveis = [];
    $scope.getAll = function () {
        $rootScope.isLoading = true;
        $http.get('/api/responsaveis').then(function successCallback(response) {
            $scope.responsaveis = response.data;
            $rootScope.isLoading = false;
        }, function errorCallback(response) {
            $rootScope.isLoading = false;
            $scope.responsaveis = [];
            AlertMessage.show('danger', "Erro ao pesquisar os responsaveis");
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

app.controller('FormResponsavelController', function ($rootScope, $scope, $http, $state, AlertMessage) {
    $scope.responsavel = {};
    $scope.submitForm = function () {
        console.log('submit');
        //TODO
    };
    $scope.clearForm = function () {
        $scope.responsavel = {};
    };
});

app.controller('AlunoController', function ($rootScope, $scope, $http, $state, AlertMessage) {
    $rootScope.isLoading = false;
    $scope.alunos = [];
    $scope.getAll = function () {
        $rootScope.isLoading = true;
        $http.get('/api/alunos').then(function successCallback(response) {
            $scope.alunos = response.data;
            $rootScope.isLoading = false;
        }, function errorCallback(response) {
            $rootScope.isLoading = false;
            $scope.alunos = [];
            AlertMessage.show('danger', "Erro ao pesquisar os alunos");
        })
    };
    $scope.edit = function (aluno) {
        console.log('Editar= ' + aluno);
        //TODO
    };
    $scope.delete = function (aluno) {
        console.log('Remover= ' + aluno);
        //TODO
    };
    $scope.new = function () {
        $state.go('alunos-new');
    };
    $scope.getAll();
});

app.controller('FormAlunoController', function ($rootScope, $scope, $http, $state, AlertMessage) {
    $scope.model = {};
    $scope.vm = {};
    $scope.vm.responsaveis = [];
    // $scope.vm.turmas = [];
    $scope.submitForm = function () {
        console.log($scope.model);
        $http.post('/api/alunos', {
            name : $scope.model.name,
            birthdate : $scope.model.birthdate,
            idResponsavelAluno : $scope.model.idResponsavelAluno
        }).then(function successCallback(response) {
            AlertMessage.show('success', 'Aluno salvo com sucesso! Matriculado na turma= ' + response.data.turma.name);
        }, function errorCallback(response) {
            AlertMessage.show('danger', "Erro ao salvar Aluno.");
        });
    };
    $scope.clearForm = function () {
        $scope.responsavel = {};
    };
    $scope.loadInputs = function () {
        $rootScope.isLoading = true;
        $http.get('/api/responsaveis-alunos').then(function successCallback(response) {
            $scope.vm.responsaveis = response.data;
        }, function errorCallback(response) {
            $scope.vm.responsaveis = [];
            AlertMessage.show('danger', "Erro ao carregar os responsaveis");
        });
    };
    $scope.formatResponsaveis = function(mae, pai) {
        if(mae && pai) {
            return mae.name + ' e ' + pai.name;
        } else if (mae) {
            return mae.name;
        } else {
            return '';
        }
    };
    $scope.loadInputs();
});