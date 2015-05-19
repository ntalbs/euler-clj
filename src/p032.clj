;; Find the sum of all products whose multiplicand(a)/multiplier(b)/product(c) identity
;; can be written as a 1 through 9 pandigital.
;;
;; In equation, a x b = c:
;; 1) c should be 4 digit number.
;; 2) possible pairs of (a,b)'s number of digits are (1,4), (2,3).
;;    (3,2) and (4,1) are symmetric and don't need to investigate.

(ns p032
  (:require [clojure.math.combinatorics :refer [permutations combinations]]))

(defn to-int [v]
  (Integer/parseInt (apply str v)))

(defn check-pattern [v digits-a digits-b]
  (let [a (to-int (take digits-a v))
        b (to-int (->> (drop digits-a v) (take digits-b)))
        c (to-int (drop (+ digits-a digits-b) v))]
    (if (= (* a b) c) c)))

(defn check [v]
  [(check-pattern v 1 4)
   (check-pattern v 2 3)])

(defn p032 []
  (->> (permutations (range 1 10))
       (mapcat check)
       (filter #(not (nil? %)))
       (into #{})
       (apply +)))

(defn solve []
  (time (println (p032))))
