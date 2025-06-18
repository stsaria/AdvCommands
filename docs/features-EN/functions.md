# Functions
※This is different from mathematical functions

A function is a named collection of processes grouped together. It might be more accurate to call it a method.

In this plugin, you can group multiple processes into a single function by connecting command blocks in the +y axis direction.
If coordinate `10 10 10` is line 1, then `10 12 10` is line 3.

Do not use repeat or chain command blocks.

You can also add arguments after the function name. (Return values are not supported)

## Usage
- Function declaration
```
declfunc functionName worldName xCoordinate yCoordinate zCoordinate
```
- Output function contents
```
/advcmd catfunc functionName
```
- Function call
```
functionName
```

## Usage Examples
```
# Declare function hoge with coordinate `20 30 10` as line 1
/advcmd declfunc hoge world 20 30 10

# Execute function hoge
/advcmd hoge

# When calling a function within another function, advcmd is not needed.
hoge
# That's all.

# Pass "hello" as an argument to function hoge
hoge hello

# Inside function hoge
cmd say <args.0>
```

When you want to add return values to functions, first decide on a variable ID using a UUID or something similar, send it as an argument to the function, and assign it within the function.