MAINCLASS=PhysicsDemo2

java -cp .:../../libs/slf4j.jar:../../libs/log4j.jar:../../libs/lwjgl.jar:../../libs/lwjgl_util.jar:../../libs/jbox2d.jar:../../out/production/FOGDESTROYER -Djava.library.path=../../native/macosx $MAINCLASS
