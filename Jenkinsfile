pipeline {
   agent any
   stages {
	
		stage('Build QA') {
			parallel {
						stage('Build QA') {
				  steps {
					sh 'mvn clean install -DskipTests=true'
				  }
				}

				stage('chrome') {
				  steps {
					sh 'mvn test -Denv=qa -Dbrowser=chrome'
				  }
				}

			}
		}
		
		stage('Build Stage'){
			parallel{
						stage('Build Stage') {
				  steps {
					sh 'mvn clean install -DskipTests=true'
				  }
				}

				stage('chrome') {
				  steps {
					sh 'mvn test -Denv=stage -Dbrowser=chrome'
				  }
				}

				stage('firefox') {
				  steps {
					sh 'mvn test -Denv=stage -Dbrowser=firefox'
				  }
				}
			}
		}
		
		stage('Publish reports') {
			steps {
				script{
					publishHTML([
						allowMissing: false,
						alwaysLinkToLastBuild: false,
						keepAll: false,
						reportDir: 'build',
						reportFiles: 'TestExecutionReport.html',
						reportName: 'Extent HTML Report',
						reportTitles: ''])
				}
			}
		}
    }
	
	tools {
      maven "M3"
   }
   
}
