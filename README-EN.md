# AdvCommands
AdvCommands is a plugin project that adds multiple features to traditional Minecraft commands.

## Why This Is Needed
There are many things that can be created with regular Minecraft commands.
Most of these heavily rely on the combination of the `execute` command, which allows for conditional branching, and repeating command blocks.

However, `execute` can be a bit difficult to understand, and using repeats increases complexity and reduces performance.

This plugin replaces these with more understandable alternatives.

## Getting Started
Install this plugin on a Spigot-based server and start it. (Please also enable command blocks.)

Once started, try running the command `/advcmd cmd say Hello world`.
You should then see `Hello world` displayed. Well, I hope so.

## Added Features
- Variables (\<v\>, setvar, delvar)
- Functions (f, declfunc, catfunc)
- Loops (for)
- Event handlers (seteventfunc)
- Random UUID (\<randuuid\>)
- UnixTime (\<unixtime\>)
- Basic arithmetic & exponentiation (\<x[+-*/%^]y\>)
- Comparisons (\<x[<>=]y\>)
- Conditional branching (if)
- Delays (waitrun)

For details, please refer to DefinedFunctions-EN.md and DefinedVariables-EN.md.

I'll explain each in order.

### Variables
Variables allow you to replace specific values with abstract names, making your code easier to understand.
Use `setvar variable_name value` to declare a variable.
Use `<variable_name>` to embed the value.
```
# Assign "aho" to function-local variable "baka"
setvar baka aho
# Display it
cmd say <baka>
# This will display "aho".

# Assign "daisuki" to global variable "zaemon" (accessible from anywhere)
setvarG zaemon daisuki
# Delete function-local variable "baka"
delvar baka
# Delete global variable "zaemon"
delvarG zaemon
```
Global variables are ignored if there are function-local variables with the same name.

### Functions
*Note: These are different from mathematical functions*

A function is a collection of related processes.
Functions allow you to avoid writing the same process multiple times, reducing program size and improving readability.

In this plugin, you can combine multiple processes into a single function by connecting command blocks vertically along the +y axis.
If coordinate `10 10 10` is the first line, then `10 12 10` would be the third line.

Please don't attach repeat or chain command blocks.

You can also add arguments to functions. (Return values are not supported.)

```
# Declare function "hoge" with first line at coordinate `20 30 10`
/advcmd declfunc hoge world 20 30 10

# Execute function "hoge"
/advcmd runfunc hoge

# When calling a function within another function, you don't need runFunc.
# Just use:
hoge

# Pass "hello" as an argument to function "hoge"
hoge hello

# Within function "hoge"
cmd say <args.0>
```
If you want to add a return value to a function, decide on a variable ID beforehand using a UUID or something similar, send it as an argument to the function, and assign it within the function.

### Loops
This repeats a process a specific number of times.

While this can be done using `scoreboard` and `execute`, this plugin simplifies it significantly.

```
# Execute function "hoge" 10 times
for 10 hoge
```

### Event Handlers
Plugins can execute specific functions when certain events (like movement or block placement) occur.
This increases readability by using an event-driven approach instead of the traditional command method of checking every tick.

Event handlers include variables related to the event.

```
# Register function "hoge" to be executed on kill events
seteventfunc onKill hoge

# Example: Send "move!" to players who move
# Chat command:
/advcmd seteventfunc onMove fuga
# Within function "fuga":
cmd tell {player.name} move!
```

### Random UUID
Using `<randuuid>` will replace that with a random UUID (without hyphens).
For example, a random UUID can be used to schedule the name of the variable that will store the results.
```
# Within function "hoge":
setvar returnId <randuuid>
piyo <returnId>
# Within function "piyo":
setvarG return<args.0> hello
# Within function "hoge":
cmd say <return<returnId>>
# This will display "hello".
```

### UnixTime
Using `<unixtime>` will replace that with the current Unix time.
This can be used for calculations such as how many seconds have passed since the game started.
```
setvar start <unixtime>
waitrun 1000 cmd say <<unixtime>-<start>>
# This should display "1".
```

### Basic Arithmetic & Exponentiation
You can easily implement arithmetic operations that previously required `scoreboard`.
Write expressions inside `<>` like `<1+1>`.

The written part will be replaced with the result of the calculation.

*Note: For three or more operations like 1+1+2, write it as `<<1+1>+<2+3>>`.*

Calculation examples:
```
# Addition
<1+1>
# Subtraction
<2-1>
# Multiplication
<5*4>
# Division
<9/3>
# Modulus (remainder)
<8%5>
# Exponentiation
<2^10>
```

### True/False
Determines whether something is true or false.

If true, it is replaced with `true`,
If false, it is replaced with `false`
```
# Is 5 the same as 5?
<5=5>
# Is 1 less than 2?
<1<2>
# Is a 1 greater than 2?
<1>2>
# Does the variable hoge exist?
<hoge?>
```

### Conditional Branching
If the given string is `true`, it executes the first function; if `false`, it executes the function after `else`.

Comparisons can also be used.
```
# If true execute function "hoge", if false execute function "panda". (Always executes "hoge")
if true hoge else panda
# The opposite:
if false panda else hoge
# Using a comparison:
if <2>1> nisu else nice
```

### Delay
Executes a specific function after a specified delay in milliseconds **in the background**.
```
# Display "hello" after one second (1000 milliseconds)
waitrun 1000 cmd say hello
```
*Note: Since this runs in the background, if you do something like `for 10 waitrun 1000 cmd say hello`, "hello" will be displayed 10 times almost simultaneously after 1 second.*

### GUI
Creates an inventory GUI similar to a game menu.
```
# When green dye in the GUI is clicked, display "Start!!!!"
# Hint?: This is a bit of a tricky usage, but running with waitrun can ignore errors on exit.
# Inside the newEmpItemStack function
if <i=4> exit else nop
itemstack itemstacks.<i> air 1 n/a
# Inside the clickGuiItem function
if <event.itemstack.displayname=startButtuonName> nop else exit
cmd say Start!!!!
# Inside the clickHandItem function
if <event.itemstack.displayname=menuOpenItemName> nop else exit
opengui menuGui <event.player.name>
# Inside the main function
declfunc newEmpItemStack world 12 10 10
declfunc clickGuiItem world 14 10 10
seteventfunc onclickguiitem
setvarG startButtonName Start!!
setvarG menuOpenItemName Game Menu
for 9 waitrun 0 newEmpItemStack
itemstack itemstacks.4 green_dye 1 <startButtuonName>
itemstack menuOpenItem compass 1 <menuOpenItemName>
newgui menuGui itemstacks StartGUI
# In chat command (lups0 is my player name)
/advcmd declfunc main world 10 10 10
/advcmd main
/advcmd give menuOpenItem lups0
```

## Important Notes
- Do not assign a reference to a variable to itself. This will cause an infinite loop.
- The same applies to functions.
