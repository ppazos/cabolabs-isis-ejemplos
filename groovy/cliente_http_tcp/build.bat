del bin\*.* /Q
groovyc -cp .;%GROOVY_HOME%\embeddable\groovy-all-2.3.0.jar -d bin -j -Jsourcepath src src/App.groovy