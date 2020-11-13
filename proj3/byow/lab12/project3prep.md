# Project 3 Prep

**For tessellating hexagons, one of the hardest parts is figuring out where to place each hexagon/how to easily place hexagons on screen in an algorithmic way.
After looking at your own implementation, consider the implementation provided near the end of the lab.
How did your implementation differ from the given one? What lessons can be learned from it?**

Answer:

While my implementation of Tesselation.java was quite illegible, failing to use getter methods and repeatedly accessing the 
instance variables in Hexagon, the given implementation took a more efficient approach: creating a 
dummy Hexagon and using it as a placeholder to access the class variables which repeatedly appeared
in the code (getHexWidth(), getHexHeight(), etc.).

**Can you think of an analogy between the process of tessellating hexagons and randomly generating a world using rooms and hallways?
What is the hexagon and what is the tesselation on the Project 3 side?**

Answer:

A hexagon represents a single unit of square footage/tile, whether it be occupied by a wall or a 
floor piece. The tesselation represents the arrangement of these tiles, which is the various
routes one can traverse in a game where user moves through hallways.

**If you were to start working on world generation, what kind of method would you think of writing first? 
Think back to the lab and the process used to eventually get to tessellating hexagons.**

Answer:

I would write a method to randomly generate a singular tile. Breaking down the task at the unit 
level is the most logical way to proceed before attempting to tesselate and work with multiple
tiles.

**What distinguishes a hallway from a room? How are they similar?**
 
Answer: 

Hallways and rooms both randomly vary in their dimensions (l, w, h). Both have visually distinct 
walls from floors. Rooms are connected by hallways; they are not independent entities spread out
through the world.
