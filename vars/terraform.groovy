def call(){
pipeline{
    agent {
    label 'agent'}

    parameters {
        choice(name: 'ENV', choices: ['dev','prod'], description: 'Pick something')
    }
    stages{
        stage('terraform init'){
            steps{
                sh 'pwd; ls -ltr; terraform init -backend-config -var-file=env-${ENV}/backend.tfvars'
            }
        }
        stage('terraform apply'){
            steps{
                sh 'terraform apply -auto-approve -var-file=env-${ENV}/${ENV}.tfvars'
            }
        }
    }
}
}