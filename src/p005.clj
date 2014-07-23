;; #005
;; smallest positive number that is evenly divisible
;; by all of the numbers from 1 to 20

(ns p005
  (require [util :refer [lcm]]))

(defn p005 []
  (reduce lcm (range 1 21)))

(defn solve []
  (time (println (p005))))
