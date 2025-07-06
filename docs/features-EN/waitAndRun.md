# Delayed Execution
**In the background**, execute a specific function on the Main thread after a specific number of milliseconds delay.

## Usage
- Execute statement after a specific number of milliseconds
```
waitrun wait_milliseconds statement
```

## Usage Example
```
# Display "Hello" after 1000 milliseconds (one second)
waitrun 1000 cmd say Hello
```

## Tips
- During execution, you can reference the variable group of the execution source. However, even if you modify them, those changes will not be applied to the original.
- Return values cannot be used in delayed execution.