# User-Defined Commands
A feature that allows users to define Minecraft commands themselves.

## Usage
- Register command event (common)
```
seteventfunc onfer function_name
```

### Dynamic Command Definition
For dynamic command definitions, you cannot freely set any command.

Instead of `/command`, it becomes a command like `/fer command`.
- Get command arguments (inside function)
```
# Argument array
<event.args>
# Argument string
<event.argsstr>
```

### Static Command Definition
For static command definitions, you can use `/command` format, but there are constraints.
- Commands can only be defined in `config.yml`.
- Command loading only occurs at startup. (It may be applied during `/reload`, but often fails)

If you can directly log into the server console, use this method!

- Register command (inside `plugins/AdvCommands/config.yml`)
```yaml:config.yml
commands:
 - command_name
```

※This is not an official command addition method, but rather a way to add commands to undocumented variables, so it may stop working at any time.