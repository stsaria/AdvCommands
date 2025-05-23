# Random
Random numbers and random strings

## Usage
- Generate a random number within a specified range (assign to a variable name as a global variable)
```
randint variable_name range_start range_end
```
- Generate a random UUID (like a macro)
```
<randuuid>
```

## Usage Examples
```
# Send "Hit!" to a random player
players players
length playersLen players
randint playerInt 0 <playersLen-1>
copyvar players.<playerInt> hitPlayer
cmd tellraw <hitPlayer.name> "Hit!"

# Use random UUID to simulate return values
# Inside function hoge
setvar returnId <randuuid>
piyo <returnId>
# Inside function piyo
setvarG return<args.0> hello
# Inside function hoge
cmd say <return<returnId>>
# This will display "hello"
```