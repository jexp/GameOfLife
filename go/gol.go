package main
import "fmt"

type Cell struct {
  x, y int
}
type Board []Cell

func (a *Cell) Out()  {
	fmt.Printf("X %d Y %d\n",a.x, a.y)
}
func (a *Cell) Equals(b Cell) bool {
	return (a.x == b.x && a.y == b.y)
}
func (a *Cell) IsAlive() bool {
	return !a.Equals(Cell{-1,-1})
}
func (a *Cell) GetNeighbours(board Board) int {
	var counter int
	for i := -1; i <= 1; i++ {
		for j := -1; j <= 1; j++ {
			var found=board.Get(a.x+i,a.y+j)
			if (found.IsAlive()) {
				counter++
			}
		}
	}
	return counter
}
func (a Board) Get(x int, y int) Cell {
	for i, key := range a {
		if (key.x == x && key.y == y) {
			return key
		}
		i++
	}
	return Cell{-1,-1}
}
func (a Board) Print() {
	width := 3
	height := 3
	for y := 0; y < height; y++ {
		for x := 0; x < width; x++ {
			found := a.Get(x,y)
			//if (found != Cell{-1,-1}) {
			//if (found.x > -1) {
			if (found.IsAlive()) {
				fmt.Print("X")
			} else {
				fmt.Print(".")
			}
		}
		fmt.Println()
	}
}
type B interface {
	Out() 
}
func main() {
    a := Cell{0,0}
    a.Out()
    fmt.Println(a)

	//errorCell := Cell{-1,-1}

	board := Board{a,Cell{1,1},a}
	//fmt.Println(board)
	//res := board.Get(0,0)
	//fmt.Println(res)
	
	board.Print()
}