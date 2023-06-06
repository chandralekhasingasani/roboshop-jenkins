def call(){
pipeline{
    agent 'any'

    parameters {
        choice(name: 'ENV', choices: ['dev','prod'], description: 'Pick something')
    }
    stages{
        stage('terraform init'){
            steps{
                sh 'terraform -init -backend-config -var-file=env-${ENV}/backend.tfvars'
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