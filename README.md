# Legacy Elytras

A Legacy Fabric mod that backports elytras, fireworks, and all of their respective mechanics to 1.7 and 1.8.

## Elytras

There are two recipes for crafting the elytra:

![image](https://user-images.githubusercontent.com/95588510/156916038-a54cbdab-9658-4719-8674-04a8d1d7a7b3.png)
![image](https://user-images.githubusercontent.com/95588510/156916078-7146a04d-1874-43ea-a00e-343e1eaabb71.png)

With the goal in mind of making them a renewable end-game item. Elytras can be repaired using leather in an anvil.

The newest release supports custom textures, but for the speedrunners keep in mind this may have issues and requires Fabric API.

Also, due to it being impossible to inject into the part of the code that makes an item equippable in an armor slot, elytras are effectively initialized as leather chestplates and they give 1.5 hearts of protection.

## Fireworks

Because fireworks were added in 1.4, the mod simply adds the flying functionality to them. They can still be obtained in the usual way, crafted with gunpowder and paper. Note that fireworks are not available in the creative menu, they can only be obtained via commands or crafting.

## Technical Details

### Flying

Most of the logic for flying is adapted from 1.12, with the exception of the player needing to be falling in order to activate the elytra. The goal was to make the elytras as close as possible to 1.14+ but while only backporting the core mechanics and not the 30+ classes that interact with elytras in some way. Flying is still activated client-side, upon which a custom defined packet is sent to the server, and the server then becomes in charge of stopping flying. Instead of using flags, flying is kept track of as a boolean attribute of PlayerEntity.

### Fireworks 

By way of a mixin, a new method onStartUse() is added to the firework class in order to launch the player while flying. Again keeping with the goal of preserving 1.11+ fireworks but also maintaining practicality, the firework item itself is responsible for adding to the player's velocity, unlike in 1.11+ where the firework rocket entity is what propels the player for about 60 ticks per firework. The former way of doing this, the one I decided on, saves us from a lot of interactions between the entity and both the Client and Server player entities. All of the velocity updates are done in the same tick, but their net effect is very close to the ~60 ticks in vanilla 1.14+. 

### Rendering 

For the most part, the rendering logic while the player is flying is adapted straight from 1.12. Again due to textures not being possible, the wings are not rendered. Also, due to the workaround for fireworks mentioned above, using fireworks while flying will result in strange behaviour with the player's feet. This is purely cosmetic and doesn't affect gameplay.
