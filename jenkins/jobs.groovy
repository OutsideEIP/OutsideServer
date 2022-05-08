pipelineJob('Test Running docker') {
    definition {
        cps {
            script('''
                pipeline {
                    agent any
                    stages {
                        stage('Test') {
                            steps {
                                sh 'docker --version'
                            }
                        }
                        stage('Test2') {
                            steps {
                                sh 'docker run hello-world'
                            }
                        }
                    }
                }
            '''.stripIndent())
            sandbox()
        }
    }
}

job('Test Docker') {
    steps {
        shell('docker --version')
    }
}

pipelineJob('OutsideServer') {
    definition {
        cpsScm {
            scm {
                git {
                    branch('${JENKINS_SERVER_REPOSITORY_BRANCH}')
                    remote {
                        credentials('jenkins')
                        url('${JENKINS_SERVER_REPOSITORY_URL}')
                    }
                }
            }
            scriptPath('./jenkins/Jenkinsfile')
        }
    }
}