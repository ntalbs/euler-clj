(ns p065
  (:require [util :refer (digits)]))

(defn- xs
  "a(n-1) a(n-2) ... a0"
  [n]
  (->> (iterate inc 1)
       (mapcat #(vector 1 (* 2 %) 1))
       (take (dec n))
       (cons 2)
       reverse))

(defn- f [a b]
  (+ b (/ 1 a)))

(defn solve []
  (->> (xs 100)
       (reduce f)
       numerator
       digits
       (apply +)))
