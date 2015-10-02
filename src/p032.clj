;; Find the sum of all products whose multiplicand(a)/multiplier(b)/product(c) identity
;; can be written as a 1 through 9 pandigital.
;;
;; In equation, a x b = c:
;; 1) c should be 4 digit number.
;; 2) possible pairs of (a,b)'s number of digits are (1,4), (2,3).
;;    (3,2) and (4,1) are symmetric and don't need to investigate.

(ns p032
  (:require [clojure.math.combinatorics :refer [permutations]]))

(defn- to-int [v]
  (Integer/parseInt (apply str v)))

(defn- to-int [ds]
  (loop [[x & more] ds acc 0]
    (if (nil? more)
      (+ (* acc 10) x)
      (recur more (+ (* acc 10) x)))))

(defn- check-pattern [v n1 n2]
  (let [a (to-int (subvec v 0 n1))
        b (to-int (subvec v n1 (+ n1 n2)))
        c (to-int (subvec v (+ n1 n2)))]
    (if (= (* a b) c) c)))

(defn- check [v]
  [(check-pattern v 1 4)
   (check-pattern v 2 3)])

(defn solve []
  (->> (permutations (range 1 10))
       (mapcat check)
       (remove #(nil? %))
       (distinct)
       (reduce +)))
