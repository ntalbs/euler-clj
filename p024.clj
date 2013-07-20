;; #024

(use '[clojure.math.combinatorics :only (permutations)])

(defn p024 []
  (apply str (nth (permutations (range 10)) (dec 1000000))))

(time (println (p024)))
