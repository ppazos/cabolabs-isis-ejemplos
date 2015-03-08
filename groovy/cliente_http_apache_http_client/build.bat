del bin\*.* /Q
groovyc -cp ".;%GROOVY_HOME%\embeddable\groovy-all-2.3.0.jar;.\lib\httpclient-4.4.jar;.\lib\httpcore-4.4.jar;.\lib\commons-logging-1.2.jar" -d bin -j -Jsourcepath src src/App.groovy