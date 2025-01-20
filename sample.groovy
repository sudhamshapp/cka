pipeline {
    agent any

    environment { 
        APP_NAME = 'my-app'
        versionn = '1.0.0'
    }

    options {
        timeout(time: 1, unit: 'HOURS')
        cleanWs()
    }

    stages {
        stage('Clone Repository') {
            steps { 
                git url: 'https://github.com/sudhamshapp/test-on-the-local.git'
            }
        }

        stage('Build') {
            steps { 
                script {
                    if (isUnix()) {
                        echo "Building on Unix"
                        // Add Unix-specific build commands here
                    } else {
                        echo "Building on Windows"
                        // Add Windows-specific build commands here
                    }
                }
            }
        }

        stage('Test') {
            steps {
                echo "Running tests"
                // Add test steps here
            }
        }

        stage('Deploy to Staging') {
            when {
                branch 'develop'
            }
            steps {
                echo "Deploying to staging environment"
                // Add staging deployment steps here
            }
        }

        stage('Deploy to Prod') {
            when {
                branch 'main'
            }
            steps {
                echo "Deploying to production environment"
                // Add production deployment steps here
            }
        }
    }

    post {
        success { 
            echo "Pipeline executed successfully"
        }

        failure { 
            echo "Pipeline failed"
        }

        always { 
            cleanWs()
        }
    }
}
