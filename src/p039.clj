(ns p039
  (:require [util :refer (gcd)]))

(def limit 1000)

(defn- triple
  "Returns a pythagorian triple for given m, n with m > n."
  [m n k]
  [(* k (- (* m m) (* n n)))
   (* k 2 m n)
   (* k (+ (* m m) (* n n)))])

(defn solve []
  (->> (for [m (range 2 (int (Math/sqrt (/ limit 4))))
             n (range 1 m)
             k (range 1 (quot limit 12))
             :when (odd? (- m n))
             :when (= 1 (gcd m n))]
         (triple m n k))
       (filter (fn [[a b c]] (<= (+ a b c) limit)))
       (group-by (fn [[a b c]] (+ a b c)))
       (apply max-key (fn [[p v]] (count v)))
       first))
