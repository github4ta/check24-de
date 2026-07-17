pipeline {
    agent any

  tools {
        jdk 'JDK_home'
        maven 'Maven'
    }

    environment {
        HEADLESS = 'true'
        BROWSER  = 'chrome'
    }

    stages {
          stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Run Tests') {
            steps {
               bat "mvn clean test -Dheadless=%HEADLESS% -Dbrowser=%BROWSER%"
            }
        }
    }

    post {
        always {
            allure includeProperties: false, jdk: 'JDK_home', results: [[path: 'allure-results']], commandline: 'allure_home'
        }
    }
}