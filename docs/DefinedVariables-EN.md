# Predefined Variables

## Normal Variables

### location
- `<world>` : World name
- `<x>` : x coordinate
- `<y>` : y coordinate
- `<z>` : z coordinate
- `<blockx>` : Block x coordinate
- `<blocky>` : Block y coordinate
- `<blockz>` : Block z coordinate
- `<yaw>` : Direction the player is facing (in degrees) (horizontal)
- `<pitch>` : Direction the player is facing (in degrees) (vertical)

Angle (yaw) increases as you turn right.
Angle (pitch) is 0 degrees when facing forward, and increases as you look down.

### entity
- `<name>` : Entity name
- `<entityid>` : Entity type ID
- `<uuid>` : Entity UUID
- `<isdead>` : Whether dead or not

### address
- `<hostname>` : Hostname
- `<port>` : Port number

### player
- `<name>` : Player name (mcid)
- `<displayname>` : Display name (\< and \> already removed)
- `<location.*>` -> location
- `<health>` : Health
- `<foodlevel>` : Food level
- `<level>` : Experience level
- `<uuid>` : Minecraft account UUID
- `<isdead>` : Whether dead or not
- `<ping>` : User's millisecond delay
- `<isop>` : Whether has operator privileges
- `<address.*>` -> address

### block
- `<materialname>` : Material name (e.g., polished_andesite)
- `<location.*>` -> location

### enchant
- `<name>` : Enchantment name (e.g., power)
- `<level>` : Enchantment level

### itemstack
- `<displayname>` : Item display name
- `<materialname>` : Material name
- `<amount>` : Stack count
- `<enchants.n>` : nth enchantment (n -> enchant)

### team
- `<name>` : Team name
- `<displayname>` : Team display name
- `<playernames.n>` : nth player name
- `<onlineplayers.n>` : nth player (online players only)

### httpresponse
- `statuscode` : Status code (e.g., 200(success), 404(not found))
- `content` : Response content

### commandsender
- `<name>` : Sender name
- `<isop>` : Whether has operator privileges

## Function Execution Variables
- `<args.n>` : nth argument
- `<argsstr>` : Arguments string (e.g., "<args.0> <args.1> <args.2>")

## Event Variables
Accessible within functions as `<event.*>`

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
- `<message>` : Message (\< and \> already removed)
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

### ondamage
- `<type>` : player or entity (player inherits from entity but treated separately)
- `<player.*>` : Only when type is player -> player
- `<entity.*>` : Only when type is entity -> entity

### ondamagebyentity
- `<type>` : player or entity (player inherits from entity but treated separately)
- `<player.*>` : Only when type is player -> player
- `<entity.*>` : Only when type is entity -> entity
- `<damagertype>` : player or entity (player inherits from entity but treated separately)
- `<damagerplayer.*>` : Only when type is player -> player
- `<damagerentity.*>` : Only when type is entity -> entity

### onfer
- `<sender.*>` : Command sender -> commandsender
- `<cmd>` : Original command
- `<argsstr>` : Arguments string
- `<args.n>` : nth argument

## For Loop "User Function" Variables
- `<i>` : Current iteration count (counted from 0)

## Other Variables
- `<randuuid>` : Random UUID (without hyphens)
- `<unixtime>` : Current UNIX time
- `<nl>` : Newline