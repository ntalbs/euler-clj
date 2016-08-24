(ns p075
  (:require [util :refer [gcd]]))

(def L 1500000)                         ; max length of wire
(def M (int (Math/sqrt (quot L 2))))    ; max of m

(defn solve1 []
  (->> (for [m (range 2 (inc M))
             n (range 1 m)
             k (range)
             :let [a (* k (- (* m m) (* n n)))
                   b (* k 2 m n)
                   c (* k (+ (* m m) (* n n)))
                   sum (+ a b c)]
             :while (<= sum L)
             :when (odd? (- m n))
             :when (= 1 (gcd m n))]
         [a b c])
       (group-by (fn [[a b c]] (+ a b c)))
       (filter (fn [[_ ls]] (= 1 (count ls))))
       count))

(defn solve2 []
  (->> (for [m (range 2 (inc M))
             n (range 1 m)
             k (range)
             :let [a (* k (- (* m m) (* n n)))
                   b (* k 2 m n)
                   c (* k (+ (* m m) (* n n)))
                   sum (+ a b c)]
             :while (<= sum L)
             :when (odd? (- m n))
             :when (= 1 (gcd m n))]
         sum)
       (group-by identity)
       (filter (fn [[_ ls]] (= 1 (count ls))))
       count))
