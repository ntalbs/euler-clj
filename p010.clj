;; #010
;; sum of all the primes below 2,000,000.

; initial: Takes around 10 secs. Too slow.
(defn prime? [n]
  (cond (= n 1) false
        (< n 4) true                    ; 2, 3
        (even? n) false
        (< n 9) true                    ; 5, 7
        (= 0 (mod n 3)) false
        :else (empty?
               (take 1 (filter
                        #(= 0 (mod n %))
                        (range 3 (inc (int (Math/sqrt n))) 2))))))

(defn p010-1 [bound]
  (reduce + (filter prime? (range 2 (inc bound)))))

(time (println (p010-1 2000000)))

; improved: TODO
