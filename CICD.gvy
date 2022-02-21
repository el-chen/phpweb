pipeline {
    agent any
    stages {
        stage('build & push docker image') {
            steps {
              withDockerRegistry(credentialsId: 'dockerhub_cred', url: 'https://index.docker.io/v1/') {
                    sh script: 'cd  $WORKSPACE'
                    sh script: 'docker build --file Dockerfile --tag docker.io/elchen8923/phpweb:$BUILD_NUMBER .'
                    sh script: 'docker push docker.io/elchen8923/phpweb:$BUILD_NUMBER'
              }	
           }		
        }
        stage('deploy-QA') {
	         steps {
                    sh script: 'sudo ansible-playbook --inventory /etc/ansible/host $WORKSPACE/deploy/deploy.yml --extra-vars "env=qa build=$BUILD_NUMBER"'
           }		
        }
    }
}
