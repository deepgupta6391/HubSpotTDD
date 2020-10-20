pipeline {
   agent any
   stages {
		stage('Running test on diff env'){
			parallel{
				stage('Test Run on QA') {
				 steps {
					sh 'mvn clean install -Denv="qa" -Dbrowser="chrome"'
				 }
				}
				stage('Test Run on Stage') {
				 steps {
					sh 'mvn clean install -Denv="stage"'
				 }
				}
			}
		}
		stage('final') {
         steps {
            sh 'echo "test execution is done"'
         }
		}
	}
      
	
	tools {
      maven "M3"
   }
   
}
