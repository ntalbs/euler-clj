(ns p054)

(defn n->p "Convert number on card to point" [c]
  (case c
    \2 2, \3 3, \4 4, \5 5, \6 6, \7 7, \8 8, \9 9, \T 10, \J 11, \Q 12, \K 13, \A 14))

(defn straight? [card-nums]
  (let [straight (->> "A23456789TJQKA"
                      (map n->p)
                      (partition 5 1)
                      (map set)
                      set)]
    (contains? straight (set card-nums))))

(defn rank [hand]
  (let [ranks [[1 1 1 1 1] [2 1 1 1] [2 2 1] [3 1 1] [] [] [3 2] [4 1]]
        nums   (->> hand (map first) (map n->p) sort (partition-by identity))
        r1     (->> nums (map count) sort reverse (.indexOf ranks))
        r2     (->> nums (sort-by count) reverse (map first) vec)
        flush? (= 1 (->> hand (map second) set count))
        str?   (straight? r2)]
    (cond
      (and flush? (= r2 [14 13 12 11 10])) [9 r2]   ; royal straight flush
      (and flush? str?) [8  r2]                     ; straight flush
      flush?            [5  r2]                     ; flush
      str?              [4  r2]                     ; straight
      :else             [r1 r2])))

(def games
  (->> (clojure.string/split (slurp "data/poker.txt") #"\r\n")
       (map #(clojure.string/split % #" "))
       (map (fn [g] {:p1 (take 5 g) :p2 (drop 5 g)}))))

(defn solve []
  (->> games
       (map #(compare (rank (:p1 %)) (rank (:p2 %))))
       (filter pos?)
       count))
