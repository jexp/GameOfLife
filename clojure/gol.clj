(ns gol)
(comment "a board only contains the coordinates of living cells")

(def size 3)

(defn neighbours 
  "All direct neighbours around [pos] and pos itself"
  [[x y]]
  (for [dx [-1 0 1] dy [-1 0 1]] [(+ x dx) (+ y dy)])
)

(defn alive
  "is pos alive in the next turn; shortcuts for when counted to 5"
  [pos board]
  (let [cnt (count (take 5 (for [p (neighbours pos) :when (board p)] true)))]
	(if (or (= cnt 3) (and (= cnt 4) (board pos))) pos nil)
))

(defn all-neighbours
    "all direct neighbours of all living cells on the board"
 [size board]
 (let [on-board (fn [[x y]] (and (>= x 0) (< x size) (>= y 0) (< y size)))]
   (set (filter on-board (mapcat #(neighbours %) board)))
 )
)

(defn next-board 
  "calculates the new board"
  ([board] (next-board size board))
  ([bsize board]
	  (set (filter #(alive % board)
		       (all-neighbours bsize board))))
)

(defn to-string 
    "creates a string representation of a board"
    ([board] (to-string size board))
    ([bsize board]
	    (apply str
	    (mapcat identity 
	       (for [x (range bsize)]
		    (concat '(\newline)
			    (for [y (range bsize)]
				 (if (board [x y]) "#" "_"))))))))

(defn time-it
    "calculates a board of a certain size n times and prints the time"
    [cnt size board]
      (time (loop [x cnt
	       b board]
	       (if (zero? x) (to-string b)
	       (recur (dec x) (next-board size b)))))
)

(def start-board #{[0 0] [1 1] [0 1] [2 2]})

(defn demo 
"runs a simple board 10 times"
([size board]
(doseq [b (take 10 (iterate #(next-board size %) board))] 
 (println (to-string size b))
 )))



(defn to-board 
  "converts '_#_###_#_' to a board"
  ([s] (to-board (int (Math/sqrt (count s))) s))

  ([width s]
  (let [cnt (count s)
       pos-of-hash (fn [idx c] (if (#{\# \*} c) 
				   [(int (/ idx width)) (rem idx width)] nil))
       ]
       (set (filter identity (map pos-of-hash (range cnt) s))))))

(def test-board (to-board 30 (str
"...........*..*..............."
"..........*..................."
"..........*...*..............."
"..**......****................"
".****........................."
"**.**........................."
".**.....**.***................"
".......*.....**.......*....***"
"......**.......*......*....*.*"
".......*.....**.......*....***"
".**.....**.***................"
"**.**........................."
".****........................."
"..**......****................"
"..........*...*..............."
"..........*..................."
"...........*..*..............."
)))


(defn alive2
      "is pos alive in the next turn"
  [pos board]
  (let [count-living (fn [cnt p] (if (board p) (inc cnt) cnt))
        gets-a-life  (fn [cnt] (or (= cnt 3) (and (= cnt 4) (board pos))))
	]
    (if (gets-a-life (reduce count-living 0 (neighbours pos))) pos nil)
  )
)



(defn next-board-old 
    "old version that evaluated the whole board"
    ([board] (next-board size board))
		 ([bsize board]
  (let [
      axis (range bsize)
      new-board (map (fn [x] (map (fn [y] (if (alive [x y] board) [x y] nil)) axis)) axis)
      ]
      (set (filter #(not (nil? %)) (mapcat (fn [x] x) new-board))))
  )
)
