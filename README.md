# Hacklahoma 2022
- This repository serves to support my Hacklahoma 2022 project. I had 24 hours to complete this project; I spent about 9 hours on it.  

## Theory for Dynamic Ruleset
- Goal is to create a cell structure that dynamically changes its ruleset to organically proliferate.
- How do we do this?
### Dynamic Ruleset Logic
- The standard ruleset for Conway's Game of Life is as follows: 
   1. Any live cell with fewer than two live neighbours dies, as if by underpopulation.
   2. Any live cell with two or three live neighbours lives on to the next generation.
   3. Any live cell with more than three live neighbours dies, as if by overpopulation.
   4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
- How can we quantify the overall "`goodness`" of the system?
   - We can try to measure the number of cells that are overcrowded and the number of cells that are undercrowded and find a healthy balance.
   - Perhaps we can individually measure the `goodness` of each cell check and apply a multiplier for a more accurate estimate.
   - If this all works, the system should *hopefully* proliferate.



