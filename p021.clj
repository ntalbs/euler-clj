;; #p021
;; Evaluate the sum of all the amicable numbers under 10000.

(defn divisor?
  "Returns true if x is a divisor of n."
  [x n] (zero? (rem n x)))

(defn sum-of-divisor [n]
  (apply + (filter (fn [x] (divisor? x n)) (range 1 (inc (quot n 2))))))

(defn amicable [a]
  (let [b (sum-of-divisor a)]
    (if (and (not= a b) (= a (sum-of-divisor b)))
      a
      0)))

(defn p021 []
  (apply + (map amicable (range 1 10000))))

(time (println (p021)))
