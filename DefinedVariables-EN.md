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
### itemstack
- `<displayname>` : Item display name
- `<materialname>` : Material name
- `<amount>` : Stack count/amount
## Variables During Function Execution
- `<args.n>` : The nth argument
- `<funcfirstblock>` : First block of the function (line 1) -> block
- `<funcnowlineblock>` : Block of the current line being executed in function -> block
## Event Variables
### onkill
- `<player.*>` -> player
- `<killer.*>` -> player
### onmove
- `<player.*>` -> player
- `<from.*>` : Original position -> location
- `<to.*>` : Destination position -> location
### onjoin
- `<player.*>` -> player
### onplaceblock
- `<player.*>` -> player
- `<block.*>` -> block
- `<placedblock.*>` -> block
### onbreakblock
- `<player.*>` -> player
- `<block.*>` -> block
### onchat
- `<message>` : Message (with \< and \> removed)
- `<player.*>` -> player
### onclickguiitem
- `<guiname>` : GUI inventory name
- `<guititle>` : GUI inventory title
- `<itemstack.*>` -> itemstack
### onclickhanditem
- `<player>` : GUI inventory name
- `<guititle>` : GUI inventory title
- `<itemstack.*>` -> itemstack
### onleave
- `<player.*>` -> player
## "User Function" Variables During Loops
- `<i>` : Current iteration count (starting from 0)