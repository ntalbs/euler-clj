;; #007
;; 10,001st prime number?

(use '[util :only (prime?)])

(defn p007 []
  (first (drop 10000 (filter prime? (iterate inc 2)))))

(time (println (p007)))
