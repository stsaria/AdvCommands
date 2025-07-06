# Random
Random numbers, random strings, and random list shuffling

## Usage
- Generate random number within a specified range
```
randint range_start range_end
shuffled variable_name_to_shuffle
```
- Generate random UUID (like a macro)
```
<randuuid>
```

## Usage Examples
```
# Send "Hit!" to a random player
players players
length players
setvar playersLen <r>
randint 0 <playersLen-1>
setvar playerInt <r>
copyvar players.<playerInt> hitPlayer
cmd tellraw <hitPlayer.name> "Hit!"

# Use random UUID to reproduce return values
# Inside function hoge
setvar returnId <randuuid>
piyo <returnId>
cmd say <return<returnId>>
# Inside function piyo
setvarG return<args.0> hello
# This will display "hello"
```