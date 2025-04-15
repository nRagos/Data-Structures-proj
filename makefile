# Define the Java compiler
JC = javac

# Specify compilation flags
JF = -d bin

# Define the source directory
SD = src

# Define the target directory for compiled .class files
BD = bin

# Define the source files
# Here, we use wildcard to compile all .java files in the source directory
# You can customize this to match your project structure
JVF = $(wildcard $(SD)/*.java)

# Define the targets
# By default, it will compile all Java files
all: $(JVF:$(SD)/%.java=$(BD)/%.class)

# Rule to compile .java files into .class files
$(BD)/%.class: $(SD)/%.java
	$(JC) $(JF) $<

# Clean up
clean:
	rm -rf $(BD)
