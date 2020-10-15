pipeline {
   agent any
   

   stages {
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
