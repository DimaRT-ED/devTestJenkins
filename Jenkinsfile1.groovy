pipeline {
    agent { label 'worker' }
    parameters {
        string (name: 'StrPARAM' , defaultValue: 'StrPARAM-def-value', description: 'Stam string')
        choice (name: 'ChPARAM', choices: [ 'ch-1', 'ch-2', 'ch-3'])
    }
    environment {
        OWNER = 'Plony'
        PROJECT = 'Jenk_Pipelines'
    }
    options {
        timestamps()
        timeout(time: 5, unit: 'MINUTES')
    }
    triggers {
        cron(* * * * *)
    }
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
                    echo " StrPARAM: ${StrPARAM} "
                    echo " OWNER: $OWNER "
                '''
            }
        }
/*
        stage('EXAMPLE') {
            steps {
                script {
                    echo " StrPARAM: ${params.StrPARAM} "
                    echo " OWNER: ${env.OWNER} "
                }
                
            }
        }
        */
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
