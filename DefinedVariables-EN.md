# Predefined Variables
## Standard Variables
### location
- `<world>` : World name
- `<x>` : x coordinate
- `<y>` : y coordinate
- `<z>` : z coordinate
- `<blockx>` : Block's x coordinate
- `<blocky>` : Block's y coordinate
- `<blockz>` : Block's z coordinate
- `<yaw>` : Direction the player is facing (in degrees) (horizontal)
- `<pitch>` : Direction the player is facing (in degrees) (vertical)

The angle (yaw) increases as you turn to the right.
The angle (pitch) is 0 degrees when looking straight ahead, and increases as you look down.
### entity
- `<name>` : Entity name
- `<entityid>` : Entity type ID
- `<uuid>` : Entity UUID
- `<isdead>` : Whether the entity is dead
### player
- `<name>` : Player name (MCID)
- `<displayname>` : Display name (with \< and \> already removed)
- `<location.*>` -> location
- `<health>` : Health points
- `<foodlevel>` : Hunger level
- `<level>` : Experience level
- `<uuid>` : Minecraft account UUID
- `<isdead>` : Whether the player is dead
### block
- `<materialname>` : Material name (example: polished_andesite)
- `<location.*>` -> location
### enchant
- `<name>` : enchant name (example: power)
- `<level>` : enchant level
### itemstack
- `<displayname>` : Item display name
- `<materialname>` : Material name
- `<amount>` : Stack count
- `<enchants.n>` : nth enchant (n -> enchant)
### team
- `<name>` : Team name
- `<displayname>` : Team display name
- `<playernames.n>` : Name of the nth player
- `<onlineplayers.n>` : nth player (only online players)
### httpresponse
- `statuscode` : status code (e.g. 200(success), 404(not found))
- `content` : response content.
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
### ondrop
- `<player.*>` -> player
- `<itemstack.*>` -> itemstack
### ondamage
- `<type>`: player or entity (player inherits from entity but is treated as a separate type)
- `<player.*>`: only when type is player -> player
- `<entity.*>`: only when type is entity -> entity
### ondamagebyentity
- `<type>`: player or entity (player inherits from entity but is treated as a separate type)
- `<player.*>`: only when type is player -> player
- `<entity.*>`: only when type is entity -> entity
- `<damagertype>`: player or entity (player inherits from entity but is treated as a separate type)
- `<damagerplayer.*>`: only when damagertype is player -> player
- `<damagerentity.*>`: only when damagertype is entity -> entity
## "User function" variables for loops
- `<i>` : Current iteration count (starting from 0)
## Others.
- `<randuuid>` : random UUID (without hyphen)
- `<unixtime>` : current UNIX time.
- `<nl>` : new line.