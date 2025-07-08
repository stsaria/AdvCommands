# Repetition
Repeat a specific number of times.

In Minecraft commands, this can be implemented using `scoreboard` and `execute`, but this plugin simplifies it much more.

## Usage
- Repeat a statement a specific number of times. (Return value is returned as an array.)
```
for counter_variable count statement
```
- Get the current count (for example, if `for i 10 nop`)
```
<i>
```

## Usage Examples
```
# Execute function hoge 10 times
for i 10 hoge
# Execute function hoge with the count 10 times
for i 10 hoge <i>
```

## Tips
- At runtime, a set of variables from the executor can be referenced or assigned to.