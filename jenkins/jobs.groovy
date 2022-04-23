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
                    branch('develop')
                    remote {
                        credentials('jenkins')
                        url('https://github.com/OutsideEIP/OutsideServer.git')
                    }
                }
            }
            scriptPath('./jenkins/Jenkinsfile')
        }
    }
}