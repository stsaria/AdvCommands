# Delayed Execution
Executes a specific function on the Main thread after a specified number of milliseconds delay **in the background**.

## Usage
- Execute a statement after a specific number of milliseconds
```
waitrun wait_milliseconds statement
```

## Usage Examples
```
# Display "hello" after 1000 milliseconds (one second)
waitrun 1000 cmd say hello
```