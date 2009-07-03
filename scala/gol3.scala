type Pos = Tuple2[Int,Int]
type Board = Set[Pos]
object GameOfLife {

  def fromString(size: Int, str : String) : Board = {
    var board : Board = Set()
    var i=str.length()-1
    while (i>=0) {
      if (str.charAt(i) == '#') board += ((i % size, i / size))
      i-=1;
    }
    board
  }
}
class GameOfLife(val size : Int, val board : Board) {
  val row = List.range(0,size)
  val all = for ( x <- row) yield( for (y <- row) yield(x,y))
  val env = List((-1,-1),(-1,0),(-1,1),(0,-1),(0,0),(0,1),(1,-1),(1,0),(1,1))

  def this(size: Int, str : String) = {
    this(size, GameOfLife.fromString(size, str))
  }

  def alive(p : Pos) : Boolean = {
    val count = neighbours( p ).count ( board.contains ( _ ) )
    count == 3 || count == 4 && board.contains( p )
  }

  def neighbours(p : Pos) : List[Pos] = {
    env.map( e => (e._1 + p._1 ,e._2 + p._2) )
  }

  def next() : GameOfLife = {
    val newBoard = for ( p <- board; n <- neighbours( p ) if (alive(n))) yield n
    new GameOfLife(size, newBoard)
  }

  override def toString() : String = {
    all.map( row => row.map( p => if (board.contains(p)) "#" else "_").mkString("","","\n")).mkString("")
  }
}

def timeIt(g: GameOfLife, count : Int) {
  val time=System.currentTimeMillis()
  var i=0
  var gol=g
  while (i<count) {
    gol=gol.next()
    i+=1
  }
  println((System.currentTimeMillis()-time) + " ms.")
}

/*var gol = new GameOfLife(5,for ( x <- List.range(0,5); y <- List.range(0,5) if (Math.random > 0.5)) yield(x,y))
var gol = new GameOfLife(3,"___###___")
while (true) { 
  gol = gol.next()
  println(gol) 
  Thread.sleep(500)
}
*/
var gol=new GameOfLife(30,
"...........#..#..............."+
"..........#..................."+
"..........#...#..............."+
"..##......####................"+
".####........................."+
"##.##........................."+
".##.....##.###................"+
".......#.....##.......#....###"+
"......##.......#......#....#.#"+
".......#.....##.......#....###"+
".##.....##.###................"+
"##.##........................."+
".####........................."+
"..##......####................"+
"..........#...#..............."+
"..........#..................."+
"...........#..#...............")

timeIt(gol, 500)
