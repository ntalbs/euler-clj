(ns p063)

(defn power [x n] (apply *' (repeat n x)))

(def limit (/ 1 (- 1 (Math/log10 9))))

(defn digits&power
  "Find the numbers which are n-digits and n-th power."
  [n]
  (for [x (range 1 10)
        :when (<= (/ (dec n) n) (Math/log10 x))]
    (power x n)))

(defn solve []
  (->> (range 1 limit)
       (mapcat digits&power)
       count))
