# Predefined Variables
## Standard Variables
### location
- `<world>` : World name
- `<x>` : x coordinate
- `<y>` : y coordinate
- `<z>` : z coordinate
- `<yaw>` : Direction the player is facing (in degrees) (horizontal)
- `<pitch>` : Direction the player is facing (in degrees) (vertical)

Angle (yaw) increases as you turn to the right.
Angle (pitch) is 0 degrees when looking straight ahead, and increases as you look down.
### player
- `<name>` : Player name (mcid)
- `<displayname>` : Display name (with \< and \> already removed)
- `<location.*>` -> location
### block
- `<materialname>` : Material name (example: polished_andesite)
- `<location.*>` -> location
### itemstack
- `<displayname>` : Item display name
- `<materialname>` : Material name
- `<amount>` : Stack count
### team
- `<name>` : Team name
- `<displayname>` : Team display name
- `<playernames.n>` : Name of the nth player
- `<onlineplayers.n>` : nth player (only online players)
## Function Execution Variables
- `<args.n>` : nth argument
- `<funcfirstblock>` : First block of the function (line 1) -> block
- `<funcnowlineblock>` : Block of the current executing line -> block
## Event Variables
### onkill
- `<player.*>` -> player
- `<killer.*>` -> player
### onmove
- `<player.*>` -> player
- `<from.*>` : Original position -> location
- `<to.*>` : Position after movement -> location
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
- `<message>` : Message (with \< and \> already removed)
- `<player.*>` -> player
### onclickguiitem
- `<guiname>` : GUI inventory name (default for player default)
- `<guititle>` : GUI inventory title
- `<itemstack.*>` -> itemstack
### onclickhanditem
- `<player>` : GUI inventory name
- `<guititle>` : GUI inventory title
- `<itemstack.*>` -> itemstack
### onleave
- `<player.*>` -> player
## "User function" variables for loops
- `<i>` : Current iteration count (starting from 0)