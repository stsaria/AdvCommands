# Functions
※This is different from mathematical functions

A function is a named collection of processes grouped together. So it might be more appropriate to call it a method.

In this plugin, you can group multiple processes into a single function by connecting command blocks in the +y axis direction. (Libraries are described on a separate page)
If coordinate `10 10 10` is line 1, then `10 12 10` is line 3.

Do not attach repeat or chain to command blocks.

You can also add arguments and return values after the function name.

## Usage
- Function declaration
```
declfunc function_name world_name x_coordinate y_coordinate z_coordinate
```
- Output function contents
```
/advcmd catfunc function_name
```
- Function call
```
function_name
```
- Exit function with return value
```
exit return_value_variable_name
```
- Save return value (result)
```
saveresult save_variable_name
```

## Usage Examples
```
# Declaration of function hoge with coordinate `20 30 10` as line 1
/advcmd declfunc hoge world 20 30 10

# Execute function hoge
/advcmd hoge

# When calling a function within a function, advcmd is not needed.
hoge
# That's all.

# Pass hello as argument to function hoge
hoge hello

# Inside function hoge
cmd say <args.0>

```
When you want to add return values to functions, first decide on a variable ID using uuid or something similar, send it as an argument to the function, and assign it within the function.