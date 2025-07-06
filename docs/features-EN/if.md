# Conditional Branching
If the given string is `true`, execute the first function; if `false`, execute the function after else.

## Usage
- If `true`, execute the first function; if `false`, execute the function after else
```
if true_or_false true_case_function else false_case_function
```
- If `true`, execute the statement
```
trueif true_or_false true_case_statement
```

## Usage Examples
```
# If true, execute function hoge; if false, execute function panda. (hoge will always be executed)
if true hoge else panda
# Reverse
if false panda else hoge
# Try using an inequality
if <2>1> nisu else nice
# Use a value assigned within if(trueif)
setvar a null
trueif true setvar a aaa
tesfunc <a>
```

## Tips
- During execution, you can reference the variable group of the execution source. However, even if you assign values at the execution destination, those changes will not be applied to the original.
- `if` can execute functions, but you cannot add arguments after them.