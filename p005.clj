;; #005
;; smallest positive number that is evenly divisible 
;; by all of the numbers from 1 to 20

(use '[util :only (gcd lcm)])

(def p005
  (reduce lcm (range 1 21)))

(time (println p005))
