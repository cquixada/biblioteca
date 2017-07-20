pipeline {
    agent { docker 'maven:3.3.3' }
    stages {
        stage('Build') {
            steps {
                bat 'mvn --version'
            }
        }
    }
}
