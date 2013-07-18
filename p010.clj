;; #010
;; sum of all the primes below 2,000,000.

(use '[util :only (prime?)])

; initial: Takes around 10 secs. Too slow.
(defn p010-1 [bound]
  (reduce + (filter prime? (range 2 (inc bound)))))

(time (println (p010-1 2000000)))

; improved: TODO
