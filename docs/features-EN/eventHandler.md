# Event Handler
In plugins, you can execute specific functions when certain events occur (such as movement or block placement).
This improves readability by using an event-driven approach where functions are executed when something happens, rather than the traditional command method of checking every tick.

Event handlers include event-related variables (event.*).

## Usage
- Register a schedule to execute a function when an event occurs
```
seteventfunc eventType functionName
```
- Reference event details within the function
```
<event.variableName>
```

## Usage Examples
```
# Register a schedule to execute the function "hoge" on kill events
seteventfunc onkill hoge

# Example: Send "move!" to a player who moved
# Chat command
/advcmd seteventfunc onmove fuga
# Inside function fuga
cmd tellraw <event.player.name> "move!"
```