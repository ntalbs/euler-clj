;; How many elements would be contained
;; in the set of reduced proper fractions for d  1,000,000?

(ns p072
  (:require [util :refer (phi)]))

; The same as phi(1) + phi(2) + ... + phi(1000000).
; Refer to http://en.wikipedia.org/wiki/Euler's_totient_function

(defn solve [limit]
  (->> (range 1 (inc limit))
       (map phi)
       (apply +)))
