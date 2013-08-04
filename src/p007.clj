;; #007
(use '[util :only (prime?)])

(defn p007 [] (last (take 10001 (filter prime? (iterate inc 2)))))

(time (println (p007)))
