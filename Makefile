build: src/*.java
	javac -cp src src/*.java

test: src/*.class
	java -cp src ClientServerTest

clean:
	rm -vf $(find . -name *.class)
