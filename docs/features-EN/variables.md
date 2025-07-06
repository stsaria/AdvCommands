# Variables
Variables are used to assign names to values.
In modern programming especially, meaningful names (like userName) are basically used instead of meaningless names (like x, y, z).

## Basic Usage

### Variable Setting and Deletion
```
# Variable assignment
setvar variable_name value

# Variable copying (copies all subordinate elements)
copyvar source_variable_name destination_variable_name

# Variable deletion
delvar variable_name
```

### Variable Embedding
```
<variable_name>             # Normal variable embedding
```

## Built-in Variables
These are special variables provided by the system. For details, refer to the predefined variables documentation.

## Variable Existence Check
```
<variable_name?>            # Check if variable exists (hierarchical check)
<variable_name??>           # Check if variable exists directly (direct check)
```

Results return `true` or `false`.

## Data Types and Type Conversion
### Supported Data Types
- **String**: Any text
- **Number**: Integer and decimal numbers (internally processed as double type)
### Type Conversion Behavior
- Automatic numerical conversion is attempted during operations
- When decimal places are 0, automatically becomes integer notation (e.g., `5.0` → `5`)

## Error Handling
### Non-existent Variables
- When referencing a non-existent variable, it remains as `variable_name`
- When using non-existent variables in operations, that operation is skipped
### Operation Errors
- Division by zero: Returns `0`
- Numerical conversion error: Throws an error

## Practical Usage Examples

### Basic Variable Operations
```
# Assign aho to function variable baka
setvar baka aho
# Create copy kasu of baka
copyvar baka kasu
# Display
cmd say <baka>
# This will display aho.
cmd say <baka!>
# This way, it executes as just <baka> instead of as a variable.

# Assign daisuki to global variable (accessible from anywhere) zaemon
setvarG zaemon daisuki
# Delete function variable baka
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

※ For available built-in variables, refer to the predefined variables documentation.

### Variable Existence Check
```
# Check if variable exists directly
cmd say Variable direct existence: <score??>
```

### Numerical Operation Examples
```
# Calculations using variables
setvar a 10
setvar b 3
cmd say Calculation result: <a+b>     # Result: 13
```

※ For details on arithmetic operations, refer to separate documentation.

### Comparison Operation Examples
```
setvar score1 85
setvar score2 92

cmd say Score comparison result: <score1<score2>    # Result: true
```

※ For details on comparison operations, refer to separate documentation.

### Utilization in Conditional Branching
```
# Branching based on variable existence
cmd say First startup: <firstTime??>
```

※ For other conditional branching, refer to separate documentation.

### Complex Operation Combinations
```
setvar x 5
setvar y 3
setvar z 2

# Combine multiple operations (processed from inside out)
setvar result <<x+y>*z>
cmd say Result: <result>    # (5+3)*2 = 16
```

※ For basic operations, refer to separate documentation.

### Handling Error Cases
```
# Handling non-existent variables
cmd say Value: <nonExistentVar>   # Displays <nonExistentVar> as is

# Numerical conversion error
setvar text "hello"
setvar num 5
cmd say Calculation result: <text+num>   # Error occurs

# Division by zero
setvar zero 0
cmd say Division result: <num/zero>   # Result: 0
```

## Important Notes
- Variable names can only use alphanumeric characters and periods (.)
- Operations are processed sequentially from left to right
- Using intermediate variables is recommended for complex operations
- Excessive use of global variables can cause increased memory usage, so be careful