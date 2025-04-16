# Defined Functions
*Note: The \<items\> written here are just for clarity; the actual syntax doesn't include these brackets.*

- `nop` : Does nothing
- `cmd <Minecraft command>` : Executes a Minecraft command through the server console.
- `declFunc <function name> <world name> <x coordinate (of the first command block)> <y coordinate> <z coordinate>` : Defines a new function.
- `catFunc <function name>` : Outputs the contents of a function.
- `exit` : intentionally exits the function with an error.
- `seteventfunc <onkill,onmove,onplaceblock,onbreakblock,onjoin,onchat,onclickguiitem,onclickhanditem,onleave> <function name>` : Executes a specific function when a specific event occurs.
- `setvar <variable name> <variable value>` : Defines or overwrites a variable.
- `delvar <variable name>` : Deletes a variable.
- `for <repetition count> <function name>` : Executes a function a specific number of times.
- `if <true,false> <function1 name> else <function2 name>` : Executes function1 if true, executes function2 if false.
- `waitrun <delay in milliseconds> <function name>` : Executes a function after a specific delay in milliseconds.
- `randint <range start> <range end> <variable name>` : assigns a random number in the range to the value for the given variable Pluto. start> <end of range> <variable name>` : assigns a random number in the range to the value for the given variable Pluto.
- `itemstack <variable_name> <material_name> <stack_size> <item_display_name>`:　Creates an ItemStack from the specified display name, material name (use the lowercase version of Spigot's Material Enum, e.g., acacia_boat), and stack size.
Assigns it to the specified global variable.
- `newgui <GUI_name> <itemstack_array_variable (e.g., hoge.0 -> itemstack, hoge.1)> <GUI_display_name>`: Creates an inventory GUI with the given display name and array of item stacks (global variables only).
- `opengui <GUI_name> <player_name>`:　Displays the specified inventory GUI to the player.
- `give <itemstack_variable> <player_name>`: Gives the item stack to the specified player.
- `length <assigned_variable_name> <variable_name>`:　Counts the number of elements in a variable structured like `variable_name.0`, `variable_name.1`, etc.
Assigns the count to the specified global variable.
(Note: If the sequence is broken or doesn't start at 0, the count may be inaccurate.)