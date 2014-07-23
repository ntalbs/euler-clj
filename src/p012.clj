;; #012
;; What is the value of the first triangle number to have over five hundred divisors?

(ns p012
  (:require [util :refer [factorize]]))

(defn d
  "Returns the number of divisors of n.
   Refer to http://mathschallenge.net/library/number/number_of_divisors"
  [n]
  (->> (factorize n)
       (map (fn [[b e]] (+ e 1)))
       (apply *)))

(defn triangle-number
  "Returns the n-th triangle number, n(n+1)/2."
  [n]
  (/ (* n (+ n 1)) 2))

(defn p012 []
  (->> (iterate inc 1)
       (map triangle-number)
       (drop-while (fn [n] (<= (d n) 500)))
       first))

;; too slow. need to improve.
(defn solve []
  (time (println (p012))))
