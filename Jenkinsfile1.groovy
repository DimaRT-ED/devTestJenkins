pipeline {
    agent { label 'worker' }

    stages {
        stage('Clean WS') {
            steps {
                sh 'ls'
                deleteDir()
            }
        }
        stage('Hello') {
            steps {
                sh 'echo "Hello World"'
                sh 'pwd'
            }
        }
        stage('Stage-2') {
            steps {
                sh'''
                    pwd
                    ls -la
                    git clone https://github.com/DimaRT-ED/JenkinsTest.git
                    ls -la
                '''
            }
        }
        stage('Stage-3') {
            steps {
                sh'''
                    ls -la
                    pwd
                '''
            }
        }
    }
    post {
        always {
            cleanWs()
            sh 'ls -la'
        }
        success {
            echo "success"
        }
        failure {
            echo "failure"
        }
    }
}
