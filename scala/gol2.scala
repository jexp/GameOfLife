type Pos = Tuple2[Int,Int]
type Board = List[Pos]

class GameOfLife(val size : Int, val board : Board) {
  val row = List.range(0,size)
  val all = for ( x <- row) yield( for (y <- row) yield(x,y))
  val env = List((-1,-1),(-1,0),(-1,1),(0,-1),(0,1),(1,-1),(1,0),(1,1))

  def alive(p : Pos) = {
    val potentials = env.map( e => (e._1 + p._1 ,e._2 + p._2) )
    val count = potentials.count ( board.contains ( _ ) )
    count > 1 && count < 4
  }

  def next() : GameOfLife = {
    val newBoard = for ( x <- row; y <- row if (alive((x,y)))) yield(x,y)
    new GameOfLife(size, newBoard)
  }

  override def toString() : String = {
    all.map( row => row.map( p => if (board.contains(p)) "#" else "_").mkString("","","\n")).mkString("")
  }
}
var gol = new GameOfLife(5,for ( x <- List.range(0,5); y <- List.range(0,5) if (Math.random > 0.5)) yield(x,y))

while (true) { 
  gol = gol.next()
  println(gol) 
  Thread.sleep(500)
}
