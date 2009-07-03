type Pos = Tuple2[Int,Int]
type Board = List[Pos]
val size = 3
class GameOfLife(val size : Int,val board : Board)

def alive(board : Board , p : Pos) = {
  val env = List((-1,-1),(-1,0),(-1,1),(0,-1),(0,1),(1,-1),(1,0),(1,1))
  val potentials = env.map( e => (e._1 + p._1 ,e._2 + p._2) )
  val count = potentials.count ( board.contains ( _ ) )
  count > 2 && count < 5
}

def next(gol : GameOfLife) : GameOfLife = {
  val row = List.range(0,gol.size)
  val board = for ( x <- row; y <- row if (alive(gol.board,(x,y)))) yield(x,y)
  new GameOfLife(gol.size, board)
}

def toString(gol : GameOfLife) : String = {
  val row = List.range(0,gol.size)
  val all = for ( x <- row) yield( for (y <- row) yield(x,y))
  all.map( row => row.map( p => if (gol.board.contains(p)) "#" else "_").mkString("","","\n")).mkString("")
}

def repeat[T](n : Int, t : T)(what: (T) => T) : T = {
  (t /: (1 to n))( (t,x) => what(t))
}

val board = new GameOfLife(3,List((1,1),(0,0)))
println(toString(board))
println(toString(repeat(1,board)(next)))
