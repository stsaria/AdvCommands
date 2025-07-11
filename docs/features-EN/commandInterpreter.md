# Command Interpreter
To execute scripted languages, you need to call an interpreter, which is a conversion device that translates instructions.

In this plugin, it is called from Spigot commands.

## Usage
- Execute a single-line script from the interpreter
```
/cakelang script
```

## Usage Examples
```
Display Hello world
/cakelang cmd say "Hello world"
```
## Tips
- The variable scope is always global when executed in the interpreter.