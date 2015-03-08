del bin\server\*.* /Q
del bin\client\*.* /Q
REM del tcp.jar
groovyc -cp .;%GROOVY_HOME%\embeddable\groovy-all-2.3.0.jar -d bin -j -Jsourcepath src src/Main.groovy
