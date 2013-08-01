;; #052
;; Find the smallest positive integer, x, 
;; such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.

(use '[util :only (digits)])

(defn check [v]
  (->> (map digits v)
       (map sort)
       (apply =)))

(defn p052 []
  (->> (iterate inc 1)
       (map (fn [n] (for [i [1 2 3 4 5 6]] (* i n))))
       (drop-while #(not (check %)))
       first))

(time (println (p052)))
