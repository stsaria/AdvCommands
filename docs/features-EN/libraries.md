# Library System

The plugin provides a library feature that offers commonly used functions and utilities. By using libraries, you can improve code reusability and enhance development efficiency.

## Overview

The library system has the following features:

- **Automatic Loading**: Libraries are automatically loaded when the plugin starts
- **Standard Libraries**: The plugin includes built-in standard libraries
- **Custom Libraries**: Users can add their own custom libraries
- **Function Usage**: Functions within libraries can be called like regular functions

## Library File Location

Library files are placed in the following location:

```
plugins/AdvCommands/libs/
```

This directory is automatically created on the first startup.

## Library File Format

### File Extension
Library files must have the `.func` extension.

Example: `myLibrary.func`

### File Content
The content of library files is written in the same format as regular function definitions:

```
# Example function within a library
cmd say Hello from library!
cmd say Library function executed
```

### 2. Creating and Using Libraries

#### Step 1: Creating a Library File
Create `plugins/AdvCommands/libs/myUtils.func`:

```
# Example content of myUtils.func
cmd say Utility function executed
setvarG lastUtilExecution <unixtime>
```

#### Step 2: Restart the Plugin
After placing the library file, restart the plugin.

#### Step 3: Calling Library Functions
```
# Call custom library function
/advcmd myUtils
```

## How Libraries Work

### Initialization Process
1. **Directory Creation**: Automatically creates the `libs` directory if it doesn't exist
2. **Standard Library Copy**: Copies built-in standard libraries to the filesystem
3. **File Search**: Searches for `.func` files in the `libs` directory
4. **Function Registration**: Registers the content of each library file as functions

### Function Name Determination
The library file name (excluding extension) becomes the function name:

- `myLibrary.func` → Function name: `myLibrary`
- `utils.func` → Function name: `utils`
- `helper.func` → Function name: `helper`

## Practical Usage Examples

### Log Output Library
```
# logger.func
cmd say [LOG] <args.0>
setvarG lastLogMessage <args.0>
setvarG lastLogTime <unixtime>
```

Usage:
```
# Using the log output library
logger "System started successfully"
```

### Math Utility Library
```
# mathUtils.func
setvar result <args.0+args.1>
setvarG mathResult <result>
cmd say Calculation result: <result>
```

Usage:
```
# Using the math calculation library
mathUtils 10 20
cmd say Last calculation result: <mathResult>
```

### Player Management Library
```
# playerManager.func
players allPlayers
length playerCount allPlayers
cmd say Current player count: <playerCount>
setvarG currentPlayerCount <playerCount>
```

Usage:
```
# Using the player management library
playerManager
```

## Important Notes

### File Name Constraints
- File names should use alphanumeric characters and underscores (_)
- Avoid multibyte characters like Japanese
- Extension must be `.func`

### Function Name Conflicts
- Don't create libraries with the same name as existing functions
- Don't use the same file name between different libraries

### Performance
- Libraries are loaded only once at startup
- Large numbers of libraries may affect startup time

### Error Handling
- If a library file cannot be loaded, warnings are output to the log
- Libraries with syntax errors will not be loaded

## Troubleshooting

### When Libraries Don't Load
1. Check if the file extension is `.func`
2. Check if the file is saved in UTF-8 encoding
3. Check if the plugin has been restarted
4. Check server logs for error messages

### When Functions Cannot Be Called
1. Check logs to see if the library loaded properly
2. Check if the function name matches the file name
3. Check for syntax errors

## Advanced Usage

### Combining Libraries
```
# composite.func - Example combining multiple libraries
logger "Starting composite process"
mathUtils <args.0> <args.1>
playerManager
logger "Composite process completed"
```

### Conditional Library Execution
```
# conditional.func - Execute libraries based on conditions
if <args.0=debug> logger else playerManager
```

## Summary

By utilizing the library system:
- Code reusability improves
- Maintainability improves
- Development efficiency improves
- System-wide consistency is maintained

Create effective libraries for more efficient Minecraft plugin development.