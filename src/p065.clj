(ns p065
  (:require [util :refer (digits)]))

(defn- xs
  "a100 a99 a98 ... a0"
  [n]
  (->> (iterate inc 1)
       (mapcat #(vector 1 (* 2 %) 1))
       (take (dec n))
       (cons 2)
       reverse))

(defn- f [a b]
  (+ b (/ 1 a)))

(defn solve []
  (->> (reduce f (xs 100))
       numerator
       digits
       (apply +)))
