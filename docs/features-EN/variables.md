# Variables
Variables are a way to assign names to values.
Particularly in modern programming, we basically assign meaningful names (like userName) rather than meaningless names (like x, y, z).

## Usage
- Setting a variable
```
setvar variable_name value
```
- Copy a variable (copies all subordinate elements like player.name)
```
copyvar source_variable_name destination_variable_name
```
- Delete a variable
```
delvar variable_name
```
- Variable embedding
```
<variable_name>
```

## Usage Examples
```
# Assign aho to local variable baka
setvar baka aho
# Create a copy kasu of baka
copyvar baka kasu
# Display
cmd say <baka>
# This will display aho.
cmd say <baka!>
# This way, it executes as just <baka> rather than as a variable.

# Assign daisuki to global variable (accessible from anywhere) zaemon
setvarG zaemon daisuki
# Delete local variable baka
delvar baka
# Delete global variable zaemon
delvarG zaemon
```