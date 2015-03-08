del bin\server\*.* /Q
del bin\client\*.* /Q
REM del tcp.jar
javac -d bin src\cliente\*.java src\servidor\*.java
