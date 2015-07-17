;; What is the millionth lexicographic permutation of
;; the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?

(ns p024
  (:require [clojure.math.combinatorics :as c]))

(defn solve []
  (apply str (c/nth-permutation (range 10) (dec 1000000))))
