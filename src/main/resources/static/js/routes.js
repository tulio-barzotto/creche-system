angular.module('app').config(function ($stateProvider, $urlRouterProvider, $httpProvider) {

    $httpProvider.interceptors.push('authInterceptor');

    $urlRouterProvider.otherwise('/');

    $stateProvider
        .state('home', {
            url: '/',
            templateUrl: 'template/home.html',
            controller: 'HomeController'
        })
        .state('login', {
            url: '/login',
            templateUrl: 'template/login.html',
            controller: 'LoginController'
        })
        .state('turmas', {
            url: '/turmas',
            templateUrl: 'template/turma/turmas.html',
            controller: 'TurmaController'
        })
        .state('responsaveis', {
            url: '/responsaveis',
            templateUrl: 'template/responsavel/responsaveis.html',
            controller: 'ResponsavelController'
        })
        .state('responsaveis-new', {
            url: '/form-responsavel',
            templateUrl: 'template/responsavel/form-responsavel.html',
            controller: 'FormResponsavelController'
        })
        .state('alunos', {
            url: '/alunos',
            templateUrl: 'template/aluno/alunos.html',
            controller: 'AlunoController'
        });
});