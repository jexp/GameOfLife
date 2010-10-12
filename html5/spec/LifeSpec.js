describe("Conway's Game of Life", function () {

    describe("a cell", function () {
        it("should die if it has less than two live neighbours", function () {
            var board = new Board(3, 3);
            board.cells = [
                [0, 0, 0],
                [0, 1, 0],
                [0, 0, 0]
            ];

            var isLive = board.isLive(1, 1);
            expect(isLive).toBeFalsy();
        });


        it("should die if it has more than three live neighbours", function () {
            var board = new Board(3,3);
            board.cells = [
                [1, 1, 1],
                [1, 1, 1],
                [1, 1, 1]
            ];

            var isLive = board.isLive(1, 1);
            expect(isLive).toBeFalsy();
        });


        it("should be born if it has three live neighbours", function () {
            var board = new Board(3,3);
            board.cells = [
               [0, 0, 0],
               [0, 0, 1],
               [1, 0, 1]
            ];

            var isLive = board.isLive(1, 1);
            expect(isLive).toBeTruthy();
        });


        it("should stay alive if it has two live neighbours", function () {
            var board = new Board(3,3);
            board.cells = [
                [0, 0, 0],
                [0, 1, 1],
                [1, 0, 0]
            ];

            var isLive = board.isLive(1, 1);
            expect(isLive).toBeTruthy();
        });

        it("should stay dead if it has two live neighbours", function () {
            var board = new Board(3,3);
            board.cells = [
               [0, 0, 0],
               [0, 0, 1],
               [1, 0, 0]
            ];

            var isLive = board.isLive(1, 1);
            expect(isLive).toBeFalsy();
        });
    });

    describe("board", function () {

        it("should create an empty board of the given width and height", function () {

            var board = new Board(3, 3);

            for (var x = 0; x < 3; x++) {
                for (var y = 0; y < 3; y++) {
                    expect(board.cells[x][y]).toEqual(0);
                }
            }

        });


        it("should treat last row as parent of first row", function () {

            var board = new Board(3,3);
            board.cells = [
	            [0, 0, 0],
	            [0, 0, 0],
	            [1, 1, 1]
	        ];

            var isLive = board.isLive(1, 0);
            expect(isLive).toBeTruthy();

        });

        it("should treat first row as child of last row", function () {

            var board = new Board(3,3);
            board.cells = [
	            [1, 1, 1],
	            [0, 0, 0],
	            [0, 0, 0]
	        ];

            var isLive = board.isLive(1, 2);
            expect(isLive).toBeTruthy();

        });

        it("should treat last column as left of first", function () {

            var board = new Board(3,3);
            board.cells = [
	            [0, 0, 1],
	            [0, 0, 1],
	            [0, 0, 1]
	        ];

            var isLive = board.isLive(0, 1);
            expect(isLive).toBeTruthy();

        });

        it("should treat first column as right of last", function () {

            var board = new Board(3, 3);
            board.cells = [
	        [1, 0, 0],
	        [1, 0, 0],
	        [1, 0, 0]
	    ];

            var isLive = board.isLive(2, 1);
            expect(isLive).toBeTruthy();

        });

        it("should correctly wrap top left corner", function () {

            var board = new Board(3, 3);
            board.cells = [
	        [0, 0, 1],
	        [1, 0, 0],
	        [1, 0, 0]
	    ];

            var isLive = board.isLive(0, 0);
            expect(isLive).toBeTruthy();

        });

        it("should correctly wrap top right corner", function () {

            var board = new Board(3, 3);
            board.cells = [
	        [1, 1, 0],
	        [0, 0, 1],
	        [0, 0, 0]
	    ];

            var isLive = board.isLive(2, 0);
            expect(isLive).toBeTruthy();

        });
        it("should correctly wrap bottom right corner", function () {

            var board = new Board(3, 3);
            board.cells = [
	        [0, 0, 1],
	        [0, 0, 0],
	        [1, 1, 0]
	    ];

            var isLive = board.isLive(2, 2);
            expect(isLive).toBeTruthy();

        });

        it("should correctly wrap bottom left corner", function () {

            var board = new Board(3, 3);
            board.cells = [
	        [1, 0, 0],
	        [1, 0, 0],
	        [0, 0, 1]
	    ];

            var isLive = board.isLive(0, 2);
            expect(isLive).toBeTruthy();

        });
    });

});