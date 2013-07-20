;; #024
;; What is the millionth lexicographic permutation of 
;; the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?

(use '[clojure.math.combinatorics :only (permutations)])

(defn p024 []
  (apply str (nth (permutations (range 10)) (dec 1000000))))

(time (println (p024)))
