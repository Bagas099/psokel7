pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    // Build Docker image
                    docker.build('my-app')
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    // Run tests inside Docker container
                    docker.image('my-app').inside {
                        sh 'npm test'
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    // Deploy to Kubernetes
                    sh 'kubectl apply -f k8s/deployment.yaml'
                }
            }
        }
    }
}
