;; Find the next triangle number that is also pentagonal and hexagonal.

(ns p045
  (:require [util :refer [perfect-square?]]))

; T(2n-1) = H(n), which means every H(n) is also a triangle number.
; So, check there exists a integer n that satisfies 3n^2-n-2h = 0
; for a given hexagonal h.

(defn hexagonal [n] (* n (- (* 2 n) 1)))

(defn pentagonal? [h]
  (let [x (+ 1 (* 24 h))]
    (if (perfect-square? x)
      (integer? (/ (+ 1 (int (Math/sqrt x))) 6))
      false)))

(defn p045 []
  (->> (iterate inc 286) ; start from H(144)
       (map hexagonal)
       (filter pentagonal?)
       first))

(defn solve []
  (time (println (p045))))
