# Game of Life
It's student project created with maven 4.0.0 and java 8 as part of Java Course in SDAacademy.

# Table of contents
* [General info](#general-info)
* [Traits of organisms](#traits-of-organisms)
* [Human player](#human-player)
* [Controls](#controls)
* [Human powers](#human-powers)
* [Animals](#animals)
* [Plants](#plants)

# General info
Game of Life is created with maven and java 8 simple console game that simulates virtual world in a form of two-dimensional array. Simple life forms with diffrent sets of behaviour coexist in this world, each taking place on a single array field. Each place in the array can be taken only by one life form at once.

Simulator is turn-based. Each turn, life forms (organisms) takes action according to their type. Some of them can move (animals and player) others can't (plants). In case of collision (when organism tries to take occuppied field) one of the life forms wins, killing or chasing away another one. If there's collision between representatives of the same species, new animal can be born on the field next to its parents. Plants tries to spread in each turn.



# Traits of organisms
| Trait | description |
| ------ | ------ |
| Strenght | stronger organism wins during collision (there are exceptions) |
| Initiative| animal with higher initiative moves first |
| Symbol | character representing organism on the game board |

# Human player
Human have traits just like other organism on the map, but his moves are not random but determined by player. Player unlike animals can't move diagonally (he can move only vertically and horizontally), but he's equipped with a set of special powers. Power can be used instead of move in each turn. They stay active for 5 turns and can be used again in another 5 turns.

# Controls
| button | description |
| ------ | ------ |
| w | move up |
| s | move down |
| a | move left |
| d | move right |
| e | use special power |

# Human powers
| id | Power | description |
| ------ | ------ | ------|
| 0 | Immortality | Human can't be killed. In case of collision with stronger animal, he moves to the next free field
| 1 | Magical elixir | Human strenght rises to 10 in first turn after activation. Then it decreases by one every turn until it comes back to start point
| 2 | Gazelle speed | In first 3 turn after activation human moves 2 field at once. In next 2 turn there's 50% chance for it. Be careful - human must move 2 field even if he prefers to move 1 field at once.
| 3 | Alzure shield | Human gets shield which is impregnable to any attacker. Attacker bypasses the human and go to the random close field.
| 4 | Deforestation | Human annihilates all life next to him

# Animals
| symbol | name | strenght | initiative | specifics of action | specifics of collision |
| ------ | ------ | ------| ---- | ------|------|
| H | human | 5 | 4 | can use powers | depends on used power
| W | wolf | 9 | 5 | - | - |
| A | antilope | 4 | 4 | moves by 2 fields at once | 50% possibility to run from fight
| S | sheep| 4 | 4 | - | - |
| F | fox | 3 | 7 | never attacks stronger animal | - |
| T | turtle | 2 | 1 | 75% chance that turtle won't move | Attackers with strength lower than 6 can't defeat him |


# Plants
| symbol | name | strenght | initiative | specifics of action | specifics of collision |
| ------ | ------ | ------| ---- | ------|------|
| G |   grass | 0 | 0 |  - | - |
| C |   clover | 0 | 0 |  tries to spread 3 times in 1 one turn | - |
| U |   ultraguarana | 0 | 0 |  - | increases strength of animal that ate it by 3 |
| B |   belladonna | 99 | 0 |  - | animal that ate belladonna dies |