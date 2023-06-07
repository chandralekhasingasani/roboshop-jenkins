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
                sh 'terraform init -backend-config=env-dev/backend.tfvars'
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