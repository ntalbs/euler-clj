;; largest palindrome made from the product of two 3-digit numbers

(ns p004
    (:require [clojure.string :as s]))

(defn palindrome? [n]
  (= (str n) (s/reverse (str n))))

(def limit 1000)

; initial
(defn solve1 []
  (->> (for [a (range 100 limit) b (range a limit)] (* a b))
       (filter palindrome?)
       (apply max)))

; improved
; P = ab = 100000x + 10000y + 1000z + 100z + 10y + x
;        = 100001x + 10010y + 1100z
;        = 11(9091x + 910y + 100z)
; 11 is prime, a or b must have a factor of 11
(defn solve2 []
  (->> (for [a (range 100 limit) b (range a limit)
             :when (or (= 0 (mod a 11)) (= 0 (mod b 11)))]
         (* a b))
       (filter palindrome?)
       (apply max)))

(defn solve []
  (time (println "initial => " (initial-approach)))
  (time (println "improved => " (improved))))
