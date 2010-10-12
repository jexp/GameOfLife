function Board(width, height) {
    this.width = width;
    this.height = height;

    this.cells = [];
    this.fill(width, height, 0);
}

Board.prototype.fill = function () {
    initialiseArray(this.cells, this.width, this.height, 0);
};

Board.prototype.isLive = function (x, y) {
    var liveNeighbours = this.getNeighbours(x, y);
    if (liveNeighbours == 2) {
        return this.cells[x][y];
    }
    return (liveNeighbours <= 3) && (liveNeighbours >= 2);
};

Board.prototype.getNeighbours = function (x, y) {
    var xLeft = this.getPreviousCell(x, this.width);
    var xRight = this.getNextCell(x, this.width);
    var yAbove = this.getPreviousCell(y, this.height);
    var yBelow = this.getNextCell(y, this.height);

    var above = this.cells[xLeft][yAbove] + this.cells[x][yAbove] + this.cells[xRight][yAbove];
    var beside = this.cells[xLeft][y] + this.cells[xRight][y];
    var below = this.cells[xLeft][yBelow] + this.cells[x][yBelow] + this.cells[xRight][yBelow];
    return above + beside + below;
};


Board.prototype.getPreviousCell = function (cell, maxSize) {
    previous = cell - 1;
    if (previous < 0) {
        previous = maxSize - 1;
    }
    return previous;
};

Board.prototype.getNextCell = function (cell, maxSize) {
    next = cell + 1;
    if (next >= maxSize) {
        next = 0;
    }
    return next;
}

Board.prototype.randomlyFill = function (weighting) {
    for (var x = 0; x < this.width; x++) {
        for (var y = 0; y < this.height; y++) {
            var rnd = Math.floor(Math.random() * (1 + weighting));
            this.cells[x][y] = rnd;
        }
    }
}

Board.prototype.draw = function (context, cellSize){

    for (var x = 0; x < this.width; x++) {
        for (var y = 0; y < this.height; y++) {
            if (this.cells[x][y] == 1) {
                context.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
            else {
                context.clearRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }
    }
}

Board.prototype.getNextBoard = function () {
    var nextBoard = new Board(width, height);

    for (var x = 0; x < this.width; x++) {
        for (var y = 0; y < this.height; y++) {
            nextBoard.cells[x][y] = this.isLive(x, y);
        }
    }

    return nextBoard;
}

function initialiseArray(cells, width, height, initialValue) {
    for (var x = 0; x < width; x++) {
        var columnArray = []
        for (var y = 0; y < height; y++) {
            columnArray[y] = initialValue;
        }
        cells[x] = columnArray;
    }
}