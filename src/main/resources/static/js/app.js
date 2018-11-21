var app = angular.module('app', [
    'ui.router',
    'ngAlerts',
    'ngMaterial',
    'ui.utils.masks'
]);

app.config(['ngAlertsProvider', function (ngAlertsProvider) {
    // Global empty list text.
    ngAlertsProvider.options.emptyListText = 'Vazio';

    // The queue timeout for new alerts.
    ngAlertsProvider.options.queueTimeout = 5000;

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

app.controller('ResponsavelController', function ($rootScope, $scope, $http, $state, AlertMessage, $window) {
    $rootScope.isLoading = false;
    $scope.responsaveis = [];
    $scope.getAll = function () {
        $rootScope.isLoading = true;
        $http.get('/api/responsaveis-alunos').then(function successCallback(response) {
            $scope.responsaveis = response.data;
            $rootScope.isLoading = false;
        }, function errorCallback(response) {
            $rootScope.isLoading = false;
            $scope.responsaveis = [];
            AlertMessage.show('danger', "Erro ao pesquisar os responsaveis");
        })
    };
    $scope.delete = function (responsavel) {
        if ($window.confirm('Tem certeza que deseja deletar responsavel mae= ' + responsavel.responsavelMae.name + '?')) {
            $rootScope.isLoading = true;
            $http.delete('/api/responsaveis-alunos/' + responsavel.id).then(function successCallback(response) {
                $scope.getAll();
                AlertMessage.show('success', 'Responsavel Aluno deletado com sucesso');
                $rootScope.isLoading = false;
            }, function errorCallback(response) {
                $rootScope.isLoading = false;
                AlertMessage.show('danger', 'Erro ao deletar responsavel aluno');
            })
        }
    };
    $scope.new = function () {
        $state.go('responsaveis-new');
    };
    $scope.getAll();
});

app.controller('FormResponsavelController', function ($rootScope, $scope, $http, $state, AlertMessage) {
    $scope.model = {};
    $scope.cadastrarPai = false;
    $scope.states = ['AC','AL','AM','AP','BA','CE','DF','ES','GO','MA',
        'MG','MS','MT','PA','PB','PE','PI','PR','RJ','RN','RO','RR',
        'RS','SC','SE','SP','TO'];
    $scope.submitForm = function () {
        if(!$scope.cadastrarPai) {
            $scope.model.responsavelPai = null;
            $scope.model.responsavelMae.dddPhoneNumber = $scope.extractDddPhoneNumber($scope.model.responsavelMae.phone);
            $scope.model.responsavelMae.phoneNumber = $scope.extractPhoneNumber($scope.model.responsavelMae.phone);
        } else {
            $scope.model.responsavelPai.dddPhoneNumber = $scope.extractDddPhoneNumber($scope.model.responsavelPai.phone);
            $scope.model.responsavelPai.phoneNumber = $scope.extractPhoneNumber($scope.model.responsavelPai.phone);
            $scope.model.responsavelMae.dddPhoneNumber = $scope.extractDddPhoneNumber($scope.model.responsavelMae.phone);
            $scope.model.responsavelMae.phoneNumber = $scope.extractPhoneNumber($scope.model.responsavelMae.phone);
        }
        console.log($scope.model);
        $http.post('/api/responsaveis-alunos', $scope.model).then(function successCallback(response) {
            AlertMessage.show('success', 'Responsavel Aluno salvo com sucesso!');
            $state.go('responsaveis');
        }, function errorCallback(response) {
            AlertMessage.show('danger', "Erro ao salvar Responsavel Aluno. " + response.data.message);
        });
    };
    $scope.extractDddPhoneNumber = function(phone) {
        if(phone) {
            return phone.substr(0, 2);
        } else {
            return null;
        }
    };
    $scope.extractPhoneNumber = function(phone) {
        if(phone) {
            return phone.substr(2, phone.length);
        } else {
            return null;
        }
    };
    $scope.clearForm = function () {
        $scope.model = {};
    };
});

app.controller('AlunoController', function ($rootScope, $scope, $http, $state, AlertMessage, $window) {
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
            AlertMessage.show('danger', 'Erro ao pesquisar os alunos');
        })
    };
    $scope.delete = function (aluno) {
        console.log(aluno);
        if ($window.confirm('Tem certeza que deseja deletar ' + aluno.name + '?')) {
            $rootScope.isLoading = true;
            $http.delete('/api/alunos/' + aluno.id).then(function successCallback(response) {
                $scope.getAll();
                AlertMessage.show('success', 'Aluno deletado com sucesso');
                $rootScope.isLoading = false;
            }, function errorCallback(response) {
                $rootScope.isLoading = false;
                AlertMessage.show('danger', 'Erro ao deletar aluno');
            })
        }
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
    $scope.submitForm = function () {
        console.log($scope.model);
        $http.post('/api/alunos', {
            name : $scope.model.name,
            birthdate : $scope.model.birthdate,
            idResponsavelAluno : $scope.model.idResponsavelAluno
        }).then(function successCallback(response) {
            AlertMessage.show('success', 'Aluno salvo com sucesso! Matriculado na turma= ' + response.data.turma.name);
            $state.go('alunos');
        }, function errorCallback(response) {
            AlertMessage.show('danger', "Erro ao salvar Aluno. " + response.data.message);
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