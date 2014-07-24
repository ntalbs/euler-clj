;; #034
;; Find the sum of all numbers which are equal to the sum of the factorial of their digits.

(ns p034
  (:require [util :refer [digits factorial]]))

(defn check [n]
  (= n
     (->> (digits n)
          (map factorial)
          (apply +))))

; Don't know the upper limit, used brute force attack.
; First, find the smallest one (X1) that satisfied the condition.
; Then try to the next one (X2) and them.
; Luckly, that's the answer.
(defn p034 []
  (->> (iterate inc 11)
       (filter check)
       (take 2)
       (apply +)))

(defn solve []
  (time (println (p034))))
