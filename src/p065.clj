;; #065

(ns p065
  (:require [util :refer (digits)]))

(defn xs [n]
  "a100 a99 a98 ... a0"
  (reverse (cons 2 (->> (iterate inc 1)
                        (mapcat #(vector 1 (* 2 %) 1))
                        (take (dec n))))))

(defn f [a b]
  (+ b (/ 1 a)))

(defn p065 []
  (->> (reduce f (xs 100))
       numerator
       digits
       (apply +)))

(defn solve []
  (time (println (p065))))
