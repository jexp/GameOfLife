package gol

/**
 * @author Michael Hunger 
 * @since 24.03.2010
 */
class GameOfLife {
  static int X=0,Y=1;
  static def env = [[-1,-1],[0,-1],[1,-1],[-1,0],[1,0],[-1,1],[0,1],[1,1]]
  def alive = []

  def neighbours(def cell) {
    env.collect { [cell[X]+it[X],cell[Y]+it[Y]] }
  }

  def aliveNeighbours(def cell) {
    neighbours(cell).findAll {alive.contains(it) }
  }

  def deadNeighbours(def cell) {
    neighbours(cell) - aliveNeighbours(cell)
  }

  def haveNeighbourCount(def coll, def counts) {
    coll.findAll { counts.contains(aliveNeighbours(it).size())}
  }

  GameOfLife next() {
    def stayingAlive = haveNeighbourCount(alive, [2,3])
    def wakingFromDead = alive.inject([]) { res,cell ->
                          res += haveNeighbourCount(deadNeighbours(cell),[3])}
    
    new GameOfLife(alive: new HashSet(stayingAlive + wakingFromDead))
  }
  String toString() {
	 (alive.min{it[Y]}[Y]..alive.max{it[Y]}[Y])
		.collect { y ->  
		(alive.min{it[X]}[X]..alive.max{it[X]}[X])
		.collect { x -> 
			alive.contains([x,y]) ? "X" : "."}
			.join("")+"\n" 
			}.join("")
  }
  static GameOfLife fromString(def str) {
	 int x=0,y=0;
	 def alive=[]
     str.each { if (it == 'X') alive+=[[x,y]];
		if (it=='\n') { x=0;y--;}
		else x++;
	 }
     new GameOfLife(alive: alive)	
  }
}
def gol=GameOfLife.fromString(
"""
   X
    X  
    X  
  XXX   

""")

200.times{ println gol = gol.next() }
