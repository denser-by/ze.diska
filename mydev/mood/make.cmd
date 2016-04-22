@echo OFF
echo building
cd mydev
cd mood
del *.class
javac SeeNek.java
cd ..
cd ..
echo start program
java mydev.mood.SeeNek
echo stop program
@echo ON