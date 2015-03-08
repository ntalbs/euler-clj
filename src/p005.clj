;; #005
;; smallest positive number that is evenly divisible
;; by all of the numbers from 1 to 20

(ns p005
  (require [util :refer [lcm]]))

(defn solve []
  (reduce lcm (range 1 21)))
