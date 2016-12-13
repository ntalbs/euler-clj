;; Reference: Sqrt root by substraction
;; http://www.afjarvis.staff.shef.ac.uk/maths/jarvisspec02.pdf

(ns p080
  (:require [util :refer [digits perfect-square?]]))

(defn sqrt
  "Compute the first 100 digits of sqrt(n)."
  [n]
  (loop [a (* 5 n) b 5]
    (if (<= (Math/log10 b) (inc 100))   ; b가 101자리까지 계산해야 100자리까지 값이 정확함
      (if (>= a b)
        (recur (-' a b) (+' b 10))
        (recur (*' 100 a) (+' (*' (quot b 10) 100) (rem b 10))))
      (quot b 10))))                    ; 101째 자리 제거

(defn solve []
  (->> (range 1 (inc 100))
       (remove perfect-square?)
       (map sqrt)
       (map digits)
       (flatten)
       (apply +)))
