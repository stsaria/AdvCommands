# Defined Variables
## Normal Variables
### location
- `<world>` : World name
- `<x>` : x coordinate
- `<y>` : y coordinate
- `<z>` : z coordinate
### player
- `<name>` : Player name (MCID)
- `<displayname>` : Display name (with \< and \> removed)
- `<location.*>` -> location
### block
- `<materialname>` : Material name (example: polished_andesite)
- `<location.*>` -> location
## Variables During Function Execution
- `<args.n>` : The nth argument
- `<funcfirstblock>` : First block of the function (line 1) -> block
- `<funcnowlineblock>` : Block of the current line being executed in function -> block
## Event Variables
### onKill
- `<player.*>` -> player
- `<killer.*>` -> player
### onMove
- `<player.*>` -> player
- `<from.*>` : Original position -> location
- `<to.*>` : Destination position -> location
### onJoin
- `<player.*>` -> player
### onPlaceBlock
- `<player.*>` -> player
- `<block.*>` -> block
- `<placedblock.*>` -> block
### onBreakBlock
- `<player.*>` -> player
- `<block.*>` -> block
### onChat
- `<message>` : Message (with \< and \> removed)
- `<player.*>` -> player
## "User Function" Variables During Loops
- `<i>` : Current iteration count (starting from 0)