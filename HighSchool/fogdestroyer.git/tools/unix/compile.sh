FILES=(
	../../src/PhysicsDemo.java
	../../src/PhysicsDemo2.java
	../../src/PhysicsDemo3.java
)
javac -cp :../../libs/slf4j.jar:../../libs/log4j.jar:../../libs/lwjgl.jar:../../libs/lwjgl_util.jar:../../libs/jbox2d.jar:../../out/production/FOGDESTROYER -d ../../out/production/FOGDESTROYER/ ${FILES[*]}
