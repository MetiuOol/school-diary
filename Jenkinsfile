pipeline {
    agent any

    tools {
        maven 'maven-3.9'
        jdk   'jdk-17'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                APP_DIR=/opt/school-diary
                JAR_FILE=$(ls target/*.jar | head -n 1)

                echo "Kopiuję JAR do $APP_DIR"
                cp "$JAR_FILE" "$APP_DIR/school-diary.jar"

                echo "Restartuję usługę school-diary"
                sudo systemctl restart school-diary
                '''
            }
        }
    }
}