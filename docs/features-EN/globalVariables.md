# Global Variables
We introduced variables earlier, but regular variables are accessed only within functions to avoid mixing them up.
However, there are cases where you want variables that can be accessed from within any function (such as fixed values for ranges).
This is when you can use global variables.

## Usage
- Switch to global variable scope and execute
```
runinglobal statement
```
- Copy global variable to current function's local variable
```
globaltolocal source_global_variable_name destination_function_variable_name
```
- Copy current function's local variable to global variable
```
localtoglobal source_function_variable_name destination_global_variable_name
```

## Usage Examples
```
# Assign a to global variable hoge
runinglobal setvar hoge a
# Copy global variable hoge to function local variable fuga
globaltolocal hoge fuga
```