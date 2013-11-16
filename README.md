HackTXGame: Web Platformer
==========================
By: Devin Sandhu, Po-Chen Yang, and Sam Speck

Overview
--------

A 2D platformer that generates levels based on the site that the user supplies.
Using the JSOUP library we generate a level based on the DOM of the website with
each column of the level being the depth of a tag.
The enemies are randomly added for additional difficulty and spawn every x amount of steps.
Levels are saved in text files for replayability.
To play the game run the main method in Game.java

To Add
------
1. Pit generation for long flat areas
2. "Pipes" when standing on an anchor tag (currently displayed as the inveted block) some button input would follow the link to a new level
3. Portals from the opening to closing of tags
4. Killable enemies
5. Some way to tie the enemies and the underlying page together, for example each enemy could be matched to a type of tag (a, p, h1) and when killed blocks represented by those tags will dissapear
6. A way for the user to see more of the underlying site
7. A score system based on time to complete level
8. A level selection screen to play old levels
9. A pause screen
10. Leaderboards
11. Background

Challenges
==========
We decided to write the game from scratch, albeit based on an engine one of us
had for another game, which have us considerable freedom but it took a large chunk
of our time to even gain the basic functionality of the game.  This made testing the level generation
from HTML part of the game very difficult since we couldn't tell whan was a fun level and what wasn't.  A game like this would require many hours of playtesting to fine tune the level generation algorithm but
as a proof of concept we feel that it works.
 
