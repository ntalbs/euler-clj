(ns p034)

(defn- factorial [n]
  (case n
    0 1
    1 1
    2 2
    3 6
    4 24
    5 120
    6 720
    7 5040
    8 40320
    9 362880))

(defn- sum-of-fact [n]
  (loop [n n acc 0]
    (if (< 0 n)
      (recur (quot n 10) (+ acc (factorial (rem n 10))))
      acc)))

(defn check [n]
  (= n (sum-of-fact n)))

(defn solve []
  (->> (range 11 (inc 2540160))
       (filter check)
       (apply +)))
