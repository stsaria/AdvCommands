# Predefined Functions

*Note: The \<〇〇\> notation used here is just for clarity - these brackets are not actually used in practice.*

Return values are represented by result*.

- `nop` : Does nothing. -> result = null
- `cmd <minecraft_command>` : Executes a Minecraft command through the server console. -> result = null
- `declfunc <function_name> <world_name> <x_coordinate(first_command_block)> <y_coordinate> <z_coordinate>` : Defines a new function. -> result = null
- `catfunc <function_name>` : Returns the contents of a function. -> result = function contents
- `exit` : Terminates the function. (Only used within functions) -> result = undefined
- `exit <return_value_variable_name>` : Terminates the function with a return value. (Only used within functions) -> result = undefined
- `seteventfunc <event_type> <function_name>` : Executes a specific function when a specific event occurs. -> result = null
- `setvar <variable_name> <variable_value>` : Defines or overwrites a variable. -> result = null
- `delvar <variable_name>` : Deletes a variable. -> result = null
- `for <repeat_count> <statement>` : Executes a statement for a specific number of times.
- `if <true,false> <function1_name> else <function2_name>` : Executes function1 if true, function2 if false. -> result.* = result of executed function
- `waitrun <delay_milliseconds> <function_name>` : Executes a function after a specific millisecond delay. -> result = null
- `randint <range_start> <range_end>` : Returns a random number within the range. -> result = random number
- `itemstack <material_name> <stack_count> <item_display_name>` : Creates an item stack from specified display name, material name (lowercase Spigot Material Enum) (e.g., acacia_boat), and stack count. -> result.* = itemstack
- `newgui <gui_name> <itemstack_array_variable(e.g., hoge.0 -> itemstack, hoge.1)(global_variables_only)> <gui_display_name>` : Creates an inventory GUI. -> result = null
- `opengui <gui_name> <player_name>` : Displays an inventory GUI to a player. -> result = null
- `give <itemstack_variable> <player_name>` : Gives an item stack to a player. -> result = null
- `length <variable_name>` : Counts and returns the number of elements in variables with integer-based ordering like `variable_name.0`, `variable_name.1`. (Cannot count accurately if interrupted or doesn't start from 0) -> result = array length
- `trueif <true,false> <code>` : An `if` statement that only processes `true` cases. Code can be of any length. -> result.* = result of executed function
- `bungeemove <server_id> <player_name>` : Moves a player using BungeeCord. -> result = null
- `teams` : Returns a list of teams. -> result.n.* = nth team
- `size <variable_name>` : Counts and returns the number of elements in variables like `variable_name.a`, `variable_name.hogeee`. -> result = number of struct elements
- `players` : Returns a list of players. -> result.n.* = nth player
- `cancel` : Terminates the function and cancels the event if possible during event handling. (Only used within functions) -> result = undefined
- `skip` : Ignores the next line of the function. (Only used within functions) -> result = undefined
- `strtolist <string>` : Converts a string into a list of individual character strings and returns it. -> result.n = nth character
- `shuffled <variable_name>` : Shuffles a list. (Destructive modification) -> result = null
- `addenchant <original_itemstack_variable> <enchantment_name> <enchantment_level>` : Adds an enchantment to an item stack. -> result = null
- `jsontostruct <json_string>` : Converts a JSON string to a struct. -> result.* = json struct
- `structtojson <struct_variable_name>` : Converts a struct to a JSON string. -> result = json string
- `ismatch <match_source_variable_name> <regex_string>` : Returns true if the value assigned to the variable matches, false if it doesn't match. -> result = whether it matches (true or false)
- `replace <replacement_source_variable_name> <replacement_regex_variable_name> <replacement_target_variable_name>` : Replaces a string using regex and returns the result. -> result = string after replacement
- `split <delimiter_source_string_variable_name> <delimiter_string>` : Splits a string and returns the result. -> result.n = nth split string
- `httpget <url>` : Sends an HTTP GET request and returns an httpresponse. -> result.* = httpresponse
- `httppost <argument_struct_variable_name> <url>` : Sends an HTTP POST request and returns an httpresponse. -> result.* = httpresponse
- `variables` : Generates and returns a list array of variables. -> result.n = string expression like `variable_name -> value`
- `exportfunc <function_name>` : Converts function contents to a proprietary format and returns it. -> result = converted string
- `importfunc <function_contents>` : Imports a function. -> result = null
- `toint <decimal_or_integer>` : Converts a value to an integer and returns it. -> result = converted integer
- `getname <struct> <value>` : Searches for and returns the variable name corresponding to a value in a struct (including arrays). If there are two, returns the first assigned one. -> result = found variable name, null if not found
- `delgui <gui_name>` : Deletes a GUI. -> result = null
- `closegui <player_name>` : Closes the GUI being displayed to a player. -> result = null
- `runinglobal <statement>` : Executes a statement in the global variable scope. -> result.* = result of executed function
- `localtoglobal <source_function_internal_variable_name> <destination_global_variable_name>` : Copies the current function's internal variable to a global variable. -> result = null
- `globaltolocal <source_global_variable_name> <destination_function_internal_variable_name>` : Copies a global variable to the current function's internal variable. -> result = null
- `deepequal <comparison_target1> <comparison_target2>` : Returns true if all subordinate elements of the target variables are the same, false otherwise. -> result = whether they are the same (true or false)
- `saveresult <destination_variable_name>` : Saves the return value. (Only used within functions) -> result = undefined