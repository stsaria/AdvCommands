# Defined Functions
*Note: The \<items\> written here are just for clarity; the actual syntax doesn't include these brackets.*

- `nop` : Does nothing
- `cmd <Minecraft command>` : Executes a Minecraft command through the server console.
- `declFunc <function name> <world name> <x coordinate (of the first command block)> <y coordinate> <z coordinate>` : Defines a new function.
- `catFunc <function name>` : Outputs the contents of a function.
- `exit` : intentionally exits the function with an error.
- `seteventfunc <onkill,onmove,onplaceblock,onbreakblock,onjoin,onchat> <function name>` : Executes a specific function when a specific event occurs.
- `setvar <variable name> <variable value>` : Defines or overwrites a variable.
- `delvar <variable name>` : Deletes a variable.
- `for <repetition count> <function name>` : Executes a function a specific number of times.
- `if <true,false> <function1 name> else <function2 name>` : Executes function1 if true, executes function2 if false.
- `waitrun <delay in milliseconds> <function name>` : Executes a function after a specific delay in milliseconds.
- `randint <range start> <range end> <variable name>` : assigns a random number in the range to the value for the given variable Pluto.
