;; #081

(defn parse-int [s]
  (Integer/parseInt s))

(def m
  (->> (clojure.string/split (slurp "../data/matrix.txt") #"\r\n")
       (map (fn [line] (vec (map parse-int (clojure.string/split line #",")))))
       vec))

(defn t
  "returns the lists of positions of elemements in diagonal order
   (/ direction) from right-bottom to left-top."
  [n]
  (concat (for [i (range n)]
            (for [j (range (inc i))]
              [(- n j 1) (+ (- n i 1) j)]))
          (for [i (range (dec n))]
            (for [j (range (- (dec n) i))]
              [(- (dec n) j i 1) j]))))

(defn min-sum [xs ys]
  (if (< (count xs) (count ys))
    (map min
         (map + (concat xs [1000000]) ys)
         (map + (concat [10000000] xs) ys))
    (map min (map + xs ys) (map + (rest xs) ys))))

(defn p081 [m]
  (->> (map (fn [ks] (for [k ks] (get-in m k))) (t (count m)))
       (reduce min-sum)))

(time (println (p081 m)))
