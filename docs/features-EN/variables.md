# Variables
Variables are used to assign names to values.
In modern programming, it's standard practice to use meaningful names (like userName) rather than meaningless names (like x, y, z).

## Basic Usage

### Setting and Deleting Variables
```
# Variable assignment
setvar variableName value                    # Function-scoped variable
setvarG variableName value                   # Global variable

# Variable copying (copies all child elements)
copyvar sourceVariableName targetVariableName     # Function-scoped variable
copyvarG sourceVariableName targetVariableName    # Global variable

# Variable deletion
delvar variableName                          # Delete function-scoped variable
delvarG variableName                         # Delete global variable
```

### Variable Embedding
```
<variableName>           # Normal variable embedding
```

## Built-in Variables
Special variables provided by the system. See the predefined variables documentation for details.

## Variable Existence Check
```
<variableName?>          # Check if variable exists (hierarchical check)
<variableName??>         # Check if variable exists directly (direct check)
```

Results return `true` or `false`.

## Variable Scope

### Function-scoped Variables
- Variables set with `setvar`
- Valid only within that function
- Automatically deleted when function ends

### Global Variables
- Variables set with `setvarG`
- Accessible from all functions
- Persist until explicitly deleted

### Variable Priority
When variables with the same name exist:
1. Function-scoped variables take priority
2. If not found in function scope, global variables are referenced

## Data Types and Type Conversion

### Supported Data Types
- **String**: Any text
- **Number**: Integers and decimal numbers (processed internally as double type)

### Type Conversion Behavior
- Automatic numeric conversion attempted during operations
- Error thrown if conversion fails
- When decimal part is 0, automatically converted to integer notation (e.g., `5.0` → `5`)

## Error Handling

### Non-existent Variables
- When referencing a non-existent variable, it remains as `<variableName>`
- When using non-existent variables in operations, that operation is skipped

### Operation Errors
- Division by zero: Returns `0`
- Number conversion error: Throws error

## Practical Usage Examples

### Basic Variable Operations
```
# Assign aho to function-scoped variable baka
setvar baka aho
# Create copy kasu of baka
copyvar baka kasu
# Display
cmd say <baka>
# This displays aho
cmd say <baka!>
# This executes as literal <baka>, not as a variable

# Assign daisuki to global variable zaemon (accessible from anywhere)
setvarG zaemon daisuki
# Delete function-scoped variable baka
delvar baka
# Delete global variable zaemon
delvarG zaemon
```

### Using Built-in Variables
```
# Use system-provided variables
setvar currentTime <unixtime>
cmd say Current time: <currentTime>
```

※ See the predefined variables documentation for available built-in variables.

### Variable Existence Check
```
# Check if variable exists directly
cmd say Variable exists directly: <score??>
```

### Numeric Operation Examples
```
# Calculations using variables
setvar a 10
setvar b 3
cmd say Calculation result: <a+b>     # Result: 13
```

※ See separate documentation for details on arithmetic operations.

### Comparison Operation Examples
```
setvar score1 85
setvar score2 92

cmd say Score comparison result: <score1<score2>    # Result: true
```

※ See separate documentation for details on comparison operations.

### Conditional Branching Usage
```
# Branching based on variable existence
cmd say First time startup: <firstTime??>
```

※ See separate documentation for other conditional branching.

### Complex Operation Combinations
```
setvar x 5
setvar y 3
setvar z 2

# Combine multiple operations (processed from inside out)
setvar result1 <x+y>
setvar finalResult <result1*z>
cmd say Result: <finalResult>    # (5+3)*2 = 16
```

※ See separate documentation for basic operations.

### Error Case Handling
```
# Handling non-existent variables
cmd say Value: <nonExistentVar>   # Displays as <nonExistentVar>

# Number conversion error
setvar text "hello"
setvar num 5
cmd say Calculation result: <text+num>   # Error occurs

# Division by zero
setvar zero 0
cmd say Division result: <num/zero>   # Result: 0
```

## Notes
- Variable names can only use alphanumeric characters and periods (.)
- Operations are processed sequentially from left to right
- Use intermediate variables for complex operations
- Excessive use of global variables can cause increased memory usage