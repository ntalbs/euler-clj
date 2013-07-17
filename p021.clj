;; #p021
;; Evaluate the sum of all the amicable numbers under 10000.

(use '[util :only (divisor? sum-of-proper-divisor)])

(defn amicable [a]
  (let [b (sum-of-proper-divisor a)]
    (if (and (not= a b) (= a (sum-of-proper-divisor b)))
      a
      0)))

(defn p021 []
  (apply + (map amicable (range 1 10000))))

(time (println (p021)))
