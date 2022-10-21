pipeline {
    agent {label "dotnet"}
     parameters {
        choice(name: 'dotnetcore4', choices: ['main', 'master', 'dotnetcore4'], description: 'dotnet') 
        string(name: 'GOAL', defaultValue: 'package', description: 'our Goal') 
     }
     triggers {
        pollSCM('*/5 * * * *')
     }
    stages {
        stage('clone') {
            steps {
                 git url:'https://github.com/relakrishna/dotnetcore4.git',
                 branch: 'master'
            }
        }
        stage('build') {
            steps {
                 sh 'dotnet build dotnetcoresample.csproj'
            }
        }
        stage('package') {
            steps {
                 sh 'dotnet publish dotnetcoresample.csproj'
            }
        }
    }
    
}