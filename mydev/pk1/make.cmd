@echo OFF
echo building
cd mydev
cd pk1
del *.class
javac Easy.java
cd ..
cd ..
echo start program
java mydev.pk1.Easy
echo stop program
@echo ON